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
package exception;

/**
 * This class is used to throw an exception when a snippet cannot be found for
 * whatever reason.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class SnippetNotFoundException extends Exception {

    /**
     * This exception should be thrown when a snippet cannot be found
     *
     * @param message the error message to display
     */
    public SnippetNotFoundException(String message) {
        super(message);
    }
}
