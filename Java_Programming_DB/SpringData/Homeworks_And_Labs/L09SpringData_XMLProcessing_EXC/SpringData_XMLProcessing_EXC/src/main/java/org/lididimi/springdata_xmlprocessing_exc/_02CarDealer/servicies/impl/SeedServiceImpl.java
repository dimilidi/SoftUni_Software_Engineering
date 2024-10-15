package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.servicies.impl;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.*;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.car.wrappers.CarImportWrapperDTO;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.customer.wrappers.CustomerImportWrapperDTO;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.part.wrappers.PartImportWrapperDTO;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.supplier.wrappers.SupplierImportWrapperDTO;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.enums.DiscountType;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.servicies.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

import static org.lididimi.springdata_xmlprocessing_exc._02CarDealer.constants.Constants.*;
import static org.lididimi.springdata_xmlprocessing_exc._02CarDealer.constants.Paths.*;


@Service
public class SeedServiceImpl implements SeedService {

    private final ModelMapper mapper;
    private final Random random;
    private final CarService carService;
    private final CustomerService customerService;
    private final SupplierService supplierService;
    private final PartService partService;
    private final SaleService saleService;

    public SeedServiceImpl(
            ModelMapper mapper,
            Random random,
            CarService carService,
            CustomerService customerService,
            SupplierService supplierService,
            PartService partService, SaleService saleService
    ) {
        this.mapper = mapper;
        this.random = random;
        this.carService = carService;
        this.customerService = customerService;
        this.supplierService = supplierService;
        this.partService = partService;
        this.saleService = saleService;
    }

    @Override
    public String seedSuppliers() throws  JAXBException {
        if (supplierService.isSupplierRepositoryNotEmpty()) {
            return SUPPLIERS_DATA_ALREADY_SEEDED;
        }

        File xml = SUPPLIERS_FILE_PATH.toFile();
        List<Supplier> suppliers = parseAndMapXML(
                xml,
                SupplierImportWrapperDTO.class,
                SupplierImportWrapperDTO::getSuppliers,
                Supplier.class
        );
        this.supplierService.saveAll(suppliers);

        return SUPPLIERS_DATA_SEEDED_SUCCESSFULLY;
    }

    @Override
    public String seedParts() throws JAXBException {
        if (this.partService.isPartRepositoryNotEmpty()) {
            return PARTS_DATA_ALREADY_SEEDED;
        }

        File xml = PARTS_FILE_PATH.toFile();
        List<Part> parts = parseAndMapXML(
                xml,
                PartImportWrapperDTO.class,
                PartImportWrapperDTO::getParts,
                Part.class
        );
        parts.forEach(this::setRandomSupplier);
        this. partService.saveAll(parts);

        return PARTS_DATA_SEEDED_SUCCESSFULLY;
    }

    @Override
    public String seedCars() throws JAXBException {
        if (carService.isCarRepositoryNotEmpty()) {
            return CARS_DATA_ALREADY_SEEDED;
        }

        File xml = CARS_FILE_PATH.toFile();
        List<Car> cars = parseAndMapXML(
                xml,
                CarImportWrapperDTO.class,
                CarImportWrapperDTO::getCars,
                Car.class
        );
        cars.forEach(this::setRandomParts);
        carService.saveCars(cars);

        return CARS_DATA_SEEDED_SUCCESSFULLY;
    }

    @Override
    public String seedCustomers() throws JAXBException {
        if (customerService.isCustomerRepositoryNotEmpty()) {
            return CUSTOMERS_DATA_ALREADY_SEEDED;
        }

        File xml = CUSTOMERS_FILE_PATH.toFile();
        List<Customer> customers = parseAndMapXML(
                xml,
                CustomerImportWrapperDTO.class,
                CustomerImportWrapperDTO::getCustomers,
                Customer.class
        );
        this.customerService.saveAll(customers);

        return CUSTOMERS_DATA_SEEDED_SUCCESSFULLY;
    }

    @Override
    public String populateSales() {
        String validationMessage = validateRepositories();

        if (validationMessage != null) {
            return validationMessage;
        }

        final Set<Sale> sales = createSales();
        this.saleService.saveAll(sales);

        return SALES_DATA_SEEDED_SUCCESSFULLY;
    }

    private Set<Sale> createSales() {
        final Set<Sale> sales = new HashSet<>();

        IntStream
                .range(0, SALES_COUNT)
                .forEach(i -> {
                    Car car = this.carService.getCarById(carService.getRandomCarId());
                    Customer customer = this.customerService.getCustomerById(customerService.getRandomCustomerId());
                    double discountPercentage = getRandomDiscount();
                    sales.add(new Sale(discountPercentage, car, customer));
        });

        return sales;
    }


    private String validateRepositories() {
        if (carService.isCarRepositoryEmpty() || customerService.isCustomerRepositoryEmpty()) {
            return CAR_OR_CUSTOMER_TABLE_EMPTY;
        }

        if (saleService.isSaleRepositoryNotEmpty()) {
            return SALES_DATA_ALREADY_SEEDED;
        }

        return null;
    }

    private double getRandomDiscount() {
        final List<DiscountType> discountTypes = Arrays.asList(DiscountType.values());
        final int randomDiscountId = this.random.nextInt(1, DiscountType.values().length);

        return discountTypes.get(randomDiscountId).getPercentage();
    }


    private <T, D, E> List<E> parseAndMapXML(File xmlFile, Class<T> wrapperClass, Function<T, List<D>> getDtoListFunction, Class<E> entityClass) throws JAXBException {
        // Create JAXB context and unmarshaller for the wrapper class
        JAXBContext context = JAXBContext.newInstance(wrapperClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        // Unmarshal the XML file into the wrapper class instance
        T wrapperDTO = (T) unmarshaller.unmarshal(xmlFile);

        // Extract the list of individual DTOs from the wrapper DTO using the passed function
        List<D> dtoList = getDtoListFunction.apply(wrapperDTO);

        // Map each DTO to the target entity class
        return dtoList.stream()
                .map(dto -> mapper.map(dto, entityClass))
                .toList();
    }

    private void setRandomSupplier(Part part) {
        final Supplier supplier = this.supplierService.getSupplierById(supplierService.getRandomSupplierId());

        part.setSupplier(supplier);
    }

    private void setRandomParts(Car car) {
        final Set<Part> parts = new HashSet<>();

        while (parts.size() < 3) {
            final Part part = this.partService.getPartById(partService.getRandomPartId());
            parts.add(part);
        }

        car.setParts(parts);
    }
}