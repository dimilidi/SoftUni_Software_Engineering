package inheritance.entities;

import composition.PlateNumber;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
//@Table(name = "cars")
public class Car extends Vehicle {

    public static final String TYPE = "CAR";
    @Basic
    private int seats;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "plate_number_id", referencedColumnName = "id")
    private PlateNumber plateNumber;

    public Car() {}

    public Car(String model, BigDecimal price, String fuelType, int seats, PlateNumber plateNumber) {
        super(TYPE, model, price, fuelType);

        this.seats = seats;
        this.plateNumber = plateNumber;
    }

}
