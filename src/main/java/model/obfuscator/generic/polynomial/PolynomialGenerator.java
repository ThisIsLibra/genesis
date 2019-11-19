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
package model.obfuscator.generic.polynomial;

import java.security.SecureRandom;

/**
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis] & Mike
 */
public class PolynomialGenerator {

    private SecureRandom random;
    private final int MAX_VALUE = 10001; //The upper bound is exclusive

    public Polynomial generate() {
        random = new SecureRandom();
        int length = random.nextInt(15) + 5;
        int[] formulaCoefficients = new int[length];
        int[] formulaPowers = new int[length];
        int[] deriativeCoefficients = new int[length];
        int[] deriativePowers = new int[length];

        for (int i = 0; i < length; i++) {
            //Get a random number that is at least 1 and maximally MAX_VALUE + 1
            int formulaCoefficient = random.nextInt(MAX_VALUE) + 1;
            //The chance that the number is a negative one, is 50% based on the the outcome of a random boolean
            if (random.nextBoolean()) {
                formulaCoefficient = formulaCoefficient * -1;
            }
            //The first coefficient of this formula is then set
            formulaCoefficients[i] = formulaCoefficient;

            //The value of the power is then calculated, which is ranges between 2 and MAX_VALUE + 2
            //The reason to use two as a minimum is the fact that the values in this array should always be more than 0 (to avoid "divide by zero" errors)
            int formulaPower = random.nextInt(MAX_VALUE) + 2;
            //The power of the i-th number is then stored in the respective array
            formulaPowers[i] = formulaPower;

            //The deriative coefficient is then calculated
            int deriativeCoeffcient = formulaCoefficient * formulaPower;
            //The deriative coefficient is then set
            deriativeCoefficients[i] = deriativeCoeffcient;

            //The deriative power is equal to the original power minus one
            int derativePower = formulaPower - 1;
            //The deriative power is then set
            deriativePowers[i] = derativePower;
        }
        //After all arrays have been filled with the correct numbers, the object is then made and returned
        return new Polynomial(formulaCoefficients, formulaPowers, deriativeCoefficients, deriativePowers);
    }
}
