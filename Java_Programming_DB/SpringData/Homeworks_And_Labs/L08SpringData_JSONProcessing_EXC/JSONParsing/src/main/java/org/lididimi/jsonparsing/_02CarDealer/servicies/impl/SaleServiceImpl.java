package org.lididimi.jsonparsing._02CarDealer.servicies.impl;

import org.lididimi.jsonparsing._02CarDealer.entities.Part;
import org.lididimi.jsonparsing._02CarDealer.entities.Sale;
import org.lididimi.jsonparsing._02CarDealer.entities.dtos.sale.SaleDiscountDTO;
import org.lididimi.jsonparsing._02CarDealer.repositories.SaleRepository;
import org.lididimi.jsonparsing._02CarDealer.servicies.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.lididimi.jsonparsing._01ProductShop.utils.Utils.writeJsonIntoFile;
import static org.lididimi.jsonparsing._02CarDealer.constants.Constants.SALES_DISCOUNTS_SAVED_SUCCESSFULLY;
import static org.lididimi.jsonparsing._02CarDealer.constants.Paths.SALES_DISCOUNTS_FILE_PATH;


@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final ModelMapper mapper;

    public SaleServiceImpl(SaleRepository saleRepository, ModelMapper mapper) {
        this.saleRepository = saleRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean isSaleRepositoryNotEmpty() {
       return this.saleRepository.count() != 0;
    }

    @Override
    public void saveAll(Set<Sale> sales) {
        saleRepository.saveAllAndFlush(sales);
    }

    @Override
    public String findAllSalesWithInformationAboutCarAndCustomer() throws IOException {
        final List<SaleDiscountDTO> saleDiscountDTOS = this.saleRepository.findAll()
                .stream()
                .map(this::toSaleDiscountDTO)
                .toList();

        writeJsonIntoFile(saleDiscountDTOS, SALES_DISCOUNTS_FILE_PATH);

        return SALES_DISCOUNTS_SAVED_SUCCESSFULLY;
    }

    private SaleDiscountDTO toSaleDiscountDTO(Sale sale) {
        BigDecimal price = sale.getCar().getParts().stream()
                .map(Part::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        final BigDecimal priceWithDiscount = price.multiply(BigDecimal.valueOf(1 - sale.getDiscount()));

        final SaleDiscountDTO saleDiscountDTO = this.mapper.map(sale, SaleDiscountDTO.class);

        saleDiscountDTO.setCustomerName(sale.getCustomer().getName());
        saleDiscountDTO.setDiscount(sale.getDiscount());
        saleDiscountDTO.setPrice(price);
        saleDiscountDTO.setPriceWithDiscount(priceWithDiscount);

        return saleDiscountDTO;
    }
}