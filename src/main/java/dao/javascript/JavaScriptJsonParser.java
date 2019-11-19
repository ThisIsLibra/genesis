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
package dao.javascript;

import dao.generic.GenericJsonParser;
import dao.generic.IClassJsonParser;
import exception.JsonParseException;
import java.util.HashMap;
import java.util.Map;
import model.language.IClass;
import model.language.IFunction;
import model.language.IVariable;
import model.language.enums.Architecture;
import model.language.javascript.JavaScriptClass;
import model.language.javascript.JavaScriptFunction;
import model.language.javascript.JavaScriptVariable;
import model.snippet.Snippet;
import org.json.JSONObject;

/**
 * This class parses the JSON files that contain JavaScript code into a snippet
 * object. It uses the abstract <code>GenericJsonParser</code> class for
 * additional language agnostic methods and implements the
 * <code>IClassJsonParser</code> interface to match the other language specific
 * implementations.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class JavaScriptJsonParser extends GenericJsonParser implements IClassJsonParser {

    /**
     * Parse the JSON object into a <code>Snippet</code> object
     *
     * @param json the JSON object to parse
     * @return a snippet with the parsed information in it
     * @throws JsonParseException if the JSON object cannot be parsed
     */
    @Override
    public Snippet parse(JSONObject json) throws JsonParseException {
        //Get the information from the JSON and store that in a snippet
        Snippet snippet = super.parseSnippetInformation(json);

        //Parse the Javascript class by getting the JSON class object from the JSON object
        json = json.getJSONObject("class");
        //Get the architecture
        Architecture architecture = Architecture.valueOf(json.getString("architecture"));
        //Get the script field
        String script = json.getString("script");
        //Create a new Javascript 
        IClass jsClass = new JavaScriptClass(architecture, script);
        //Iterate through all techniques
        jsClass.setTechniques(super.getTechniques(json.getJSONArray("techniques")));
        //Add the givne script as a function to the class
        jsClass = addScript(jsClass, script);

        //Get all variables from the JSON object
        JSONObject variablesJson = json.getJSONObject("variables");
        //Loops through all variables
        for (String variableName : variablesJson.keySet()) {
            //Get the variable body, based on the variable name (as the name is the key in the set)
            String variableBody = variablesJson.getString(variableName);
            //Create a variable object
            IVariable variable = new JavaScriptVariable(variableName, variableBody);
            //Add the variable object to the class
            jsClass.addVariable(variable);
        }
        //Add the IClass object to the snippet
        snippet.setClassObject(jsClass);
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
        //Instantiate the name of the function
        String name = "jsFunction";
        //Create the arguments mapping, which is empty since no arguments are required
        Map<String, String> arguments = new HashMap<>();
        //Create the Javascript function object
        IFunction mainFunction = new JavaScriptFunction(name, arguments, script);
        //Add the function object to the class object
        classObject.addFunction(mainFunction);
        //Declare the new script, which should call the newly added function
        String newScript = name + "();";
        //Set the new script value to call the newly added function
        ((JavaScriptClass) classObject).setScript(newScript);
        //Return the altered class object
        return classObject;
    }

}
