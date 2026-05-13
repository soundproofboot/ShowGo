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
 * Unfollow performer
 */
@WebServlet(
        urlPatterns = { "/removePerformerFollow"}
)
public class RemovePerformerFollow extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        get id from request params
        int performerId = Integer.parseInt(req.getParameter("performer_id"));

        HttpSession session = req.getSession();
        User userInSession = (User) session.getAttribute("user");
        if (userInSession == null) {
//            if user not logged in, redirect
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        } else {
            GenericDao<User> userDao = new GenericDao<>(User.class);
            Performer performer = new GenericDao<>(Performer.class).getById(performerId);
            User userFromDb = userDao.getById(userInSession.getId());
            if (performer != null && userFromDb != null) {
//                if user and performer exist, remove and update user
                userFromDb.removePerformerFollow(performer);
                userDao.update(userFromDb);

                User userAfterUpdate = userDao.getById(userInSession.getId());
                session.setAttribute("user", userAfterUpdate);
            }

            resp.sendRedirect(req.getContextPath() + "/performers/" + performerId);
        }
    }
}
