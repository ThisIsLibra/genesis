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
package model.language.javascript;

import model.language.ClassConstants;
import model.language.IClass;
import model.language.IFunction;
import model.language.IVariable;
import model.language.enums.Architecture;
import model.language.enums.Language;
import model.language.generic.GenericClass;

/**
 * The class implements the JavaScript language in Genesis. The abstract
 * <code>GenericClass</code> is used to implement part of the functionality that
 * is required by the <code>IClass</code> interface.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class JavaScriptClass extends GenericClass implements IClass {

    /**
     * The 'main' function of the script, also known as the entry point of the
     * script. This is the code that is first executed when the script is
     * called.
     */
    private String script;

    /**
     * Creates an instance of a JavaScript class, which extends the
     * <code>GenericClass</code> and implements the <code>IClass</code>
     * interface.
     *
     * @param architecture the architecture of the script, which specifies the
     * platform and CPU architecture
     * @param script the code to execute first. Note that it may use functions
     * and variables that are declared within this class
     */
    public JavaScriptClass(Architecture architecture, String script) {
        super(architecture, Language.JAVASCRIPT);
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
     * Builds the JavaScript class, including all global variables, functions
     * and the entry point
     *
     * @return returns the complete JavaScript class in the form of a string
     */
    @Override
    public String build() {
        //Create a stringbuilder object
        StringBuilder output = new StringBuilder();
        //Build each variable
        for (IVariable variable : super.getVariables()) {
            output.append(variable.build());
            output.append(ClassConstants.NEW_LINE);
        }
        //Add a new line
        output.append(ClassConstants.NEW_LINE);
        //Build each function
        for (IFunction function : super.getFunctions()) {
            output.append(function.build());
            output.append(ClassConstants.NEW_LINE);
        }
        //Add a new line
        output.append(ClassConstants.NEW_LINE);
        //Add the script
        output.append(script);
        //Return the class
        return output.toString();
    }
}
