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

/**
 * This object contains all required information to easily implement the golden
 * ratio obfuscation method in any language specific obfuscator. Use the method
 * that is given in <code>GoldenRatioGenerator</code> to see how the generation
 * process works.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis] & Mike
 */
public class GoldenRatio {

    private double value;
    private double fibonacciEntry1;
    private double fibonacciEntry2;
    private int maximumIterations;

    public GoldenRatio(double value, int maximumIterations) {
        this.value = value;
        this.fibonacciEntry1 = 1;
        this.fibonacciEntry2 = 1;
        this.maximumIterations = maximumIterations;
    }

    public double getValue() {
        return value;
    }

    public double getFibonacciEntry1() {
        return fibonacciEntry1;
    }

    public double getFibonacciEntry2() {
        return fibonacciEntry2;
    }

    public int getMaximumIterations() {
        return maximumIterations;
    }

}
