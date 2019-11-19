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
package model.language.generic;

import java.util.Map;
import java.util.Set;
import model.language.enums.Language;

/**
 * This class contains generic functions that can be used in any language. To
 * suit the need of any language, only the bare minimum requirements are
 * implemented within this class. Additional methods should be implemented in
 * the language specific implementation.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public abstract class GenericFunction {

    /**
     * The unique dependencies of the function
     */
    private Set<String> dependencies;

    /**
     * The name of the function
     */
    private String name;

    /**
     * The arguments that the function expects in a map. The key of the map is
     * the variable's name, the value is the type. This ensures a unique
     * variable name.
     */
    private Map<String, String> arguments;

    /**
     * The body of the function, containing all logic
     */
    private String body;

    /**
     * The language of the function
     */
    private Language language;

    public GenericFunction(Language language, Set<String> dependencies, String name, Map<String, String> arguments, String body) {
        //Sets the language
        this.language = language;
        //Sets the dependencies
        this.dependencies = dependencies;
        //Sets the function name
        this.name = name;
        //Sets the arguments
        this.arguments = arguments;
        //Sets the body
        this.body = body;
    }

    /**
     * Gets all unique dependencies of this function
     *
     * @return all unique dependencies of this function
     */
    public Set<String> getDependencies() {
        return dependencies;
    }

    /**
     * Gets the function's name
     *
     * @return the function's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the function's name
     *
     * @param name the new name of the function
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The arguments the function expects, where the key is the variable name
     * and the value is the variable type (if applicable)
     *
     * @return the arguments of this function
     */
    public Map<String, String> getArguments() {
        return arguments;
    }

    /**
     * Sets the body of the function
     *
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Gets the body of the function
     *
     * @return the function's body
     */
    public String getBody() {
        return body;
    }

    /**
     * Gets the function's language
     *
     * @return the language in which the function is written
     */
    public Language getLanguage() {
        return language;
    }
}
