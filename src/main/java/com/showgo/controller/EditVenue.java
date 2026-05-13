package com.showgo.controller;

import com.showgo.entity.User;
import com.showgo.entity.Venue;
import com.showgo.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Edit venue details if owner
 */
@WebServlet(
        urlPatterns = {"/editVenue/*"}
)
public class EditVenue extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        get id from path and venue from db
        String path = req.getPathInfo();
        int venueId = Integer.parseInt(path.substring(1));
        Venue venue = new GenericDao<>(Venue.class).getById(venueId);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || user.getId() != venue.getUser().getId()) {
//            if user does not have permission, redirect
            resp.sendRedirect(req.getContextPath() + "/venue/" + venueId);
        } else {
//            set venue on request
            req.setAttribute("venue", venue);
            req.setAttribute("title", "Update " + venue.getName());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/editVenue.jsp");
            dispatcher.forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        get id from path, venue from db, and venue data from params
        String path = req.getPathInfo();
        int venueId = Integer.parseInt(path.substring(1));

        String venueName = req.getParameter("name");
        String streetAddress = req.getParameter("street_address");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String description = req.getParameter("description");

        HttpSession session = req.getSession();
        User userInSession = (User) session.getAttribute("user");
        GenericDao<Venue> venueDao = new GenericDao<>(Venue.class);
        Venue venue = venueDao.getById(venueId);
        if (userInSession != null
                && !venueName.isEmpty()
                && !streetAddress.isEmpty()
                && !city.isEmpty()
                && !state.isEmpty()
                && !description.isEmpty()
                && userInSession.getId() == venue.getUser().getId()
        ) {
//            if user has permission and required data exists, update venue and reset user in session
            venue.setName(venueName);
            venue.setStreetAddress(streetAddress);
            venue.setCity(city);
            venue.setState(state);
            venue.setDescription(description);
            venueDao.update(venue);

            User updatedUser = new GenericDao<>(User.class).getById(userInSession.getId());
            session.setAttribute("user", updatedUser);
        }
            resp.sendRedirect(req.getContextPath() + "/venues/" + venueId);
    }
}
