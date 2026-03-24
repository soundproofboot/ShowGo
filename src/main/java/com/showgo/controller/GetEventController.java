package com.showgo.controller;

import com.showgo.entity.Event;
import com.showgo.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Get all events and related venue
 */
@WebServlet(
        urlPatterns = { "/getAllEvents" }
)
public class GetEventController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao<Event> dao = new GenericDao<>(Event.class);
        req.setAttribute("allEvents", dao.getAll());
        req.setAttribute("title", "Get Events");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/getAllEvents.jsp");
        dispatcher.forward(req, resp);
    }
}
