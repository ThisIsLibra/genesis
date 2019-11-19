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
package model.alterator.vba;

import model.alterator.IAlterator;
import model.alterator.generic.GenericAlterator;
import model.language.IClass;
import model.snippet.SnippetAlteration;

/**
 * This alterator should only be used in combination with the
 * <code>VbaClass</code> implementation of the <code>IClass</code> interface.
 *
 * It alterates the variables and functions of the <code>VbaClass</code>.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class VbaAlterator extends GenericAlterator implements IAlterator {

    /**
     * Alterates the given <code>IClass</code> with the given
     * <code>SnippetAlteration</code>'s <code>KeyValuePair</code>s. The
     * <code>IVariable</code>s and <code>IFunction</code>s within the
     * <code>IClass</code> are altered due to this.
     *
     * @param classObject the object to alterate
     * @param alteration the key-value pairs to search and replace in the
     * classObject
     * @return the alterated <code>IClass</code>.
     */
    @Override
    public IClass alterate(IClass classObject, SnippetAlteration alteration) {
        //Alterate all variables
        classObject = super.alterateVariables(classObject, alteration);
        //Alterate all functions
        classObject = super.alterateFunctions(classObject, alteration);
        //Return the class object after all changes have been made (if any)
        return classObject;
    }
}
