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

@WebServlet(
        urlPatterns = { "/removeVenueFollow"}
)
public class RemoveVenueFollow extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int venueId = Integer.parseInt(req.getParameter("venue_id"));

        HttpSession session = req.getSession();
        User userInSession = (User) session.getAttribute("user");
        if (userInSession == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        } else {
            GenericDao<User> userDao = new GenericDao<>(User.class);
            Venue venue = new GenericDao<>(Venue.class).getById(venueId);
            User userFromDb = userDao.getById(userInSession.getId());
            if (venue != null && userFromDb != null) {
                userFromDb.removeVenueFollow(venue);
                userDao.update(userFromDb);

                User userAfterUpdate = userDao.getById(userInSession.getId());

                session.setAttribute("user", userAfterUpdate);
            }

            resp.sendRedirect(req.getContextPath() + "/venues/" + venueId);
        }
    }
}