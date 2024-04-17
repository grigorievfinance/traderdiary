package com.grigorievfinance.traderdiary.web.position;

import com.grigorievfinance.traderdiary.model.Position;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import static com.grigorievfinance.traderdiary.util.DateTimeUtil.parseLocalDate;
import static com.grigorievfinance.traderdiary.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping("/positions")
public class JspPositionController extends AbstractPositionController {

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        super.delete(getId(request));
        return "redirect:/positions";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request, Model model) {
        model.addAttribute("position", super.get(getId(request)));
        return "positionForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("position", new Position(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000));
        return "positionForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request) {
        Position position = new Position(LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("profit")));

        if (request.getParameter("id").isEmpty()) {
            super.create(position);
        } else {
            super.update(position, getId(request));
        }
        return "redirect:/positions";
    }

    @GetMapping("/filter")
    public String getBetween(HttpServletRequest request, Model model) {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        model.addAttribute("positions", super.getBetween(startDate, startTime, endDate, endTime));
        return "positions";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
