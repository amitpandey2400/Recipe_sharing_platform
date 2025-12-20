package web;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import platform.Platform;

@WebListener
public class AppInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Platform platform = new Platform();
        platform.loadData(); // loads users + recipes from file

        ServletContext ctx = sce.getServletContext();
        ctx.setAttribute("platform", platform);

        System.out.println("Vedic Bytes Platform initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext ctx = sce.getServletContext();
        Platform platform = (Platform) ctx.getAttribute("platform");
        if (platform != null) {
            platform.saveData();
            System.out.println("Vedic Bytes Platform data saved.");
        }
    }
}
