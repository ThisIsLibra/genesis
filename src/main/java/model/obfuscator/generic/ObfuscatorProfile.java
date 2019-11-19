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

/**
 * Obfuscation profiles are used when requested a sample via the API. The
 * profiles serve as an abstract layer between the implemented obfuscation
 * techniques and used techniques. As the techniques can be used iteratively and
 * in combination, specifying all techniques in the API would take a lot of
 * time. The purpose of Genesis is to make this process simple, hence the
 * profiles.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public enum ObfuscatorProfile {
    NONE,
    LOW,
    MEDIUM,
    HIGH
}
