package inheritance.entities;

import composition.Driver;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "trucks")
public class Truck extends Vehicle {

    public static final String TYPE = "TRUCK";
    @Column(name = "load_capacity")
    private double loadCapacity;

    @ManyToMany
    @JoinTable(
            name = "trucks_drivers",
            joinColumns = @JoinColumn(name = "truck_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id", referencedColumnName = "id"))
    private List<Driver> drivers;

    public Truck() {
        this.drivers = new ArrayList<>();
    }

    public Truck(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public Truck(String model, BigDecimal price, String fuelType, double loadCapacity) {
        super(TYPE, model, price, fuelType);

        this.loadCapacity = loadCapacity;
    }
}
