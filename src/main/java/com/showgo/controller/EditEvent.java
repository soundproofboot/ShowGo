package com.showgo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.showgo.dto.PerformerDto;
import com.showgo.entity.Event;
import com.showgo.entity.EventPerformer;
import com.showgo.entity.Performer;
import com.showgo.entity.User;
import com.showgo.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Edit event if owner
 */
@WebServlet(
        urlPatterns = {"/editEvent/*"}
)
public class EditEvent extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        get id from path and event from db
        String path = req.getPathInfo();
        int eventId = Integer.parseInt(path.substring(1));
        Event event = new GenericDao<>(Event.class).getById(eventId);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || user.getId() != event.getVenue().getUser().getId()) {
//            if user does not have permission, redirect
            resp.sendRedirect(req.getContextPath() + "/events/" + eventId);
        } else {
//            set current performers list as json to populate form
            ObjectMapper mapper = new ObjectMapper();
            Set<EventPerformer> eventPerformers = event.getPerformers();
            List<PerformerDto> performerDtos = eventPerformers.stream()
                .map(p -> new PerformerDto(p.getPerformer().getId(), p.getPerformer().getName()))
                .collect(Collectors.toList());
            String performersJson = mapper.writeValueAsString(performerDtos);
            req.setAttribute("performersJson", performersJson);

//            need to format for js date picker
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            String eventStart = event.getEventStart().format(formatter);
            req.setAttribute("eventStart", eventStart);

//            set event on request
            req.setAttribute("event", event);
            req.setAttribute("title", "Update " + event.getTitle());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/editEvent.jsp");
            dispatcher.forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        get id from path and event values from params
        String path = req.getPathInfo();
        int eventId = Integer.parseInt(path.substring(1));
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        LocalDateTime eventStart = LocalDateTime.parse(req.getParameter("event_start"));
        String ticketPrice = req.getParameter("ticket_price");
        String lineup = req.getParameter("lineup");

        HttpSession session = req.getSession();
        User userInSession = (User) session.getAttribute("user");

        GenericDao<Event> eventDao = new GenericDao<>(Event.class);
        Event event = eventDao.getById(eventId);

        if (userInSession != null
            && event != null
            && !title.isEmpty()
            && !description.isEmpty()
        ) {
//            if user and required data is valid...
            Double priceToSet = null;
            if (ticketPrice != null && !ticketPrice.isEmpty()) {
//                if price provided, set
                priceToSet = Double.parseDouble(ticketPrice);
            }
            event.setTitle(title);
            event.setDescription(description);
            event.setEventStart(eventStart);
            event.setTicketPrice(priceToSet);

//            remove existing performers from lineup
            for (EventPerformer eventPerformer : new ArrayList<>(event.getPerformers())) {
                event.removeEventPerformer(eventPerformer.getPerformer());
            }
            if (!lineup.isEmpty()) {
//                if lineup is not empty, add performers listed
                List<String> performerIdList = List.of(lineup.split(","));
                for (String performerId : performerIdList) {
                    Performer performerToAdd = new GenericDao<>(Performer.class).getById(Integer.parseInt(performerId));
                    if (performerToAdd != null) {
                        event.addEventPerformer(performerToAdd);
                    }
                }
            }

//            update event and update user in session
            eventDao.update(event);
            User userAfterUpdate = new GenericDao<>(User.class).getById(userInSession.getId());
            session.setAttribute("user", userAfterUpdate);
        }

        resp.sendRedirect(req.getContextPath() + "/events/" + eventId);
    }
}
