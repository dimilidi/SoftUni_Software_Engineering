package org.lididimi.nltworkshop.service.impl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.lididimi.nltworkshop.data.entities.Company;
import org.lididimi.nltworkshop.data.entities.Project;
import org.lididimi.nltworkshop.repositories.CompanyRepository;
import org.lididimi.nltworkshop.repositories.ProjectRepository;
import org.lididimi.nltworkshop.service.CompanyService;
import org.lididimi.nltworkshop.service.ProjectService;
import org.lididimi.nltworkshop.service.model.ProjectImportModel;
import org.lididimi.nltworkshop.service.model.ProjectRootImportModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    public static final Path FILE_PATH = Path.of("src/main/resources/files/xmls/projects.xml");

    private final ProjectRepository projectRepository;
    private final CompanyService companyService;
    private final ModelMapper modelMapper;
    private final XmlMapper xmlMapper;
    private final CompanyRepository companyRepository;

    public ProjectServiceImpl(
            ProjectRepository projectRepository,
            CompanyService companyService,
            ModelMapper modelMapper,
            XmlMapper xmlMapper,
            CompanyRepository companyRepository) {
        this.projectRepository = projectRepository;
        this.companyService = companyService;
        this.modelMapper = modelMapper;
        this.xmlMapper = xmlMapper;
        this.companyRepository = companyRepository;
    }

    @Override
    public boolean isImported() {
        return projectRepository.count() > 0;
    }

    @Override
    public void seedData() throws IOException {
        ProjectRootImportModel projectRootImportModel = xmlMapper.readValue(FILE_PATH.toFile(), ProjectRootImportModel.class);
        List<ProjectImportModel> projectImportModels = projectRootImportModel.getProjectImportModels();

        projectImportModels.forEach(this::saveToDB);
    }

    private String saveToDB(ProjectImportModel projectImportModel) {
        Project project = modelMapper.map(projectImportModel, Project.class);
      //  project.setStartDate(LocalDate.parse(projectImportModel.getStartDate()));
        Optional<Company> companyOpt = companyRepository.findByName(projectImportModel.getCompanyName().getName());
        companyOpt.ifPresent(project::setCompany);
        projectRepository.save(project);

        return "Successfully imported project " + projectImportModel.getCompanyName().getName();
    }

    @Override
    public String readFile() throws IOException {
        return Files.readString(FILE_PATH);
    }

    @Override
    public String getFinishedProjects() {
        List<Project> projects = projectRepository.findByIsFinishedTrue();
        return projects.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }
}
