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
package model.language;

/**
 * The interface of a new variable. A new language should be implemented within
 * a new package named <code>model.language.*</code>, where the asterisk is the
 * language name that is implemented by the classes that reside within the
 * package. The <code>model.language.generic</code> package contains generic
 * classes. The newly implemented class should implement this interface and
 * extend the <code>GenericVariable.java</code> class.
 *
 * Note that this class contains functionality that is required for all
 * supported languages. Additional fields should be created in the language
 * specific implementation.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public interface IVariable {

    /**
     * Gets the name of the variable
     *
     * @return the variable's name
     */
    public String getName();

    /**
     * Sets the name of the variable
     *
     * @param name the new name to set
     */
    public void setName(String name);

    /**
     * Gets the body of the variable. This could be the type declaration or the
     * value assignment
     *
     * @return the body
     */
    public String getBody();

    /**
     * Sets the body of the variable (the declaration or the value assignment)
     *
     * @param body the body to be set
     */
    public void setBody(String body);

    /**
     * Returns the variable as a string, ready to be used within an
     * <code>IClass</code> object. This function should be implemented in the
     * language specific class, since the layout of a variable differs per
     * language.
     *
     * @return the variable as a string
     */
    public String build();
}
