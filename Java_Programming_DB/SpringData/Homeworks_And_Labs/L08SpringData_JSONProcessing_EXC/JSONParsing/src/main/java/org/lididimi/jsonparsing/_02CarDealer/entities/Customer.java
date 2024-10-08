package org.lididimi.jsonparsing._02CarDealer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lididimi.jsonparsing._02CarDealer.entities.base.BaseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Column
    private String name;

    @Column(name = "birth_date")
    private LocalDateTime birthDate;

    @Column(name = "is_young_driver", columnDefinition = "BOOLEAN")
    private Boolean isYoungDriver;

    @OneToMany(mappedBy = "customer",fetch = FetchType.EAGER)
    private Set<Sale> sales;
}
