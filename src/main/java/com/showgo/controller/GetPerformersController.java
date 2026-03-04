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
import java.io.IOException;

/**
 * Get all performers
 */
@WebServlet(
        urlPatterns = { "/getAllPerformers"}
)
public class GetPerformersController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao<Performer> dao = new GenericDao<>(Performer.class);
        req.setAttribute("allPerformers",  dao.getAll());
        req.setAttribute("title", "Get Performers");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/getAllPerformers.jsp");
        dispatcher.forward(req, resp);
    }
}
