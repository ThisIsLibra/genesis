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
package model.alterator.generic;

import model.language.IClass;
import model.language.IFunction;
import model.language.IVariable;
import model.snippet.KeyValuePair;
import model.snippet.SnippetAlteration;

/**
 * The <code>GenericAlterator</code> should be used in a language specific
 * implementation of the <code>IAlterator</code>. It contains the functionality
 * that the <code>IClass</code> class uses by default. Additional functions
 * should be implemented in the language specific implementation.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public abstract class GenericAlterator {

    /**
     * Alters all <code>IVariable</code> objects within the <code>IClass</code>
     * object using the key-value mapping that reside within the
     * <code>SnippetAlteration</code> object.
     *
     * @param classObject the class object to alterate
     * @param alteration the object that contains the key-value pairs
     * @return the alterated class
     */
    public IClass alterateVariables(IClass classObject, SnippetAlteration alteration) {
        //Iterate through all variables
        for (IVariable variable : classObject.getVariables()) {
            //Get the body of the variable
            String body = variable.getBody();
            //Iterate through all given key value pairs
            for (KeyValuePair kvp : alteration.getKeyValuePairs()) {
                //If the body contains the key of the key value pair, it is replaced with the value of the key value pair
                if (body.contains(kvp.getKey())) {
                    //Replace the key with the value in the body
                    body = body.replace(kvp.getKey(), kvp.getValue());
                    //Set the new body in the variable
                    variable.setBody(body);
                }
            }
        }
        //Return the class object after the modifications happened (in case none happened, the object is returned as-is)
        return classObject;
    }

    /**
     * Alters all <code>IFunction</code> objects within the <code>IClass</code>
     * object using the key-value mapping that reside within the
     * <code>SnippetAlteration</code> object.
     *
     * @param classObject the class object to alterate
     * @param alteration the object that contains the key-value pairs
     * @return the alterated class
     */
    public IClass alterateFunctions(IClass classObject, SnippetAlteration alteration) {
        //Iterate through all functions
        for (IFunction function : classObject.getFunctions()) {
            //Get the function's body
            String body = function.getBody();
            //Iterate through all given key value pairs
            for (KeyValuePair kvp : alteration.getKeyValuePairs()) {
                //If the body contains the key, the key is replaced with the value
                if (body.contains(kvp.getKey())) {
                    //Replace the key with the value in the function body
                    body = body.replace(kvp.getKey(), kvp.getValue());
                    //Set the new function body
                    function.setBody(body);
                }
            }
        }
        //Return the class object after all changes have been made (if none, the object is returned as-is)
        return classObject;
    }
}
