package com.grigorievfinance.traderdiary.web;

import com.grigorievfinance.traderdiary.service.PositionService;
import com.grigorievfinance.traderdiary.service.UserService;
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
    private UserService userService;

    @Autowired
    private PositionService positionService;

    @GetMapping("/")
    public String root() {
        log.info("root");
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        log.info("users");
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        log.info("setUser {}", userId);
        SecurityUtil.setAuthUserId(userId);
        return "redirect:positions";
    }

    @GetMapping("/positions")
    public String getPositions(Model model) {
        log.info("positions");
        model.addAttribute("positions", PositionUtil.getTos(positionService.getAll(SecurityUtil.authUserId()), SecurityUtil.authUserMaxLoss()));
        return "positions";
    }
}