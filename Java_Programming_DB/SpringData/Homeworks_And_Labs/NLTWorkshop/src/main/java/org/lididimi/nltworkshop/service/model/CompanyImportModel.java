package org.lididimi.nltworkshop.service.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class CompanyImportModel {

    @JacksonXmlProperty(isAttribute = true)
    private String name;

    public CompanyImportModel() {
    }

    public CompanyImportModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
