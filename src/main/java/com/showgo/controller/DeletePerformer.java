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
 * Delete a performer
 */
@WebServlet(
        urlPatterns = { "/deletePerformer/*" }
)
public class DeletePerformer extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        int performerId = Integer.parseInt(pathInfo.substring(1));
        GenericDao<Performer> performerDao = new GenericDao<>(Performer.class);
        Performer performer =  performerDao.getById(performerId);

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || performer == null || user.getId() != performer.getUser().getId()) {
            resp.sendRedirect(req.getContextPath() + "/performers/" + performerId);
        } else {
            GenericDao<User> userDao = new GenericDao<>(User.class);
            User userFromDb =  userDao.getById(user.getId());
            if (userFromDb != null) {
                userFromDb.removePerformer(performer);
                userDao.update(userFromDb);

                User userAfterUpdate = new GenericDao<>(User.class).getById(userFromDb.getId());
                session.setAttribute("user", userAfterUpdate);

                resp.sendRedirect(req.getContextPath() + "/dashboard");
            }
        }
    }
}
