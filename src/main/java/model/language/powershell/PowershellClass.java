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
package model.language.powershell;

import model.language.ClassConstants;
import model.language.IClass;
import model.language.IFunction;
import model.language.IVariable;
import model.language.enums.Architecture;
import model.language.enums.Language;
import model.language.generic.GenericClass;

/**
 * The class implements the Powershell language in Genesis. The abstract
 * <code>GenericClass</code> is used to implement part of the functionality that
 * is required by the <code>IClass</code> interface.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class PowershellClass extends GenericClass implements IClass {

    /**
     * The 'main' function of the script, also known as the entry point of the
     * script. This is the code that is first executed when the script is
     * called.
     */
    private String script;

    /**
     * Creates an instance of a Powershell class, which extends the
     * <code>GenericClass</code> and implements the <code>IClass</code>
     * interface.
     *
     * @param architecture the architecture of the script, which specifies the
     * platform and CPU architecture
     * @param script the code to execute first. Note that it may use functions
     * and variables that are declared within this class
     */
    public PowershellClass(Architecture architecture, String script) {
        super(architecture, Language.POWERSHELL);
        this.script = script;
    }

    /**
     * Gets the entry point script of this class
     *
     * @return the entry point script
     */
    public String getScript() {
        return script;
    }

    /**
     * Sets the entry point script for this class
     *
     * @param script the script to set as entry point
     */
    public void setScript(String script) {
        this.script = script;
    }

    /**
     * Builds the Powershell class, including all global variables, functions
     * and the entry point
     *
     * @return returns the complete Powershell class in the form of a string
     */
    @Override
    public String build() {
        //Create a string builder object
        StringBuilder result = new StringBuilder();
        //Add all variables
        for (IVariable variable : super.getVariables()) {
            result.append(variable.build());
            result.append(ClassConstants.NEW_LINE);
        }
        //Add a new line
        result.append(ClassConstants.NEW_LINE);
        //Add all functions
        for (IFunction function : super.getFunctions()) {
            result.append(function.build());
            result.append(ClassConstants.NEW_LINE);
        }
        //Add a new line
        result.append(ClassConstants.NEW_LINE);
        //Add the script
        result.append(script);
        //Return the class in string format
        return result.toString();
    }

}
