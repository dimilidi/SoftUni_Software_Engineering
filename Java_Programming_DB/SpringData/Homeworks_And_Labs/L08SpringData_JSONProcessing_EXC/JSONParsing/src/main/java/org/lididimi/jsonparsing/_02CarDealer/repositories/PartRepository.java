package org.lididimi.jsonparsing._02CarDealer.repositories;

import org.lididimi.jsonparsing._02CarDealer.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, Long> {
}
