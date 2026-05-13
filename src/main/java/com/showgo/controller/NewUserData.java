package com.showgo.controller;

import com.showgo.entity.User;
import com.showgo.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Receives basic user data via POST request, updates user and forwards back to dashboard with
 * updated user in session
 */
@WebServlet(
        urlPatterns = {"/newUserData"}
)
public class NewUserData extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String userCity = req.getParameter("city");
        String userState = req.getParameter("state");
        HttpSession session = req.getSession();
        User userInSession =  (User) session.getAttribute("user");
        if (
                !username.isEmpty()
             && !userCity.isEmpty()
             && !userState.isEmpty()
             && userInSession != null
        ) {
//            if user logged in and valid data provided, update user and reset in session
            GenericDao<User> userDao = new GenericDao<>(User.class);

            userInSession.setUsername(username);
            userInSession.setCity(userCity);
            userInSession.setState(userState);
            userDao.update(userInSession);

            User userAfterUpdate = userDao.getById(userInSession.getId());

            session.setAttribute("user", userAfterUpdate);
        }

        resp.sendRedirect("dashboard.jsp");
    }
}
