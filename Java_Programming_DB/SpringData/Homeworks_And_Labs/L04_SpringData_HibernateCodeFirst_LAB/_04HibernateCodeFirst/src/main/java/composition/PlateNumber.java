package composition;

import inheritance.entities.Car;
import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "plate_numbers")
public class PlateNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String number;

    @OneToOne(targetEntity = Car.class, mappedBy = "plateNumber")
    private Car car;

    public PlateNumber() {}

    public PlateNumber(String number) {
        this.number = number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
