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

/**
 * Creates an <code>Message</code> object which contains a single field:
 * <code>message</code>. This object can easily be converted into JSON using
 * <code>Gson</code> and serves to easily return the error message via the API
 * in a JSON format.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class Message {

    /**
     * The message to display as in the JSON output
     */
    private String message;

    /**
     * Creates a <code>Message</code> object which contains a single field:
     * <code>message</code>. This object can easily be converted into JSON using
     * <code>Gson</code> and serves to easily a message via the API in JSON
     * format.
     *
     * @param message the exception message
     */
    public Message(String message) {
        this.message = message;
    }

    /**
     * Gets the message
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message
     *
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns this message in JSON notation using the
     * <code>com.google.gson.Gson</code> dependency
     *
     * @return this message object in JSON format
     */
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
