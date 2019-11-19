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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import exception.AlteratorNotFoundException;
import exception.JsonFolderNotFoundException;
import exception.JsonParseException;
import exception.ObfuscatorNotFoundException;
import exception.SnippetNotFoundException;
import model.alterator.IAlterator;
import model.language.IClass;
import model.language.enums.Language;
import model.snippet.SnippetAlteration;
import model.snippet.Snippet;
import model.obfuscator.IObfuscatorHandler;

/**
 * This class serves requests that are building final snippets. A final snippet
 * is one that is at least alterated and possibly also obfuscated.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class BuildSnippetService {

    /**
     * Build a snippet based on a given <code>SnippetAlteration</code> object
     * (in JSON form)
     *
     * @param snippetAlterationJson the required information to build the
     * snippet with
     * @return the specified snippet
     * @throws SnippetNotFoundException if the snippet's id cannot be found in
     * the loaded snippets list
     * @throws AlteratorNotFoundException if there is no alterator for the
     * specified language
     * @throws ObfuscatorNotFoundException if there is no obfuscator for the
     * specified language
     * @throws JsonParseException if the JSON file cannot be parsed (i.e. an the
     * JSON is malformed)
     * @throws JsonFolderNotFoundException if the folder where the JSON files
     * reside is not found
     */
    public Snippet buildSnippet(String snippetAlterationJson) throws SnippetNotFoundException, AlteratorNotFoundException, ObfuscatorNotFoundException, JsonParseException, JsonFolderNotFoundException {
        //Parse the JSON string
        SnippetAlteration snippetAlteration = parseSnippetAlteration(snippetAlterationJson);
        //Instantiate the services
        SnippetService snippetService = new SnippetService();
        LanguageService languageService = new LanguageService();
        //Get the snippet and the language
        Snippet snippet = snippetService.getSnippet(snippetAlteration.getSnippetId());
        //Gets the language of the snippet
        Language language = snippet.getClassObject().getLanguage();
        //Get the language specific alterator
        IAlterator alterator = languageService.getAlterator(language);
        //Get the alterated class object
        IClass classObject = alterator.alterate(snippet.getClassObject(), snippetAlteration);
        //Get the correct obfuscator
        IObfuscatorHandler obfuscator = languageService.getObfuscator(language);
        //Obfuscate the code (the class remains unchanged if the profile is set to NONE
        classObject = obfuscator.obfuscate(classObject, snippetAlteration.getObfuscationProfile());
        //Set the alterated (and possibly obfuscated) snippet before returning the whole snippet
        snippet.setClassObject(classObject);
        //Return the snippet newly created snippet
        return snippet;
    }

    /**
     * Parses a JSON string into a <code>SnippetAlteration</code> object
     *
     * @param json the JSON string to parse
     * @return the JSON string as a <code>SnippetAlteration</code> object
     * @throws JsonParseException is thrown when the provided JSON string does
     * not match the <code>SnippetAlteration</code> lay-out
     */
    private SnippetAlteration parseSnippetAlteration(String json) throws JsonParseException {
        try {
            //Creates a new gson object
            Gson gson = new Gson();
            //Creates a new JSON parser object
            JsonParser parser = new JsonParser();
            //Parse the given string into a JSON object
            JsonObject snippetAlterationJson = parser.parse(json).getAsJsonObject();
            //Return a SnippetAlteration object from the class
            return gson.fromJson(snippetAlterationJson, SnippetAlteration.class);
        } catch (JsonSyntaxException e) {
            //If an error occurs when parsing the JSON string, an exception is thrown
            throw new JsonParseException("The given input cannot be parsed and is likely malformed!");
        }
    }
}
