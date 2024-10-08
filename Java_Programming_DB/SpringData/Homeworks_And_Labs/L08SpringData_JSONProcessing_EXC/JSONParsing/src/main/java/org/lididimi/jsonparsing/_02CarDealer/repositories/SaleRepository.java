package org.lididimi.jsonparsing._02CarDealer.repositories;

import org.lididimi.jsonparsing._02CarDealer.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
