package org.lididimi.jsonparsing._02CarDealer.servicies;

import org.lididimi.jsonparsing._02CarDealer.entities.Car;

import java.io.IOException;
import java.util.List;

public interface CarService {
    Car getCarById(long id);

    boolean isCarRepositoryNotEmpty();

    boolean isCarRepositoryEmpty();

    void saveCars(List<Car> cars);

    long getRandomCarId();

    String findAllCarsFromMakeToyota() throws IOException;

    String findAllCarsAndTheirParts() throws IOException;
}
