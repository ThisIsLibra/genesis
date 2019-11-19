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
package model.language.vba;

import model.language.ClassConstants;
import model.language.IVariable;
import model.language.generic.GenericVariable;

/**
 * A variable for the <code>VbaClass</code>, which extends the
 * <code>IVariable</code> interface and inherits functionality from the
 * <code>GenericVariable</code>.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class VbaVariable extends GenericVariable implements IVariable {

    /**
     * Creates an instance of the <code>VbaVariable</code> class, which
     * implements the <code>IVariable</code> interface and has extended
     * functionality from the <code>GenericVariable</code> class.
     *
     * @param name the variable's name
     * @param body the body of the variable (could be the type to set it to, or
     * the instantiation of it)
     */
    public VbaVariable(String name, String body) {
        super(name, body);
    }

    /**
     * Returns the variable in the form of a string
     *
     * @return the variable in the form of a string
     */
    @Override
    public String build() {
        //Returns the variable in the form of "Const [name] = [body]"
        return "Const" + ClassConstants.SPACE + super.getName() + ClassConstants.SET_EQUALS + super.getBody();
    }

}
