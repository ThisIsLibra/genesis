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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.language.ClassConstants;
import model.language.IClass;
import model.language.IFunction;
import model.language.IVariable;
import model.language.javascript.JavaScriptClass;
import model.language.javascript.JavaScriptFunction;
import model.obfuscator.generic.GenericObfuscator;
import model.obfuscator.generic.magicsquare.MagicSquare;

/**
 * The language specific string obfuscator for the JavaScript langauge. Only
 * actions which target strings should be placed here.
 *
 * @author Max 'Libra' Kersten (@LibraAnalysis)
 */
public class JavaScriptObfuscator extends GenericObfuscator {

    /**
     * The language specific enclosing for strings
     */
    protected final String ENCLOSING = "\"";

    /**
     * The start of the language specific call to decode a base64 string
     */
    protected final String BASE64_CALL = "atob(";

    /**
     * The language specific function to convert an integer into a character
     */
    protected final String SPLIT_CALL = "String.fromCharCode(";

    /**
     * Encodes all strings in the class with the base64 encoding
     *
     * @param classObject the class object to obfuscate
     * @return the obfuscated class object
     */
    protected IClass encodeStrings(IClass classObject) {
        //Save all strings that reside within the given class
        List<String> strings = super.getStringObfuscator().getStrings(classObject, ENCLOSING);

        //Map each string to a the encoded string
        Map<String, String> mapping = new HashMap<>();

        for (String string : strings) {
            //Encode the string
            String encodedString = BASE64_CALL + ENCLOSING + super.getStringObfuscator().base64EncodeString(string) + ENCLOSING + ClassConstants.BRACKET_CLOSE;
            //Add quotes for the replace
            string = ENCLOSING + string + ENCLOSING;
            //Add the mapping
            mapping.put(string, encodedString);
        }

        //Replace the old name with the new name (replace the string with the function call)
        classObject = super.remapFunctions(classObject, mapping);

        //Get the script
        String script = ((JavaScriptClass) classObject).getScript();
        //Get the strings from the script
        List<String> entryPointStrings = super.getStringObfuscator().getStrings(ENCLOSING, script);

        //Iterate through all strings
        for (String string : entryPointStrings) {
            //Encode the string
            String encodedString = BASE64_CALL + ENCLOSING + super.getStringObfuscator().base64EncodeString(string) + ENCLOSING + ClassConstants.BRACKET_CLOSE;
            //Add quotes to fully replace the string
            string = ENCLOSING + string + ENCLOSING;
            //Replace all occurences
            script = script.replace(string, encodedString);
        }
        //Set the body within the class object
        ((JavaScriptClass) classObject).setScript(script);
        //Iterate through all variables
        for (IVariable variable : classObject.getVariables()) {
            //Search the body of each variable for strings
            List<String> variableStrings = super.getStringObfuscator().getStrings(ENCLOSING, variable.getBody());
            //Iterate through all variable strings
            for (String string : variableStrings) {
                //Variables are only encoded once every time encodeStrings is called
                if (string.contains(BASE64_CALL)) {
                    continue;
                }
                //Encode the string in a runtime decode call
                String encodedString = BASE64_CALL + ENCLOSING + super.getStringObfuscator().base64EncodeString(string) + ENCLOSING + ClassConstants.BRACKET_CLOSE;
                //Add quotes to replace the string
                string = ENCLOSING + string + ENCLOSING;
                //Replace all occurences
                variable.setBody(variable.getBody().replace(string, encodedString));
            }
        }
        //Return the class object
        return classObject;
    }

    /**
     * Splits a string per character, which is shown as a integer instead of a
     * character
     *
     * @param string the string to obfuscate
     * @return the obfuscated string
     */
    protected String splitStringCharacterCodes(String string) {
        //Create a new list that contains the 
        List<String> parsedString = new ArrayList<>();
        char[] characters = string.toCharArray();
        //Loop through all characters
        for (int i = 0; i < characters.length; i++) {
            //Get the character value as integer, out of which a character is obtained during runtime
            parsedString.add(SPLIT_CALL + ((int) characters[i]) + ClassConstants.BRACKET_CLOSE);
        }
        //Return the concatenated string
        return super.getStringObfuscator().concatenateString(parsedString, "", ClassConstants.PLUS);
    }

    /**
     * Splits the provided string into characters, which are concatenated during
     * runtime
     *
     * @param string the string to split
     * @return a list, where each item is a character, surrounded by the
     * <code>ENCLOSING</code>'s value
     */
    protected String splitStringCharacters(String string) {
        //Create a string with all characters of the string in it
        List<String> parsedString = new ArrayList<>();
        //Create a character array of the given string
        char[] characters = string.toCharArray();
        //Loop through the character array per character
        for (int i = 0; i < characters.length; i++) {
            //Add the used enclosing, as that is required for the concatenation
            parsedString.add(ENCLOSING + characters[i] + ENCLOSING);
        }
        //Concatenate the string and return the end result
        return super.getStringObfuscator().concatenateString(parsedString, "", ClassConstants.PLUS);
    }

