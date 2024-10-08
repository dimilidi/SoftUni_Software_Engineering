package org.lididimi.jsonparsing._02CarDealer.servicies.impl;

import com.google.gson.Gson;
import org.lididimi.jsonparsing._02CarDealer.entities.*;
import org.lididimi.jsonparsing._02CarDealer.entities.dtos.car.CarImportDTO;
import org.lididimi.jsonparsing._02CarDealer.entities.dtos.customer.CustomerImportDTO;
import org.lididimi.jsonparsing._02CarDealer.entities.dtos.part.PartImportDTO;
import org.lididimi.jsonparsing._02CarDealer.entities.dtos.supplier.SupplierImportDTO;
import org.lididimi.jsonparsing._02CarDealer.entities.enums.DiscountType;
import org.lididimi.jsonparsing._02CarDealer.servicies.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.IntStream;

import static org.lididimi.jsonparsing._02CarDealer.constants.Constants.*;
import static org.lididimi.jsonparsing._02CarDealer.constants.Paths.*;


@Service
public class SeedServiceImpl implements SeedService {

    private final Gson gson;
    private final ModelMapper mapper;
    private final Random random;
    private final CarService carService;
    private final CustomerService customerService;
    private final SupplierService supplierService;
    private final PartService partService;
    private final SaleService saleService;

    public SeedServiceImpl(
            Gson gson,
            ModelMapper mapper,
            Random random,
            CarService carService,
            CustomerService customerService,
            SupplierService supplierService,
            PartService partService, SaleService saleService
    ) {
        this.gson = gson;
        this.mapper = mapper;
        this.random = random;
        this.carService = carService;
        this.customerService = customerService;
        this.supplierService = supplierService;
        this.partService = partService;
        this.saleService = saleService;
    }

    @Override
    public String seedSuppliers() throws IOException {
        if (supplierService.isSupplierRepositoryNotEmpty()) {
            return SUPPLIERS_DATA_ALREADY_SEEDED;
        }

        String json = Files.readString(SUPPLIERS_FILE_PATH);
        List<Supplier> suppliers = parseJsonToList(json, SupplierImportDTO[].class, Supplier.class);
        this.supplierService.saveAll(suppliers);

        return SUPPLIERS_DATA_SEEDED_SUCCESSFULLY;
    }

    @Override
    public String seedParts() throws IOException {
        if (this.partService.isPartRepositoryNotEmpty()) {
            return PARTS_DATA_ALREADY_SEEDED;
        }

        String json = Files.readString(PARTS_FILE_PATH);
        List<Part> parts = parseJsonToList(json, PartImportDTO[].class, Part.class);
        parts.forEach(this::setRandomSupplier);
        this. partService.saveAll(parts);

        return PARTS_DATA_SEEDED_SUCCESSFULLY;
    }

    @Override
    public String seedCars() throws IOException {
        if (carService.isCarRepositoryNotEmpty()) {
            return CARS_DATA_ALREADY_SEEDED;
        }

        String json = Files.readString(CARS_FILE_PATH);
        List<Car> cars = parseJsonToList(json, CarImportDTO[].class, Car.class);
        cars.forEach(this::setRandomParts);
        carService.saveCars(cars);

        return CARS_DATA_SEEDED_SUCCESSFULLY;
    }

    @Override
    public String seedCustomers() throws IOException {
        if (customerService.isCustomerRepositoryNotEmpty()) {
            return CUSTOMERS_DATA_ALREADY_SEEDED;
        }

        String json = Files.readString(CUSTOMERS_FILE_PATH);
        List<Customer> customers = parseJsonToList(json, CustomerImportDTO[].class, Customer.class);
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

    private <T, R> List<R> parseJsonToList(String json, Class<T[]> dtoClass, Class<R> entityClass) {
        // Parse JSON into array of DTOs
        T[] dtoArray = this.gson.fromJson(json, dtoClass);

        // Map each DTO to the target entity class
        return Arrays.stream(dtoArray)
                .map(dto -> this.mapper.map(dto, entityClass))
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