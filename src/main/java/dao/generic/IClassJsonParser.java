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
package dao.generic;

import exception.JsonParseException;
import model.snippet.Snippet;
import org.json.JSONObject;

/**
 * This interface is used for all language specific JSON parsers. The
 * <code>IClass</code> part of the name is a references to the
 * <code>IClass</code> interface.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public interface IClassJsonParser {

    /**
     * Parses the JSON object into a snippet.
     * @param json the JSON object to be parsed
     * @return a snippet object
     * @throws JsonParseException is thrown if the JSON cannot be parsed
     */
    public Snippet parse(JSONObject json) throws JsonParseException;
}
