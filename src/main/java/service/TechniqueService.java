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
package service;

import exception.TechniqueNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.mitre.Technique;

/**
 * This services contains all functions that have to do with
 * <code>Technique</code>s.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class TechniqueService {

    /**
     * Gets all techniques that are present in the <code>Technique</code>
     * enumeration
     *
     * @return all techniques in a list object
     */
    public List<Technique> getAllTechniques() {
        return Arrays.asList(Technique.values());
    }

    /**
     * Converts all given strings into techniques
     *
     * @param techniqueStrings the list of strings to convert
     * @return a list of techniques
     * @throws TechniqueNotFoundException if there is no match for the given
     * string, this exception is thrown
     */
    public List<Technique> getTechniquesFromStrings(List<String> techniqueStrings) throws TechniqueNotFoundException {
        //Store the current technique to possibly use it in an error message
        String technique = "";
        try {
            //Create a new list of techniques
            List<Technique> techniques = new ArrayList<>();
            //Iterate through all given strings
            for (String techniqueString : techniqueStrings) {
                //Set the current technique to use in the error message if the convesion goes wrong
                technique = techniqueString;
                //Add the technique to the list
                techniques.add(Technique.valueOf(techniqueString));
            }
            //Return all techniques
            return techniques;
        } catch (IllegalArgumentException ex) {
            //Throw an error message if the technique cannot be found
            throw new TechniqueNotFoundException("Cannot find the given technique: " + technique);
        }

    }
}
