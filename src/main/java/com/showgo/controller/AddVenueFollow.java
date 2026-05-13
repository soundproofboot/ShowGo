package com.showgo.controller;

import com.showgo.entity.User;
import com.showgo.entity.Venue;
import com.showgo.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Add follow to venue for user
 */
@WebServlet(
        urlPatterns = { "/addVenueFollow"}
)
public class AddVenueFollow extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Get venue and user in session
        int venueId = Integer.parseInt(req.getParameter("venue_id"));
        HttpSession session = req.getSession();
        User userInSession = (User) session.getAttribute("user");

        if (userInSession == null) {
//            if not logged in, redirect
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        } else {
//            add venue follow to user from db, update and reset in session
            GenericDao<User> userDao = new GenericDao<>(User.class);
            User userFromDb = userDao.getById(userInSession.getId());
            Venue venue = new GenericDao<>(Venue.class).getById(venueId);
            if (venue != null) {
                userFromDb.addVenueFollow(venue);
                userDao.update(userFromDb);

                User userAfterUpdate = userDao.getById(userFromDb.getId());
                session.setAttribute("user", userAfterUpdate);
                resp.sendRedirect(req.getContextPath() + "/venues/" + venueId);
            } else {
                resp.sendRedirect(req.getContextPath() + "/venues/" + venueId);
            }
        }
    }
}
