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
package dao.powershell;

import dao.generic.GenericJsonParser;
import dao.generic.IClassJsonParser;
import exception.JsonParseException;
import java.util.HashMap;
import java.util.Map;
import model.language.IClass;
import model.language.IFunction;
import model.language.IVariable;
import model.language.enums.Architecture;
import model.language.powershell.PowershellClass;
import model.language.powershell.PowershellFunction;
import model.language.powershell.PowershellVariable;
import model.snippet.Snippet;
import org.json.JSONObject;

/**
 * This class parses the JSON files that contain Powershell code into a snippet
 * object. It uses the abstract <code>GenericJsonParser</code> class for
 * additional language agnostic methods and implements the
 * <code>IClassJsonParser</code> interface to match the other language specific
 * implementations.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class PowershellJsonParser extends GenericJsonParser implements IClassJsonParser {

    /**
     * Parses the JSON object into a snippet that contains Powershell code
     *
     * @param json the JSON object to parse
     * @return the result of the parsing, in the form of a snippet object
     * @throws JsonParseException is thrown if the JSON file cannot be parsed
     */
    @Override
    public Snippet parse(JSONObject json) throws JsonParseException {
        Snippet snippet = super.parseSnippetInformation(json);
        //Get the JSON class object from the JSONObject
        json = json.getJSONObject("class");
        //Get the architecture
        Architecture architecture = Architecture.valueOf(json.getString("architecture"));
        //Get the script
        String script = json.getString("script");
        //Create the Powershell class object
        IClass powershellClass = new PowershellClass(architecture, script);
        //Iterate through all techniques
        powershellClass.setTechniques(super.getTechniques(json.getJSONArray("techniques")));
        //Add the script as a function
        powershellClass = addScript(powershellClass, script);

        //Gets all variables
        JSONObject variablesJson = json.getJSONObject("variables");
        //Loops through all variables
        for (String variableName : variablesJson.keySet()) {
            //Get the type based on the name, as the name is the key in the set
            String variableType = variablesJson.getString(variableName);
            //Create a variable
            IVariable variable = new PowershellVariable(variableName, variableType);
            //Add the variable to the class
            powershellClass.addVariable(variable);
        }
        //Add the class to the snippet
        snippet.setClassObject(powershellClass);
        //Return the snippet
        return snippet;
    }

    /**
     * Adds the script as a function to the class and overwrites the script to a
     * function call to the newly created function
     *
     * @param classObject the class object to alter
     * @param script the script to store in the function
     * @return the modified class object
     */
    private IClass addScript(IClass classObject, String script) {
        //Sets the name of the main function
        String name = "psFunction";
        //Creates an empty map since there are no arguments
        Map<String, String> arguments = new HashMap<>();
        //Creates the function object, where the body contains the given script
        IFunction mainFunction = new PowershellFunction(name, arguments, script);
        //Add the function to the class object
        classObject.addFunction(mainFunction);
        //Set the new script to the name of the function that was just added
        ((PowershellClass) classObject).setScript(name);
        //Return the modified class object
        return classObject;
    }

}
