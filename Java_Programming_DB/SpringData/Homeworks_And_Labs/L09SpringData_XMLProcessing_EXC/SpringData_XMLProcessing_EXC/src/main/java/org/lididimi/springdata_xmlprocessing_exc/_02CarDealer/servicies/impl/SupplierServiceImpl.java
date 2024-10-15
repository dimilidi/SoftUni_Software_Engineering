package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.servicies.impl;

import jakarta.xml.bind.JAXBException;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.Supplier;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.supplier.SupplierNotImporterDTO;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.supplier.wrappers.SupplierNotImporterWrapperDTO;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.repositories.SupplierRepository;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.servicies.SupplierService;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.utils.Utils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.lididimi.springdata_xmlprocessing_exc._02CarDealer.constants.Constants.SUPPLIER_NOT_IMPORTER_BY_PART_COUNT_SAVED_SUCCESSFULLY;
import static org.lididimi.springdata_xmlprocessing_exc._02CarDealer.constants.Paths.LOCAL_SUPPLIERS_FILE_PATH;
import static org.lididimi.springdata_xmlprocessing_exc._02CarDealer.utils.Utils.writeXMLIntoFile;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final Random random;

    public SupplierServiceImpl(SupplierRepository supplierRepository, Random random) {
        this.supplierRepository = supplierRepository;
        this.random = random;
    }

    @Override
    public Supplier getSupplierById(long id) {
        return this.supplierRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public long getRandomSupplierId() {
        return Utils.getRandomEntityId(supplierRepository, random);
    }

    @Override
    public boolean isSupplierRepositoryNotEmpty() {
        return supplierRepository.count() > 0;
    }

    @Override
    public void saveAll(List<Supplier> suppliers) {
        supplierRepository.saveAllAndFlush(suppliers);
    }


    @Override
    public String findAllLocalSuppliers() throws IOException, JAXBException {
        final List<SupplierNotImporterDTO> suppliersNotImporterByPartCountDTO =
                this.supplierRepository.findAllByIsImporterFalseAndPartsCount()
                        .orElseThrow(NoSuchElementException::new);

        SupplierNotImporterWrapperDTO supplierDTO = new SupplierNotImporterWrapperDTO(suppliersNotImporterByPartCountDTO);
        writeXMLIntoFile(supplierDTO, LOCAL_SUPPLIERS_FILE_PATH);

        return SUPPLIER_NOT_IMPORTER_BY_PART_COUNT_SAVED_SUCCESSFULLY;
    }
}
