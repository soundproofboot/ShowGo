package com.showgo.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
}
