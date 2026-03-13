package com.showgo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@WebServlet(
        urlPatterns = { "/logout" }
)
public class Logout extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    public static String CLIENT_ID;
    public static String LOGOUT_URL;
    public static String REDIRECT_URL;

    @Override
    public void init() throws ServletException {
        super.init();
        logger.debug("Logout.java init method");
        ServletContext context = getServletContext();
        Properties properties = (Properties) context.getAttribute("properties");
        CLIENT_ID = properties.getProperty("client.id");
        LOGOUT_URL = properties.getProperty("logoutURL");
        REDIRECT_URL = properties.getProperty("redirectURL");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("doGet in Logout.java");
        if (LOGOUT_URL != null && LOGOUT_URL.length() > 0
                && CLIENT_ID != null && CLIENT_ID.length() > 0) {
            String url = LOGOUT_URL + "?client_id=" + CLIENT_ID + "&logout_uri=" + REDIRECT_URL;
            logger.debug("logout url");
            logger.debug(url);
            resp.sendRedirect(url);
        } else {
            logger.debug("required properties were not set");
        }
    }
}
