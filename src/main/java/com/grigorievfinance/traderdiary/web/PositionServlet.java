package com.grigorievfinance.traderdiary.web;

import com.grigorievfinance.traderdiary.model.Position;
import com.grigorievfinance.traderdiary.web.position.PositionRestController;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static com.grigorievfinance.traderdiary.util.DateTimeUtil.parseLocalDate;
import static com.grigorievfinance.traderdiary.util.DateTimeUtil.parseLocalTime;

public class PositionServlet extends HttpServlet {
    private PositionRestController positionController;

    @Override
    public void init() {
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        positionController = springContext.getBean(PositionRestController.class);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        Position position = new Position(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("symbol"),
                Integer.parseInt(request.getParameter("profitLoss")));
        if (StringUtils.hasLength(request.getParameter("id"))) {
            positionController.update(position, getId(request));
        } else {
            positionController.create(position);
        }
        response.sendRedirect("positions");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action == null ? "all" : action) {
            case "delete" -> {
                int id = getId(request);
                positionController.delete(id);
                response.sendRedirect("positions");
            }
            case "create", "update" -> {
                final Position position = "create".equals(action) ?
                        new Position(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        positionController.get(getId(request));
                request.setAttribute("position", position);
                request.getRequestDispatcher("/positionForm.jsp").forward(request, response);
            }
            case "filter" -> {
                LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
                LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
                LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
                LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
                request.setAttribute("positions", positionController.getBetween(startDate, startTime, endDate, endTime));
                request.getRequestDispatcher("/positions.jsp").forward(request, response);
            }
            default -> {
                request.setAttribute("positions", positionController.getAll());
                request.getRequestDispatcher("/positions.jsp").forward(request, response);
            }
        }
    }

    private int getId(HttpServletRequest request) {
        String id = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(id);
    }
}
