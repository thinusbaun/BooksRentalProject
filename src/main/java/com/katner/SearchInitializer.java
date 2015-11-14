package com.katner; /**
 * Created by michal on 14.11.15.
 */

import com.katner.util.EntityManagerHelper;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class SearchInitializer implements ServletContextListener {
    public void contextInitialized(ServletContextEvent event) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(EntityManagerHelper.getEntityManager());
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Do stuff during webapp's startup.
    }

    public void contextDestroyed(ServletContextEvent event) {
        // Do stuff during webapp's shutdown.
    }

}
