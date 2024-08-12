package com.resellerapp.controller;

import com.resellerapp.model.OfferHomeDTO;
import com.resellerapp.service.UserService;
import com.resellerapp.service.OfferService;
import com.resellerapp.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final OfferService offerService;
    private final UserService userService;

    public HomeController(OfferService offerService, UserService userService) {
        this.offerService = offerService;
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        return SecurityUtils
                .checkAccess(userService, "home", false)
                .orElseGet(() -> new ModelAndView("index"));
    }

    @GetMapping("/home")
    public ModelAndView home() {
        return SecurityUtils
                .checkAccess(userService, "login", true)
                .orElseGet(() -> {
                    ModelAndView modelAndView = new ModelAndView("home");
                    OfferHomeDTO offersForHomePage = offerService.getOffersForHomePage();
                    modelAndView.addObject("offerHomeDTO", offersForHomePage);
                    return modelAndView;
                });
    }
}
