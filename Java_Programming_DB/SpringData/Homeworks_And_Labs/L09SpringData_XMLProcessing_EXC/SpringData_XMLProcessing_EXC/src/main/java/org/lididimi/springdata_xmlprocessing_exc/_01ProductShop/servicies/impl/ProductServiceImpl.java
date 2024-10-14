package org.lididimi.springdata_xmlprocessing_exc._01ProductShop.servicies.impl;

import jakarta.transaction.Transactional;
import jakarta.xml.bind.JAXBException;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.Product;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.product.ProductDTO;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.product.ProductInRangeWithNoBuyerDTO;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.product.wrapper.ProductsInRangeWithNoBuyerWrapperDTO;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.repositories.ProductRepository;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.servicies.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static org.lididimi.springdata_xmlprocessing_exc._01ProductShop.constants.Constants.NO_PRODUCTS_FOR_GIVEN_CRITERIA;
import static org.lididimi.springdata_xmlprocessing_exc._01ProductShop.constants.Constants.PRODUCTS_IN_RANGE_SAVED_SUCCESSFULLY;
import static org.lididimi.springdata_xmlprocessing_exc._01ProductShop.constants.Paths.PRODUCTS_IN_RANGE_FILE_PATH;
import static org.lididimi.springdata_xmlprocessing_exc._01ProductShop.utils.Utils.writeXMLIntoFile;

@Service
public class ProductServiceImpl implements ProductService {

    private final ModelMapper mapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ModelMapper mapper, ProductRepository productRepository) {
        this.mapper = mapper;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public String findAllProductsInPriceRange(BigDecimal minRange, BigDecimal maxRange) throws IOException, JAXBException {

        List<Product> products = this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(minRange, maxRange)
                        .orElseThrow(() -> new NoSuchElementException(NO_PRODUCTS_FOR_GIVEN_CRITERIA));

        final List<ProductInRangeWithNoBuyerDTO> productsInRangeDTO = products
                .stream()
                .map(product -> this.mapper.map(product, ProductDTO.class))
                .map(ProductDTO::toProductInRangeWithBuyerDTO)
                .toList();

        final ProductsInRangeWithNoBuyerWrapperDTO productsInRangeWithNoBuyerWrapperDTO =
                new ProductsInRangeWithNoBuyerWrapperDTO(productsInRangeDTO);

        writeXMLIntoFile(productsInRangeWithNoBuyerWrapperDTO, PRODUCTS_IN_RANGE_FILE_PATH);

        return PRODUCTS_IN_RANGE_SAVED_SUCCESSFULLY;
    }

}