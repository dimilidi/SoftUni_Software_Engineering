package inheritance.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
//@Table(name = "bikes")
public class Bike extends Vehicle{

    public static final String TYPE = "BIKE";

    public Bike() {}

    public Bike(String model, BigDecimal price, String fuelType) {
        super(TYPE, model, price, fuelType);
    }

}
