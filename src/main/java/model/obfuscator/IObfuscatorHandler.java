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
package model.obfuscator;

import model.language.IClass;
import model.obfuscator.generic.ObfuscatorProfile;

/**
 * Any language specific implementation of the <code>IObfuscatorHandler</code>
 * interface should only alter the given <code>IClass</code> object. To avoid
 * reinventing the wheel over and over, the <code>GenericObfuscator</code> class
 * exists. As a rule of thumb, every function that is not bound to a specific
 * language, should be placed in there.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public interface IObfuscatorHandler {

    /**
     * Obfuscate the given class object with the given obfuscator profile
     *
     * @param classObject the class object to obfuscate
     * @param profile the profile to use during the obfuscation
     * @return the obfuscated class
     */
    public IClass obfuscate(IClass classObject, ObfuscatorProfile profile);
}
