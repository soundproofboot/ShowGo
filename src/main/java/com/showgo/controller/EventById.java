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
 * Page for a single event
 */
@WebServlet(
        urlPatterns = {"/events/*"}
)
public class EventById extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        get id from path and venue from db
        String path = req.getPathInfo();
        int eventId = Integer.parseInt(path.substring(1));
        Event event = new GenericDao<>(Event.class).getById(eventId);
        req.setAttribute("event", event);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/event.jsp");
        dispatcher.forward(req, resp);
    }
}
