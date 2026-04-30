package com.showgo.controller;

import com.showgo.entity.Event;
import com.showgo.entity.User;
import com.showgo.persistence.EventDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(
        urlPatterns = { "/"}
)
public class Home extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EventDao eventDao = new EventDao();
        HttpSession session = req.getSession();

        if (session.getAttribute("user") == null) {
//            get 20 most recently created events
            req.setAttribute("events", eventDao.getMostRecentEvents(20));
        } else {
            User user = (User) session.getAttribute("user");
            String city = user.getCity();
            String state = user.getState();

            if (city != null && state != null) {
                List<Event> allLocalEvents = eventDao.getEventsByCityState(city, state);

                req.setAttribute("events", allLocalEvents);
            }
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/home.jsp");
        dispatcher.forward(req, resp);
    }
}
