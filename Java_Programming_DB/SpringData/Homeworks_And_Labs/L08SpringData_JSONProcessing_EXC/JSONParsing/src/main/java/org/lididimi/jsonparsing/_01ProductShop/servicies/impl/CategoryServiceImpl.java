package org.lididimi.jsonparsing._01ProductShop.servicies.impl;

import org.lididimi.jsonparsing._01ProductShop.entities.dtos.category.CategoryByProductsSummaryDTO;
import org.lididimi.jsonparsing._01ProductShop.repositories.CategoryRepository;
import org.lididimi.jsonparsing._01ProductShop.servicies.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.lididimi.jsonparsing._01ProductShop.constants.Constants.CATEGORIES_WITH_PRODUCTS_SAVED_SUCCESSFULLY;
import static org.lididimi.jsonparsing._01ProductShop.constants.Constants.NO_DATA_IN_CATEGORY_OR_PRODUCTS;
import static org.lididimi.jsonparsing._01ProductShop.constants.Paths.CATEGORIES_BY_PRODUCT_COUNT_FILE_PATH;
import static org.lididimi.jsonparsing._01ProductShop.utils.Utils.writeJsonIntoFile;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final ModelMapper mapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(ModelMapper mapper, CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String getCategoriesByProductSummary() throws IOException {
        final List<CategoryByProductsSummaryDTO> categoriesByProductSummary =
                this.categoryRepository.getCategoriesByProductSummary()
                        .orElseThrow(() -> new NoSuchElementException(NO_DATA_IN_CATEGORY_OR_PRODUCTS))
                        .stream()
                        .map(category -> this.mapper.map(category, CategoryByProductsSummaryDTO.class))
                        .toList();

        writeJsonIntoFile(categoriesByProductSummary, CATEGORIES_BY_PRODUCT_COUNT_FILE_PATH);

        return CATEGORIES_WITH_PRODUCTS_SAVED_SUCCESSFULLY;
    }
}
