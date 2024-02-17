package com.grigorievfinance.traderdiary.web;

import com.grigorievfinance.traderdiary.model.Position;
import com.grigorievfinance.traderdiary.repository.InMemoryPositionRepository;
import com.grigorievfinance.traderdiary.repository.PositionRepository;
import com.grigorievfinance.traderdiary.util.PositionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class PositionServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(PositionServlet.class);
    private PositionRepository positionRepository;
    public void init() {
        positionRepository = new InMemoryPositionRepository();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        Position position = new Position(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("symbol"),
                Integer.parseInt(request.getParameter("profitLoss")));
        log.info(position.isNew() ? "Create {}" : "Update {}", position);
        positionRepository.save(position);
        response.sendRedirect("positions");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete id={}", id);
                positionRepository.delete(id);
                response.sendRedirect("positions");
                break;
            case "create":
            case "update":
                final Position position = "create".equals(action) ?
                        new Position(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        positionRepository.get(getId(request));
                request.setAttribute("position", position);
                request.getRequestDispatcher("/positionForm.jsp").forward(request, response);
                break;
            default:
                log.info("getAll");
                request.setAttribute("positions",
                        PositionUtil.getTos(positionRepository.getAll(), 1000));
                request.getRequestDispatcher("/positions.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String id = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(id);
    }
}
