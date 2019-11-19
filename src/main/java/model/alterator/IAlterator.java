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
package model.alterator;

import model.language.IClass;
import model.snippet.SnippetAlteration;

/**
 * The <code>IAlterator</code> class is used to replace all strings within an
 * <code>IClass</code> object. The <code>SnippetAlteration</code> class contains
 * all required information to select, alternate and obfuscate a snippet.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public interface IAlterator {

    /**
     * The given <code>IClass</code> object will be searched for strings that
     * are included within the <code>SnippetAlteration</code> object. These
     * strings will then be replaced with the desired values, as they are
     * retrieved from the user's input.
     *
     * @param classObject the class to replace the strings in
     * @param alteration the object which contains the key-value pairs used
     * during the replacement
     * @return the alterated <code>classObject</code> object
     */
    public IClass alterate(IClass classObject, SnippetAlteration alteration);

    /**
     * Alterates all functions within the given <code>classObject</code>.
     *
     * @param classObject the class object to alterate
     * @param alteration the object which contains the key-value pairs used
     * during the replacement
     * @return the alterated <code>classObject</code> object
     */
    public IClass alterateFunctions(IClass classObject, SnippetAlteration alteration);

    /**
     * Alterates all variables within the given <code>classObject</code>.
     *
     * @param classObject the class object to alterate
     * @param alteration the object which contains the key-value pairs used
     * during the replacement
     * @return the alterated <code>classObject</code> object
     */
    public IClass alterateVariables(IClass classObject, SnippetAlteration alteration);
}
