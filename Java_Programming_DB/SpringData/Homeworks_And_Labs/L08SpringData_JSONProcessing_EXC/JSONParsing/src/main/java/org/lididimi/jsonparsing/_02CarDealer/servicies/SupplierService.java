package org.lididimi.jsonparsing._02CarDealer.servicies;

import org.lididimi.jsonparsing._02CarDealer.entities.Supplier;

import java.io.IOException;
import java.util.List;

public interface SupplierService {
    Supplier getSupplierById(long id);

    long getRandomSupplierId();

    boolean isSupplierRepositoryNotEmpty();

    void saveAll(List<Supplier> suppliers);

    String findAllLocalSuppliers() throws IOException;
}
