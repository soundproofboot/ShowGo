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
        urlPatterns = { "/signup" }
)
public class Signup extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    public static String CLIENT_ID;
    public static String SIGNUP_URL;
    public static String REDIRECT_URL;

    @Override
    public void init() throws ServletException {
        super.init();
        logger.debug("Signup.java init method");
        ServletContext context = getServletContext();
        Properties properties = (Properties) context.getAttribute("properties");
        CLIENT_ID = properties.getProperty("client.id");
        SIGNUP_URL = properties.getProperty("signupURL");
        REDIRECT_URL = properties.getProperty("redirectURL");
    }

    /**
     * Route to the aws-hosted cognito signup page.
     * @param req servlet request
     * @param resp servlet response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("doGet in Signup.java");
        if (SIGNUP_URL != null && SIGNUP_URL.length() > 0
                && REDIRECT_URL != null && REDIRECT_URL.length() > 0
                && CLIENT_ID != null && CLIENT_ID.length() > 0) {
            String url = SIGNUP_URL + "?response_type=code&client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URL;
            logger.debug("signup url");
            logger.debug(url);
            resp.sendRedirect(url);
        } else {
            logger.debug("required properties were not set");
        }
    }
}
