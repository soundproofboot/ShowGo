package com.showgo.controller;

import com.showgo.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        urlPatterns = { "/dashboard"}
)
public class Dashboard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = "Dashboard";
        req.setAttribute("title", title);

        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            System.out.println("there is a user in session");
            User user = (User) session.getAttribute("user");
            System.out.println(user);

        } else {
            System.out.println("there is not a user in session");

        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("/dashboard.jsp");
        dispatcher.forward(req, resp);
    }
}
