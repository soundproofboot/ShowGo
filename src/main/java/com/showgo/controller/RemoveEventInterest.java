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
 * Remove user interest in event
 */
@WebServlet(
        urlPatterns = { "/removeEventInterest"}
)
public class RemoveEventInterest extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        get event id from request params
        int eventId = Integer.parseInt(req.getParameter("event_id"));

        HttpSession session = req.getSession();
        User userInSession = (User) session.getAttribute("user");
        if (userInSession == null || eventId == 0) {
//            if not logged in or no event id, redirect
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        } else {
            User userFromDb = new GenericDao<>(User.class).getById(userInSession.getId());
            Event event = new GenericDao<>(Event.class).getById(eventId);
            if (event != null) {
//                if event is valid, remove interest from user and update
                userFromDb.removeEventInterest(event);
                GenericDao<User> userDao = new GenericDao<>(User.class);
                userDao.update(userFromDb);

                User userAfterUpdate = userDao.getById(userInSession.getId());

                session.setAttribute("user", userAfterUpdate);
                resp.sendRedirect(req.getContextPath() + "/events/" + eventId);
            } else {
                resp.sendRedirect(req.getContextPath() + "/events/" + eventId);
            }
        }
    }
}
