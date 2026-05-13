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
 * Edit performer details if owner
 */
@WebServlet(
        urlPatterns = {"/editPerformer/*"}
)
public class EditPerformer extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        get id and performer from db
        String path = req.getPathInfo();
        int performerId = Integer.parseInt(path.substring(1));
        Performer performer = new GenericDao<>(Performer.class).getById(performerId);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || user.getId() != performer.getUser().getId()) {
//            if user does not have permission, redirect
            resp.sendRedirect(req.getContextPath() + "/performers/" + performerId);
        } else {
//            set performer on request
            req.setAttribute("performer", performer);
            req.setAttribute("title", "Update " + performer.getName());
            RequestDispatcher dispatcher = req.getRequestDispatcher("/editPerformer.jsp");
            dispatcher.forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        get id from path and performer from db
        String path = req.getPathInfo();
        int performerId = Integer.parseInt(path.substring(1));
        String performerName = req.getParameter("name");
        String description = req.getParameter("description");
        String genre = req.getParameter("genre");

        HttpSession session = req.getSession();
        User userInSession = (User) session.getAttribute("user");
        GenericDao<Performer> performerDao = new GenericDao<>(Performer.class);
        Performer performer = performerDao.getById(performerId);

        if (userInSession != null
            && !performerName.isEmpty()
            && !description.isEmpty()
            && !genre.isEmpty()
            && userInSession.getId() == performer.getUser().getId()
        ) {
//            if user has permission and data valid, set new values and update performer
            performer.setName(performerName);
            performer.setDescription(description);
            performer.setGenre(genre);
            performerDao.update(performer);

//           update suer and reset in session
            User updatedUser = new GenericDao<>(User.class).getById(userInSession.getId());
            session.setAttribute("user", updatedUser);
        }

        resp.sendRedirect(req.getContextPath() + "/performers/" + performerId);
    }
}
