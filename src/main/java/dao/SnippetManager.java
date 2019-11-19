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
package dao;

import exception.JsonFolderNotFoundException;
import exception.JsonParseException;
import exception.SnippetAlreadyExistsException;
import exception.SnippetNotFoundException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.snippet.Snippet;
import model.snippet.SnippetConstants;
import org.json.JSONObject;

/**
 * This class manages all loaded snippets as a static instance. As such, the
 * snippets can be loaded once (i.e. during the start-up of Genesis), after
 * which they're all accessible from the RAM, leading to much faster results
 * when searching through all snippets.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class SnippetManager {

    /**
     * The list with all snippets
     */
    private static List<Snippet> snippets = new ArrayList<>();

    /**
     * A list with strings (each entry is equal to
     * <code>snippet.toString()</code>) that is used to check if a snippet is
     * already present in the <code>snippets</code> list
     */
    private static List<String> snippetHashes = new ArrayList<>();

    /**
     * Adds a snippet to the internal snippets list
     *
     * @param snippet the snippet to add
     * @throws SnippetAlreadyExistsException is thrown if there already exists a
     * snippet with the ID of the snippet that is to be added
     */
    public static void addSnippet(Snippet snippet) throws SnippetAlreadyExistsException {
        //If the list of hashes already contains the hash, then thrown an exception
        if (snippetHashes.contains(snippet.toString())) {
            throw new SnippetAlreadyExistsException("The snippet (id: " + snippet.getId() + ") already exists!");
        }
        //If the snippet hash did not exist, the snippet is added to the list of hashes to avoid future duplicates
        snippetHashes.add(snippet.toString());
        //Additionally, the complete snippet is added to the list of loaded snippets (in RAM)
        snippets.add(snippet);
    }

    /**
     * Returns all loaded snippets in a list
     *
     * @return return all loaded snippets in a list
     */
    public static List<Snippet> getSnippets() {
        return snippets;
    }

    /**
     * Loads all snippets from the disk, as specified in the
     * <code>SnippetLoader</code>
     *
     * @throws JsonFolderNotFoundException if the folder where the JSON files
     * are stored cannot be found
     * @throws JsonParseException if a JSON file cannot be parsed properly
     */
    public static void loadSnippets() throws JsonFolderNotFoundException, JsonParseException {
        //Create a new instance of the snippet loader
        SnippetLoader loader = new SnippetLoader();
        //Load all snippes, and store the result in the snippets list
        snippets = loader.loadSnippets();
        //Reset the list of snippet hashes
        snippetHashes = new ArrayList<>();
        //Add hashes of all loaded snippets to the hashes list, which is later used to avoid adding duplicate snippets
        for (Snippet snippet : snippets) {
            snippetHashes.add(snippet.toString());
        }
    }

    /**
     * Gets a snippet from the list of loaded snippets that matches the unique
     * id
     *
     * @param id the id of the snippet to match
     * @return the snippet that corresponds with the id
     * @throws SnippetNotFoundException is thrown when there is no snippet
     * loaded that corresponds with the given id
     * @throws JsonParseException if the snippet's JSON is malformed
     * @throws JsonFolderNotFoundException if the JSON folder cannot be found
     */
    public static Snippet getSnippet(String id) throws SnippetNotFoundException, JsonParseException, JsonFolderNotFoundException {
        //Iterate through all snippets
        for (Snippet snippet : snippets) {
            //If the given ID matches the ID of a snippet (disregarding the casing), a new instance of the snippet is returned
            if (snippet.getId().equalsIgnoreCase(id)) {
                //Creates a snippet loader instance
                SnippetLoader snippetLoader = new SnippetLoader();
                //Return a snippet object, based on the given ID
                return snippetLoader.loadSnippet(id);
            }
        }
        //If all snippets are iterated, but no match is found, an exception is thrown
        throw new SnippetNotFoundException("No snippet found for the given ID: " + id);
    }

    /**
     * Parses a JSONObject into a <code>Snippet</code>, which is then returned.
     * Note that it is NOT added to the list of snippets inside this class (the
     * <code>SnippetManager</code>)!
     *
     * @param jsonSnippet the snippet in JSON format
     * @return the instance of the <code>Snippet</code> that was parsed
     * @throws JsonParseException is thrown if the JSON is either malformed or
     * an unimplemented language is used
     */
    public static Snippet parseSnippet(JSONObject jsonSnippet) throws JsonParseException {
        //Create a new snippet loader instance
        SnippetLoader snippetLoader = new SnippetLoader();
        //Return a snippet object, based on the provided JSON input
        return snippetLoader.parseSnippet(jsonSnippet);
    }

    /**
     * Saves a snippet to the disk in the snippet folder (as specified in
     * <code>SnippetConstants.SNIPPET_FOLDER</code>).
     *
     * @param jsonSnippet the snippet data to save
     * @param fileName the file name of the snippet to save
     * @throws IOException is thrown if an error occurs during the writing of
     * the file
     */
    public static void saveSnippetToDisk(String jsonSnippet, String fileName) throws IOException {
        try {
            //Creates a new buffered writer (minimises system calls to write the data, thus minimising the server load) object, based on the snippet file that resides in the snippet folder
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(SnippetConstants.SNIPPET_FOLDER + fileName + ".json"), true));
            //Writes the output
            bw.write(jsonSnippet);
            //Adds a newline
            bw.newLine();
            //Closes the writer
            bw.close();
        } catch (IOException ex) {
            //If an error occurs, a matching error message is thrown
            throw new IOException("Unable to write '" + fileName + ".json' to " + SnippetConstants.SNIPPET_FOLDER + "! Check if the required permissions are granted before you try again.");
        }
    }
}
