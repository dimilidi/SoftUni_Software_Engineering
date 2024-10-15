package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.repositories;

import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.Supplier;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.supplier.SupplierNotImporterDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    @Query("""
            SELECT NEW org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.supplier.SupplierNotImporterDTO(s.id, s.name, COUNT(p.id))
            FROM Supplier s
            JOIN s.parts p
            WHERE s.isImporter = FALSE
            GROUP BY s.id
            """)
    Optional<List<SupplierNotImporterDTO>> findAllByIsImporterFalseAndPartsCount();

}
