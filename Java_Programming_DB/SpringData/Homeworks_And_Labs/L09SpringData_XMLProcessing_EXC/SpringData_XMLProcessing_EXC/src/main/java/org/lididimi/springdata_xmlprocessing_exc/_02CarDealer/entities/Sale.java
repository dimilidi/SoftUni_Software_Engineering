package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

    @Column
    private Double discount;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private Car car;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private Customer customer;
}