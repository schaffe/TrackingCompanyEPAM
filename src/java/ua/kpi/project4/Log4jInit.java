package ua.kpi.project4;

import java.io.File;
import javax.servlet.*;
import org.apache.log4j.PropertyConfigurator;

/**
 * Class provides initialize of Log4j Logger
 */
public class Log4jInit {

    public void contextInitialized(ServletContextEvent event) {
        String homeDir = event.getServletContext().getRealPath("/");
        //Set direction to direction whith property file for Log4j Logger 
        File propertiesFile = new File(homeDir, "WEB-INF/classes/log4j.properties");
        PropertyConfigurator.configure(propertiesFile.toString());
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }
}
