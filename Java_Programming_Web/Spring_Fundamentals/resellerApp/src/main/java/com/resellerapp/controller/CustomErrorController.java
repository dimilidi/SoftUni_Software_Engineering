package com.resellerapp.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        int statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        ModelAndView modelAndView = new ModelAndView();

        if (statusCode == 404) {
            modelAndView.setViewName("errors/404");
        } else if (statusCode == 405) {
            modelAndView.setViewName("errors/405");
        } else {
            modelAndView.setViewName("errors/error");
        }

        modelAndView.addObject("statusCode", statusCode);
        return modelAndView;
    }


}
