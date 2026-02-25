package com.showgo.controller;

import com.showgo.persistence.UserDao;

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
        urlPatterns = { "/getUser"}
)
public class GetUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDao userDao = new UserDao();
        req.setAttribute("user", userDao.getById(1));
        req.setAttribute("title", "Get User");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/getUser.jsp");
        dispatcher.forward(req, resp);
    }
}
