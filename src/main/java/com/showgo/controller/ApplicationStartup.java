package com.showgo.controller;

import com.showgo.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.Properties;

/**
 * Servlet runs on startup to set properties in servlet context
 */
@WebServlet(
        name = "applicationStartup",
        urlPatterns = {"/showgo-startup"},
        loadOnStartup = 1
)
public class ApplicationStartup extends HttpServlet implements PropertiesLoader {

    public void init() throws ServletException {
            Properties properties = loadProperties("/cognito.properties");
            getServletContext().setAttribute("properties", properties);
    }
}
