package org.lididimi.jsonparsing._02CarDealer.repositories;

import org.lididimi.jsonparsing._02CarDealer.entities.Customer;
import org.lididimi.jsonparsing._02CarDealer.entities.dtos.customer.CustomerWithTotalSalesDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<List<Customer>> findAllByOrderByBirthDateAscIsYoungDriverAsc();

    @Query("""
            SELECT NEW org.lididimi.jsonparsing._02CarDealer.entities.dtos.customer.CustomerWithTotalSalesDTO(c.name, COUNT(DISTINCT s.car.id), SUM(p.price ))
            FROM Customer c
            JOIN c.sales s
            JOIN s.car car
            JOIN car.parts p
            GROUP BY c.id
            HAVING COUNT(DISTINCT s.car.id) > 0
            ORDER BY SUM(p.price) DESC, COUNT(DISTINCT s.car.id) DESC

            """)
    Optional<List<CustomerWithTotalSalesDTO>> findAllBoughtCars();

}