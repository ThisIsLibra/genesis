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

import dao.SnippetManager;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import model.language.enums.Language;
import model.mitre.Technique;
import model.snippet.Snippet;
import model.snippet.SnippetConstants;

/**
 * This class is used to obtain status information of the Genesis instance. An
 * example is the current version of Genesis.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class StatusService {

    /**
     * Gets the amount of loaded snippets from the snippet manager
     *
     * @return the amount of loaded snippets
     */
    public int getSnippetCount() {
        //Gets the size of the list of snippets that resides in the RAM
        return SnippetManager.getSnippets().size();
    }

    /**
     * Returns the version of Genesis
     *
     * @return Genesis' version
     */
    public String getVersion() {
        //Returns the version of Genesis as defined in the snippet constants
        return SnippetConstants.VERSION;
    }

    public String getUsedTechniqueCount() {
        //Get all loaded snippets
        List<Snippet> snippets = SnippetManager.getSnippets();
        //Create a set (automatically removes duplicates) to store all used techniques in
        Set<Technique> usedTechniqueCount = new HashSet<>();
        //Iterate through all snippets
        for (Snippet snippet : snippets) {
            //Add all techniques of each snippet to the set (duplicates are not added in the set)
            usedTechniqueCount.addAll(snippet.getClassObject().getTechniques());
        }
        //Return the amount of snippets within the set as a string
        return Integer.toString(usedTechniqueCount.size());
    }

    /**
     * Gets the technique that is used in the most snippets. First, all snippets
     * are iterated, during which each used technique is counted. Afterwards,
     * the highest score is returned
     *
     * @return the name and count of the technique in the following format:
     * <code>technique.toString()</code>
     */
    public String getMostUsedTechnique() {
        //Get all loaded snippets from the RAM
        List<Snippet> snippets = SnippetManager.getSnippets();
        //Create a mapping, where the technique is the key and the count of this technique is the value (a boxed integer)
        Map<Technique, Integer> techniqueMapping = new HashMap<>();
        //Loop through all snippets
        for (Snippet snippet : snippets) {
            //For each technique in the snippet
            for (Technique technique : snippet.getClassObject().getTechniques()) {
                //If the mapping contains the key, add the count, otherwise add it with the value 1 (as it is the first time it is encountered)
                if (techniqueMapping.containsKey(technique)) {
                    Integer count = techniqueMapping.get(technique);
                    count++;
                    techniqueMapping.put(technique, count);
                } else {
                    techniqueMapping.put(technique, 1);
                }
            }
        }
        //Declare the top result
        Technique topTechnique = Technique.AccessTokenManipulation; //assign a random value to avoid an error in the return statement
        Integer topCount = 0;
        //The boolean is used to detect if the topTechnique variable has been changed. If not, there is no top technique, meaning that a different value needs to be returned in the end
        boolean changed = false;
        //Get the top scoring result
        for (Map.Entry<Technique, Integer> entry : techniqueMapping.entrySet()) {
            //Get the current technique
            Technique technique = entry.getKey();
            //Get the count of the technique
            Integer count = entry.getValue();
            //If the count of this technique is higher than the one that is thus far known as the top one, then replace it
            if (count > topCount) {
                //The changed boolean is set to true, meaning that the result is based on snippets, instead of the set value above
                changed = true;
                topCount = count;
                topTechnique = technique;
            }
        }
        //If this boolean is true, the result is based on techniques that are used in snippets
        if (changed) {
            return topTechnique.toString();
        } else { //If the boolean is false, the result is not based on techniques that are used within snippets, thus a dash is to be returned
            return "-";
        }
    }

    /**
     * Gets the language that is used in the most snippets. First, all snippets
     * are iterated, during which each used language is counted. Afterwards, the
     * highest score is returned
     *
     * @return the name and count of the technique in the following format:
     * <code>language.toString() + " (" + count + " occurences)"</code>
     */
    public String getMostUsedLanguage() {
        //Get all snippets
        List<Snippet> snippets = SnippetManager.getSnippets();
        //Create a mapping where the key is the language and the value is a boxed integer that represents the count
        Map<Language, Integer> languageMapping = new HashMap<>();
        //Loop through all snippets
        for (Snippet snippet : snippets) {
            //Get the snippet's language
            Language language = snippet.getClassObject().getLanguage();
            //If the mapping already contains the key, then raise the value with one, else add it with the value 1, as it is the first time the language is encountered
            if (languageMapping.containsKey(language)) {
                Integer count = languageMapping.get(language);
                count++;
                languageMapping.put(language, count);
            } else {
                languageMapping.put(language, 1);
            }

        }
        //Declare the top result
        Language topLanguage = Language.JAVASCRIPT; //assign a random value to avoid an error in the return statement
        Integer topCount = 0;
        boolean changed = false;
        //Get the top scoring result
        for (Map.Entry<Language, Integer> entry : languageMapping.entrySet()) {
            //Get the langauge and count of an entry
            Language language = entry.getKey();
            Integer count = entry.getValue();
            //If the count of the currently iterating langauge is higher than the top language (so far), then the values are overwritten
            if (count > topCount) {
                //Set the boolean's value to true, to indicate that the resulting top language is based on snippets, instead of the default value
                changed = true;
                topCount = count;
                topLanguage = language;
            }
        }
        //If this boolean is true, the result is based on snippets
        if (changed) {
            return topLanguage.toString();
        } else { //In any other case, there were no snippets to iterate through
            return "-";
        }

    }
}
