package org.lididimi.nltworkshop.web.controllers;

import org.lididimi.nltworkshop.service.CompanyService;
import org.lididimi.nltworkshop.service.EmployeeService;
import org.lididimi.nltworkshop.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

    private final ProjectService projectService;
    private final EmployeeService employeeService;
    private final CompanyService companyService;

    public HomeController(ProjectService projectService, EmployeeService employeeService, CompanyService companyService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
        this.companyService = companyService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public ModelAndView home() {
        boolean isAllDataImported = projectService.isImported() &&
                employeeService.isImported() &&
                companyService.isImported();

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("areImported", isAllDataImported);
        return modelAndView;
    }

}

