package com.plannerapp.controller;

import com.plannerapp.model.dto.user.UserLoginBindingModel;
import com.plannerapp.model.dto.user.UserRegisterBindingModel;
import com.plannerapp.service.UserService;
import com.plannerapp.util.LoggedUser;
import com.plannerapp.util.security.CheckSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CheckSecurity(shouldBeLoggedIn = false)
    @GetMapping("/login")
    public ModelAndView login(@ModelAttribute("userLoginBindingModel")  UserLoginBindingModel userLoginBindingModel) {
        return new ModelAndView("login");
    }


    @CheckSecurity(shouldBeLoggedIn = false)
    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("userLoginBindingModel") @Valid UserLoginBindingModel userLoginBindingModel,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return  new ModelAndView("login");
        }

        boolean hasSuccessfulLogin = userService.login(userLoginBindingModel);

        if (!hasSuccessfulLogin) {
            ModelAndView modelAndView =  new ModelAndView("login");
            modelAndView.addObject("hasLoginError", true);
            return  modelAndView;
        }

        return new ModelAndView("redirect:/home");
    }

    @CheckSecurity(shouldBeLoggedIn = false)
    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute("userRegisterBindingModel")  UserRegisterBindingModel userRegisterBindingModel) {
        return new ModelAndView("register");
    }

    @CheckSecurity(shouldBeLoggedIn = false)
   @PostMapping("/register")
    public ModelAndView register(
            @Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
   ) {
       if (bindingResult.hasErrors()) {
           redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
           redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);

           return  new ModelAndView("register");
       }

       boolean hasSuccessfulRegistration = userService.register(userRegisterBindingModel);

        if (!hasSuccessfulRegistration) {
           ModelAndView modelAndView =  new ModelAndView("register");
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("hasRegistrationError", true);

            return  modelAndView;
        }

       return new ModelAndView("redirect:/login");
    }

    @CheckSecurity(shouldBeLoggedIn = true)
    @PostMapping("/logout")
    public ModelAndView logout() {
        this.userService.logout();
        return new ModelAndView("redirect:/");
    }
}
