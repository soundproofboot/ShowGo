package com.showgo.controller;

import com.showgo.dto.PerformerDto;
import com.showgo.entity.Performer;
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
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Get a single venue by id
 */
@WebServlet(
        urlPatterns = {"/venues/*"}
)
public class VenueById extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        int venueId = Integer.parseInt(path.substring(1));
        Venue venue = new GenericDao<>(Venue.class).getById(venueId);

        HttpSession session = req.getSession();
        User userInSession = (User) session.getAttribute("user");
        System.out.println("userInSession = " + userInSession);
        System.out.println("venue id");
        System.out.println(venue.getUser().getId());
        if (userInSession != null && userInSession.getId() == venue.getUser().getId()) {
            System.out.println("should return performers");
            ObjectMapper mapper = new ObjectMapper();
            List<Performer> allPerformers = new GenericDao<>(Performer.class).getAll();
            System.out.println(allPerformers);
            List<PerformerDto> performerDtos = allPerformers.stream()
                    .map(p -> new PerformerDto(p.getId(), p.getName()))
                    .collect(Collectors.toList());
            System.out.println(performerDtos);
            String performersJson = mapper.writeValueAsString(performerDtos);
            System.out.println(performersJson);
            req.setAttribute("performersJson", performersJson);
        }

        req.setAttribute("venue", venue);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/venue.jsp");
        dispatcher.forward(req, resp);
    }
}
