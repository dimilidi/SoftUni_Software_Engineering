package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.base.BaseEntity;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity {

    @Column
    private String name;

    @Column(name = "is_importer", columnDefinition = "TINYINT")
    private Boolean isImporter;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.EAGER)
    private Set<Part> parts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return Objects.equals(name, supplier.name)
                && Objects.equals(isImporter, supplier.isImporter)
                && Objects.equals(parts, supplier.parts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isImporter, parts);
    }
}

