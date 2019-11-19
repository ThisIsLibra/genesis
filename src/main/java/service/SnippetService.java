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
import exception.JsonFolderNotFoundException;
import exception.JsonParseException;
import exception.SnippetAlreadyExistsException;
import exception.SnippetNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.language.enums.Architecture;
import model.language.enums.Language;
import model.snippet.Snippet;
import org.json.JSONObject;

/**
 * This service is to be used to interact with the <code>SnippetManager</code>
 * class. The reason for this is the possible addition of logic based on the
 * results that are returned by the <code>SnippetManager</code>. This class can
 * contain additional methods that manipulate the results that are returned by
 * the <code>SnippetManager</code>.
 *
 * In short, the generic funcions reside within the <code>SnippetManager</code>
 * whilst specific functions reside in this service class.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class SnippetService {

    /**
     * Loads all snippets from the disk, as specified in the
     * <code>SnippetLoader</code>
     *
     * @throws JsonFolderNotFoundException if the folder where the JSON files
     * are stored cannot be found
     * @throws JsonParseException if a JSON file cannot be parsed properly
     */
    public void loadSnippets() throws JsonFolderNotFoundException, JsonParseException {
        SnippetManager.loadSnippets();
    }

    /**
     * Adds a snippet to the internal snippets list
     *
     * @param snippet the snippet to add
     * @throws SnippetAlreadyExistsException is thrown when the snippet that is
     * to be added, already exists
     */
    public void addSnippet(Snippet snippet) throws SnippetAlreadyExistsException {
        SnippetManager.addSnippet(snippet);
    }

    /**
     * Returns all loaded snippets in a list
     *
     * @return return all loaded snippets in a list
     */
    public List<Snippet> getSnippets() {
        return SnippetManager.getSnippets();
    }

    /**
     * Get a single <code>Snippet</code> object if it matches the given id. Note
     * that the id of each snippet is unique, as duplicates are not loaded.
     *
     * @param id the id to match
     * @return the snippet that corresponds with the snippet
     * @throws SnippetNotFoundException is thrown when the id does not match any
     * of the loaded snippets
     * @throws JsonParseException if the JSON file cannot be parsed (i.e. it is
     * malformed)
     * @throws JsonFolderNotFoundException if the JSON folder cannot be found
     */
    public Snippet getSnippet(String id) throws SnippetNotFoundException, JsonParseException, JsonFolderNotFoundException {
        return SnippetManager.getSnippet(id);
    }

    /**
     * Creates a snippet based on the JSON string, which is then saved to the
     * disk (persisting it across reboots) and added to the list of snippets
     * that resides in memory (making it directly available).
     *
     * @param jsonSnippet the JSON blob to parse into a <code>Snippet</code>
     * @throws JsonParseException if the parsing is impossible because the JSON
     * is malformed or the used language is not supported by Genesis
     * @throws IOException if the persistence method encounters an error
     * @throws SnippetAlreadyExistsException if the snippet already exists
     */
    public void createSnippet(String jsonSnippet) throws JsonParseException, IOException, SnippetAlreadyExistsException {
        //Creates a snippet object, based on the given string
        JSONObject snippetJsonObject = new JSONObject(jsonSnippet);
        //Parses the snippet in the snippet manager
        Snippet snippet = SnippetManager.parseSnippet(snippetJsonObject);
        //Adds the snippet to the list that resides in the RAM
        SnippetManager.addSnippet(snippet);
        //Saves the snippet to the disk
        SnippetManager.saveSnippetToDisk(jsonSnippet, snippet.getId());

    }

    /**
     * Gets all architectures that can be used in a snippet
     *
     * @return a list object with all architectures in it
     */
    public List<Architecture> getArchitectures() {
        return Arrays.asList(Architecture.values());
    }

    /**
     * Gets a list of all implemented languages
     *
     * @return a list object of all implemented languages in Genesis
     */
    public List<Language> getLanguages() {
        List<Language> output = new ArrayList<>();
        output.addAll(Arrays.asList(Language.values()));
        return output;
    }
}
