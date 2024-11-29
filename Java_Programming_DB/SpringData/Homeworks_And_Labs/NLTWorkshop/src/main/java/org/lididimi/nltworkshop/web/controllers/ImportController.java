package org.lididimi.nltworkshop.web.controllers;

import jakarta.xml.bind.JAXBException;
import org.lididimi.nltworkshop.service.CompanyService;
import org.lididimi.nltworkshop.service.EmployeeService;
import org.lididimi.nltworkshop.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/import")
public class ImportController {
    private final CompanyService companyService;
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    public ImportController(CompanyService companyService, ProjectService projectService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/xml")
    public ModelAndView xml() {
        boolean[] imported = {
                companyService.isImported(),
                projectService.isImported(),
                employeeService.isImported()
        };

        ModelAndView mav = new ModelAndView("xml/import-xml");
        mav.addObject("areImported", imported);
        return mav;
    }

   @GetMapping("/companies")
    public ModelAndView companies() throws IOException {
        ModelAndView mav = new ModelAndView("xml/import-companies");
        mav.addObject("companies", companyService.readFile());
        return mav;
    }

    @PostMapping("/companies")
    public ModelAndView companiesPost() throws JAXBException, IOException {
        ModelAndView mav = new ModelAndView("redirect:xml");
        companyService.seedData();
        return mav;
    }

    @GetMapping("/projects")
    public ModelAndView projects() throws IOException {
        ModelAndView mav = new ModelAndView("xml/import-projects");
        mav.addObject("projects", projectService.readFile());

        return mav;
    }

    @PostMapping("/projects")
    public ModelAndView projectsPost() throws JAXBException, IOException {
        ModelAndView mav = new ModelAndView("redirect:xml");
        projectService.seedData();
        return mav;
    }

    @GetMapping("/employees")
    public ModelAndView employees() throws IOException {
        ModelAndView mav = new ModelAndView("xml/import-employees");
        mav.addObject("employees", employeeService.readFile());
        return mav;
    }

    @PostMapping("/employees")
    public ModelAndView employeesPost() throws IOException, JAXBException {
        ModelAndView mav = new ModelAndView("redirect:xml");
        employeeService.seedData();
        return mav;
    }
}
