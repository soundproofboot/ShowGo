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
 * TODO make it do anything else or remove if this page is static
 * set up home page servlet to test it's working
 */
@WebServlet(
        urlPatterns = { "/otherPage"}
)
public class OtherPage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDao userDao = new UserDao();
        req.setAttribute("user", userDao.getById(1));
        req.setAttribute("title", "Other Page");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/otherPage.jsp");
        dispatcher.forward(req, resp);
    }
}
