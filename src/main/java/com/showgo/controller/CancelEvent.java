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

/**
 * Delete an event
 */
@WebServlet(
        urlPatterns = {"/cancelEvent/*"}
)
public class CancelEvent extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        get event id from path and event from db
        String path = req.getPathInfo();
        int eventId = Integer.parseInt(path.substring(1));
        GenericDao<Event> eventDao = new GenericDao<>(Event.class);
        Event event = eventDao.getById(eventId);

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || event == null || user.getId() != event.getVenue().getUser().getId()) {
//            if user does not have permission to delete event, redirect
            resp.sendRedirect(req.getContextPath() + "/events/" + eventId);
        } else {
//            get hosting venue and call remove event, update user in session
            Venue venue = event.getVenue();
            venue.removeEvent(event);
            new GenericDao<>(Venue.class).update(venue);
            User userAfterUpdate =  new GenericDao<>(User.class).getById(user.getId());
            session.setAttribute("user", userAfterUpdate);

            resp.sendRedirect(req.getContextPath() + "/venues/" + venue.getId());
        }
    }
}
