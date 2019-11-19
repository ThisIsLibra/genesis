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
package model.mitre;

import java.util.List;

/**
 * This class contains the name of a tactic, as well as a list of techniques
 * that is used within said technique. A list of all tactics can be found here:
 * https://attack.mitre.org/tactics/enterprise/
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class Tactic {

    /**
     * The tactic's name
     */
    private String name;

    /**
     * The techniques that are used within the specified technique
     */
    private List<Technique> techniques;

    /**
     * Creates a tactic object
     *
     * @param name the name of the tactic
     * @param techniques the corresponding techniques
     */
    public Tactic(String name, List<Technique> techniques) {
        //Sets the name
        this.name = name;
        //Sets the techniques
        this.techniques = techniques;
    }

    /**
     * Gets the name of the tactic
     *
     * @return the tactic's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the techniques that correspond with this tactic
     *
     * @return all used techniques
     */
    public List<Technique> getTechniques() {
        return techniques;
    }
}
