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

/**
 * This class contains generic functions that can be used in any language. To
 * suit the need of any language, only the bare minimum requirements are
 * implemented within this class. Additional methods should be implemented in
 * the language specific implementation.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public abstract class GenericVariable {

    /**
     * The name of the variable
     */
    private String name;

    /**
     * The body is equal to either the instantation or the (type)declaration
     */
    private String body;

    public GenericVariable(String name, String body) {
        //Sets the name
        this.name = name;
        //Sets the body
        this.body = body;
    }

    /**
     * Gets the name of the variable
     *
     * @return the name of the variable
     */
    public String getName() {
        return name;
    }

    /**
     * Gets either the instantation or the (type)declaration
     *
     * @return the instantation or the (type)declaration
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the name of the variable
     *
     * @param name the new name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the body of the variable (the declaration or the value assignment)
     *
     * @param body the body to be set
     */
    public void setBody(String body) {
        this.body = body;
    }

}
