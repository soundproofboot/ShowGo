package com.showgo.controller;

import com.showgo.entity.Venue;
import com.showgo.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Get all venues and users following them
 */
@WebServlet(
        urlPatterns = { "/getAllVenues"}
)
public class GetVenueController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao<Venue> dao = new GenericDao<>(Venue.class);
        req.setAttribute("allVenues", dao.getAll());
        req.setAttribute("title", "Get Venues");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/getAllVenues.jsp");
        dispatcher.forward(req, resp);
    }
}
