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
package dao.vba;

import dao.generic.GenericJsonParser;
import dao.generic.IClassJsonParser;
import exception.JsonParseException;
import java.util.HashMap;
import java.util.Map;
import model.language.IClass;
import model.language.IFunction;
import model.language.IVariable;
import model.language.enums.Architecture;
import model.language.vba.VbaClass;
import model.language.vba.VbaFunction;
import model.language.vba.VbaVariable;
import model.snippet.Snippet;
import org.json.JSONObject;

/**
 * Parses a JSON file into a snippet that contains VBA macro code. It uses the
 * abstract <code>GenericJsonParser</code> class for additional language
 * agnostic methods and implements the <code>IClassJsonParser</code> interface
 * to match the other language specific implementations.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class VbaJsonParser extends GenericJsonParser implements IClassJsonParser {

    /**
     * Parses the JSON object into a Snippet that contains VBA macro code.
     *
     * @param json the JSON to be parsed
     * @return a snippet with information and the VBA macro code in it
     * @throws JsonParseException is thrown when the parsing of the JSON object
     * results in an error
     */
    @Override
    public Snippet parse(JSONObject json) throws JsonParseException {
        //Snippet snippet = super.parseSnippetInformation(json.getJSONObject("information"));
        Snippet snippet = super.parseSnippetInformation(json);
        //Get the JSON class object from the JSONObject
        json = json.getJSONObject("class");
        //Create the class object based on the given architecture
        Architecture architecture = Architecture.valueOf(json.getString("architecture"));
        //Create the VBA class object
        IClass vbaClass = new VbaClass(architecture);
        //Iterate through all techniques
        vbaClass.setTechniques(super.getTechniques(json.getJSONArray("techniques")));
        //Get the script field
        String script = json.getString("script");
        //Add the functions to the VBA class object
        vbaClass = addScriptAsFunction(vbaClass, script);

        //Get all variables from the JSON object
        JSONObject variablesJson = json.getJSONObject("variables");
        //Iterate through all variables
        for (String variableName : variablesJson.keySet()) {
            //Get the variable type based on the variable's name (which is the key in the set)
            String variableType = variablesJson.getString(variableName);
            //Create a variable object
            IVariable variable = new VbaVariable(variableName, variableType);
            //Add the variable to the class
            vbaClass.addVariable(variable);
        }

        //Set the class object
        snippet.setClassObject(vbaClass);
        //Return the snippet
        return snippet;
    }

    /**
     * Adds the script as a sub to the VBA class, after which two additional
     * functions are added: <code>Document_Open</code> and
     * <code>Workbook_Open</code>. These two functions execute the function
     * which contains the given script when the document or workbook is opened.
     *
     * @param classObject the class object to add the functions to
     * @param script the script to add in one of the functions
     * @return the modified class object
     */
    private IClass addScriptAsFunction(IClass classObject, String script) {
        //Define the type of the function
        String type = "Sub";
        //Define the name of the function that is 
        String scriptFunctionName = "myFunction";
        //Define the name of the function that should automatically be opened when the VBA is put in a Word macro
        String name = "Document_Open";
        //Create an empty list of arguments, since none are used
        Map<String, String> arguments = new HashMap<>();
        //Since the function is a sub, no return type is required
        String returnType = "";
        //Create the function that contains the user's script
        IFunction myFunction = new VbaFunction(type, scriptFunctionName, arguments, returnType, script);
        //Add the function to the class object
        classObject.addFunction(myFunction);
        //Create the document open function
        IFunction documentOpen = new VbaFunction(type, name, arguments, returnType, scriptFunctionName);
        //Add the function to the class object
        classObject.addFunction(documentOpen);
        //Set the name to the workbook open function
        name = "Workbook_Open";
        //Create the workbook open function
        IFunction workbookOpen = new VbaFunction(type, name, arguments, returnType, scriptFunctionName);
        //Add the workbook open function to the class object
        classObject.addFunction(workbookOpen);
        //Return the class object
        return classObject;
    }

}
