package inheritance.entities;

import composition.Company;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
//@Table(name = "plains")
public class Plane extends Vehicle {

    public static final String TYPE = "PLAIN";

    @Column(name = "passenger_capacity")
    private int passengerCapacity;

    @ManyToOne
    private Company owner;

    public Plane() {}

    public Plane(String model, BigDecimal price, String fuelType, int passengerCapacity, Company owner) {
        super(TYPE, model, price, fuelType);

        this.passengerCapacity = passengerCapacity;
        this.owner = owner;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public Company getOwner() {
        return owner;
    }

    public void setOwner(Company owner) {
        this.owner = owner;
    }
}
