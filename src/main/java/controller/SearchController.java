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
import java.util.Base64;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.snippet.Snippet;
import service.SearchService;

/**
 * This controller handles all search related activity.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
@Stateless
@Path("search")
public class SearchController {

    /**
     * This method returns all snippets that contain the given keyword. The
     * keyword is received as a base64 encoded string, which is decoded in the
     * back-end. A match in any of the following fields, will add a snippet to
     * the search results:
     *
     * -The snippet's ID, name, description, author, date, language. or
     * architecture
     *
     * - The snippet's techniques (can also be singular)
     *
     * - The tactic where a technique belongs to (multiple are possible, as can
     * be seen in the MITRE ATT&CK matrix)
     *
     * This method is a only reachable when a GET request is sent to the API
     * base (as specified in <code>controller.RestConfig.java</code>), after
     * which the path for this class (as specified above in <code>@Path</code>)
     * needs to be appended. Additionally, the method's <code>@QueryParam</code>
     * needs to be appended.
     *
     * An example of this is: <code>api/v1/search?keyword=mySearchTerm</code>
     *
     * If the operation is successful, a HTTP OK (200) status is returned,
     * together with a JSON array of snippets. If no matches are found, the HTTP
     * OK (200) status will be returned, together with an empty JSON array.
     *
     * @param keyword the keyword to search for in a base64 encoded format
     * @return all snippets which match the given keyword
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchByType(@QueryParam("keyword") String keyword) {
        //Create a new gson object
        Gson gson = new Gson();
        //Create a new search service instance
        SearchService searchService = new SearchService();
        //Decode the given string into a normal string
        keyword = new String(Base64.getDecoder().decode(keyword));
        //Get all snippets based on the given keyword
        List<Snippet> snippets = searchService.getSnippetByKeyword(keyword);
        //Convert the snippet list into JSON format
        String jsonOutput = gson.toJson(snippets);
        //Return a HTTP OK (200) status with the list of snippets in JSON format
        return Response.status(Response.Status.OK).entity(jsonOutput).build();
    }
}
