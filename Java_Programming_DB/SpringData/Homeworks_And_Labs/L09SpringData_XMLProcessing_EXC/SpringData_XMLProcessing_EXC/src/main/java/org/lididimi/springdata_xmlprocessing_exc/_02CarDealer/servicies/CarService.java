package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.servicies;

import jakarta.xml.bind.JAXBException;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.Car;

import java.io.IOException;
import java.util.List;

public interface CarService {
    Car getCarById(long id);

    boolean isCarRepositoryNotEmpty();

    boolean isCarRepositoryEmpty();

    void saveCars(List<Car> cars);

    long getRandomCarId();

    String findAllCarsFromMakeToyota() throws IOException, JAXBException;

    String findAllCarsAndTheirParts() throws IOException, JAXBException;
}
