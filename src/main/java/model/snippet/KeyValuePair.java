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

/**
 * This class serves as a Java version of a key-value pair, in which both are
 * represented as strings.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class KeyValuePair {

    /**
     * The key
     */
    private String key;

    /**
     * The value
     */
    private String value;

    /**
     * Creates a key-value pair
     *
     * @param key the key
     * @param value the value
     */
    public KeyValuePair(String key, String value) {
        //Sets the key
        this.key = key;
        //Sets the value
        this.value = value;
    }

    /**
     * Gets the key
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key
     *
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Gets the value
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value
     *
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

}
