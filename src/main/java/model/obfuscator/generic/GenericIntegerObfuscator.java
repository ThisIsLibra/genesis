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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains generic methods to obfuscate integers in the given
 * <code>IClass</code> object. Note that Strings can also be represented as an
 * array of integers, since a string is an array of characters, where each
 * character can be written as an integer. The better the combination of the
 * String- and Integer obfuscator of a given language, the better the
 * obfuscation is.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class GenericIntegerObfuscator {

    /**
     * Get the integers from a given string. The string matches this regular
     * expression <code>(\d+)</code> between the provided <code>enclosing</code>
     * strings.
     *
     * @param text the string to search through
     * @return the matches, if no matches are found, an empty list is returned
     */
    public List<Integer> getIntegers(String text) {
        //Create a list where all matches are stored
        List<Integer> matches = new ArrayList<>();
        //Use lazy/ungreedy flag to be able to find more than one integer on a single line
        Pattern pattern = Pattern.compile("(\\d+)");
        //Match the given string with the constructed pattern
        Matcher matcher = pattern.matcher(text);
        //Iterate through all matches
        while (matcher.find()) {
            //Get the match
            String match = matcher.group();
            //Convert the string into an integer
            matches.add(Integer.parseInt(match));
        }
        //Return all matches
        return matches;
    }
}
