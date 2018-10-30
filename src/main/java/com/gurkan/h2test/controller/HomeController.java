package com.gurkan.h2test.controller;

import com.gurkan.h2test.domain.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping("/")
    public ModelAndView getHomePage(@AuthenticationPrincipal User user) {
        return new ModelAndView("home", "user", user);
    }

}