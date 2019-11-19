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

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import service.StatusService;

/**
 * This controller is used to serve statistics and status information on the
 * default front-end. The status details are obtained using the
 * <code>StatusService</code>.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
@Stateless
@Path("status")
public class StatusController {

    /**
     * This method returns the amount of snippets that are currently present in
     * Genesis, in a JSON object. The object has a single field ("message"),
     * which contains the value.
     *
     * This method is a only reachable when a GET request is sent to the API
     * base (as specified in <code>controller.RestConfig.java</code>), after
     * which the path for this class (as specified above in <code>@Path</code>)
     * needs to be appended. Additionally, the method's <code>@Path</code> needs
     * to be appended.
     *
     * An example of this is: <code>api/v1/status/snippets</code>
     *
     * No error scenario is present, since the loading of snippets handles
     * errors on its own during the start of the application on the server. If
     * there are no snippets, the value "0" is returned.
     *
     * @return the amount of loaded snippets in JSON format
     */
    @GET
    @Path("snippets")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSnippetCount() {
        //Creates a status service instance
        StatusService statusService = new StatusService();
        //Stores the input in the message object
        Message message = new Message(Integer.toString(statusService.getSnippetCount()));
        //Send the HTTP OK (200) status with the message in JSON form as a response
        return Response.status(Response.Status.OK).entity(message.toString()).build();
    }

    /**
     * This method returns the version of Genesis (as defined in
     * <code>service.StatusService.java::getVersion()</code>), in a JSON object.
     * The object has a single field ("message"), which contains the value.
     *
     * This method is a only reachable when a GET request is sent to the API
     * base (as specified in <code>controller.RestConfig.java</code>), after
     * which the path for this class (as specified above in <code>@Path</code>)
     * needs to be appended. Additionally, the method's <code>@Path</code> needs
     * to be appended.
     *
     * An example of this is: <code>api/v1/status/version</code>
     *
     * No error scenario is present, since the string is a constant value
     *
     * @return the version of Genesis in JSON format
     */
    @GET
    @Path("version")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getVersion() {
        //Creates a status service instance
        StatusService statusService = new StatusService();
        //Stores the version in a message object
        Message message = new Message(statusService.getVersion());
        //Sends the HTTP OK (200) status with the version in JSON format
        return Response.status(Response.Status.OK).entity(message.toString()).build();
    }

    /**
     * This method returns the most used technique within all snippets, in a
     * JSON object. The object has a single field ("message"), which contains
     * the value.
     *
     * This method is a only reachable when a GET request is sent to the API
     * base (as specified in <code>controller.RestConfig.java</code>), after
     * which the path for this class (as specified above in <code>@Path</code>)
     * needs to be appended. Additionally, the method's <code>@Path</code> needs
     * to be appended.
     *
     * An example of this is: <code>api/v1/status/topTechnique</code>
     *
     * No error scenario is present, since the techniques are constant values
     * and one of the constant values is returned. If there are no snippets, a
     * dash ("-") is returned.
     *
     * @return the most used technique in all snippets
     */
    @GET
    @Path("topTechnique")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMostUsedTechnique() {
        //Creates a new status service instance
        StatusService statusService = new StatusService();
        //Stores the most used technique in a message object
        Message message = new Message(statusService.getMostUsedTechnique());
        //Sends a HTTP OK (200) status with the most used technique as a response
        return Response.status(Response.Status.OK).entity(message.toString()).build();
    }

    /**
     * This method returns amount of unique techniques that are used in all
     * snippets, in a JSON object. The object has a single field ("message"),
     * which contains the value.
     *
     * This method is a only reachable when a GET request is sent to the API
     * base (as specified in <code>controller.RestConfig.java</code>), after
     * which the path for this class (as specified above in <code>@Path</code>)
     * needs to be appended. Additionally, the method's <code>@Path</code> needs
     * to be appended.
     *
     * An example of this is: <code>api/v1/status/techniqueCount</code>
     *
     * No error scenario is present, since the techniques are constant values
     * and the snippet loading has its own error handling. If no snippets can be
     * loaded, the snippet count will stay at zero, meaning that the unique
     * count of techniques will also remain zero.
     *
     * @return the amount of unique techniques that are being used in the
     * snippets
     */
    @GET
    @Path("techniqueCount")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTechniqueCount() {
        //Creates a new status service instance
        StatusService statusService = new StatusService();
        //Stores the used technique count in a message object
        Message message = new Message(statusService.getUsedTechniqueCount());
        //Sends the HTTP OK (200) status with the used technique count message object as a response
        return Response.status(Response.Status.OK).entity(message.toString()).build();
    }

    /**
     * This method returns most used langauge in all snippets, in a JSON object.
     * The object has a single field ("message"), which contains the value.
     *
     * This method is a only reachable when a GET request is sent to the API
     * base (as specified in <code>controller.RestConfig.java</code>), after
     * which the path for this class (as specified above in <code>@Path</code>)
     * needs to be appended. Additionally, the method's <code>@Path</code> needs
     * to be appended.
     *
     * An example of this is: <code>api/v1/status/topLanguage</code>
     *
     * No error scenario is present, since the snippet loading has its own error
     * handling. If no snippets can be loaded, the snippet count will stay at
     * zero. In that case, a dash ("-") is returned.
     *
     * @return the amount of unique techniques that are being used in the
     * snippets
     */
    @GET
    @Path("topLanguage")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMostUsedLanguage() {
        //Creates a new status service instance
        StatusService statusService = new StatusService();
        //Stores the most used language in a message object
        Message message = new Message(statusService.getMostUsedLanguage());
        //Sends the HTTP OK (200) status with the most used language message object as a response
        return Response.status(Response.Status.OK).entity(message.toString()).build();
    }
}
