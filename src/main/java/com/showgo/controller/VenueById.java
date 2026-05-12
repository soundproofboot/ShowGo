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

        req.setAttribute("venue", venue);
        req.setAttribute("title", venue.getName());
        RequestDispatcher dispatcher = req.getRequestDispatcher("/venue.jsp");
        dispatcher.forward(req, resp);
    }
}
