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

import model.language.enums.Language;
import java.util.Map;
import java.util.Set;

/**
 * The interface of a new function. A new language should be implemented within
 * a new package named <code>model.language.*</code>, where the asterisk is the
 * language name that is implemented by the classes that reside within the
 * package. The <code>model.language.generic</code> package contains generic
 * classes. The newly implemented class should implement this interface and
 * extend the <code>GenericFunction.java</code> class.
 *
 * Note that this class contains functionality that is required for all
 * supported languages. Additional fields should be created in the language
 * specific implementation.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public interface IFunction {

    /**
     * Gets all the dependencies of this function. Due to the usage of the
     * <code>Set</code>, there are no duplicates.
     *
     * @return all unique dependencies
     */
    public Set<String> getDependencies();

    /**
     * The function's name
     *
     * @return the name of the function
     */
    public String getName();

    /**
     * Sets the function's name
     *
     * @param name the new name
     */
    public void setName(String name);

    /**
     * The arguments that are passed to the function. The key of the mapping is
     * the variable name, the value is the type (if required for the language,
     * otherwise an empty string is to be provided).
     *
     * @return the arguments for the function, where the mapping's key equals
     * the argument's name and the value the type of the argument
     */
    public Map<String, String> getArguments(); //Map<type, name>

    /**
     * The body of the function: all the code that is to be executed resides
     * here.
     *
     * @return the content of the function
     */
    public String getBody();

    /**
     * The body of the function is set with this function.
     *
     * @param body the value that should be set as the function's body
     */
    public void setBody(String body);

    /**
     * The programming/scripting language of the function
     *
     * @return the programming/scripting language in which the function is
     * written
     */
    public Language getLanguage();

    /**
     * The build function builds the function, based upon the required
     * components (such as the name, the arguments and the body). When using the
     * <code>GenericFunction</code>, this function should be implemented within
     * the language specific class.
     *
     * @return the complete executable function in a single string
     */
    public String build();
}
