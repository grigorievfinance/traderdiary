package com.grigorievfinance.traderdiary.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PositionServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(PositionServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("forward  to meals");
        request.getRequestDispatcher("/positions.jsp").forward(request, response);
    }
}
