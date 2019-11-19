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

import exception.AlteratorNotFoundException;
import exception.JsonFolderNotFoundException;
import exception.JsonParseException;
import exception.ObfuscatorNotFoundException;
import exception.SnippetNotFoundException;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.snippet.Snippet;
import service.BuildSnippetService;

/**
 * This controller handles all functions regarding the building of a
 * <code>Snippet</code>. It is not to be confused with the
 * <code>SnippetController</code>, since a snippet is required in the build
 * process: additional information is also required. As such, this class serves
 * a different purpose.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
@Stateless
@Path("build")
public class BuildSnippetController {

    /**
     * This method is a only reachable when a POST request is sent to the API
     * base (as specified in <code>controller.RestConfig.java</code>), after
     * which the path for this class (as specified above in <code>@Path</code>)
     * needs to be appended.
     *
     * An example of this is: <code>api/v1/build</code>
     *
     * The method requires a JSON object in the POST body with a snippet ID (as
     * calculated in <code>model.snippet.Snippet.java::hash()</code>), the
     * obfuscation profile (as specified in
     * <code>model.obfuscator.generic.ObfuscatorProfile.java</code>), and an
     * array of KeyValuePairs (as specified in
     * <code>model.snippet.KeyValuePair.java</code>) which contain the relevant
     * key-value pairs to alterate the sample.
     *
     * The Snippet is then build using the <code>BuildSnippetService</code> (as
     * specified in <code>service.BuildSnippetService</code>).
     *
     * If the operation is successful, the HTTP OK (200) status is returned,
     * together with the plain text version of the generated sample. If an error
     * occurred, the user will be given a JSON object which contains a single
     * field name "message", which contains the error message.
     *
     * @param json the JSON input as specified above
     * @return the generated snippet which is obfuscated based on the requested
     * obfuscation profile
     * @throws ObfuscatorNotFoundException if there is no obfuscator for the
     * given language whilst one is requested
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response build(String json) throws ObfuscatorNotFoundException {
        try {
            //Creates the build snippet service
            BuildSnippetService buildSnippetService = new BuildSnippetService();
            //Uses the build snippet service to create a snippet object from the given JSON string
            Snippet snippet = buildSnippetService.buildSnippet(json);
            //Builds the snippet via the class object's build function
            String snippetBuild = snippet.getClassObject().build();
            //Returns the HTTP OK (200) status together with the generated snippet
            return Response.status(Response.Status.OK).entity(snippetBuild).build();
        } catch (SnippetNotFoundException | AlteratorNotFoundException | JsonFolderNotFoundException | JsonParseException ex) {
            //The exception message is stored in a message object
            Message message = new Message(ex.getMessage());
            //The error message is returned in JSON form, together with a HTTP NOT FOUND (404) status code
            return Response.status(Response.Status.NOT_FOUND).entity(message.toString()).build();
        }
    }
}
