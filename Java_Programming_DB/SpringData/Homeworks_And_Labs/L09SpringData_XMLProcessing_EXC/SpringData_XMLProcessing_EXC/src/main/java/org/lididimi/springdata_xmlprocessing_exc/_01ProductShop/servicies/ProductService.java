package org.lididimi.springdata_xmlprocessing_exc._01ProductShop.servicies;

import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.math.BigDecimal;

public interface ProductService {
    String findAllProductsInPriceRange(BigDecimal minRange, BigDecimal maxRange) throws IOException, JAXBException;
}
