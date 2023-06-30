/*
 * *** BEGIN LICENSE BLOCK *****
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
 * Portions created by the Initial Developer are Copyright (C) 2013-2021
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
 * *** END LICENSE BLOCK *****
 */

package org.dcm4chee.arc.restore.rs;

import org.dcm4che3.net.Device;
import org.dcm4chee.arc.store.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * @author Gunter Zeilinger (gunterze@protonmail.com)
 * @since Jun 2023
 */
@RequestScoped
@Path("aets/{aet}/rs")
public class RestoreInstancesRS {
    private static final Logger LOG = LoggerFactory.getLogger(RestoreInstancesRS.class);

    @Inject
    private Device device;

    @Inject
    private StoreService storeService;

    @PathParam("aet")
    private String aet;

    @QueryParam("readPixelData")
    @Pattern(regexp = "true|false")
    private String purgeAfterDelay;

    @Context
    private HttpServletRequest request;

    @POST
    @Path("/studies/{StudyInstanceUID}/stgver")
    @Produces("application/dicom+json,application/json")
    public Response studyStorageCommit(
            @PathParam("StudyInstanceUID") String studyUID) {
        return restoreInstances(studyUID, null);
    }

    @POST
    @Path("/studies/{StudyInstanceUID}/series/{SeriesInstanceUID}/stgver")
    @Produces("application/dicom+json,application/json")
    public Response seriesStorageCommit(
            @PathParam("StudyInstanceUID") String studyUID,
            @PathParam("SeriesInstanceUID") String seriesUID) {
        return restoreInstances(studyUID, seriesUID);
    }

    private boolean purgeAfterDelay() {
        return Boolean.parseBoolean(purgeAfterDelay);
    }

    private Response restoreInstances(String studyUID, String seriesUID) {
        //TODO
        throw new WebApplicationException("Not yet implemented");
    }

}