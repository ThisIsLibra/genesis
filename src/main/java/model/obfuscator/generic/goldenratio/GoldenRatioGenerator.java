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
package model.obfuscator.generic.goldenratio;

import java.util.Random;

/**
 * This class is used to generate a <code>GoldenRatio</code> object. When the
 * ratio of two quanitities (i.e. integers or doubles) is the same as the ratio
 * of the stum of the larger of the two.
 *
 * The creation of such as an object should be done to replace one value
 * (although said value might occur more than once in the code where the value
 * is replaced). In the language specific obfuscator, the code to calculate the
 * golden ratio (which is the same as is present in this class) should also be
 * implemented, since it needs to be present in the final output.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis] & Mike
 */
public class GoldenRatioGenerator {

    public GoldenRatio generate() {
        int maximumIterations = new Random().nextInt(280) + 20;
        double fibonacciEntry1 = 1;
        double fibonacciEntry2 = 1;
        double temp = 0;
        for (int i = 0; i < maximumIterations; i++) {
            temp = fibonacciEntry2;
            fibonacciEntry2 += fibonacciEntry1;
            fibonacciEntry1 = temp;
        }
        double value = fibonacciEntry2 / fibonacciEntry1;
        return new GoldenRatio(value, maximumIterations);
    }
}
