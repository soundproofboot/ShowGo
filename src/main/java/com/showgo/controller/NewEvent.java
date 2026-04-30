package com.showgo.controller;

import com.showgo.entity.Event;
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
import java.time.LocalDateTime;

/**
 * Create new event for venue
 */
@WebServlet(
        urlPatterns = { "/newEvent"}
)
public class NewEvent extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int venueId = Integer.parseInt(req.getParameter("venue_id"));
        String title = req.getParameter("title");
        LocalDateTime eventStart = LocalDateTime.parse(req.getParameter("event_start"));

        HttpSession session = req.getSession();
        User userInSession = (User) session.getAttribute("user");
        if (userInSession != null
            && venueId != 0
            && !title.isEmpty()
        ) {
            GenericDao<Venue> venueDao = new GenericDao<>(Venue.class);
            Venue venueFromDb = venueDao.getById(venueId);
            if (userInSession.getId() != venueFromDb.getUser().getId()) {
                throw new Error("Not allowed");
            } else {
                venueFromDb.addEvent(new Event(title, venueFromDb, eventStart));
                venueDao.update(venueFromDb);
                User userAfterUpdate = new GenericDao<>(User.class).getById(userInSession.getId());
                session.setAttribute("user", userAfterUpdate);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/venues/" + venueId);
    }
}
