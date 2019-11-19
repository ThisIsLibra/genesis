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
package model.obfuscator.javascript;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.language.IClass;
import model.obfuscator.generic.ObfuscatorProfile;
import model.obfuscator.IObfuscatorHandler;

/**
 * The language specific obfuscator for the JavaScript language, which
 * implements the <code>IObfuscatorHandler</code> interface. In here, the given
 * obfuscation profile is 'translated' into actions for the language specific
 * obfuscator, which are then executed. At last, the modified
 * <code>IClass</code> object is returned.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class JavaScriptObfuscatorHandler implements IObfuscatorHandler {

    /**
     * Obfuscates the given <code>IClass</code> object. The amount of
     * obfuscation depends on the given <code>ObfuscatorProfile</code> value.
     *
     * @param classObject the class object to obfuscate
     * @param profile the obfuscation profile, which is used to determine the
     * level of obfuscation that will be applied to the given class object
     * @return the obfuscated <code>IClass</code> object
     */
    @Override
    public IClass obfuscate(IClass classObject, ObfuscatorProfile profile) {
        //If no obfuscation should be added, simply return the given object
        if (profile.equals(ObfuscatorProfile.NONE)) {
            return classObject;
        }
        //Create an obfuscator object for this language
        JavaScriptObfuscator obfuscator = new JavaScriptObfuscator();
        //Amount of insertions per time
        int insertionSize = 6;
        //Assume buffer size
        int bufferSize = 10000;
        //Generate code based on buffer size
        List<String> deadCode = obfuscator.generateCode(bufferSize);
        //Insert dead code
        //classObject = obfuscator.getStringObfuscator().insertCode(classObject, deadCode, insertionSize);
        //Since the upper bound is exclusive and 0 is a potential outcome, the minimum value is 1, to ensure that all content is at least encoded once
        int encodingIterations = new SecureRandom().nextInt(5) + 1;
        //Loop 
        for (int i = 0; i < encodingIterations; i++) {
            classObject = obfuscator.encodeStrings(classObject);
        }
        //Save all strings that reside within the given class' functions
        List<String> strings = obfuscator.getStringObfuscator().getStrings(classObject, obfuscator.ENCLOSING);

        //Map each string to the encoded string
        Map<String, String> mapping = new HashMap<>(); //Map<Old, New>

        //Split strings and store them in the mapping for replacement later on
        for (String string : strings) {
            //Skip empty strings
            if (string.isEmpty()) {
                continue;
            }
            //Create the parsed string variable
            String parsedString = "";
            //Obfuscate based on profile, although no check is done if the NONE value is given, as that has already been checked in the start
            switch (profile) {
                case LOW:
                    parsedString = obfuscator.splitStringCharacters(string);
                    break;
                case MEDIUM:
                    parsedString = obfuscator.splitStringCharacterCodes(string);
                    break;
                case HIGH:
                    parsedString = obfuscator.splitStringMagicSquares(classObject, string);
                    break;
            }
            //Add enclosing to the string
            string = obfuscator.ENCLOSING + string + obfuscator.ENCLOSING;
            //Store the old and new string in a mapper
            mapping.put(string, parsedString);
        }
        //Remap functions based on the mapping
        classObject = obfuscator.remapFunctions(classObject, mapping);

        //Return class object
        return classObject;
    }
}
