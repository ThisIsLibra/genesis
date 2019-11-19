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

import java.util.HashSet;
import java.util.Map;
import model.language.ClassConstants;
import model.language.IFunction;
import model.language.enums.Language;
import model.language.generic.GenericFunction;

/**
 * * A function for the <code>VbaClass</code>, which extends the
 * <code>IFunction</code> interface and inherits functionality from the
 * <code>GenericFunction</code>.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class VbaFunction extends GenericFunction implements IFunction {

    /**
     * The function type is either a <code>Sub</code> or a
     * <code>Function</code>.
     */
    private String type;

    /**
     * The return type of the function
     */
    private String returnType;

    /**
     * Creates an instance of the <code>VbaFunction</code> class, which
     * implements the <code>IFunction</code> interface and has extended
     * functionality from the <code>GenericFunction</code> class.
     *
     * @param type the function's type (<code>Sub</code> or
     * <code>Function</code>)
     * @param name the function's name
     * @param arguments the function's arguments (where the key is the
     * variable's name and the value is the type)
     * @param returnType the function's return type
     * @param body the function's body
     */
    public VbaFunction(String type, String name, Map<String, String> arguments, String returnType, String body) {
        //There are no dependencies in VBA, as such an empty hashset will be provided the the generic function
        super(Language.VBA, new HashSet<String>(), name, arguments, body);
        //Sets the function type
        this.type = type;
        //Sets the return type
        this.returnType = returnType;
    }

    /**
     * Builds the function in the form of a string
     *
     * @return the function in the form of a string
     */
    @Override
    public String build() {
        //Create a string builder object
        StringBuilder function = new StringBuilder();
        //Append the function type and name
        function.append(type + ClassConstants.SPACE + super.getName() + ClassConstants.BRACKET_OPEN);
        //Add all arguments
        for (Map.Entry<String, String> entry : super.getArguments().entrySet()) {
            String argumentName = entry.getKey();
            String argumentType = entry.getValue();
            function.append(argumentType + ClassConstants.SPACE + argumentName + ClassConstants.COMMA + ClassConstants.SPACE);
        }
        //Remove the redundant comma and space from the last argument
        int index = function.toString().lastIndexOf(ClassConstants.COMMA);
        if (index != -1) {
            function.delete(index, index + 2);
        }
        //Close the arguments bracket, add the return type, and add a new line
        function.append(ClassConstants.BRACKET_CLOSE + ClassConstants.SPACE + returnType + ClassConstants.NEW_LINE);
        //Add each line of the body with two tabs as indentation
        for (String line : super.getBody().split("\n")) {
            function.append("\t\t" + line + ClassConstants.NEW_LINE);
        }
        //Append a tab, after which the type is added
        function.append("\t");
        if (type.equalsIgnoreCase("sub")) {
            function.append("End Sub");
        } else { //if its not a sub it is a function
            function.append("End Function");
        }
        //Add a new line
        function.append(ClassConstants.NEW_LINE);
        //Return the function in the form of a string
        return function.toString();
    }
}
