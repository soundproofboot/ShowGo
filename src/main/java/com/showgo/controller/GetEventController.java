package com.showgo.controller;

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

/**
 * Get all events and related venue
 */
@WebServlet(
        urlPatterns = { "/events" }
)
public class GetEventController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EventDao dao = new EventDao();
//        giet city and state from request params
        String city = req.getParameter("city");
        String state = req.getParameter("state");

        if (city == null || state == null) {
//            if no city and state in params, set to logged in user's city and state if logged in
            HttpSession session = req.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                city = user.getCity();
                state = user.getState();
            }
        }
//        set attributes for city/state for display
        req.setAttribute("citySearched", city);
        req.setAttribute("stateSearched", state);

        req.setAttribute("allEvents", dao.getEventsByCityState(city, state));

        if (city == null || state == null) {
//            conditional title based on search by location or not
            req.setAttribute("title", "Event Search");
        } else {
            req.setAttribute("title", "Events in " + city + ", " + state);
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/getAllEvents.jsp");
        dispatcher.forward(req, resp);
    }
}
