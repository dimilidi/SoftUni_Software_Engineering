package com.plannerapp.controller;

import com.plannerapp.model.dto.task.TasksAddBindingModel;
import com.plannerapp.service.TaskService;
import com.plannerapp.util.LoggedUser;
import com.plannerapp.util.security.CheckSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final LoggedUser loggedUser;

    public TaskController(TaskService taskService, LoggedUser loggedUser) {
        this.taskService = taskService;
        this.loggedUser = loggedUser;
    }

    @CheckSecurity(shouldBeLoggedIn = true)
    @GetMapping("/add")
    public ModelAndView add(@ModelAttribute("tasksAddBindingModel") TasksAddBindingModel tasksAddBindingModel) {
        return new ModelAndView("task-add");
    }

    @CheckSecurity(shouldBeLoggedIn = true)
    @PostMapping("/add")
    public ModelAndView add(
            @ModelAttribute("tasksAddBindingModel") @Valid TasksAddBindingModel tasksAddBindingModel,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return  new ModelAndView("task-add");
        }

        taskService.add(tasksAddBindingModel);

        return new ModelAndView("redirect:/home");
    }

    @CheckSecurity(shouldBeLoggedIn = true)
    @PostMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        taskService.remove(id);

        return new ModelAndView("redirect:/home");
    }

    @CheckSecurity(shouldBeLoggedIn = true)
    @PostMapping("/return/{id}")
    public ModelAndView returnTask(@PathVariable("id") Long id) {
        taskService.assign(id, null);

        return new ModelAndView("redirect:/home");
    }

    @CheckSecurity(shouldBeLoggedIn = true)
    @PostMapping("/assign/{id}")
    public ModelAndView assign(@PathVariable("id") Long id) {
        String username = loggedUser.getUsername();

        taskService.assign(id, username);

        return new ModelAndView("redirect:/home");
    }
}
