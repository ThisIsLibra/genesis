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

import java.util.HashSet;
import java.util.Set;
import model.language.IClass;
import model.language.IFunction;
import model.language.IVariable;
import model.language.enums.Architecture;
import model.language.enums.Language;
import model.mitre.Technique;

/**
 * This class contains generic functions that can be used in any language. To
 * suit the need of any language, only the bare minimum requirements are
 * implemented within this class. Additional methods should be implemented in
 * the language specific implementation.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public abstract class GenericClass {

    /**
     * The set of MITRE ATT&CK techniques
     */
    private Set<Technique> techniques;

    /**
     * The used architecture
     */
    private Architecture architecture;

    /**
     * The global variables
     */
    private Set<IVariable> variables;

    /**
     * The functions within the class
     */
    private Set<IFunction> functions;

    /**
     * The required dependencies
     */
    private Set<String> dependencies;

    /**
     * The language that is used within this instance
     */
    private Language language;

    /**
     * The constructor of the generic class extension for a language specific
     * class
     *
     * @param architecture the architecture of the given class
     * @param language the language of the given class
     */
    public GenericClass(Architecture architecture, Language language) {
        //Sets the architecture
        this.architecture = architecture;
        //Sets the language
        this.language = language;
        //Creates the dependencies set object
        this.dependencies = new HashSet<>();
        //Creates the techniques set object
        this.techniques = new HashSet<>();
        //Creates the variables set object
        this.variables = new HashSet<>();
        //Creates the functions set object
        this.functions = new HashSet<>();
    }

    /**
     * Returns the language that is used within this instance
     *
     * @return the used language
     */
    public Language getLanguage() {
        return this.language;
    }

    /**
     * Compares the architecture, function names and variable names to see if
     * the two classes contain the same information.
     *
     * @param iClass the class to compare this instance with
     * @return true if they contain the same, false if otherwise
     */
    public boolean compare(IClass iClass) {
        //Check if the architecture matches
        if (architecture.equals(iClass.getArchitecture())) {
            return true;
        }
        //Check if a variable already exists (case insensitive)
        for (IVariable variable : iClass.getVariables()) {
            for (IVariable existingVariable : variables) {
                if (existingVariable.getName().equalsIgnoreCase(variable.getName())) {
                    return true;
                }
            }
        }
        //Check if a function already exists (casea insensitive)
        for (IFunction function : functions) {
            for (IFunction existingFunction : functions) {
                if (existingFunction.getName().equalsIgnoreCase(function.getName())) {
                    return true;
                }
            }
        }
        //After all checks have been passed, the instances contain different content, thus they are not equal
        return false;
    }

    /**
     * Merges the classes if none of the variable or function names are in use.
     * Additionally, the architecture of both classes should match.
     *
     * @param iClass the class to merge
     * @return true if the merge was successful, false if otherwise. If the
     * result is false, no changes were made.
     */
    public boolean merge(IClass iClass) {
        //Check if the content of the class is the same
        if (compare(iClass)) {
            return false;
        }
        //Merge all dependencies
        dependencies.addAll(iClass.getDependencies());
        //Merge all techniques
        techniques.addAll(iClass.getTechniques());
        //Merge variables
        variables.addAll(iClass.getVariables());
        //Merge functions
        functions.addAll(iClass.getFunctions());
        //All additions have been performed succesfully, thus the function return true
        return true;
    }

    /**
     * All dependencies that are required for this class
     *
     * @return all required permissions
     */
    public Set<String> getDependencies() {
        return dependencies;
    }

    /**
     * Sets the MITRE AT&CK techniques that are used in this class
     *
     * @param techniques the techniques to set
     */
    public void setTechniques(Set<Technique> techniques) {
        this.techniques = techniques;
    }

    /**
     * All used MITRE ATT&CK techniques for the functionality in the class
     *
     * @return all used MITRE ATT&CK techniques
     */
    public Set<Technique> getTechniques() {
        return techniques;
    }

    /**
     * Gets the architecture of the class
     *
     * @return the architecture of the class
     */
    public Architecture getArchitecture() {
        return architecture;
    }

    /**
     * Adds the given variable to the list of variables. If a variable with the
     * same name (case insensitive) already exists, false is returned
     *
     * @param variable the variable to add
     * @return false if a variable already exists with the given variable's name
     * (case insensitive), true if otherwise
     */
    public boolean addVariable(IVariable variable) {
        for (IVariable currentVariable : variables) {
            if (currentVariable.getName().equalsIgnoreCase(variable.getName())) {
                return false;
            }
        }
        return variables.add(variable);
    }

    /**
     * Gets all global variables that reside within the class
     *
     * @return a set of all global variables
     */
    public Set<IVariable> getVariables() {
        return variables;
    }

    /**
     * Checks if the class already contains the given function, purely based on
     * the case insensitive name of the given function
     *
     * @param function the function to check
     * @return true if the class contains the function, false if otherwise
     */
    public boolean containsFunction(IFunction function) {
        //Iterate through all functions
        for (IFunction iteratedFunction : functions) {
            //If the function name matches (disregarding the casing) with the givne function, true is returned
            if (function.getName().equalsIgnoreCase(iteratedFunction.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Add a function to the list of functions. If the function name (case
     * insensitive) is already in use, false is returned. The function's
     * dependencies and techniques are added to the list within the class.
     *
     * @param function the function to add
     * @return false if a function with the same name (case insensitive) already
     * exists, true otherwise
     */
    public boolean addFunction(IFunction function) {
        //If the function already exists (based on the function name), the method returns false
        if (containsFunction(function)) {
            return false;
        }
        //If the function does not exist in this class, all dependencies, techniques are added to the class
        dependencies.addAll(function.getDependencies());
        //The function is added to the function list
        functions.add(function);
        //Everything is set, so the addition is succesful, meaning true needs to be returned
        return true;
    }

    /**
     * Get all functions that reside within the class
     *
     * @return a set of all functions
     */
    public Set<IFunction> getFunctions() {
        return functions;
    }
}
