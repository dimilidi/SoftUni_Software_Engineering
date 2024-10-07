package org.lididimi.jsonparsing._01ProductShop.servicies;

import java.io.IOException;
import java.math.BigDecimal;

public interface ProductService {
    String findAllProductsInPriceRange(BigDecimal minRange, BigDecimal maxRange) throws IOException;
}
