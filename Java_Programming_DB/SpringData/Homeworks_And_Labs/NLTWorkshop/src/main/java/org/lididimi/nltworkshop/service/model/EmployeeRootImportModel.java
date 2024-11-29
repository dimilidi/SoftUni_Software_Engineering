package org.lididimi.nltworkshop.service.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "employees")
public class EmployeeRootImportModel {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "employee")
    private List<EmployeeImportModel> employees;

    public List<EmployeeImportModel> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeImportModel> employees) {
        this.employees = employees;
    }
}
