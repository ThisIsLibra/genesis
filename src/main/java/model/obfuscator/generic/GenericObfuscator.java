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
package model.obfuscator.generic;

import java.util.Map;
import model.language.IClass;
import model.language.IFunction;

/**
 * This class contains functions that are language agnostic. Implementing a
 * language specific obfuscator is done by using the <code>IObfuscator</code>
 * interface and this class. The generic functions are already implemented,
 * which leaves the language specific implementation up to the language specific
 * obfuscation.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public abstract class GenericObfuscator {

    /**
     * The generic string obfuscator object
     */
    private GenericStringObfuscator stringObfuscator;

    /**
     * The generic integer obfuscator object
     */
    private GenericIntegerObfuscator integerObfuscator;

    /**
     * The generic control flow obfuscator object
     */
    private GenericControlFlowObfuscator controlFlowObfuscator;

    /**
     * A class which contains all generic techniques
     */
    private GenericObfuscatorTechniques obfuscatorTechniques;

    /**
     * Creates a new GenericObfuscator object, which instantiates four objects:
     * a generic string obfuscator, a generic integer obfuscator, a generic
     * control flow obfuscator, and a generic obfuscator technique generator
     */
    public GenericObfuscator() {
        //Instantiate the generic string obfuscator object
        stringObfuscator = new GenericStringObfuscator();
        //Instantiate the generic integer obfuscator object
        integerObfuscator = new GenericIntegerObfuscator();
        //Instantiate the generic control flow obfuscator object
        controlFlowObfuscator = new GenericControlFlowObfuscator();
        //Instantiate the generic obfuscator techniques object
        obfuscatorTechniques = new GenericObfuscatorTechniques();
    }

    /**
     * Gets the generic string obfuscator
     *
     * @return the generic string obfuscator object
     */
    public GenericStringObfuscator getStringObfuscator() {
        return stringObfuscator;
    }

    /**
     * Gets the generic integer obfuscator
     *
     * @return the generic integer obfuscator object
     */
    public GenericIntegerObfuscator getIntegerObfuscator() {
        return integerObfuscator;
    }

    /**
     * Gets the generic control flow obfuscator
     *
     * @return the generic control flow obfuscator object
     */
    public GenericControlFlowObfuscator getControlFlowObfuscator() {
        return controlFlowObfuscator;
    }

    /**
     * Gets the generic obfuscator technique wrapper
     *
     * @return the generic obfuscator technique wrapper object
     */
    public GenericObfuscatorTechniques getObfuscatorTechniques() {
        return obfuscatorTechniques;
    }

    /**
     * Replaces the key of the given mapping with the value of the given mapping
     * in the given <code>IClass</code> object. Only the functions of the
     * <code>IClass</code> object are iterated, since this is part of the
     * generic class. Additional properties need to be iterated in the language
     * specific class.
     *
     * @param classObject the class to iterate
     * @param mapping the mapping to use during the iteration
     * @return the altered
     */
    public IClass remapFunctions(IClass classObject, Map<String, String> mapping) {
        //Iterate through the mapping
        for (Map.Entry<String, String> entry : mapping.entrySet()) {
            //Get the old name, which is the key in the mapping
            String oldName = entry.getKey();
            //Get the new name, which is the value in the mapping
            String newName = entry.getValue();

            //Iterate through all functions
            for (IFunction function : classObject.getFunctions()) {
                //Get the body
                String body = function.getBody();
                //Replace all occurences of the old name with the new name
                body = body.replace(oldName, newName);
                //Set the new body
                function.setBody(body);
            }
        }
        //Return the class object with changes (if any)
        return classObject;
    }
}
