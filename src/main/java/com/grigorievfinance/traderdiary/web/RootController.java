package com.grigorievfinance.traderdiary.web;

import com.grigorievfinance.traderdiary.service.PositionService;
import com.grigorievfinance.traderdiary.util.PositionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {

    private static final Logger log = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private PositionService positionService;

    @GetMapping("/")
    public String root() {
        log.info("root");
        return "redirect:positions";
    }

    @GetMapping("/users")
    public String getUsers() {
        log.info("users");
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        log.info("setUser {}", userId);
        SecurityUtil.setAuthUserId(userId);
        return "redirect:positions";
    }

    @GetMapping("/login")
    public String login() {
        log.info("login");
        return "login";
    }

    @GetMapping("/positions")
    public String getPositions(Model model) {
        log.info("positions");
        model.addAttribute("positions", PositionUtil.getTos(positionService.getAll(SecurityUtil.authUserId()), SecurityUtil.authUserMaxLoss()));
        return "positions";
    }
}
