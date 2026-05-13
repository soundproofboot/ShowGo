package com.showgo.controller;

import com.showgo.entity.Event;
import com.showgo.entity.User;
import com.showgo.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Add user interest in event via post request
 */
@WebServlet(
        urlPatterns = { "/addEventInterest"}
)
public class AddEventInterest extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Get event id and user in session
        int eventId = Integer.parseInt(req.getParameter("event_id"));
        HttpSession session = req.getSession();
        User userInSession =  (User) session.getAttribute("user");

//        If no user, redirect
        if (userInSession == null || eventId == 0) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        else {
//            Get user from database
            User userFromDb = new GenericDao<>(User.class).getById(userInSession.getId());
            Event event = new GenericDao<>(Event.class).getById(eventId);
            if (event != null) {
//                Add event interest, update, and reset in session
                userFromDb.addEventInterest(event);
                GenericDao<User> userDao = new GenericDao<>(User.class);
                userDao.update(userFromDb);

                User userAfterUpdate = userDao.getById(userFromDb.getId());
                session.setAttribute("user", userAfterUpdate);
                resp.sendRedirect(req.getContextPath() + "/events/" + eventId);
            } else {
                resp.sendRedirect(req.getContextPath() + "/events/" + eventId);
            }
        }
    }
}
