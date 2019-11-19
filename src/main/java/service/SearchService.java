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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import model.mitre.Tactic;
import model.mitre.Technique;
import model.snippet.Snippet;

/**
 * This class is used to search through the loaded snippets, which are present
 * in the RAM.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class SearchService {

    /**
     * This method queries all loaded snippets with the given keyword. The
     * fields which are used to search in are:
     *
     * -The snippet's ID, name, description, author, date, language. or
     * architecture
     *
     * - The snippet's techniques (can also be singular)
     *
     * - The tactic where a technique belongs to (multiple are possible, as can
     * be seen in the MITRE ATT&CK matrix)
     *
     * @param keyword the keyword to match (case insensetive)
     * @return a list of snippets which contain the keyword
     */
    public List<Snippet> getSnippetByKeyword(String keyword) {
        //Create a list where all matches are stored
        List<Snippet> searchResult = new ArrayList<>();
        //Iterate through all loaded snippets
        for (Snippet snippet : SnippetManager.getSnippets()) {
            //if the keyword (partially) matches the snippet ID, the snippet is added to the result list
            if (contains(snippet.getId(), keyword)) {
                searchResult.add(snippet);
            } else if (contains(snippet.getTitle(), keyword)) { //if the keyword (partially) matches the snippet's title, the snippet is added to the result list
                searchResult.add(snippet);
            } else if (contains(snippet.getDescription(), keyword.toLowerCase())) { //if the keyword (partially) matches the snippet's description, the snippet is added to the result list
                searchResult.add(snippet);
            } else if (contains(snippet.getAuthor(), keyword)) { //if the keyword (partially) matches the snippet's author, the snippet is added to the result list
                searchResult.add(snippet);
            } else if (contains(snippet.getDate(), keyword)) { //if the keyword (partially) matches the snippet's creation date, the snippet is added to the result list
                searchResult.add(snippet);
            } else if (contains(snippet.getClassObject().getLanguage().toString(), keyword)) { //if the keyword (partially) matches the snippet's langauge, the snippet is added to the result list
                searchResult.add(snippet);
            } else if (contains(snippet.getClassObject().getArchitecture().toString(), keyword)) { //if the keyword (partially) matches the snippet's architecture, the snippet is added to the result list
                searchResult.add(snippet);
            } else if (contains(snippet.getClassObject().getTechniques(), keyword)) { //if the keyword (partially) matches any of the snippet's techniques, the snippet is added to the result list
                searchResult.add(snippet);
            } else if (searchTactic(snippet.getClassObject().getTechniques(), keyword)) { //if the keyword (partially) matches the name of a tactic, any snippet that contains a technique that is present in that tactic, is added to the result list
                searchResult.add(snippet);
            }
        }
        return searchResult;
    }

    /**
     * Assumes the keyword parameter equals the name of a tactic, after which
     * the techniques for this tactic are compared to the techniques list (which
     * is derived from the snippet's <code>IClass</code> object).
     *
     * If any of the techniques in the snippet matches with the given tactic,
     * the value <code>true</code> is returned. If there are no matches, or if
     * the given keyword is not a valid tactic name, the value
     * <code>false</code> is returned.
     *
     * @param techniques the list of techniques to compare with the tactic
     * @param keyword the name of the tactic
     * @return true if any of the techniques match the techniques in the tactic.
     * False if there are no matches or if the keyword is not a valid tactic
     * name
     */
    private boolean searchTactic(Set<Technique> techniques, String keyword) {
        //Create an instance of the tactic service
        TacticService tacticService = new TacticService();
        //Create a list with all tactics i nit
        List<Tactic> tactics = tacticService.getAllTactics();
        //Create a list where all matched tactics will be stored
        List<Tactic> matchedTactics = new ArrayList<>();
        //Iterate through all tactics
        for (Tactic tactic : tactics) {
            //If the keyword matches (partially) with the tactic name, the tactic is added to the list of matches
            if (contains(tactic.getName(), keyword)) {
                matchedTactics.add(tactic);
            }
        }

        //Iterate through all given techniques
        for (Technique technique : techniques) {
            //Iterate through all matched tactics
            for (Tactic tactic : matchedTactics) {
                //If the tactic contains the technique, true is returned
                if (tactic.getTechniques().contains(technique)) {
                    return true;
                }
            }
        }
        //If there is no match, false is returned
        return false;
    }

    /**
     * Checks if the given technique contains the given match, regardless of the
     * casing of either parameter.
     *
     * @param techniques the technique list to check
     * @param match the match to find in the text
     * @return true if the text contains the match, false if not
     */
    private boolean contains(Set<Technique> techniques, String match) {
        //Iterate through all techniques
        for (Technique technique : techniques) {
            //if the technique, in lower case, contains the given match, in lower case, a match is found, which results in returning true
            if (technique.toString().toLowerCase().contains(match.toLowerCase())) {
                return true;
            }
        }
        //Else, no match is found and the value false should be returned
        return false;
    }

    /**
     * Checks if the given text contains the given match, regardless of the
     * casing of either parameter.
     *
     * @param text the text to check
     * @param match the match to find in the text
     * @return true if the text contains the match, false if not
     */
    private boolean contains(String text, String match) {
        //If the text in lower case contains the string to match in lower case, true is returned
        if (text.toLowerCase().contains(match.toLowerCase())) {
            return true;
        }
        //Else, there is no match, meaning false needs to be returned
        return false;
    }
}
