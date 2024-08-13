package com.plannerapp.controller;

import com.plannerapp.model.dto.task.TaskHomeViewModel;
import com.plannerapp.service.TaskService;
import com.plannerapp.util.LoggedUser;
import com.plannerapp.util.security.CheckSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private final TaskService taskService;
    private final LoggedUser loggedUser;

    public HomeController(TaskService taskService, LoggedUser loggedUser) {
        this.taskService = taskService;
        this.loggedUser = loggedUser;
    }

    @CheckSecurity(shouldBeLoggedIn = false)
    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }


    @CheckSecurity(shouldBeLoggedIn = true)
    @GetMapping("/home")
    public ModelAndView home() {
        TaskHomeViewModel viewModel = taskService.getHomeViewData(loggedUser.getUsername());

        return new ModelAndView("home", "tasks", viewModel);
    }
}
