package org.lididimi.nltworkshop.service.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class EmployeeImportModel {

    @JacksonXmlProperty(localName = "first-name")
    private String firstName;

    @JacksonXmlProperty(localName = "last-name")
    private String lastName;

    @JacksonXmlProperty()
    private Integer age;

    @JacksonXmlProperty()
    private ProjectImportModel project;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ProjectImportModel getProject() {
        return project;
    }

    public void setProject(ProjectImportModel project) {
        this.project = project;
    }
}
