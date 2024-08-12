package com.resellerapp.controller;

import com.resellerapp.model.UserLoginBindingModel;
import com.resellerapp.model.UserRegisterBindingModel;
import com.resellerapp.util.LoggedUser;
import com.resellerapp.service.UserService;
import com.resellerapp.util.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;
    private final LoggedUser loggedUser;

    public UserController(UserService userService, LoggedUser loggedUser) {
        this.userService = userService;
        this.loggedUser = loggedUser;
    }

    @ModelAttribute("registerData")
    public UserRegisterBindingModel initRegisterData() {
        return new UserRegisterBindingModel();
    }

    @ModelAttribute("loginData")
    public UserLoginBindingModel initLoginData() {
        return new UserLoginBindingModel();
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return SecurityUtils
                .checkAccess(userService, "home", false)
                .orElseGet(() -> new ModelAndView("login"));
    }

    @PostMapping("/login")
    public ModelAndView login(
            @Valid @ModelAttribute("userLoginBindingModel") UserLoginBindingModel userLoginBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
            ) {
        return SecurityUtils
                .checkAccess(userService, "home", false)
                .orElseGet(() -> {
                    if (bindingResult.hasErrors()) {
                        redirectAttributes.addFlashAttribute("loginData", userLoginBindingModel);
                        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginData", bindingResult);

                        return new ModelAndView("redirect:/login");
                    }

                    boolean isLogged = userService.login(userLoginBindingModel);

                    if (!isLogged) {
                        redirectAttributes.addFlashAttribute("loginData", userLoginBindingModel);
                        redirectAttributes.addFlashAttribute("badCredentials", true);

                        return new ModelAndView("redirect:/login");
                    }

                    return new ModelAndView("redirect:/home");
                });
    }

     @GetMapping("/register")
    public ModelAndView register() {
         return SecurityUtils
                 .checkAccess(userService, "home", false)
                 .orElseGet(() -> new ModelAndView("register"));
    }

     @PostMapping("/register")
    public ModelAndView register(
            @Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
            ) {
         return SecurityUtils
                 .checkAccess(userService, "home", false)
                 .orElseGet(() -> {
                     if (bindingResult.hasErrors()) {
                         redirectAttributes.addFlashAttribute("registerData", userRegisterBindingModel);
                         redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);

                         return new ModelAndView("redirect:/register");
                     }

                     boolean isRegistered = userService.register(userRegisterBindingModel);

                     String view = isRegistered ? "redirect:/login" : "register";
                     return new ModelAndView(view);
                 });
    }

    @PostMapping("/logout")
    public  ModelAndView logout() {
        return SecurityUtils
                .checkAccess(userService, "index", false)
                .orElseGet(() -> {
                    userService.logout();
                    return new ModelAndView("redirect:/");
                });
    }
}
