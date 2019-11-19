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
package startup;

import dao.SnippetManager;
import exception.JsonFolderNotFoundException;
import exception.JsonParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Startup;
import javax.ejb.Singleton;

/**
 * This class provides two additional features: one can execute code to execute
 * after the application has been loaded, and one can execute code just before
 * the application closes.
 *
 * @author Max 'Libra' Kersten [@LibraAnalysis]
 */
@Singleton
@Startup
public class StartUp {

    /**
     * Code that is executed directly after loading the application
     */
    @PostConstruct
    public void postConstruct() {
        //Loads all snippets that reside within the snippet folder
        loadAllSnippets();
    }

    @PreDestroy
    public void preDestory() {
        //Code that is executed during the shutdown of the application
    }

    /**
     * Loads all snippets that reside in the snippet folder. Any error is
     * visible in the server's log.
     */
    private void loadAllSnippets() {
        try {
            //Loads all snippets
            SnippetManager.loadSnippets();
        } catch (JsonFolderNotFoundException ex) {
            Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JsonParseException ex) {
            Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
