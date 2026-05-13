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
 * Delete a venue
 */
@WebServlet(
        urlPatterns = { "/deleteVenue/*"}
)
public class DeleteVenue extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        get id from path and venue from db
        String pathInfo = req.getPathInfo();
        int venueId = Integer.parseInt(pathInfo.substring(1));
        GenericDao<Venue> venueDao = new GenericDao<>(Venue.class);
        Venue venue = venueDao.getById(venueId);

        HttpSession session = req.getSession();
        User user =  (User)session.getAttribute("user");

        if (user == null || venue == null || user.getId() != venue.getUser().getId()) {
//            if user does not have permission to delete, redirect
            resp.sendRedirect(req.getContextPath() + "/venues/" + venueId);
        } else {
//            remove venue from user, update and reset in session
            GenericDao<User> userDao = new GenericDao<>(User.class);
            User userFromDb = userDao.getById(user.getId());
            if (userFromDb != null) {
                userFromDb.removeVenue(venue);
                userDao.update(userFromDb);

                User userAfterUpdate = userDao.getById(userFromDb.getId());
                session.setAttribute("user",  userAfterUpdate);

                resp.sendRedirect(req.getContextPath() + "/dashboard");
            }
        }
    }

}
