package entities._05BillsPaymentSystem;

import entities.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "billing_details")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BillingDetail extends BaseEntity {

    @Column(nullable = false)
    private String number;

    @ManyToOne
    private User owner;

    public BillingDetail() {}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
