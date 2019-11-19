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

import java.security.SecureRandom;

/**
 * Class to generate a <code>MagicSquare</code> object of an arbitrary size
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis] & Mike
 */
public class MagicSquareGenerator {

    /**
     * Generates a magic square with a given size
     *
     * @param size the size of the magic square. If this value is even, it is
     * either increased or decreased by one before the square is calculated
     * @return the magic square with the given size
     */
    public MagicSquare generate(int size) {
        //Square generation at this point only works using odd sizes
        if (!isOdd(size)) {
            //If the value is not odd, the value is increased or decreased by 1
            SecureRandom random = new SecureRandom();
            if (random.nextBoolean()) {
                size++;
            } else {
                size--;
            }
        }
        //The magic square is stored in a two dimensional array
        int[][] magicSquare = calculate(size);
        //The value of the square is stored in a different field for later use
        int value = calculateMagicSquareValue(magicSquare);
        //The object is returned
        return new MagicSquare(magicSquare, value, size);
    }

    /**
     * Checks if the given value is odd or even
     *
     * @param value the value to check
     * @return true if the value is odd, false if the value is even
     */
    private boolean isOdd(int value) {
        //If the given value modulo two equals zero, the value is even
        if (value % 2 == 0) {
            //Since the value is even, false is returned
            return false;
        } else {
            //If not, the value is odd, and true is returned
            return true;
        }
    }

    /**
     * Calculates the magic square. The code is taken from Brendan
     * (https://stackoverflow.com/a/36040894)
     *
     * @return a two dimensional integer array which contains the square's
     * values
     */
    private int[][] calculate(int size) {
        int[][] magicSquare = new int[size][size];
        int number = 1;
        int row = 0;
        int column = size / 2;
        int curr_row;
        int curr_col;
        while (number <= size * size) {
            magicSquare[row][column] = number;
            number++;
            curr_row = row;
            curr_col = column;
            row -= 1;
            column += 1;
            if (row == -1) {
                row = size - 1;
            }
            if (column == size) {
                column = 0;
            }
            if (magicSquare[row][column] != 0) {
                row = curr_row + 1;
                column = curr_col;
                if (row == -1) {
                    row = size - 1;
                }
            }
        }
        return magicSquare;
    }

    /**
     * Gets the value of the magic square by adding the values from the top left
     * corner downwards until the bottom left corner has been reached (iterating <code>magicSquare[i][0]<code>)
     *
     * @param magicSquare the magic square to calculate the value for
     * @return the value of the given magic square
     */
    private int calculateMagicSquareValue(int[][] magicSquare) {
        //The value, which is 0 and increased by the fields that are read
        int value = 0;
        //Read from top to bottom on one index, whilst the other stays zero. There are more methods to get the value, but all correct methods provide the same result, as such, a simple version is used here
        for (int i = 0; i < magicSquare.length; i++) {
            value += magicSquare[i][0];
        }
        //Return the value
        return value;
    }
}
