package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.servicies;

import jakarta.xml.bind.JAXBException;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.Supplier;

import java.io.IOException;
import java.util.List;

public interface SupplierService {
    Supplier getSupplierById(long id);

    long getRandomSupplierId();

    boolean isSupplierRepositoryNotEmpty();

    void saveAll(List<Supplier> suppliers);

    String findAllLocalSuppliers() throws IOException, JAXBException;
}
