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

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import model.language.IClass;

/**
 * A snippet contains information about the code it contains: the title,
 * description, author and date. Additionally, an <code>IClass</code> objected
 * (named <code>classObject</code>) is embedded in the snippet. The information
 * about the snippet is also stored in JSON format in the <code>json</code>
 * field.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public class Snippet {

    /**
     * The snippet's ID (a SHA-256 hash of all, excluding the
     * <code>IClass</code> object)
     */
    private String id;

    /**
     * The title of the snippet
     */
    private String title;

    /**
     * The description of the snippet
     */
    private String description;

    /**
     * The author of the snippet
     */
    private String author;

    /**
     * The creation date of the snippet
     */
    private String date;

    /**
     * The key value pairs that are taken from the snippet's code base. This
     * field is sent to the front-end in order to be used during the build
     * process.
     */
    private List<KeyValuePair> alterators;

    /**
     * The <code>IClass</code> object that contains the code for the snippet
     */
    private IClass classObject;

    /**
     * Creates a Snippet object which contains the snippet information
     *
     * @param title the snippet's title
     * @param description the snippet's description
     * @param author the snippet's author
     * @param date the snippet's creation date
     * @param alterators the key-value pairs that need to be filled in by the
     * user before the <code>IClass</code> object is alterated
     */
    public Snippet(String title, String description, String author, String date, List<KeyValuePair> alterators) {
        //Sets the title
        this.title = title;
        //Sets the description
        this.description = description;
        //Sets the author
        this.author = author;
        //Sets the date
        this.date = date;
        //Sets the alterators
        this.alterators = alterators;
        //Calculates the snippet's ID based on the hash function
        this.id = hash();
    }

    /**
     * Gets the alterators in this snippet
     *
     * @return this snippet's alterators
     */
    public List<KeyValuePair> getAlterators() {
        return alterators;
    }

    /**
     * Sets the alterators for this snippet
     *
     * @param alterators the alterators to set
     */
    public void setAlterators(List<KeyValuePair> alterators) {
        this.alterators = alterators;
    }

    /**
     * Gets the ID of this snippet (a SHA-256 hash of all fields aside from the
     * <code>IClass</code> fields. If the SHA-256 algorithm cannot be found, a
     * random UUID is used.
     *
     * @return the ID of this snippet
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the <code>IClass</code> object
     *
     * @param classObject the <code>IClass</code> object to set
     */
    public void setClassObject(IClass classObject) {
        this.classObject = classObject;
    }

    /**
     * Gets the <code>IClass</code> object
     *
     * @return the <code>IClass</code> object that this snippet contains
     */
    public IClass getClassObject() {
        return classObject;
    }

    /**
     * Gets the title of this snippet
     *
     * @return the title of the snippet
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the description of this snippet
     *
     * @return the snippet's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the author of this snippet
     *
     * @return this snippet's author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the creation date of this snippet
     *
     * @return the snippet's creation date
     */
    public String getDate() {
        return date;
    }

    /**
     * Checks if the given snippet is equal to this one, based on the
     * <code>toString()</code> function of the snippet. Note that the casing of
     * the fields that are used in the <code>toString()</code> function is
     * irrelevant.
     *
     * @param snippet the snippet to compare to this one
     * @return true if the snippets are equal, false if they're not
     */
    public boolean isEqual(Snippet snippet) {
        //If the values of toString() of both snippets are equal, the snippet is considered equal
        if (snippet.toString().equalsIgnoreCase(toString())) {
            return true;
        }
        return false;
    }

    /**
     * Creates a SHA-256 hash based off the custom <code>toString</code> method
     * within this class. If the SHA-256 algorithm cannot be found, a random
     * UUID is returned.
     *
     * Code is partially taken from https://stackoverflow.com/a/3103722
     *
     * @return the hash of this class, used as an ID for the class.
     */
    private String hash() {
        //Try to hash this snippet's values
        try {
            //Get the SHA-256 hashing algorithm
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            //Get the toString() value of this snippet
            String text = this.toString();
            //Set the value to hash
            md.update(text.getBytes(StandardCharsets.UTF_8));
            //Hash the value
            byte[] digest = md.digest();
            //Store the value as a 64 digit hexadecimal string
            String hash = String.format("%064x", new BigInteger(1, digest));
            //Return the hash
            return hash;
        } catch (NoSuchAlgorithmException ex) {
            //If the SHA-256 exception is not found, a random UUID is returned (excluding the dashes in between the fields
            //This is only used in the case of an exception, since the hash should be based on something that is the same every time, unlike a UUID
            return java.util.UUID.randomUUID().toString().replace("-", "");
        }
    }

    /**
     * Returns a snippet in form of a string, formatted in the following way:
     * <code>"Snippet{" + "title=" + title + ", description=" + description + ", author=" + author + ", date=" + date + '}';</code>
     *
     * @return the snippet in the form of a string
     */
    @Override
    public String toString() {
        //Returns a string of this snippet based on specific fields
        return "Snippet{" + "title=" + title + ", description=" + description + ", author=" + author + ", date=" + date + '}';
    }
}
