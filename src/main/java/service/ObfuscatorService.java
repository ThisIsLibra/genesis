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
package service;

import java.util.Arrays;
import java.util.List;
import model.obfuscator.generic.ObfuscatorProfile;

/**
 * A service that handles all functions that relate directly to the obfuscator
 * and are exposed via the API. Indirect exposure (such as building a snippet)
 * should be done via the corresponding <code>BuildSnippet</code> controller and
 * service.
 *
 * @author Max 'Libra' Kersten (@LibraAnalysis)
 */
public class ObfuscatorService {

    /**
     * Returns a list of all obfuscator profiles that are within the
     * <code>model.obfuscator.generic.ObfuscatorProfile.java</code> enum.
     *
     * @return all obfuscator profiles that are present in Genesis
     */
    public List<ObfuscatorProfile> getObfuscatorProfiles() {
        return Arrays.asList(ObfuscatorProfile.values());
    }

}
