package com.showgo.controller;

import com.showgo.entity.Performer;
import com.showgo.entity.User;
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
 * Register a new performer for user
 */
@WebServlet(
        urlPatterns = { "/newPerformer"}
)
public class NewPerformer extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "New Performer");
        RequestDispatcher dispatcher = req.getRequestDispatcher("newPerformer.jsp");
        dispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String performerName = req.getParameter("performer_name");
        HttpSession session = req.getSession();
        User userInSession = (User) session.getAttribute("user");
        if (userInSession != null && !performerName.isEmpty()) {
            GenericDao<User> userDao = new GenericDao<>(User.class);
            User userFromDb = userDao.getById(userInSession.getId());
            userFromDb.addPerformer(new Performer(performerName, userFromDb));
            userDao.update(userFromDb);

            User userAfterUpdate = userDao.getById(userInSession.getId());
            session.setAttribute("user", userAfterUpdate);

            resp.sendRedirect(req.getContextPath() + "/dashboard");
        }
    }
}
