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

import dao.generic.IClassJsonParser;
import dao.javascript.JavaScriptJsonParser;
import dao.powershell.PowershellJsonParser;
import dao.vba.VbaJsonParser;
import exception.AlteratorNotFoundException;
import exception.JsonParseException;
import exception.ObfuscatorNotFoundException;
import model.alterator.IAlterator;
import model.alterator.javascript.JavaScriptAlterator;
import model.alterator.powershell.PowershellAlterator;
import model.alterator.vba.VbaAlterator;
import model.language.enums.Language;
import model.obfuscator.javascript.JavaScriptObfuscatorHandler;
import model.obfuscator.vba.VbaObfuscatorHandler;
import model.obfuscator.IObfuscatorHandler;
import model.obfuscator.powershell.PowershellObfuscatorHandler;

/**
 * This class serves as a single entry point to obtain any language specific
 * instance of an object. This makes it easier to add new languages to any part
 * of Genesis. Additionally, it eases the usage of all the language specific
 * versions by simplifying the code: add the language in the methods in this
 * class after the language specific classes are made, and that should be it!
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class LanguageService {

    /**
     * Gets a language specific <code>IClassJsonParser</code> instance for the
     * given language
     *
     * @param language the language to instantiate the parser for
     * @return the language specific parser object
     * @throws JsonParseException is thrown when there is no parser for the
     * requested language
     */
    public IClassJsonParser getJsonParser(Language language) throws JsonParseException {
        //Returns a language specific JSON parser, based on the given language
        switch (language) {
            case JAVASCRIPT:
                return new JavaScriptJsonParser();
            case POWERSHELL:
                return new PowershellJsonParser();
            case VBA:
                return new VbaJsonParser();
            default:
                throw new JsonParseException("The given JSON string is either malformed or uses a language (" + language + ") that is not implemented in Genesis.");
        }
    }

    /**
     * Gets a language specific <code>IAlternator</code> instance for the given
     * language
     *
     * @param language the language to instantiate the alterator for
     * @return the language specific alterator object
     * @throws AlteratorNotFoundException is thrown when there is no alterator
     * for the given language
     */
    public IAlterator getAlterator(Language language) throws AlteratorNotFoundException {
        //Returns a language specific alterator, based on the given language
        switch (language) {
            case JAVASCRIPT:
                return new JavaScriptAlterator();
            case POWERSHELL:
                return new PowershellAlterator();
            case VBA:
                return new VbaAlterator();
            default:
                throw new AlteratorNotFoundException("No alterator found for the specified language: " + language.toString());
        }
    }

    /**
     * Gets an obfuscator instance based on the provided language
     *
     * @param language the language to create an obfuscator instance for
     * @return an obfuscator instance for the specified language
     * @throws ObfuscatorNotFoundException is thrown if the given language is
     * not supported
     */
    public IObfuscatorHandler getObfuscator(Language language) throws ObfuscatorNotFoundException {
        //Returns a language specific obfuscator handler, based on the given language
        switch (language) {
            case JAVASCRIPT:
                return new JavaScriptObfuscatorHandler();
            case POWERSHELL:
                return new PowershellObfuscatorHandler();
            case VBA:
                return new VbaObfuscatorHandler();
            default:
                throw new ObfuscatorNotFoundException("No obfuscator can be found for the language: " + language.toString());
        }
    }

}
