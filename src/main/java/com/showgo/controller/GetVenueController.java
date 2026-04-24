package com.showgo.controller;

import com.showgo.entity.User;
import com.showgo.persistence.VenueDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Get all venues and users following them
 */
@WebServlet(
        urlPatterns = { "/venues"}
)
public class GetVenueController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        VenueDao dao = new VenueDao();
        String city = req.getParameter("city");
        String state = req.getParameter("state");

        if (city == null || state == null) {
            HttpSession session = req.getSession();
            if (session.getAttribute("user") != null) {
                User user = (User) session.getAttribute("user");
                city = user.getCity();
                state = user.getState();
            }
        }
        req.setAttribute("citySearched", city);
        req.setAttribute("stateSearched", state);

        req.setAttribute("allVenues", dao.getVenuesByCityState(city, state));
        if (city == null || state == null) {
            req.setAttribute("title", "Venue Search");
        } else {
            req.setAttribute("title", "Venues in " + city + ", " + state);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/getAllVenues.jsp");
        dispatcher.forward(req, resp);
    }
}
