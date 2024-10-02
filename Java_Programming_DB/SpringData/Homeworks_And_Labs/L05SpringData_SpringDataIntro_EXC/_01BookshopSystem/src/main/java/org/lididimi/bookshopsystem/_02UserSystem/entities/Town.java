package org.lididimi.bookshopsystem._02UserSystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "towns")
public class Town extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    public Town() {}

    public Town(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
