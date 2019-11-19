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

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

/**
 * Ensures the CORS header handling for the front-end by accepting the all
 * requests. As this is not an API that is to be exposed to the outside, the
 * security implications of this can be ignored.
 *
 * The code is mainly taken from StackOverflow:
 * https://stackoverflow.com/a/54697701
 *
 * @author Sorin Penteleiciuc (StackOverflow user name)
 */
@Provider
@PreMatching
public class CorsResponseFilter implements ContainerResponseFilter {

    /**
     * Every request passes through this function based on the annotated
     * interfaces, which then adds an exception based on the CORS header
     *
     * @param requestContext the request context
     * @param responseContext the response request
     */
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        allowExceptionCors(requestContext, responseContext, requestContext.getHeaderString("Origin"));
    }

    /**
     * Allows the request to go through as an exception
     *
     * @param requestContext the request context
     * @param responseContext the response context
     * @param origin the request's origin
     */
    private void allowExceptionCors(ContainerRequestContext requestContext, ContainerResponseContext responseContext, String origin) {
        //Gets the method header
        String methodHeader = requestContext.getHeaderString("Access-Control-Request-Method");
        //Gets the request headers
        String requestHeaders = requestContext.getHeaderString("Access-Control-Request-Headers");

        //Get the headers from the response context
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        //Set multiple fields in the header
        headers.putSingle("Access-Control-Allow-Origin", origin);
        headers.putSingle("Access-Control-Allow-Credentials", "true");
        headers.putSingle("Access-Control-Allow-Methods", methodHeader);
        headers.putSingle("Access-Control-Allow-Headers", "x-requested-with," + (requestHeaders == null ? "" : requestHeaders));
    }
}
