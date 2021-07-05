/*
 * **** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is part of dcm4che, an implementation of DICOM(TM) in
 * Java(TM), hosted at https://github.com/dcm4che.
 *
 * The Initial Developer of the Original Code is
 * J4Care.
 * Portions created by the Initial Developer are Copyright (C) 2015-2019
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 * See @authors listed below
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU General Public License Version 2 or later (the "GPL"), or
 * the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the MPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the MPL, the GPL or the LGPL.
 *
 * **** END LICENSE BLOCK *****
 *
 */

package org.dcm4chee.arc.export.dcm2hl7;

import org.dcm4che3.conf.api.hl7.IHL7ApplicationCache;
import org.dcm4che3.net.Device;
import org.dcm4chee.arc.conf.ExporterDescriptor;
import org.dcm4chee.arc.exporter.Exporter;
import org.dcm4chee.arc.exporter.ExporterProvider;
import org.dcm4chee.arc.hl7.HL7Sender;
import org.dcm4chee.arc.retrieve.RetrieveService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Vrinda Nayak (vrinda.nayak@j4care.com)
 * @since May 2021
 */
@ApplicationScoped
@Named("hl7")
public class Dcm2Hl7ExporterProvider implements ExporterProvider  {

    @Inject
    private Device device;

    @Inject
    private RetrieveService retrieveService;

    @Inject
    private HL7Sender hl7Sender;

    @Inject
    private IHL7ApplicationCache hl7AppCache;

    @Override
    public Exporter getExporter(ExporterDescriptor descriptor) {
        return new Dcm2Hl7Exporter(descriptor, hl7Sender, device, retrieveService, hl7AppCache);
    }
}