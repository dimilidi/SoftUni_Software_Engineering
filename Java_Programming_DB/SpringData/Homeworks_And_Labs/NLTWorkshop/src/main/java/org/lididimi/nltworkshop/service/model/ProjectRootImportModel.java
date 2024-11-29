package org.lididimi.nltworkshop.service.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "projects")
public class ProjectRootImportModel {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "project")
    private List<ProjectImportModel> projects;


    public List<ProjectImportModel> getProjectImportModels() {
        return projects;
    }

    public void setProjectImportModels(List<ProjectImportModel> projectImportModels) {
        this.projects = projectImportModels;
    }

}
