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
package model.obfuscator.generic.magicsquare;

/**
 * This class contains a magic square object. The magic square itself is present
 * as a two dimensional integer array. The value and size of the magic square
 * are also stored in this class.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis] & Mike
 */
public class MagicSquare {

    /**
     * The magic square as a two dimensional array
     */
    private int[][] magicSquare;

    /**
     * The value of the magic square
     */
    private int value;

    /**
     * The size of the magic square
     */
    private int squareSize;

    public MagicSquare(int[][] magicSquare, int value, int squareSize) {
        //Sets the two dimensional array
        this.magicSquare = magicSquare;
        //Sets the value
        this.value = value;
        //Sets the square size
        this.squareSize = squareSize;
    }

    /**
     * Get the magic square as a two dimensional array
     *
     * @return the magic square as a two dimensional array
     */
    public int[][] getMagicSquare() {
        return this.magicSquare;
    }

    /**
     * Get the value of the magic square
     *
     * @return the value of the magic square
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets the size of the square
     *
     * @return the size of the square
     */
    public int getSquareSize() {
        return this.squareSize;
    }
}
