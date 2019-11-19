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
import exception.JsonFolderNotFoundException;
import exception.JsonParseException;
import exception.SnippetAlreadyExistsException;
import exception.SnippetNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.language.enums.Architecture;
import model.language.enums.Language;
import model.snippet.Snippet;
import service.SnippetService;

/**
 * This class handles all actions related to <code>snippet</code>s. Note that
 * building a <code>snippet</code> requires more information than the
 * <code>snippet</code> alone, and is thus handled in a different controller
 * (see <code>BuildController</code>).
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
@Stateless
@Path("snippets")
public class SnippetController {

    /**
     * This method returns all snippets that are loaded into Genesis (as defined
     * in <code>dao.SnippetManager.java</code>).
     *
     * This method is a only reachable when a GET request is sent to the API
     * base (as specified in <code>controller.RestConfig.java</code>), after
     * which the path for this class (as specified above in <code>@Path</code>)
     * needs to be appended.
     *
     * An example of this is: <code>api/v1/snippets</code>
     *
     * If the operation is successful, a HTTP OK (200) status is returned,
     * together with a JSON array that contains all snippets.
     *
     * @return all snippets that are loaded into Genesis
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSnippets() {
        //Creates a new gson object
        Gson gson = new Gson();
        //Creates a new snippet service instance
        SnippetService snippetService = new SnippetService();
        //Gets al snippets, which are then converted into JSON format
        String jsonOutput = gson.toJson(snippetService.getSnippets());
        //Sends a HTTP OK (200) status as a response, together with the JSON array that contains all snippets
        return Response.status(Response.Status.OK).entity(jsonOutput).build();
    }

    /**
     * This method loads all snippets from the disk
     *
     * This method is a only reachable when a PUT request is sent to the API
     * base (as specified in <code>controller.RestConfig.java</code>), after
     * which the path for this class (as specified above in <code>@Path</code>)
     * needs to be appended.
     *
     * An example of this is: <code>api/v1/snippets</code>
     *
     * If the operation is successful, a HTTP OK (200) status is returned. If
     * the operation is not successful, a HTTP NOT FOUND (404) status is
     * returned, together with an error message in JSON format, which resides at
     * the "message" field in the returned JSON object.
     *
     * @return a HTTP OK (200) status if all snippets are loaded correctly.
     * Else, a HTTP NOT FOUND (404) status is returned, together with a JSON
     * object that contains the error message in the "message" field
     */
    @PUT
    public Response loadAllSnippets() {
        try {
            //Creates a new instance of the snippet service
            SnippetService snippetService = new SnippetService();
            //Loads all snippets via the snippet service
            snippetService.loadSnippets();
            //Returns a HTTP OK (200) status as a response
            return Response.status(Response.Status.OK).build();
        } catch (JsonFolderNotFoundException | JsonParseException ex) {
            //Creates a new message object based on the exception's message
            Message message = new Message(ex.getMessage());
            //Returns a HTTP NOT FOUND (404) status, together with the error message, as a response
            return Response.status(Response.Status.NOT_FOUND).entity(message.toString()).build();
        }
    }

    /**
     * This method returns a snippet based on a given ID (as calculated in
     * <code>model.snippet.Snippet.java::hash()</code>).
     *
     * This method is a only reachable when a GET request is sent to the API
     * base (as specified in <code>controller.RestConfig.java</code>), after
     * which the path for this class (as specified above in <code>@Path</code>)
     * needs to be appended. Additionally, the method's <code>@PathParam</code>
     * needs to be appended, which is a variable in this case.
     *
     * An example of this is: <code>api/v1/snippet/{id}</code>
     *
     * If the operation is successful, a HTTP OK (200) status is returned,
     * together with the snippet is returned as a JSON object. If the operation
     * is not successful, a HTTP NOT FOUND (404) status is returned, together
     * with a JSON object that contains a single field: "message". This is the
     * error message.
     *
     * @param id the snippet's ID
     * @return the snippet that corresponds with the given ID
     */
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSnippetById(@PathParam("id") String id) {
        try {
            //Creates a snippet service instance
            SnippetService snippetService = new SnippetService();
            //Gets a snippet based on the given ID
            Snippet snippet = snippetService.getSnippet(id);
            //Creates a new gson object
            Gson gson = new Gson();
            //Converts the snippet into a JSON object
            String jsonOutput = gson.toJson(snippet);
            //Returns a HTTP OK (200) status, together with the snippet in JSON format
            return Response.status(Response.Status.OK).entity(jsonOutput).build();
        } catch (SnippetNotFoundException | JsonParseException | JsonFolderNotFoundException ex) {
            //Stores the exception message in a message object
            Message message = new Message(ex.getMessage());
            //Returns a HTTP NOT FOUND (404) status, together with the message in JSON format
            return Response.status(Response.Status.NOT_FOUND).entity(message.toString()).build();
        }
    }

    /**
     * This method creates a snippet, based on the language specific snippet
     * layout (as specified in the language specific JSON parsers, which can be
     * found in
     * <code>service.LanguageService.java::getJsonParser(Language language)</code>
     * and <code>dao.generic.GenericJsonParser.java</code>), which is then added
     * to the loaded snippets in the RAM and stored on the disk in the location
     * that is specified at
     * <code>model.snippet.SnippetConstants.java::SNIPPET_FOLDER</code>.
     *
     * This method is a only reachable when a POST request is sent to the API
     * base (as specified in <code>controller.RestConfig.java</code>), after
     * which the path for this class (as specified above in <code>@Path</code>)
     * needs to be appended. Additionally, the method's <code>@Path</code> needs
     * to be appended.
     *
     * An example of this is: <code>api/v1/snippets/create</code>
     *
     * If the operation is successful, a HTTP OK (200) status is returned. If
     * the operation is unsuccessful, a HTTP NOT FOUND (404) status is returned,
     * together with a JSON object that contains a single field ("message"),
     * which contains the error message.
     *
     * @param snippetJson the JSON form of the snippet that is to be added
     * @return all architectures that are supported within Genesis
     */
    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSnippet(String snippetJson) {
        try {
            //Creates a new snippet service instance
            SnippetService snippetService = new SnippetService();
            //Creates a snippet via the service
            snippetService.createSnippet(snippetJson);
            //Sends a HTTP OK (200) status as a response
            return Response.status(Response.Status.OK).build();
        } catch (JsonParseException | IOException | SnippetAlreadyExistsException ex) {
            //Stores the exception message in a message object
            Message message = new Message(ex.getMessage());
            //Sends a HTTP NOT FOUND (404) status, together with the error message in JSON format
            return Response.status(Response.Status.NOT_FOUND).entity(message.toString()).build();
        }
    }

    /**
     * This method returns all languages that are supported within Genesis
     *
     * This method is a only reachable when a GET request is sent to the API
     * base (as specified in <code>controller.RestConfig.java</code>), after
     * which the path for this class (as specified above in <code>@Path</code>)
     * needs to be appended. Additionally, the method's <code>@Path</code> needs
     * to be appended.
     *
     * An example of this is: <code>api/v1/snippets/languages</code>
     *
     * If the operation is successful, a HTTP OK (200) status is returned,
     * together with a JSON array that contains the languages that are supported
     * by Genesis, as strings.
     *
     * @return all languages that are supported within Genesis
     */
    @GET
    @Path("languages")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLanguages() {
        //Creates a snippet service instance
        SnippetService snippetService = new SnippetService();
        //Gets the supported languages
        List<Language> languages = snippetService.getLanguages();
        //Creates a gson object
        Gson gson = new Gson();
        //Converts the list of languages into a JSON array of strings
        String jsonOutput = gson.toJson(languages);
        //Sends a HTTP OK (200) status as a response, together with the JSON array that contains the languages
        return Response.status(Response.Status.OK).entity(jsonOutput).build();
    }

    /**
     * This method returns all architectures that are supported within Genesis
     *
     * This method is a only reachable when a GET request is sent to the API
     * base (as specified in <code>controller.RestConfig.java</code>), after
     * which the path for this class (as specified above in <code>@Path</code>)
     * needs to be appended. Additionally, the method's <code>@Path</code> needs
     * to be appended.
     *
     * An example of this is: <code>api/v1/snippets/architectures</code>
     *
     * If the operation is successful, a HTTP OK (200) status is returned,
     * together with a JSON array that contains the architectures that are
     * supported by Genesis, as strings.
     *
     * @return all architectures that are supported within Genesis
     */
    @GET
    @Path("architectures")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArchitectures() {
        //Creates a snippet service instance
        SnippetService snippetService = new SnippetService();
        //Gets all supported architectures
        List<Architecture> architectures = snippetService.getArchitectures();
        //Creates a gson object
        Gson gson = new Gson();
        //Converts the list of architectures into a JSON array of strings
        String jsonOutput = gson.toJson(architectures);
        //Send a HTTP OK (200) status, together with the JSON array that contains the architectures
        return Response.status(Response.Status.OK).entity(jsonOutput).build();
    }
}
