package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.repositories;

import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
