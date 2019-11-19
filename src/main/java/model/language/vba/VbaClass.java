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
import model.language.IClass;
import model.language.IFunction;
import model.language.IVariable;
import model.language.enums.Architecture;
import model.language.enums.Language;
import model.language.generic.GenericClass;

/**
 * The class implements the VBA macro language in Genesis. The abstract
 * <code>GenericClass</code> is used to implement part of the functionality that
 * is required by the <code>IClass</code> interface.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class VbaClass extends GenericClass implements IClass {

    /**
     * Creates an instance of a VBA macro class, which extends the
     * <code>GenericClass</code> and implements the <code>IClass</code>
     * interface.
     *
     * @param architecture the architecture of the script, which specifies the
     * platform and CPU architecture
     */
    public VbaClass(Architecture architecture) {
        super(architecture, Language.VBA);
    }

    /**
     * Builds the VBA macro class, including all global variables and defined
     * functions
     *
     * @return returns the complete VBA macro class in the form of a string
     */
    @Override
    public String build() {
        //Creates a string builder object
        StringBuilder output = new StringBuilder();
        //Add all variables
        for (IVariable variable : super.getVariables()) {
            output.append(ClassConstants.TAB + variable.build());
            output.append(ClassConstants.NEW_LINE);
        }
        //Add a new line
        output.append(ClassConstants.NEW_LINE);
        //Add all functions
        for (IFunction function : super.getFunctions()) {
            output.append(ClassConstants.TAB + function.build());
            output.append(ClassConstants.NEW_LINE);
        }
        //Return the class in the form of a string
        return output.toString();
    }
}
