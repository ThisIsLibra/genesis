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
package dao.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.mitre.Technique;
import model.snippet.KeyValuePair;
import model.snippet.Snippet;
import model.snippet.SnippetConstants;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This class is to be used together with the <code>IClassJsonParser</code>
 * interface for a language specific parser implementation. This class contains
 * functions that are language agnostic and simplify the creation of a parser
 * for a new class.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public abstract class GenericJsonParser {

    /**
     * Parses the <code>information</code> JSON object into a snippet. Note that
     * this snippet does not have an <code>IClass</code> instance set. This
     * should be done within the language specific parser.
     *
     * @param json the <code>information</code> JSON object
     * @return a snippet with the information fields filled in, without an
     * <code>IClass</code> instance set.
     */
    public Snippet parseSnippetInformation(JSONObject json) {
        //Gets a list of key value pair objects
        List<KeyValuePair> alterators = getAlterators(json.toString());
        //Gets the information-object from the snippet
        json = json.getJSONObject("information");
        //Get the title from the information object
        String title = json.getString("title");
        //Get the description from the information object
        String description = json.getString("description");
        //Get the author from the information object
        String author = json.getString("author");
        //Get the date from the information object
        String date = json.getString("date");
        //Return a new snippet based on this information (excluding the IClass object, since this is language specific)
        return new Snippet(title, description, author, date, alterators);
    }

    /**
     * Parses a JSON array into a set of techniques
     *
     * @param json the JSON array to be parsed
     * @return a set of techniques. Note that this set is possibly empty, if no
     * techniques are present within the JSON array.
     */
    public Set<Technique> getTechniques(JSONArray json) {
        //Creates the set (automatic removal of duplicates) in which all techniques will be stored
        Set<Technique> techniques = new HashSet<>();
        //Iterate through all items within the JSON array
        for (int i = 0; i < json.length(); i++) {
            //Gets the string that is stored at index i
            String technique = json.getString(i);
            //Ignore any empty entries in the array
            if (!technique.isEmpty()) {
                //Add the technique to the set
                //TODO make techniques case insensitive (?)
                techniques.add(Technique.valueOf(technique));
            }
        }
        //Return all techniques
        return techniques;
    }

    /**
     * Gets all arguments from a JSON object.
     *
     * @param json the JSON object to be parsed
     * @return a mapping in which the keys are the variable names and the value
     * is the type or body of the variable (depending on the usage within the
     * JSON object and the language in which it is used). The mapping can be
     * empty if no argument are given.
     */
    public Map<String, String> getArguments(JSONObject json) {
        //Creates the map to store all argumentts in, where the variable name is the key, and the value is the value
        Map<String, String> arguments = new HashMap<>();
        //Iterate through the keyset of the JSON object
        for (String variableName : json.keySet()) {
            //Gets the variable value, based on the varaible name
            String variableValue = json.getString(variableName);
            //Store the combination in the map
            arguments.put(variableName, variableValue);
        }
        //Return the map with all arguments
        return arguments;
    }

    /**
     * Get the strings from a given string. The string matches this regular
     * expression <code>([\s\S]*?)</code> between the string
     * <code>SnippetConstants.ALTERATOR_OPEN</code> and
     * <code>SnippetConstants.ALTERATOR_CLOSE</code>.
     *
     * @param json the string to search through
     * @return the matches, if no matches are found, an empty list is returned
     */
    private List<KeyValuePair> getAlterators(String json) {
        //Creates a list of key value pairs where all matches are stored
        List<KeyValuePair> matches = new ArrayList<>();
        //Use lazy/ungreedy flag to be able to use two or more strings on a single line. Note that the strings need to be between the alterator open and close markings
        Pattern pattern = Pattern.compile(SnippetConstants.ALTERATOR_OPEN + "([\\s\\S]*?)" + SnippetConstants.ALTERATOR_CLOSE);
        //Match the pattern on the given JSON string
        Matcher matcher = pattern.matcher(json);
        //Iterate through all matches
        while (matcher.find()) {
            //Get the match
            String match = matcher.group();
            //Add the match as a new key value pair, where the match is the key and the value is empty
            matches.add(new KeyValuePair(match, ""));
        }
        //Return all key value pairs that are found
        return matches;
    }
}
