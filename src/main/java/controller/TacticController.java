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
import exception.TacticNotFoundException;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import service.TacticService;

/**
 * Handles everything related to MITRE ATT&CK <code>Tactic</code>s.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
@Stateless
@Path("tactics")
public class TacticController {

    /**
     * This method returns all MITRE ATT&CK Tactics with the corresponding
     * ATT&CK Techniques.
     *
     * This method is a only reachable when a GET request is sent to the API
     * base (as specified in <code>controller.RestConfig.java</code>), after
     * which the path for this class (as specified above in <code>@Path</code>)
     * needs to be appended.
     *
     * An example of this is: <code>api/v1/tactics</code>
     *
     * If the operation is successful, a HTTP OK (200) status is returned,
     * together with a JSON array that contains JSON objects. Each object
     * contains two fields: "name" (a string) and "techniques" (an array of
     * strings). Since this is a read-only operation within the back-end, no
     * failure is expected.
     *
     * @return all MITRE ATT&CK Tactics with corresponding ATT&CK Techniques per
     * Tactic
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTactics() {
        //Creates a new gson object
        Gson gson = new Gson();
        //Creates an instance of the tactic service
        TacticService tacticService = new TacticService();
        //Gets all tactics in JSON format
        String jsonTactics = gson.toJson(tacticService.getAllTactics());
        //Returns the HTTP OK (200) status, together with the tactics in JSON format
        return Response.status(Response.Status.OK).entity(jsonTactics).build();
    }

    /**
     * This method returns all MITRE ATT&CK Techniques for a given Tactic.
     *
     * This method is a only reachable when a GET request is sent to the API
     * base (as specified in <code>controller.RestConfig.java</code>), after
     * which the path for this class (as specified above in <code>@Path</code>)
     * needs to be appended. Additionally, the method's <code>@Path</code> needs
     * to be appended.
     *
     * An example of this is: <code>api/v1/tactics/initialaccess</code>
     *
     * If the operation is successful, a JSON object is returned, which contains
     * two fields: "name" (a string) and "techniques" (an array of strings). If
     * an error occurs, the HTTP NOT FOUND (404) status is returned, together
     * with a JSON object that contains a single field: "message". This field
     * contains the exact error message.
     *
     * @param tactic the Tactic to get all Techniques for
     * @return all corresponding ATT&CK Techniques for the given Tactic
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{tactic}")
    public Response getTactic(@PathParam("tactic") String tactic) {
        try {
            //Creates a new gson object
            Gson gson = new Gson();
            //Create a new tactic service instance
            TacticService tacticService = new TacticService();
            //Gets the tactic by the given name, which is then converted into JSON format
            String jsonTactic = gson.toJson(tacticService.getTacticByName(tactic).getTechniques());
            //Sends the HTTP OK (200) status, together with the tactic in JSON format
            return Response.status(Response.Status.OK).entity(jsonTactic).build();
        } catch (TacticNotFoundException ex) {
            //Stores the exception message in a message object
            Message message = new Message(ex.getMessage());
            //Sends the HTTP NOT FOUND (404) status, together with the message in JSON format
            return Response.status(Response.Status.NOT_FOUND).entity(message.toString()).build();
        }
    }
}
