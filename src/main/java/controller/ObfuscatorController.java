/*
 * Copyright (C) 2019 Max 'Libra' Kersten [@LibraAnalysis]
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package controller;

import com.google.gson.Gson;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import service.ObfuscatorService;

/**
 * The controller which handles everything that directly accesses the obfuscator
 * via the API
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
@Stateless
@Path("obfuscator")
public class ObfuscatorController {

    /**
     * This method returns all obfuscation profiles that reside within Genesis
     * (as specified in
     * <code>model.obfuscator.generic.ObfuscatorProfile</code>).
     *
     * This method is a only reachable when a GET request is sent to the API
     * base (as specified in <code>controller.RestConfig.java</code>), after
     * which the path for this class (as specified above in <code>@Path</code>)
     * needs to be appended. Additionally, the method's <code>@Path</code> needs
     * to be appended.
     *
     * An example of this is: <code>api/v1/obfuscator/profiles</code>
     *
     * If the operating is successful, a HTTP OK (200) status is returned,
     * together with a JSON array that contains all profiles, where each profile
     * is a string.
     *
     * @return all obfuscation profiles within Genesis, in JSON format
     */
    @GET
    @Path("profiles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getObfuscatorProfiles() {
        //Creates a new gson object
        Gson gson = new Gson();
        //Creates a new obfuscator service instance
        ObfuscatorService obfuscatorService = new ObfuscatorService();
        //Get all obfuscator profiles in JSON format
        String json = gson.toJson(obfuscatorService.getObfuscatorProfiles());
        //Send a HTTP OK (200) status with the JSON array of the obfuscator profiles as a response
        return Response.status(Response.Status.OK).entity(json).build();
    }
}
