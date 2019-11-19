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
package model.obfuscator.vba;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.language.ClassConstants;
import model.language.IClass;
import model.language.IFunction;
import model.language.IVariable;
import model.language.vba.VbaFunction;
import model.obfuscator.generic.GenericObfuscator;
import model.obfuscator.generic.magicsquare.MagicSquare;

/**
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class VbaObfuscator extends GenericObfuscator {

    /**
     * The language specific enclosing for strings
     */
    protected final String ENCLOSING = "\"";

    /**
     * The start of the language specific call to decode a base64 string
     */
    protected String BASE64_CALL;

    /**
     * The language specific function to convert an integer into a character
     */
    protected final String SPLIT_CALL = "Chr(";

    /**
     * A list that contains unique strings that are ready to use, generated when
     * this class is instantiated
     */
    private List<String> uniqueStrings;

    /**
     * Creates an instance of the VBA obfuscator, which creates a list of unique
     * strings in the constructor
     */
    public VbaObfuscator() {
        List<String> uniqueStrings = new ArrayList<>();

        for (int stringLength = 0; stringLength < 30; stringLength++) {
            //65d tm 90d
            for (int character = 65; character < 91; character++) {
                String toAdd = new String(new char[stringLength]).replace("\0", new String("" + (char) character));
                //String toAdd = new String("" + (char) character) * stringLength;
                if (toAdd.isEmpty()) {
                    continue;
                }
                uniqueStrings.add(toAdd);
            }
            //97d tm 122d
            for (int character = 97; character < 123; character++) {
                String toAdd = new String(new char[stringLength]).replace("\0", new String("" + (char) character));
                if (toAdd.isEmpty()) {
                    continue;
                }
                uniqueStrings.add(toAdd);
            }
        }
        /**
         * Comparator taken from Chris Mart's StackOverflow reply:
         * https://stackoverflow.com/a/52063614
         */
        Comparator c = new Comparator<String>() {
            public int compare(String s1, String s2) {
                return Integer.compare(s1.length(), s2.length());
            }
        };
        Collections.sort(uniqueStrings, c);
        this.uniqueStrings = uniqueStrings;
    }

    /**
     * Encodes all strings in the class with the base64 encoding
     *
     * @param classObject the class object to obfuscate
     * @return the obfuscated class object
     */
    protected IClass encodeStrings(IClass classObject) {
        //Create the base64 decoder function, as VBA has no default method of decoding base64 encoded strings
        IFunction base64DecodeFunction = addBase64Decoder(super.getStringObfuscator().generateRandomString(5));

        //Make sure the name of the function is not in use
        while (classObject.containsFunction(base64DecodeFunction)) {
            String base64DecodeFuncitonName = super.getStringObfuscator().generateRandomString(5);
            base64DecodeFunction.setName(base64DecodeFuncitonName);
        }

        //The base64_call variable is equal to the function, after which a bracket open is added
        BASE64_CALL = base64DecodeFunction.getName() + ClassConstants.BRACKET_OPEN;

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
        if (mapping.size() > 0) {
            classObject = super.remapFunctions(classObject, mapping);
        }

        //Iterate through all variables
        for (IVariable variable : classObject.getVariables()) {
            //Search the body of each variable for strings
            List<String> variableStrings = super.getStringObfuscator().getStrings(ENCLOSING, variable.getBody());
            for (String string : variableStrings) {
                //Variables are only encoded once every time encodeStrings is called
                if (string.contains(BASE64_CALL)) {
                    continue;
                }
                String encodedString = BASE64_CALL + ENCLOSING + super.getStringObfuscator().base64EncodeString(string) + ENCLOSING + ClassConstants.BRACKET_CLOSE + ClassConstants.BRACKET_CLOSE;
                string = ENCLOSING + string + ENCLOSING;
                variable.setBody(variable.getBody().replace(string, encodedString));
            }
        }
        //Avoid recursion by 'decoding strings in the decode function using the decode function' by adding it after everything has been changed
        classObject.addFunction(base64DecodeFunction);
        //Return the class object
        return classObject;
    }

    /**
     * Splits a string into character codes, which is then returned as a single
     * string
     *
     * @param string the string to obfuscate
     * @return the obfuscated string
     */
    protected String splitStringCharacterCodes(String string) {
        //The parsed string to be concatenated
        List<String> parsedString = new ArrayList<>();
        //The string is converted into a character array
        char[] characters = string.toCharArray();
        //Loop through all characters
        for (int i = 0; i < characters.length; i++) {
            //Get the character value as integer, out of which a character is obtained during runtime
            parsedString.add(SPLIT_CALL + ((int) characters[i]) + ClassConstants.BRACKET_CLOSE);
        }
        //Return the result as a single string
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
        //The parsed string is stored in this list
        List<String> parsedString = new ArrayList<>();
        //Convert the string into a character array
        char[] characters = string.toCharArray();
        //Iterate over all characters
        for (int i = 0; i < characters.length; i++) {
            //Add enclosing to each character, as is required for the concatenate string function below
            parsedString.add(ENCLOSING + characters[i] + ENCLOSING);
        }
        //Return the concatenated string
        return super.getStringObfuscator().concatenateString(parsedString, "", ClassConstants.PLUS);
    }

    /**
     * Splits a string into characters, into integer representations of these
     * characters, into a function that returns a certain value, plus or minus
     * another value to eventually represent the character's integer value
     *
     * @param classObject the class object to which functions are added
     * @param string the string to obfuscated
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
        //List<String> uniqueStrings = super.getStringObfuscator().generateRandomStrings(25);

        //Generate unique name for function
        //TODO fix the unique strings to always work, instead of working with a buffer, note that this is for all languages
        String functionName = uniqueStrings.get(0);
        IFunction tempFunction = new VbaFunction(ENCLOSING, functionName, new HashMap<String, String>(), ENCLOSING, ENCLOSING);
        while (classObject.containsFunction(tempFunction)) {
            uniqueStrings.remove(functionName);
            functionName = uniqueStrings.get(0);
            tempFunction.setName(functionName);
        }
        String magicSquareString = "";
        int[][] magicArray = magicSquare.getMagicSquare();
        for (int row = 0; row < magicArray.length; row++) {
            for (int column = 0; column < magicArray.length; column++) {
                magicSquareString += "    items(" + row + ", " + column + ") = " + magicArray[row][column] + "\n";
            }
        }
        //Generate body
        List<String> randomNames = super.getStringObfuscator().generateRandomStrings(3);
        String items = randomNames.get(0);
        String value = randomNames.get(1);
        String i = randomNames.get(2);
        String body = "Dim " + items + "(" + magicSquare.getSquareSize() + "," + magicSquare.getSquareSize() + ") as Integer\n";
        body += magicSquareString.substring(0, magicSquareString.length() - 1); //remove the comma and the newline of the last value of the two dimensional array
        //TODO Insert multiple read methods, as now the same method is used during every read operation
        body += "\n"
                + "Dim " + value + " As Integer\n"
                + value + " = 0\n"
                + "Dim " + i + " As Integer\n"
                + "For " + i + " = 0 to " + magicSquare.getSquareSize() + "\n"
                + "    " + value + " = " + value + " + " + items + "(" + i + ", 0)\n"
                + "Next " + i + "\n"
                + "\n"
                + functionName + " = value";
        //Read function here
        IFunction magicSquareFunction = new VbaFunction("Function", functionName, new HashMap<String, String>(), "As Integer", body);
        return magicSquareFunction;
    }

    /**
     * A method to base64 decode base64 encoded values.
     *
     * Copyright 1999, by Antonin Foller, Motobit Software, http://Motobit.cz
     *
     * Direct URL: https://www.motobit.com/tips/detpg_Base64/
     *
     * @param name the name of the base64 decode function
     * @return the function as an <code>IFunction</code> object
     */
    private IFunction addBase64Decoder(String name) {
        String dictionary = super.getStringObfuscator().generateRandomString(10);
        String argument = super.getStringObfuscator().generateRandomString(10);
        String dataLength = super.getStringObfuscator().generateRandomString(10);
        String sOut = super.getStringObfuscator().generateRandomString(10);
        String groupBegin = super.getStringObfuscator().generateRandomString(10);
        String numDataBytes = super.getStringObfuscator().generateRandomString(10);
        String CharCounter = super.getStringObfuscator().generateRandomString(10);
        String thisChar = super.getStringObfuscator().generateRandomString(10);
        String thisData = super.getStringObfuscator().generateRandomString(10);
        String nGroup = super.getStringObfuscator().generateRandomString(10);
        String pOut = super.getStringObfuscator().generateRandomString(10);
        String base64DecodeFunctionBody
                = "  Const " + dictionary + " = \"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/\"\n"
                + "  Dim " + dataLength + ", " + sOut + ", " + groupBegin + "\n"
                + "  \n"
                + "  " + argument + " = Replace(" + argument + ", vbCrLf, \"\")\n"
                + "  " + argument + " = Replace(" + argument + ", vbTab, \"\")\n"
                + "  " + argument + " = Replace(" + argument + ", \" \", \"\")\n"
                + "  \n"
                + "  " + dataLength + " = Len(" + argument + ")\n"
                + "  \n"
                + "  For " + groupBegin + " = 1 To " + dataLength + " Step 4\n"
                + "    Dim " + numDataBytes + ", " + CharCounter + ", " + thisChar + ", " + thisData + ", " + nGroup + ", " + pOut + "\n"
                + "    " + numDataBytes + " = 3\n"
                + "    " + nGroup + " = 0\n"
                + "\n"
                + "    For " + CharCounter + " = 0 To 3\n"
                + "\n"
                + "      " + thisChar + " = Mid(" + argument + ", " + groupBegin + " + " + CharCounter + ", 1)\n"
                + "\n"
                + "      If " + thisChar + " = \"=\" Then\n"
                + "        " + numDataBytes + " = " + numDataBytes + " - 1\n"
                + "        " + thisData + " = 0\n"
                + "      Else\n"
                + "        " + thisData + " = InStr(1, " + dictionary + ", " + thisChar + ", vbBinaryCompare) - 1\n"
                + "      End If\n"
                + "\n"
                + "      " + nGroup + " = 64 * " + nGroup + " + " + thisData + "\n"
                + "    Next\n"
                + "    \n"
                + "    " + nGroup + " = Hex(" + nGroup + ")\n"
                + "    \n"
                + "    " + nGroup + " = String(6 - Len(" + nGroup + "), \"0\") & " + nGroup + "\n"
                + "    \n"
                + "    " + pOut + " = Chr(CByte(\"&H\" & Mid(" + nGroup + ", 1, 2))) + _\n"
                + "      Chr(CByte(\"&H\" & Mid(" + nGroup + ", 3, 2))) + _\n"
                + "      Chr(CByte(\"&H\" & Mid(" + nGroup + ", 5, 2)))\n"
                + "    \n"
                + "    " + sOut + " = " + sOut + " & Left(" + pOut + ", " + numDataBytes + ")\n"
                + "  Next\n"
                + "\n"
                + "  " + name + " = " + sOut;
        Map<String, String> arguments = new HashMap<>();
        arguments.put(argument, "ByVal");
        IFunction base64DecodeFunction = new VbaFunction("Function", name, arguments, "As String", base64DecodeFunctionBody);
        return base64DecodeFunction;
    }

    /**
     * Generates lines of code to later insert into the script with the sole
     * purpose of cluttering the original document
     *
     * @param amount the amount of unique strings to return
     * @return a list of variable declarations and instantiations
     */
    protected List<String> generateCode(int amount) {
        //Generate twice as many strings as are requested, as each generated piece of code requires two strings from this list
        List<String> uniqueVariableNames = super.getStringObfuscator().generateRandomStrings(amount * 2);
        //Creates a list that is used to store the result in
        List<String> loc = new ArrayList<>();
        //Iterate through the request amount, note that i is incremented with 2 instead of 1
        for (int i = 0; i < uniqueVariableNames.size(); i = i + 2) {
            //Get a random string to use as the varaible's name
            String name = uniqueVariableNames.get(i);
            //Create the variable and assign a value
            loc.add("Dim " + name + " As String\n" + name + " = \"" + uniqueVariableNames.get(i + 1) + "\"\n");
        }
        //Return all created code
        return loc;
    }
}
