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
        System.out.println("hit post route");
        String venueName = req.getParameter("venue_name");
        String city = req.getParameter("city");
        String state = req.getParameter("state");

        HttpSession session = req.getSession();
        User userInSession = (User) session.getAttribute("user");
        if (userInSession != null
        && !venueName.isEmpty()
        && !city.isEmpty()
        && !state.isEmpty()) {
            System.out.println("got all the data");
            GenericDao<User> userDao = new GenericDao<>(User.class);
            User userFromDb = userDao.getById(userInSession.getId());
            System.out.println("adding venue");
            userFromDb.addVenue(new Venue(venueName, city, state, userFromDb));
            userDao.update(userFromDb);

            User userAfterUpdate =  userDao.getById(userInSession.getId());
            session.setAttribute("user", userAfterUpdate);

            resp.sendRedirect(req.getContextPath() + "/dashboard");

        }

    }
}
