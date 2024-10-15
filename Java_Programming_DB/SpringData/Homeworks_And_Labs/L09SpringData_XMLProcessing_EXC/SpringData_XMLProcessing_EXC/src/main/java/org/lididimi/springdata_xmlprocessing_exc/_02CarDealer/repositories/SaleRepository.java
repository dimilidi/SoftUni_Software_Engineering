package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.repositories;

import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
