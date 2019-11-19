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
package model.obfuscator.powershell;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.language.ClassConstants;
import model.language.IClass;
import model.language.IFunction;
import model.language.IVariable;
import model.language.powershell.PowershellClass;
import model.language.powershell.PowershellFunction;
import model.obfuscator.generic.GenericObfuscator;
import model.obfuscator.generic.magicsquare.MagicSquare;

/**
 * The language specific string obfuscator for the Powershell langauge. Only
 * actions which target strings should be placed here.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class PowershellObfuscator extends GenericObfuscator {

    /**
     * The language specific enclosing for strings
     */
    protected final String ENCLOSING = "\"";

    /**
     * The start of the language specific call to decode a base64 string
     */
    protected final String BASE64_CALL = ClassConstants.BRACKET_OPEN + "[System.Text.Encoding]::ASCII.GetString([System.Convert]::FromBase64String(";

    /**
     * The language specific function to convert an int into a character
     */
    protected final String SPLIT_CALL = "([char]";

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
            String encodedString = BASE64_CALL + ENCLOSING + super.getStringObfuscator().base64EncodeString(string) + ENCLOSING + ClassConstants.BRACKET_CLOSE + ClassConstants.BRACKET_CLOSE + ClassConstants.BRACKET_CLOSE;
            //Add quotes for the replace
            string = ENCLOSING + string + ENCLOSING;
            //Add the mapping
            mapping.put(string, encodedString);
        }

        //Replace the old name with the new name (replace the string with the function call)
        if (mapping.size() > 0) {
            classObject = super.remapFunctions(classObject, mapping);
        }

        String script = ((PowershellClass) classObject).getScript();
        List<String> entryPointStrings = super.getStringObfuscator().getStrings(ENCLOSING, script);

        for (String string : entryPointStrings) {
            //Encode the string
            String encodedString = BASE64_CALL + ENCLOSING + super.getStringObfuscator().base64EncodeString(string) + ENCLOSING + ClassConstants.BRACKET_CLOSE + ClassConstants.BRACKET_CLOSE + ClassConstants.BRACKET_CLOSE;
            //Add quotes to fully replace the string
            string = ENCLOSING + string + ENCLOSING;
            script = script.replace(string, encodedString);
        }
        //Set the body within the class object
        ((PowershellClass) classObject).setScript(script);
        //Iterate through all variables
        for (IVariable variable : classObject.getVariables()) {
            //Search the body of each variable for strings
            List<String> variableStrings = super.getStringObfuscator().getStrings(ENCLOSING, variable.getBody());
            for (String string : variableStrings) {
                //Variables are only encoded once every time encodeStrings is called
                if (string.contains(BASE64_CALL)) {
                    continue;
                }
                String encodedString = BASE64_CALL + ENCLOSING + super.getStringObfuscator().base64EncodeString(string) + ENCLOSING + ClassConstants.BRACKET_CLOSE + ClassConstants.BRACKET_CLOSE + ClassConstants.BRACKET_CLOSE;
                string = ENCLOSING + string + ENCLOSING;
                variable.setBody(variable.getBody().replace(string, encodedString));
            }
        }
        //Return the class object
        return classObject;
    }

    /**
     * Split the string into character codes (integer representation of a
     * character)
     *
     * @param string the string to split
     * @return the splitted string
     */
    protected String splitStringCharacterCodes(String string) {
        //Create a list to store the parsed string in
        List<String> parsedString = new ArrayList<>();
        //Convert the string into a character array
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
        //A list to store the end result in
        List<String> parsedString = new ArrayList<>();
        //Convert the given string into a character array
        char[] characters = string.toCharArray();
        //Iterate through the character array
        for (int i = 0; i < characters.length; i++) {
            //Add the enclosing as it is required for the concatenate function below
            parsedString.add(ENCLOSING + characters[i] + ENCLOSING);
        }
        //Concatenate the string and return the result
        return super.getStringObfuscator().concatenateString(parsedString, "", ClassConstants.PLUS);
    }

    /**
     * Splits a string into a string that uses multiple magic square functions
     * to get the interger representation of the character, which is then
     * concatenated
     *
     * @param classObject the class object that contains the string, to which
     * the magic square functions will be added
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
            String magicSquareFunctionCall = magicSquareFunction.getName();
            //Get the string to replace the original string with. The string looks like (magicSquareFunctionCall + difference). If the difference is negative, the minus overrules plus.
            String characterReplacement = ClassConstants.BRACKET_OPEN + ClassConstants.BRACKET_OPEN + magicSquareFunctionCall + ClassConstants.BRACKET_CLOSE + ClassConstants.SPACE + ClassConstants.PLUS + difference + ClassConstants.BRACKET_CLOSE;
            //Add conversion from integer to character with the replacement value to the result list
            parsedString.add(SPLIT_CALL + characterReplacement + ClassConstants.BRACKET_CLOSE);
        }
        //Return the concatenated string
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
        uniqueStrings.remove(functionName);
        String magicSquareString = "";
        int[][] magicArray = magicSquare.getMagicSquare();
        for (int row = 0; row < magicArray.length; row++) {
            magicSquareString += ClassConstants.BRACKET_OPEN;
            for (int column = 0; column < magicArray.length; column++) {
                magicSquareString += magicArray[row][column] + ", ";
            }
            magicSquareString = magicSquareString.substring(0, magicSquareString.length() - 2) + ")" + ","; //removing the redundant space and comma at the last entry
        }
        magicSquareString = magicSquareString.substring(0, magicSquareString.length() - 2) + "\n"; //Remove redundant comma
        //Generate body
        String body = "$items = ";
        body += magicSquareString.substring(0, magicSquareString.length() - 1); //remove the comma and the newline of the last value of the two dimensional array
        //TODO Insert multiple read methods, as now the same method is used during every read operation
        body += ");\n"
                + "$value = 0;\n"
                + "For ($i = 0; $i -le $items.Count - 1; $i++) {\n" //count can also be replaced with "Length" for more randomisation
                + "    $value += $items[$i][0];\n"
                + "}\n"
                + "return $value;";
        //read function here
        IFunction magicSquareFunction = new PowershellFunction(functionName, new HashMap<String, String>(), body);
        //Add IFunction to the IClass object
        while (classObject.containsFunction(magicSquareFunction)) {
            magicSquareFunction.setName(uniqueStrings.get(0)); //some other name
            uniqueStrings.remove(0);
        }
        return magicSquareFunction;
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
        //Generate a certain amount of unique strings (the requested amount times two, as two strings are used per string)
        List<String> uniqueVariableNames = super.getStringObfuscator().generateRandomStrings(amount * 2);
        //Create a list to store the output in
        List<String> loc = new ArrayList<>();
        //Iterate as many times as required, note that i is incremented with 2 instead of 1
        for (int i = 0; i < uniqueVariableNames.size(); i = i + 2) {
            //Gets a random variable that is used as the name
            String name = uniqueVariableNames.get(i);
            //Creates a variable and instantiates it based on another string (index i+1, hence the increase with 2), which is added in the output list
            loc.add("$" + name + ClassConstants.SET_EQUALS + ENCLOSING + uniqueVariableNames.get(i + 1) + ENCLOSING + ClassConstants.SEMICOLON + ClassConstants.NEW_LINE);
        }
        //Return the list of variables
        return loc;
    }
}
