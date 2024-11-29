package org.lididimi.nltworkshop.web.controllers;

import jakarta.validation.Valid;
import org.lididimi.nltworkshop.service.UserService;
import org.lididimi.nltworkshop.web.models.UserLoginModel;
import org.lididimi.nltworkshop.web.models.UserRegisterModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("register")
    public ModelAndView register() {
        ModelAndView mav = new ModelAndView("user/register");
        mav.addObject("time", LocalDate.now());
        return mav;
    }

    @PostMapping("register")
    public ModelAndView registerPost(@ModelAttribute  @Valid UserRegisterModel userRegisterModel) {
        ModelAndView mav = new ModelAndView("redirect:login");

        if(!userService.validateRegisterUserModel(userRegisterModel)) {
            mav.setViewName("user/register");
            mav.addObject("error", "Invalid input data / User already exists");

            return mav;
        }
       userService.registerUser(userRegisterModel);
        return mav;
    }

    @GetMapping("login")
    public ModelAndView login() {
        return new ModelAndView("user/login");
    }

    @PostMapping("login")
    public ModelAndView loginPost(@ModelAttribute  @Valid UserLoginModel userLoginModel) {
        ModelAndView mav = new ModelAndView("redirect:/home");

        if(!userService.loggedIn(userLoginModel)) {
            mav.setViewName("user/login");
            mav.addObject("error", "User not found");

            return mav;
        }
       // userService.loginUser(userLoginModel);
        return mav;
    }
}
