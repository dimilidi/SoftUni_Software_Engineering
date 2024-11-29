package org.lididimi.nltworkshop.service.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.math.BigDecimal;

public class ProjectImportModel {

    @JacksonXmlProperty
    private String name;

    @JacksonXmlProperty
    private String description;

    @JacksonXmlProperty(localName = "start-date")
    private String startDate;

    @JacksonXmlProperty(localName = "is-finished")
    private boolean isFinished;

    @JacksonXmlProperty()
    private BigDecimal payment;

    @JacksonXmlProperty(localName = "company")
    private CompanyImportModel companyName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public CompanyImportModel getCompanyName() {
        return companyName;
    }

    public void setCompanyName(CompanyImportModel companyName) {
        this.companyName = companyName;
    }
}
