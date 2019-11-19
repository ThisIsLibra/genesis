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

import dao.generic.IClassJsonParser;
import exception.JsonFolderNotFoundException;
import exception.JsonParseException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.language.enums.Language;
import model.snippet.Snippet;
import model.snippet.SnippetConstants;
import org.json.*;
import service.LanguageService;

/**
 * This class loads the snippets from the disk into snippet objects. This class
 * is to be used with the <code>SnippetManager</code>, which is a class with
 * static functions and variables. Therefore, the list of snippets resides in
 * memory and is only resident once within the memory of Genesis. This allows
 * the loading to be done once and only to be done again when an additional
 * snippet is placed within the snippet folder. Regardless of the amount of
 * requests to the list of snippets, only the one within the memory is accessed,
 * resulting in a fast response from Genesis.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class SnippetLoader {

    /**
     * The description of the snippets that are loaded (in JSON format) are
     * stored in this list. This way, duplicates are not loaded twice
     */
    private List<String> loadedSnippets;

    /**
     * Create an SnippetLoader object, which can load all snippets that are
     * within the <code>SnippetConstants.SNIPPET_FOLDER</code> folder on the
     * disk.
     */
    public SnippetLoader() {
        loadedSnippets = new ArrayList<>();
    }

    /**
     * Loads a single snippet based on the ID. The file name is equal to the ID
     * of the snippet plus ".json".
     *
     * @param snippetId the id of the snippet to load
     * @return the loaded snippet
     * @throws JsonParseException if the json file cannot be parsed correctly
     * @throws JsonFolderNotFoundException if the json file cannot be found
     */
    protected Snippet loadSnippet(String snippetId) throws JsonParseException, JsonFolderNotFoundException {
        //The folder in which all the JSON files reside (in the root of the file system)
        File jsonFolder = new File(SnippetConstants.SNIPPET_FOLDER);
        //Checks if the folder exists or if the folder is a file
        if (!jsonFolder.exists()) {
            throw new JsonFolderNotFoundException("Unable to load the JSON folder because it does not exist!");
        }
        if (jsonFolder.isFile()) {
            throw new JsonFolderNotFoundException("Unable to load the JSON folder because it is a file!");
        }

        //Listing all files in the folder
        File[] jsonFiles = jsonFolder.listFiles();
        for (File jsonFile : jsonFiles) {
            try {
                //Ignores the ".git" directory and all files that are within it
                if (jsonFile.getAbsolutePath().contains(".git") || !jsonFile.getName().equalsIgnoreCase(snippetId + ".json")) {
                    continue;
                }

                //Load the file from the disk
                JSONObject jsonObject = loadFileFromDisk(jsonFile);
                //Return the parsed value
                return parseSnippet(jsonObject);
            } catch (IOException ex) {
                throw new JsonParseException("Unable to load " + jsonFile.getAbsolutePath() + "!");
            }
        }
        throw new JsonParseException("Unable to find a Snippet file for the given Snippet ID (" + snippetId + ")!");
    }

    /**
     * Loads all snippets that reside within
     * <code>SnippetConstants.SNIPPET_FOLDER</code> folder in the file system.
     * All files are parsed with the corresponding parser and returned within a
     * list.
     *
     * @return a list of all loaded snippets
     * @throws JsonFolderNotFoundException is thrown if the
     * <code>SnippetConstants.SNIPPET_FOLDER</code> folder cannot be found or if
     * the folder is actually a file
     * @throws JsonParseException is thrown if a JSON file cannot be parsed with
     * one of the existing parsers
     */
    protected List<Snippet> loadSnippets() throws JsonFolderNotFoundException, JsonParseException {
        //To avoid duplicates, the variables are instantiated
        List<Snippet> snippets = new ArrayList<>();
        loadedSnippets = new ArrayList<>();
        //The folder in which all the JSON files reside (in the root of the file system)
        File jsonFolder = new File(SnippetConstants.SNIPPET_FOLDER);
        //Checks if the folder exists or if the folder is a file
        if (!jsonFolder.exists()) {
            throw new JsonFolderNotFoundException("Unable to load the JSON folder because it does not exist!");
        }
        if (jsonFolder.isFile()) {
            throw new JsonFolderNotFoundException("Unable to load the JSON folder because it is a file!");
        }

        //Listing all files in the folder
        File[] jsonFiles = jsonFolder.listFiles();
        for (File jsonFile : jsonFiles) {
            //Ignores the ".git" directory and all files that are within it
            if (jsonFile.getAbsolutePath().contains(".git")) {
                continue;
            }
            try {
                //Load the file from the disk
                JSONObject jsonObject = loadFileFromDisk(jsonFile);
                Snippet snippet = parseSnippet(jsonObject);
                //The loaded snippets are checked: if the newly loaded snippet already exists within the list, then it is skipped
                if (!loadedSnippets.contains(snippet.toString())) {
                    //Since the snippet is not loaded yet, it should be added to avoid duplicates of this snippet in the future
                    loadedSnippets.add(snippet.toString());
                    //The final list of all snippets should also contain this snippet
                    snippets.add(snippet);
                }
            } catch (IOException ex) {
                throw new JsonParseException("Unable to load " + jsonFile.getAbsolutePath());
            }
        }
        return snippets;
    }

    /**
     * Parses a JSONObject into a <code>Snippet</code>.
     *
     * @param jsonObject the Snippet object to parse
     * @return the parsed result in a <code>Snippet</code> object
     * @throws JsonParseException is thrown if the JSONObject could not be
     * parsed
     */
    protected Snippet parseSnippet(JSONObject jsonObject) throws JsonParseException {
        //Gets the language field from the class, which is stored as a value from the Language enumeration
        Language language = Language.valueOf(jsonObject.getJSONObject("class").getString("language"));
        //Instantiates the language service to obtain a language specific instance of a class later on
        LanguageService languageService = new LanguageService();
        //Gets the language specific parser that matches with this language
        IClassJsonParser iClassJsonParser = languageService.getJsonParser(language);
        //Returns the parsed result in the form of a Snippet object
        return iClassJsonParser.parse(jsonObject);
    }

    /**
     * Loads a file from the disk and creates a JSON object from it.
     *
     * @param file The file to be read
     * @return the content of the file in the form of a JSON object
     * @throws FileNotFoundException is thrown if the file cannot be found
     * @throws IOException is thrown if something goes wrong when accessing the
     * file
     */
    private JSONObject loadFileFromDisk(File file) throws FileNotFoundException, IOException {
        //Try to open the file using a file reader in a buffered reader to minimise the amount of system calls (and thus load on the system)
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            //Creates a new string builder object
            StringBuilder sb = new StringBuilder();
            //Reads a line from the buffered reader
            String line = br.readLine();

            //As long as the line is not null, it means that there is line
            while (line != null) {
                //The current line is appended
                sb.append(line);
                //A line separator is added based on the operating system
                sb.append(System.lineSeparator());
                //The next line is read
                line = br.readLine();
            }
            //Return the string in the form of a JSON object
            return new JSONObject(sb.toString());
        }
    }
}
