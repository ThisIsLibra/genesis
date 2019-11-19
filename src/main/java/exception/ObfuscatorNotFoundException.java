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
 * This class is used to throw an exception when no obfuscator can be found
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class ObfuscatorNotFoundException extends Exception {

    /**
     * Throw when no obfuscator can be found
     *
     * @param message the error message
     */
    public ObfuscatorNotFoundException(String message) {
        super(message);
    }
}
