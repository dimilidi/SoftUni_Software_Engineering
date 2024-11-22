package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.repositories;

import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<List<Car>> findAllByMakeOrderByModelAscTravelledDistanceDesc(String make);

}