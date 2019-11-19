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
package model.language.enums;

/**
 * The target architecture for the given snippet, where both the operating
 * system and it's CPU architecture is taken into account. If no version of the
 * operating system is given, all versions are included.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
public enum Architecture {
    ANY,
    WINDOWS,
    WINDOWS_X86,
    WINDOWS_X64,
    WINDOWS_XP_X86,
    WINDOWS_VISTA_X86,
    WINDOWS_VISTA_X64,
    WINDOWS_7_X86,
    WINDOWS_7_X64,
    WINDOWS_8_X86,
    WINDOWS_8_X64,
    WINDOWS_81_X86,
    WINDOWS_81_X64,
    WINDOWS_10_X86,
    WINDOWS_10_X64,
    LINUX,
    LINUX_X86,
    LINUX_X64,
    MACOS,
    MACOS_X86,
    MACOS_X64
}
