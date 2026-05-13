package com.showgo.controller;

import com.showgo.entity.Event;
import com.showgo.entity.Performer;
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
import java.util.List;

/**
 * Create new event for venue
 */
@WebServlet(
        urlPatterns = { "/newEvent"}
)
public class NewEvent extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        get venue and event data from form
        int venueId = Integer.parseInt(req.getParameter("venue_id"));
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String lineup = req.getParameter("lineup");
        LocalDateTime eventStart = LocalDateTime.parse(req.getParameter("event_start"));
        String ticketPrice = req.getParameter("ticket_price");

        HttpSession session = req.getSession();
        User userInSession = (User) session.getAttribute("user");
        if (userInSession != null
            && venueId != 0
            && !title.isEmpty()
            && !description.isEmpty()
        ) {
//            if user logged in, valid venue and valid data
            GenericDao<Venue> venueDao = new GenericDao<>(Venue.class);
            Venue venueFromDb = venueDao.getById(venueId);
            if (userInSession.getId() != venueFromDb.getUser().getId()) {
//                if user does not have permission, throw
                throw new Error("Not allowed");
            } else {
//                set ticket price if provided
                Double priceToSet = null;
                if (ticketPrice != null && !ticketPrice.isEmpty()) {
                    priceToSet = Double.parseDouble(ticketPrice);
                }
//                create new event with event data
                Event newEvent = new Event(title, description, venueFromDb, eventStart, priceToSet);
//                if lineup contains performer ids
                if (!lineup.isEmpty()) {
//                    split into list of each performer id
                    List<String> performerIdList = List.of(lineup.split(","));
                    for (String performerIdStr : performerIdList) {
                        int performerId =  Integer.parseInt(performerIdStr);
                        Performer performer = new GenericDao<>(Performer.class).getById(performerId);
                        if (performer != null) {
//                            add performer to lineup if they exist
                            newEvent.addEventPerformer(performer);
                        }
                    }
                }
//                add event and update user in session
                venueFromDb.addEvent(newEvent);
                venueDao.update(venueFromDb);
                User userAfterUpdate = new GenericDao<>(User.class).getById(userInSession.getId());
                session.setAttribute("user", userAfterUpdate);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/venues/" + venueId);
    }
}
