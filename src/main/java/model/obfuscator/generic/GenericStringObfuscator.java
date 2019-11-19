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

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.language.ClassConstants;
import model.language.IClass;
import model.language.IFunction;

/**
 * This class contains generic methods that specifically relate to strings.
 * These methods can be used during the obfuscation process to simplify adding
 * new methods in a given language. This class is NOT language specific, as it
 * is accessible via the <em>GenericObfuscator</em> class, which is extended in
 * the language specific classes.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class GenericStringObfuscator {

    /**
     * The Base64 encoder that is used to encode strings
     */
    private Encoder encoder;

    /**
     * Creates an instance of this object. The purpose of this class is to
     * obfuscate strings based on one or more provided <code>IClass</code>
     * objects.
     */
    public GenericStringObfuscator() {
        encoder = Base64.getEncoder();
    }

    /**
     * Insert garbage code in every function
     *
     * @param classObject the class to insert the code in
     * @param loc the lines of code (loc) that are possibly added
     * @param amount the amount of additions that should be done if an insertion
     * is done
     * @return the modified <code>IClass</code> object
     */
    public IClass insertCode(IClass classObject, List<String> loc, int amount) {
        //TODO avoid sending a LOC list, as this can be generated in this class(?)
        //Creates a secure random object
        SecureRandom random = new SecureRandom();
        //Iterates through all functions within the given class object
        for (IFunction function : classObject.getFunctions()) {
            //Initiate the new body as an empty string, to which new lines are appended
            String newBody = "";
            //The boolean which decides if code is inserted at a given point
            boolean insertLine;
            //The scanner reads the whole function body
            Scanner scanner = new Scanner(function.getBody());
            //Iterate through each line 
            while (scanner.hasNextLine()) {
                //Get a single line
                String line = scanner.nextLine();
                //Loop as many lines of code as amount is in size
                for (int i = 0; i < amount; i++) {
                    //Set the insert line boolean to either true or false (making the addition of a line roughly 50% of the size of amount, but not always the same
                    insertLine = random.nextBoolean();
                    //If the boolean is true and the provided list is not empty, a new line of code is added to the function's body
                    if (insertLine && loc.size() > 0) {
                        //Get a random index
                        int randomIndex = random.nextInt(loc.size());
                        //Add the random line of code at the random index to the body of the function
                        newBody += loc.get(randomIndex);
                        //Remove the entry at the given index to avoid reusing it
                        loc.remove(randomIndex);
                    }
                }
                //Add a newline character, since it is removed by the scanner
                newBody += line + "\n";
            }
            //Close the scanner object
            scanner.close();
            //Set the new body in the function
            function.setBody(newBody);
        }
        //Return the changed class object
        return classObject;
    }

    /**
     * Concatenate a string, as is shown in the example below.
     *
     * The original string is: <code>"abc"</code>
     *
     * The concatenated version differs: <code>("a" + "b" + "c")</code>
     *
     * @param value the string to obfuscate
     * @param stringEnclosing the enclosing of the string. Examples are
     * <code>"</code> or <code>'</code>
     * @param concatenator the sign used to concatenate strings, in most
     * languages the <code>+</code> is used, but different languages use
     * different methods
     * @return the concatenated string
     */
    public String concatenateString(List<String> value, String stringEnclosing, String concatenator) {
        //Create a new string builder object
        StringBuilder result = new StringBuilder();
        //Append an opening bracket
        result.append(ClassConstants.BRACKET_OPEN);
        //Iterate through the list of strings
        for (String string : value) {
            //Enclose the string between the givne enclosing, after which a space, the given concatenator and another space are added
            result.append(stringEnclosing + string + stringEnclosing + ClassConstants.SPACE + concatenator + ClassConstants.SPACE);
        }
        //Remove the redundant comma and space from the last argument
        int index = result.lastIndexOf(concatenator);
        if (index != -1) {
            result.delete(index, (index + 2));
        }
        //Add the closing bracket
        result.append(ClassConstants.BRACKET_CLOSE);
        //Return the string
        return result.toString();
    }

    /**
     * Generates a list of random strings, which are unique within the given
     * list. This function can be used to create strings for variable names,
     * since all names are unique, no duplicates can occur.
     *
     * @param amount the amount of random strings
     * @return the list of random strings, where the size of the list equals
     * <code>amount</code>.
     */
    public List<String> generateRandomStrings(int amount) {
        //Create an array list to store the output in
        List<String> output = new ArrayList<>();
        //Check if the amount is less than zero, if so: return the empty list
        if (amount < 0) {
            return output;
        }
        //Iterate as many times as requested
        for (int i = 0; i < amount; i++) {
            //Create a random string
            String randomString = generateRandomString(20);
            //If the string is already present in the list, decrease i with one, causing the next loop to try again
            if (output.contains(randomString)) {
                i--;
            } else { //if the string is not present, add it to the output list
                output.add(randomString);
            }
        }
        //Return the output
        return output;
    }

    /**
     * Returns a random string based on the dictionary
     * <code>abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ</code>. The
     * length of the string is equal to at least 10 characters, with a maximum
     * of the given length (<code>lengthIndication</code>) plus 10.
     *
     * @param lengthIndication the length that is roughly requested. The
     * returned string is maximally 15 characters longer
     * @return a random string which is equal to at least 10 and maximally equal
     * to the value of <code>lengthIndication</code> plus 10.
     */
    public String generateRandomString(int lengthIndication) {
        /**
         * Ensure that no negative value is used. If this is the case, simply
         * set the value to zero, since the additional 10 random characters that
         * are generated will suffice in this case
         */
        if (lengthIndication < 0) {
            lengthIndication = 0;
        }
        //Create the dictionary variable
        String dictionary = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Create the output variable
        String output = "";
        //Create a new secure random object
        SecureRandom random = new SecureRandom();
        //Get the length, with 10 as a minimum value
        int length = random.nextInt(lengthIndication) + 10;
        //Iterate for the given length
        for (int i = 0; i < length; i++) {
            //Get a random value that is within the bounds of the given dictionary
            int randomValue = random.nextInt(dictionary.length());
            //Append the character from the dictionary to the output
            output += dictionary.substring(randomValue, randomValue + 1);
        }
        //Return the output
        return output;
    }

    /**
     * Encodes the given string into the base64 representation of said string
     *
     * @param value the string to encode
     * @return the base64 encoded string
     */
    public String base64EncodeString(String value) {
        return new String(encoder.encode(value.getBytes()));
    }

    /**
     * Get the strings from a given string. The string matches this regular
     * expression <code>([\s\S]*?)</code> between the provided
     * <code>enclosing</code> strings.
     *
     * @param enclosing the encoding of a string, which is commonly done with
     * the use of <code>"</code> or <code>'</code>
     * @param body the string to search through
     * @return the matches, if no matches are found, an empty list is returned
     */
    public List<String> getStrings(String enclosing, String body) {
        //Create a list that contains all matches
        List<String> matches = new ArrayList<>();
        //Use lazy/ungreedy flag to be able to find more than one string on a single line
        Pattern pattern = Pattern.compile(enclosing + "([\\s\\S]*?)" + enclosing);
        //Match the pattern on the given body
        Matcher matcher = pattern.matcher(body);
        //Iterate through all matches
        while (matcher.find()) {
            //Get the match
            String rawMatch = matcher.group();
            //Remove the quotes around it
            String match = rawMatch.substring(1, rawMatch.length() - 1);
            //Add the match
            matches.add(match);
        }
        //Return all matches
        return matches;
    }

    /**
     * Gets all strings from each function within the <code>IClass</code>
     * object. A string is the text in between the provided
     * <code>enclosing</code>.
     *
     * @param enclosing the enclosing of a string, which is commonly done with
     * the use of <code>"</code> or <code>'</code>
     * @param iClass the class whose functions need to be iterated
     * @return the function name as a key and a list of all strings as value.
     * Note that the map can be empty, if there are no matches
     */
    public List<String> getStrings(IClass iClass, String enclosing) {
        //Create an array list that contains all strings that are found
        List<String> strings = new ArrayList<>();
        //Iterate through all functions
        for (IFunction function : iClass.getFunctions()) {
            //Get all strings from the function's body
            List<String> stringsFromBody = getStrings(enclosing, function.getBody());
            //Add all strings into the list
            strings.addAll(stringsFromBody);
        }
        //Return all strings
        return strings;
    }
}
