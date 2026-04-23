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
import java.util.List;

/**
 * Get a single user by id
 */
@WebServlet(
        urlPatterns = {"/users/*"}
)
public class UserById extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        System.out.println("path: " + path);
        String userName = path.substring(1);
        System.out.println("userName: " + userName);
        User thisUser = new GenericDao<>(User.class).getByPropertyEqual("username", userName).get(0);
        req.setAttribute("thisUser", thisUser);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user.jsp");
        dispatcher.forward(req, resp);
    }
}
