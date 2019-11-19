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
package model.alterator.javascript;

import java.util.List;
import model.alterator.IAlterator;
import model.alterator.generic.GenericAlterator;
import model.language.IClass;
import model.language.javascript.JavaScriptClass;
import model.snippet.KeyValuePair;
import model.snippet.SnippetAlteration;

/**
 * This alterator should only be used in combination with the
 * <code>JavaScriptClass</code> implementation of the <code>IClass</code>
 * interface.
 *
 * It alterates the variables, functions and the script of the
 * <code>JavaScriptClass</code>.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class JavaScriptAlterator extends GenericAlterator implements IAlterator {

    /**
     * Alterates the given <code>IClass</code> with the given
     * <code>SnippetAlteration</code>'s <code>KeyValuePair</code>s. The
     * <code>IVariable</code>s, <code>IFunction</code>s and the
     * <code>script</code> within the <code>IClass</code> are altered due to
     * this.
     *
     * @param classObject the object to alterate
     * @param alteration the key-value pairs to search and replace in the
     * classObject
     * @return the alterated <code>IClass</code>.
     */
    @Override
    public IClass alterate(IClass classObject, SnippetAlteration alteration) {
        //Alterate all variables
        classObject = super.alterateVariables(classObject, alteration);
        //Alterate all functions
        classObject = super.alterateFunctions(classObject, alteration);
        //Alterate the script
        classObject = alterateScript(classObject, alteration.getKeyValuePairs());
        //Return the class object after all changes have been made (if any)
        return classObject;
    }

    /**
     * Alterates the <code>JavaScriptClass</code> specific <code>script</code>
     * field.
     *
     * @param classObject the class to alterate
     * @param keyValuePairs the key-value pairs to search and replace in the
     * classObject
     * @return the alterated <code>IClass</code> object
     */
    private IClass alterateScript(IClass classObject, List<KeyValuePair> keyValuePairs) {
        //Get the script from the Javascript class object
        String script = ((JavaScriptClass) classObject).getScript();
        //Iterate through all given key value pairs
        for (KeyValuePair kvp : keyValuePairs) {
            //If the script contains the key, replace it with the value
            if (script.contains(kvp.getKey())) {
                //Replace the key within the script with the value
                script = script.replace(kvp.getKey(), kvp.getValue());
                //Set the new script in the class object
                ((JavaScriptClass) classObject).setScript(script);
            }
        }
        //Return the updated class object with the changes (if any)
        return classObject;
    }

}
