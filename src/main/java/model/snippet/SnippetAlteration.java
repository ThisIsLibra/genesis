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
package model.snippet;

import java.util.List;
import model.obfuscator.generic.ObfuscatorProfile;

/**
 * This object is received via the API in the <code>BuildService></code> and
 * contains all the required information to return an alterated and possibly
 * obfuscated snippet build.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class SnippetAlteration {

    /**
     * The snippet id to match with
     */
    private String snippetId;

    /**
     * The requested obfuscation profile
     */
    private ObfuscatorProfile profile;

    /**
     * A list of key-value pairs which are used during the alternating process
     */
    private List<KeyValuePair> keyValuePairs;

    /**
     * Creates a <code>SnippetAlteration</code> object
     *
     * @param snippetId the snippet id to match with
     * @param profile the requested obfuscation profile
     * @param keyValuePairs the list of key-value pairs to alterate the snippet
     * with
     */
    public SnippetAlteration(String snippetId, ObfuscatorProfile profile, List<KeyValuePair> keyValuePairs) {
        //Sets the snippet id
        this.snippetId = snippetId;
        //Sets the obfuscation profile
        this.profile = profile;
        //Sets the key value pairs
        this.keyValuePairs = keyValuePairs;
    }

    /**
     * Gets the snippet id
     *
     * @return the snippet id
     */
    public String getSnippetId() {
        return snippetId;
    }

    /**
     * Sets the snippet id
     *
     * @param id the id to set
     */
    public void setSnippetId(String id) {
        this.snippetId = id;
    }

    /**
     * Gets the obfuscation profile
     * @return the obfuscation profile
     */
    public ObfuscatorProfile getObfuscationProfile() {
        return profile;
    }

    /**
     * Gets the key-value pairs
     *
     * @return the key-value pairs
     */
    public List<KeyValuePair> getKeyValuePairs() {
        return keyValuePairs;
    }

    /**
     * Sets the key-value pairs
     *
     * @param alters the key-value pairs to set
     */
    public void setKeyValuePairs(List<KeyValuePair> alters) {
        this.keyValuePairs = alters;
    }
}
