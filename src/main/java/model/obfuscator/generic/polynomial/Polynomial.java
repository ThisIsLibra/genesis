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

/**
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis] & Mike
 */
public class Polynomial {

    private int[] formulaCoefficients;
    private int[] formulaPowers;
    private int[] deriativeCoefficients;
    private int[] deriativePowers;

    public Polynomial(int[] formulaCoefficients, int[] formulaPowers, int[] deriativeCoefficients, int[] deriativePowers) {
        this.formulaCoefficients = formulaCoefficients;
        this.formulaPowers = formulaPowers;
        this.deriativeCoefficients = deriativeCoefficients;
        this.deriativePowers = deriativePowers;
    }

    public int[] getFormulaCoefficients() {
        return formulaCoefficients;
    }

    public int[] getFormulaPowers() {
        return formulaPowers;
    }

    public int[] getDeriativeCoefficients() {
        return deriativeCoefficients;
    }

    public int[] getDeriativePowers() {
        return deriativePowers;
    }
}
