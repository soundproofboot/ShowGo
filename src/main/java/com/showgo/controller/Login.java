package com.showgo.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/** Begins the authentication process using AWS Cognito
 *
 */
@WebServlet(
        urlPatterns = { "/login" }
)
public class Login extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    public static String CLIENT_ID;
    public static String LOGIN_URL;
    public static String REDIRECT_URL;

    @Override
    public void init() throws ServletException {
        super.init();
        logger.debug("Login.java init method");
        ServletContext context = getServletContext();
        Properties properties = (Properties) context.getAttribute("properties");
        CLIENT_ID = properties.getProperty("client.id");
        LOGIN_URL = properties.getProperty("loginURL");
        REDIRECT_URL = properties.getProperty("redirectURL");
    }

    /**
     * Route to the aws-hosted cognito login page.
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("doGet in Login.java");
        if (LOGIN_URL != null && LOGIN_URL.length() > 0
                && REDIRECT_URL != null && REDIRECT_URL.length() > 0
                && CLIENT_ID != null && CLIENT_ID.length() > 0) {
            String url = LOGIN_URL + "?response_type=code&client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URL;
            logger.debug("url constructed from cognito properties");
            logger.debug(url);
            resp.sendRedirect(url);
        } else {
            logger.debug("required properties were not set");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/error2.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
