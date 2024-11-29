package org.lididimi.nltworkshop.web.controllers;

import org.lididimi.nltworkshop.service.EmployeeService;
import org.lididimi.nltworkshop.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/export")
public class ExportController {

    private final ProjectService projectsService;
    private final EmployeeService employeeService;

    public ExportController(ProjectService projectsService, EmployeeService employeeService) {
        this.projectsService = projectsService;
        this.employeeService = employeeService;
    }

    @GetMapping("/project-if-finished")
    public ModelAndView finishedProjects() {
        String finishedProjects = projectsService.getFinishedProjects();
        ModelAndView mav = new ModelAndView("export/export-project-if-finished");
        mav.addObject("projectsIfFinished", finishedProjects);
        return mav;
    }

    @GetMapping("/employees-above")
    public ModelAndView employeesAbove() {
        String employeesInfo = employeeService.getЕmployeesAbove25();
        ModelAndView mav = new ModelAndView("export/export-employees-with-age");
        mav.addObject("employeesAbove", employeesInfo);
        return mav;
    }
}
