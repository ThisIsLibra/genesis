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

import java.util.HashSet;
import java.util.Map;
import model.language.ClassConstants;
import model.language.IFunction;
import model.language.enums.Language;
import model.language.generic.GenericFunction;

/**
 * * A function for the <code>JavaScriptClass</code>, which extends the
 * <code>IFunction</code> interface and inherits functionality from the
 * <code>GenericFunction</code>.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class JavaScriptFunction extends GenericFunction implements IFunction {

    /**
     * Creates an instance of the <code>JavaScriptFunction</code> class, which
     * implements the <code>IFunction</code> interface and has extended
     * functionality from the <code>GenericFunction</code> class.
     *
     * @param name the function's name
     * @param arguments the function's arguments (where the key is the
     * variable's name and the value is the type)
     * @param body the function's body
     */
    public JavaScriptFunction(String name, Map<String, String> arguments, String body) {
        super(Language.JAVASCRIPT, new HashSet<String>(), name, arguments, body);
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
        //Adds the function tag and name
        function.append("function" + ClassConstants.SPACE + super.getName() + ClassConstants.BRACKET_OPEN);
        //Adds the arguments
        for (Map.Entry<String, String> entry : super.getArguments().entrySet()) {
            String argumentName = entry.getKey();
            function.append(argumentName + ClassConstants.COMMA + ClassConstants.SPACE);
        }
        //Remove the redundant comma and space from the last argument
        int index = function.toString().lastIndexOf(ClassConstants.COMMA);
        if (index != -1) {
            function.delete(index, index + 2);
        }
        //Close the argument brackets and open the curly brackets 
        function.append(ClassConstants.BRACKET_CLOSE + ClassConstants.SPACE + ClassConstants.CURLY_OPEN + ClassConstants.NEW_LINE);
        //Add the function body
        function.append(super.getBody() + ClassConstants.NEW_LINE);
        //Close the curly bracket
        function.append(ClassConstants.CURLY_CLOSE);
        //Return the function in string format
        return function.toString();
    }

}
