package org.lididimi.springdata_xmlprocessing_exc._02CarDealer.servicies.impl;

import jakarta.xml.bind.JAXBException;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.Part;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.Sale;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.sale.SaleDiscountDTO;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.entities.dtos.sale.wrappers.SaleDiscountWrapperDTO;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.repositories.SaleRepository;
import org.lididimi.springdata_xmlprocessing_exc._02CarDealer.servicies.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.lididimi.springdata_xmlprocessing_exc._02CarDealer.constants.Constants.SALES_DISCOUNTS_SAVED_SUCCESSFULLY;
import static org.lididimi.springdata_xmlprocessing_exc._02CarDealer.constants.Paths.SALES_DISCOUNTS_FILE_PATH;
import static org.lididimi.springdata_xmlprocessing_exc._02CarDealer.utils.Utils.writeXMLIntoFile;


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
    public String findAllSalesWithInformationAboutCarAndCustomer() throws IOException, JAXBException {
        final List<SaleDiscountDTO> saleDiscountDTOS = this.saleRepository.findAll()
                .stream()
                .map(this::toSaleDiscountDTO)
                .toList();

        SaleDiscountWrapperDTO saleDiscountWrapperDTO = new SaleDiscountWrapperDTO(saleDiscountDTOS);

        writeXMLIntoFile(saleDiscountWrapperDTO, SALES_DISCOUNTS_FILE_PATH);

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