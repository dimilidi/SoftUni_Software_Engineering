package org.lididimi.nltworkshop.data.entities;

import jakarta.persistence.*;
import org.lididimi.nltworkshop.data.entities.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

    @Column(nullable = false)
     private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
     private String description;

    @Column(name = "is_finished")
     private Boolean isFinished;

    @Column(nullable = false)
     private BigDecimal payment;

    @Column(name = "start_date")
     private LocalDate startDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
     private Company company;

    @Override
    public String toString() {
        return String.format("Project Name: %s%n  Description: %s%n  %.2f", name, description, payment);
    }

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

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
