package com.showgo.controller;

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

/**
 * Form to create new venue for user
 */
@WebServlet(
        urlPatterns = { "/newVenue"}
)
public class NewVenue extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "New Venue");
        RequestDispatcher dispatcher = req.getRequestDispatcher("newVenue.jsp");
        dispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        get venue data from request params
        String venueName = req.getParameter("venue_name");
        String streetAddress = req.getParameter("street_address");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String description = req.getParameter("description");

        HttpSession session = req.getSession();
        User userInSession = (User) session.getAttribute("user");
        if (userInSession != null
        && !venueName.isEmpty()
        && !streetAddress.isEmpty()
        && !city.isEmpty()
        && !state.isEmpty()
        && !description.isEmpty()) {
//            if data valid and user logged in, add venue and update user
            GenericDao<User> userDao = new GenericDao<>(User.class);
            User userFromDb = userDao.getById(userInSession.getId());
            userFromDb.addVenue(new Venue(venueName, city, state, streetAddress, description, userFromDb));
            userDao.update(userFromDb);

            User userAfterUpdate =  userDao.getById(userInSession.getId());
            session.setAttribute("user", userAfterUpdate);

            resp.sendRedirect(req.getContextPath() + "/dashboard");
        }
    }
}
