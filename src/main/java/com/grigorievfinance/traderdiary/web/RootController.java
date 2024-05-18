package com.grigorievfinance.traderdiary.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    private static final Logger log = LoggerFactory.getLogger(RootController.class);


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

    @GetMapping("/login")
    public String login() {
        log.info("login");
        return "login";
    }

    @GetMapping("/positions")
    public String getPositions(Model model) {
        log.info("positions");
        return "positions";
    }
}
