package com.showgo.controller;

import com.showgo.entity.Performer;
import com.showgo.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Get a single performer by id
 */
@WebServlet(
        urlPatterns = {"/performers/*"}
)
public class PerformerById extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        int performerId = Integer.parseInt(path.substring(1));
        Performer performer = new GenericDao<>(Performer.class).getById(performerId);
        req.setAttribute("performer", performer);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/performer.jsp");
        dispatcher.forward(req, resp);
    }
}
