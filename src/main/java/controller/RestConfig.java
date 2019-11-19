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
package controller;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * This class serves as a single declaration of the application path within the
 * API. Due to this reference, all other classes can simply provide a path
 * relative to this.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
@ApplicationPath("api/v1")
public class RestConfig extends Application {
}
