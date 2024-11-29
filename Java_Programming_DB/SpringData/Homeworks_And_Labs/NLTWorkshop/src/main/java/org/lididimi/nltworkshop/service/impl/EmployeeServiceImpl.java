package org.lididimi.nltworkshop.service.impl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.xml.bind.JAXBException;
import org.lididimi.nltworkshop.data.entities.Employee;
import org.lididimi.nltworkshop.data.entities.Project;
import org.lididimi.nltworkshop.repositories.EmployeeRepository;
import org.lididimi.nltworkshop.repositories.ProjectRepository;
import org.lididimi.nltworkshop.service.EmployeeService;
import org.lididimi.nltworkshop.service.model.EmployeeImportModel;
import org.lididimi.nltworkshop.service.model.EmployeeRootImportModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public static final Path FILE_PATH = Path.of("src/main/resources/files/xmls/employees.xml");
    private final EmployeeRepository employeeRepository;
    private final XmlMapper xmlMapper;
    private final ModelMapper modelMapper;
    private final ProjectRepository projectRepository;

    public EmployeeServiceImpl(
            EmployeeRepository employeeRepository,
            XmlMapper xmlMapper,
            ModelMapper modelMapper,
            ProjectRepository projectRepository
    ) {
        this.employeeRepository = employeeRepository;
        this.xmlMapper = xmlMapper;
        this.modelMapper = modelMapper;
        this.projectRepository = projectRepository;
    }

    @Override
    public boolean isImported() {
        return employeeRepository.count() > 0;
    }

    @Override
    public void seedData() throws JAXBException, IOException, JAXBException {
        EmployeeRootImportModel employeeRootImportModel = xmlMapper.readValue(FILE_PATH.toFile(), EmployeeRootImportModel.class);
        employeeRootImportModel.getEmployees().forEach(this::saveToDB);
    }

    private String saveToDB(EmployeeImportModel employeeImportModel) {
        Employee employee = modelMapper.map(employeeImportModel, Employee.class);
        Optional<Project> projectOpt = projectRepository.findByName(employeeImportModel.getProject().getName());
        projectOpt.ifPresent(employee::setProject);
        employeeRepository.save(employee);
        return "Successfully inserted employee";
    }

    @Override
    public String readFile() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String getЕmployeesAbove25() {
        List<Employee> employees = employeeRepository.findByAgeGreaterThan(25);
        return employees.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }
}
