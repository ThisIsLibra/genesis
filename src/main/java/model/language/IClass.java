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

import java.util.Set;
import model.language.enums.Architecture;
import model.language.enums.Language;
import model.mitre.Technique;

/**
 * The interface for a class of any language. A new language should be
 * implemented within a new package named <code>model.language.*</code>, where
 * the asterisk is the language name that is implemented by the classes that
 * reside within the package. The <code>model.language.generic</code> package
 * contains generic classes. The newly implemented class should implement this
 * interface and extend the <code>GenericClass.java</code> class.
 *
 * Note that this class contains functionality that is required for all
 * supported languages. Additional fields should be created in the language
 * specific implementation.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public interface IClass {

    /**
     * Gets the language in which the class is written
     *
     * @return the language in which the class is written
     */
    public Language getLanguage();

    /**
     * Sets the MITRE AT&CK techniques that are used in this class
     *
     * @param techniques the techniques to set
     */
    public void setTechniques(Set<Technique> techniques);

    /**
     * Get a set with all techniques that relate to the MITRE ATT&CK framework.
     *
     * @return the set with all MITRE ATT&CK techniques
     */
    public Set<Technique> getTechniques();

    /**
     * Get the architecture of the class, see the
     * <code>model.language.enums.Architecture.java</code> class for all
     * possible options.
     *
     * @return the architecture of the class
     */
    public Architecture getArchitecture();

    /**
     * Get all the dependencies that are used within the class
     *
     * @return the used dependencies
     */
    public Set<String> getDependencies();

    /**
     * Add a global variable to the class
     *
     * @param variable the variable to add
     * @return true if the addition is successful, false if it fails
     */
    public boolean addVariable(IVariable variable);

    /**
     * Get all global variables that reside within the class
     *
     * @return the global variables
     */
    public Set<IVariable> getVariables();

    /**
     * Checks if the class already contains the given function, purely based on
     * the case sensitive name of the given function
     *
     * @param function the function to check
     * @return true if the class contains the function, false if otherwise
     */
    public boolean containsFunction(IFunction function);

    /**
     * Add a function to the class. When the <code>GenericClass</code> is used,
     * all dependencies and MITRE ATT&CK techniques are automatically merged
     * with their respective fields within the <code>GenericClass</code>.
     *
     * @param function the function to add
     * @return true if the addition was successful, false if otherwise
     */
    public boolean addFunction(IFunction function);

    /**
     * Gets all functions that reside within the class
     *
     * @return a set of all functions
     */
    public Set<IFunction> getFunctions();

    /**
     * Merges the given class with this class
     *
     * @param iClass the class to merge into this instance
     * @return true if the merge is successful, false if a conflict arises
     */
    public boolean merge(IClass iClass);

    /**
     * Compares this instance with another instance based on all fields that
     * reside within <code>GenericClass</code>.
     *
     * @param iClass the class to compare this instance with
     * @return true if the instances are the same, false if otherwise
     */
    public boolean compare(IClass iClass);

    /**
     * The build function builds the class, based upon the added variables and
     * functions. When using the <code>GenericClass</code>, this function should
     * be implemented within the language specific class.
     *
     * @return the complete executable class based in a single string
     */
    public String build();
}
