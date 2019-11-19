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
package model.obfuscator.generic;

import model.obfuscator.generic.magicsquare.MagicSquare;
import model.obfuscator.generic.magicsquare.MagicSquareGenerator;
import model.obfuscator.generic.polynomial.Polynomial;
import model.obfuscator.generic.polynomial.PolynomialGenerator;

/**
 * The functions in this class are bridges between the generators and their
 * return values, thus simplifying the code when using. New methods should be
 * included in the Genesis project, after which they should be linked in this
 * class to allow easy (indirect) access in the language specific obfuscator
 * classes via the Generic String or Integer obfuscators.
 *
 * @author Max 'Libra' Kerstsen [@LibraAnalysis]
 */
public class GenericObfuscatorTechniques {

    /**
     * The generator to create <code>MagicSquare</code> objects
     */
    private MagicSquareGenerator magicSquareGenerator;

    private PolynomialGenerator polynomialGenerator;

    /**
     * Creates an object that contains all embedded techniques, which can then
     * be generated using one of the listed generators
     */
    public GenericObfuscatorTechniques() {
        //Instantiate a magic square generator object
        magicSquareGenerator = new MagicSquareGenerator();
        //Instantiate a polynomial generator object
        polynomialGenerator = new PolynomialGenerator();

    }

    /**
     * Generates a magic square with a given size
     *
     * @param size the size of the magic square. If this value is even, it is
     * either increased or decreased by one before the square is calculated
     * @return the magic square with the given size
     */
    public MagicSquare getMagicSquare(int size) {
        return magicSquareGenerator.generate(size);
    }

    /**
     * Get a polynomial object
     *
     * @return a polynomial object
     */
    public Polynomial getPolynomial() {
        return polynomialGenerator.generate();
    }
}
