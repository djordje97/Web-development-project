package projekatWeb.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import projekatWeb.dao.ConnectionMenager;

public class InitListener implements ServletContextListener {
    public void contextDestroyed(ServletContextEvent arg0)  { 
        ConnectionMenager.close();
    }

    public void contextInitialized(ServletContextEvent event)  { 
        ConnectionMenager.open();
    }
	
}
