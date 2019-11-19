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

import exception.TacticNotFoundException;
import java.util.ArrayList;
import java.util.List;
import model.mitre.Tactic;
import model.mitre.TacticManager;

/**
 * In this class, all tactics can be accessed, via a function, by obtaining them
 * all or by searching for a name
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class TacticService {

    /**
     * Gets all tactics in a list
     *
     * @return a list with all tactics
     */
    public List<Tactic> getAllTactics() {
        List<Tactic> tactics = new ArrayList<>();
        tactics.add(TacticManager.getInitialAccess());
        tactics.add(TacticManager.getExecution());
        tactics.add(TacticManager.getPersistence());
        tactics.add(TacticManager.getPrivilegeEscalation());
        tactics.add(TacticManager.getDefenseEvasion());
        tactics.add(TacticManager.getCredentialAccess());
        tactics.add(TacticManager.getDiscovery());
        tactics.add(TacticManager.getLateralMovement());
        tactics.add(TacticManager.getCollection());
        tactics.add(TacticManager.getExfiltration());
        tactics.add(TacticManager.getCommandAndControl());
        tactics.add(TacticManager.getImpact());
        return tactics;
    }

    /**
     * Get a tactic by its name (disregarding the casing)
     *
     * @param name the name to search for (case insensitive)
     * @return the tactic that matches the name
     * @throws TacticNotFoundException is thrown when there is no match for the
     * given name
     */
    public Tactic getTacticByName(String name) throws TacticNotFoundException {
        //Create a list of tactics
        List<Tactic> tactics = getAllTactics();
        //Iterate through all tactics
        for (Tactic tactic : tactics) {
            //If the tactic name equals the given name (disregarding the casing), it is returned
            if (tactic.getName().equalsIgnoreCase(name)) {
                return tactic;
            }
        }
        //If no technique can be found, an exception is thrown
        throw new TacticNotFoundException("Unable to find a tactic which matches the name " + name);
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Initial Access</code> tactic (see
     * https://attack.mitre.org/tactics/TA0001/)
     *
     * @return the techniques corresponding with the <code>Intial Access</code>
     * tactic and the name of the tactic in a <code>Tactic</code> object.
     */
    public Tactic getInitialAccess() {
        return TacticManager.getInitialAccess();
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Execution</code> tactic (see
     * https://attack.mitre.org/tactics/TA0002/)
     *
     * @return the techniques corresponding with the <code>Execution</code>
     * tactic and the name of the tactic in a <code>Tactic</code> object.
     */
    public Tactic getExecution() {
        return TacticManager.getExecution();
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Persistance</code> tactic (see
     * https://attack.mitre.org/tactics/TA0003/)
     *
     * @return the techniques corresponding with the <code>Persistance</code>
     * tactic and the name of the tactic in a <code>Tactic</code> object.
     */
    public Tactic getPersistance() {
        return TacticManager.getPersistence();
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Privilege Escalation</code> tactic (see
     * https://attack.mitre.org/tactics/TA0004/)
     *
     * @return the techniques corresponding with the
     * <code>Privilege Escalation</code> tactic and the name of the tactic in a
     * <code>Tactic</code> object.
     */
    public Tactic getPrivilegeEscalation() {
        return TacticManager.getPrivilegeEscalation();
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Defense Evasion</code> tactic (see
     * https://attack.mitre.org/tactics/TA0005/)
     *
     * @return the techniques corresponding with the
     * <code>Defense Evasion</code> tactic and the name of the tactic in a
     * <code>Tactic</code> object.
     */
    public Tactic getDefenseEvasion() {
        return TacticManager.getDefenseEvasion();
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Credential Access</code> tactic (see
     * https://attack.mitre.org/tactics/TA0006/)
     *
     * @return the techniques corresponding with the
     * <code>Credential Access</code> tactic and the name of the tactic in a
     * <code>Tactic</code> object.
     */
    public Tactic getCredentialAccess() {
        return TacticManager.getCredentialAccess();
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Discovery</code> tactic (see
     * https://attack.mitre.org/tactics/TA0007/)
     *
     * @return the techniques corresponding with the <code>Discovery</code>
     * tactic and the name of the tactic in a <code>Tactic</code> object.
     */
    public Tactic getDiscovery() {
        return TacticManager.getDiscovery();
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Lateral Movement</code> tactic (see
     * https://attack.mitre.org/tactics/TA0008/)
     *
     * @return the techniques corresponding with the
     * <code>Lateral Movement</code> tactic and the name of the tactic in a
     * <code>Tactic</code> object.
     */
    public Tactic getLateralMovement() {
        return TacticManager.getLateralMovement();
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Collection</code> tactic (see
     * https://attack.mitre.org/tactics/TA0009/)
     *
     * @return the techniques corresponding with the <code>Collection</code>
     * tactic and the name of the tactic in a <code>Tactic</code> object.
     */
    public Tactic getCollection() {
        return TacticManager.getCollection();
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Exfiltration</code> tactic (see
     * https://attack.mitre.org/tactics/TA0010/)
     *
     * @return the techniques corresponding with the <code>Exfiltration</code>
     * tactic and the name of the tactic in a <code>Tactic</code> object.
     */
    public Tactic getExfiltration() {
        return TacticManager.getExfiltration();
    }

    /**
     * Gets the list of techniques that correspond with the
     * <code>Command & Control</code> tactic (see
     * https://attack.mitre.org/tactics/TA0011/)
     *
     * @return the techniques corresponding with the
     * <code>Command & Control</code> tactic and the name of the tactic in a
     * <code>Tactic</code> object.
     */
    public Tactic getCommandAndControl() {
        return TacticManager.getCommandAndControl();
    }

    /**
     * Gets the list of techniques that correspond with the <code>Impact</code>
     * tactic (see https://attack.mitre.org/tactics/TA0040/)
     *
     * @return the techniques corresponding with the <code>Impact</code> tactic
     * and the name of the tactic in a <code>Tactic</code> object.
     */
    public Tactic getImpact() {
        return TacticManager.getImpact();
    }
}
