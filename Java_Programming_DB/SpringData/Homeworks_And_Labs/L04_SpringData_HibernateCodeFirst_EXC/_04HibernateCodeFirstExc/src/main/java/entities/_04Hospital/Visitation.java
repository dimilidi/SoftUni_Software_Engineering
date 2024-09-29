package entities._04Hospital;

import entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "visitations")
public class Visitation extends BaseEntity {

    @Column(columnDefinition = "DATETIME", nullable = false)
    private LocalDate date;

    @Column(columnDefinition = "TEXT", length = 1000, nullable = false)
    private String comments;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Diagnose diagnose;

    @ManyToOne
    private Medicament medicament;


    public Visitation() {
    }

    public Visitation(LocalDate date, String comments, Patient patient, Diagnose diagnose, Medicament medicament) {
       this();

        this.date = date;
        this.comments = comments;
        this.patient = patient;
        this.diagnose = diagnose;
        this.medicament = medicament;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }
}
