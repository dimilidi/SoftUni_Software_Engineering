package org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(min = 3)
    private String lastName;

    @Column
    private int age;

    @OneToMany(targetEntity = Product.class, mappedBy = "seller")
    @Fetch(FetchMode.JOIN)
    private Set<Product>  sellingProducts;

    // @Fetch(FetchMode.JOIN) -> Hibernate-specific annotation (non-JPA standard);
    // an optimization that tells Hibernate to use a SQL join
    // to fetch related entities in a single query,
    // reducing database load and preventing N+1 issues.
    @Fetch(FetchMode.JOIN)
    @OneToMany(targetEntity = Product.class, mappedBy = "buyer")
    private Set<Product> boughtProducts;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    private Set<User> friends;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return  Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName)
                && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, getId());
    }
}