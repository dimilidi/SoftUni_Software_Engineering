package org.lididimi.nltworkshop.service.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "companies")
public class CompanyRootImportModel {

    @JacksonXmlProperty(localName = "company")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<CompanyImportModel> companies;

    public CompanyRootImportModel() {
    }

    public CompanyRootImportModel(List<CompanyImportModel> companies) {
        this.companies = companies;
    }

    public List<CompanyImportModel> getCompanies() {
        return companies;
    }

    public void setCompanies(List<CompanyImportModel> companies) {
        this.companies = companies;
    }
}
