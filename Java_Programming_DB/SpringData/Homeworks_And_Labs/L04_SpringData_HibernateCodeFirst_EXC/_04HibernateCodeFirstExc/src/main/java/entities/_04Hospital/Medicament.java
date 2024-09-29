package entities._04Hospital;

import entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medicaments")
public class Medicament extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "medicament")
    private Set<Visitation> visitations;

    public Medicament() {
        this.visitations = new HashSet<>();
    }

    public Medicament(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Visitation> getVisitations() {
        return visitations;
    }

    public void setVisitations(Set<Visitation> visitations) {
        this.visitations = visitations;
    }
}
