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
import service.TechniqueService;

/**
 * Handles all MITRE ATT&CK <code>Technique</code> related API calls
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
@Stateless
@Path("techniques")
public class TechniqueController {

    /**
     * This method returns all MITRE ATT&CK techniques that reside within
     * Genesis (as specified in <code>model.mitre.Technique.java</code>).
     *
     * This method is a only reachable when a GET request is sent to the API
     * base (as specified in <code>controller.RestConfig.java</code>), after
     * which the path for this class (as specified above in <code>@Path</code>)
     * needs to be appended.
     *
     * An example of this is: <code>api/v1/techniques</code>
     *
     * If the operating is successful, a HTTP OK (200) status is returned,
     * together with a JSON array that contains all techniques, where each
     * technique is a string.
     *
     * @return all MITRE ATT&CK techniques in a JSON array to the user
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTechniques() {
        //Creates a gson object
        Gson gson = new Gson();
        //Creates an instance of the technique service
        TechniqueService techniqueService = new TechniqueService();
        //Converts the technique list into a JSON array
        String jsonOutput = gson.toJson(techniqueService.getAllTechniques());
        //Return a HTTP OK (200) status, together with the JSON array that contains the techniques
        return Response.status(Response.Status.OK).entity(jsonOutput).build();
    }
}