    /**
     * Splits a string into a a string that contains function calls to magic
     * squares to obtain the character value, which are converted back during
     * runtime
     *
     * @param classObject the class object to add the magic square functions to
     * @param string the string to obfuscate
     * @return the obfuscated string
     */
    protected String splitStringMagicSquares(IClass classObject, String string) {
        //Convert the string into a character array
        char[] characters = string.toCharArray();
        //Initialise the list
        List<String> parsedString = new ArrayList<>();

        //If they are, loop through each character of the string that needs to be parsed
        for (int i = 0; i < characters.length; i++) {
            //Create a magic square with a maximum size of 50 (meaning 49 or 51 at most, as only odd sized squares are generated)
            MagicSquare magicSquare = super.getObfuscatorTechniques().getMagicSquare(new SecureRandom().nextInt(10) + 1);
            //Get the difference between the the integer value of the character and the value of the magic square when it is read. This is used later on to get the correct value back
            int difference = characters[i] - magicSquare.getValue();
            //Create the function object for the magic square
            IFunction magicSquareFunction = createMagicSquareFunction(classObject, magicSquare);
            //Add the function to the class object
            classObject.addFunction(magicSquareFunction);
            //Get the function call, including brackets, to the magic square function
            String magicSquareFunctionCall = magicSquareFunction.getName() + ClassConstants.BRACKETS;
            //Get the string to replace the original string with. The string looks like (magicSquareFunctionCall + difference). If the difference is negative, the minus overrules plus.
            String characterReplacement = ClassConstants.BRACKET_OPEN + magicSquareFunctionCall + ClassConstants.SPACE + ClassConstants.PLUS + difference + ClassConstants.BRACKET_CLOSE;
            //Add conversion from integer to character with the replacement value to the result list
            parsedString.add(SPLIT_CALL + characterReplacement + ClassConstants.BRACKET_CLOSE);
        }
        //Return the end result in a single string
        return super.getStringObfuscator().concatenateString(parsedString, "", ClassConstants.PLUS);
    }

    /**
     * Creates an <code>IFunction</code> object which contains the magic square
     * function object
     *
     * @param classObject the class object to check if the used function name is
     * already in use
     * @param magicSquare the magic square to add to the class object
     * @return the function object for the magic square
     */
    private IFunction createMagicSquareFunction(IClass classObject, MagicSquare magicSquare) {
        //Get a list of unique strings, 25 will do fine
        List<String> uniqueStrings = super.getStringObfuscator().generateRandomStrings(25);
        //Generate unique name for function
        String functionName = uniqueStrings.get(0);
        //Remove the used string, as it is not unique anymore
        uniqueStrings.remove(functionName);
        //The magic square string
        String magicSquareString = "";
        //Obtain the magic square
        int[][] magicArray = magicSquare.getMagicSquare();
        //Create the two dimensional array in Javascript
        for (int row = 0; row < magicArray.length; row++) {
            magicSquareString += "    [";
            for (int column = 0; column < magicArray.length; column++) {
                magicSquareString += magicArray[row][column] + ", ";
            }
            magicSquareString = magicSquareString.substring(0, magicSquareString.length() - 2) + "]" + ",\n"; //removing the redundant space and comma at the last entry
        }
        magicSquareString = magicSquareString.substring(0, magicSquareString.length() - 2) + "\n"; //Remove redundant comma
        //Generate body with random names
        List<String> randomNames = super.getStringObfuscator().generateRandomStrings(3);
        String items = randomNames.get(0);
        String value = randomNames.get(1);
        String i = randomNames.get(2);
        String body = "var " + items + " = [\n";
        body += magicSquareString.substring(0, magicSquareString.length() - 1); //remove the comma and the newline of the last value of the two dimensional array
        //TODO Insert multiple read methods, as now the same method is used during every read operation
        body += "];\n"
                + "var " + value + " = 0;\n"
                + "for (var " + i + " = 0; " + i + " < " + items + ".length; " + i + "++) {\n"
                + "    " + value + " += " + items + "[" + i + "][0];\n"
                + "}\n"
                + "return " + value + ";";
        //The magic square function is added
        IFunction magicSquareFunction = new JavaScriptFunction(functionName, new HashMap<String, String>(), body);
        //Add IFunction to the IClass object, note the unique name
        while (classObject.containsFunction(magicSquareFunction)) {
            magicSquareFunction.setName(uniqueStrings.get(0)); //some other name
            uniqueStrings.remove(0);
        }
        return magicSquareFunction;
    }

    /**
     * //TODO implement this function to add more methods to read a magic
     * square
     *
     * Generates a method to get the magic constant
     *
     * @return a method to read the given magic square
     */
    private String getMagicConstant() {
        /**
         * Option 1: loop through [i][x], where x is a constant between 0 and
         * the size of the magic square
         */
        /**
         * Option 2: loop through [x][i], where is x is a constant between 0 and
         * the size of the magic square
         */
        /**
         * Option 3: use formula: n[(n^2+1)/2] to calculate the magic constant
         */
        return null;
    }

    /**
     * Generates random (dead) code, which can later be inserted as garbage
     * code.
     *
     * @param amount the amount of unique names that are present in the returned
     * list
     * @return a list filled with garbage code, where each list entry is a
     * single piece of garbage code.
     */
    protected List<String> generateCode(int amount) {
        //Get random strings equal to the given amount times two, since two strings are used for a single string
        List<String> uniqueVariableNames = super.getStringObfuscator().generateRandomStrings(amount * 2);
        //Create an array list to store the result in
        List<String> loc = new ArrayList<>();
        //Loop through the givne amount, note that i steps with 2 instead of 1
        for (int i = 0; i < uniqueVariableNames.size(); i = i + 2) {
            //Gets the variable name from the random string list
            String name = uniqueVariableNames.get(i);
            //Add the line of code, which contains the variable declaration and instantiation within one entry in the list
            loc.add("var " + name + ClassConstants.SEMICOLON + ClassConstants.NEW_LINE + name + ClassConstants.SET_EQUALS + ENCLOSING + uniqueVariableNames.get(i + 1) + ENCLOSING + ClassConstants.SEMICOLON + ClassConstants.NEW_LINE);
        }
        //Return all lines of code
        return loc;
    }
}
