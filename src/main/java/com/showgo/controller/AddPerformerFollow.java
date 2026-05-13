package com.showgo.controller;

import com.showgo.entity.Performer;
import com.showgo.entity.User;
import com.showgo.persistence.GenericDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Add follow to performer for user
 */
@WebServlet(
        urlPatterns = { "/addPerformerFollow"}
)
public class AddPerformerFollow extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Get performer and user from session
        int performerId = Integer.parseInt(req.getParameter("performer_id"));
        HttpSession session = req.getSession();
        User userInSession = (User) session.getAttribute("user");

        if (userInSession == null) {
//            if not logged in, redirect
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        } else {
//            add performer follow to user from db, update, and reset in session
            GenericDao<User> userDao = new GenericDao<>(User.class);
            User userFromDb = userDao.getById(userInSession.getId());
            Performer performer = new GenericDao<>(Performer.class).getById(performerId);

            if (performer != null) {
                userFromDb.addPerformerFollow(performer);
                userDao.update(userFromDb);

                User userAfterUpdate = userDao.getById(userFromDb.getId());
                session.setAttribute("user", userAfterUpdate);
            }

            resp.sendRedirect(req.getContextPath() + "/performers/" + performerId);
        }
    }
}
