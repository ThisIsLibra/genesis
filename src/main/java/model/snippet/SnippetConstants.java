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
package model.snippet;

/**
 * This class contains constants that are used within <code>Snippet</code>s.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class SnippetConstants {

    /**
     * The code that is used within the snippets to mark the start of a variable
     * that should be replaced
     */
    public static final String ALTERATOR_OPEN = "<<";

    /**
     * The code that is used within the snippets to mark the end of a variable
     * that should be replaced
     */
    public static final String ALTERATOR_CLOSE = ">>";

    /**
     * The location on the disk where all snippet files are stored (in the form
     * of an absolute path)
     */
    public static final String SNIPPET_FOLDER = "/json/";

    /**
     * The version of Genesis
     */
    public static final String VERSION = "1.0-stable";
}
