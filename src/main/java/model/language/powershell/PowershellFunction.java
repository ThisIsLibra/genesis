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

import java.util.HashSet;
import java.util.Map;
import model.language.ClassConstants;
import model.language.IFunction;
import model.language.enums.Language;
import model.language.generic.GenericFunction;

/**
 * A function for the <code>PowershellClass</code>, which extends the
 * <code>IFunction</code> interface and inherits functionality from the
 * <code>GenericFunction</code>.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class PowershellFunction extends GenericFunction implements IFunction {

    /**
     * Set if the function is a CmdletBinding, left empty otherwise
     */
    private String cmdletBinding;

    /**
     * Creates an instance of the <code>PowershellFunction</code> class, which
     * implements the <code>IFunction</code> interface and has extended
     * functionality from the <code>GenericFunction</code> class.
     *
     * @param name the name of the function
     * @param cmdletBinding the CmdletBinding tag
     * @param arguments the function's arguments, in which the key of the map is
     * the argument's name and the value is the type
     * @param body the function's body
     */
    public PowershellFunction(String name, String cmdletBinding, Map<String, String> arguments, String body) {
        super(Language.POWERSHELL, new HashSet<String>(), name, arguments, body);
        this.cmdletBinding = cmdletBinding;
    }

    /**
     * Creates an instance of the <code>PowershellFunction</code> class, which
     * implements the <code>IFunction</code> interface and has extended
     * functionality from the <code>GenericFunction</code> class.
     *
     * @param name the name of the function
     * @param arguments the function's arguments, in which the key of the map is
     * the argument's name and the value is the type
     * @param body the function's body
     */
    public PowershellFunction(String name, Map<String, String> arguments, String body) {
        super(Language.POWERSHELL, new HashSet<String>(), name, arguments, body);
        this.cmdletBinding = "";
    }

    /**
     * Builds the function in the form of a string
     *
     * @return the function in the form of a string
     */
    @Override
    public String build() {
        //Creates a string builder object
        StringBuilder function = new StringBuilder();
        //Appends the function tag, name, and opens the curly brackets
        function.append("function " + super.getName() + ClassConstants.SPACE + ClassConstants.CURLY_OPEN + ClassConstants.NEW_LINE);
        //Check if the cmdlet binding is an empty string
        if (!cmdletBinding.equals("")) {
            //If not, add the cmdlet binding
            function.append(ClassConstants.TAB + cmdletBinding + ClassConstants.NEW_LINE);
        }
        //Add the function parameters
        function.append(ClassConstants.TAB + "param" + ClassConstants.BRACKET_OPEN + ClassConstants.NEW_LINE);
        for (Map.Entry<String, String> entry : super.getArguments().entrySet()) {
            String parameterName = entry.getKey();
            String parameterValue = entry.getValue();
            function.append(ClassConstants.TAB + ClassConstants.TAB + parameterValue + ClassConstants.NEW_LINE + parameterName + ClassConstants.NEW_LINE);
        }
        //Close the arguments and add a new line
        function.append(ClassConstants.TAB + ClassConstants.BRACKET_CLOSE + ClassConstants.NEW_LINE);
        //Add the function body
        function.append(ClassConstants.TAB + super.getBody() + ClassConstants.NEW_LINE);
        //Close the function with a curly bracket
        function.append(ClassConstants.CURLY_CLOSE);
        //Return the function in string format
        return function.toString();
    }
}
