package com.showgo.controller;

import com.showgo.entity.User;
import com.showgo.persistence.GenericDao;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * get a user
 */
@WebServlet(
        urlPatterns = { "/getAllUsers"}
)
public class GetUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        GenericDao<User> dao = new GenericDao<>(User.class);
        req.setAttribute("user", dao.getById(1));
        req.setAttribute("allUsers", dao.getAll());
        req.setAttribute("title", "Get User");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/getAllUsers.jsp");
        dispatcher.forward(req, resp);
    }
}
