package org.lididimi.bookshopsystem.service.impl;

import org.lididimi.bookshopsystem.entitty.Author;
import org.lididimi.bookshopsystem.entitty.Category;
import org.lididimi.bookshopsystem.repository.CategoryRepository;
import org.lididimi.bookshopsystem.service.CategoryService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories(List<Category> categories) {
        this.categoryRepository.saveAllAndFlush(categories);
    }

    @Override
    public boolean isDataSeeded() {
        return this.categoryRepository.count() > 0;
    }

    @Override
    public Set<Category> getRandomCategories() {
        Set<Category> categories = new HashSet<>();
        int randomCount = ThreadLocalRandom
                .current()
                .nextInt(1, 4);
        for (int i = 0; i < randomCount ; i++) {
            Long randomId = ThreadLocalRandom
                    .current()
                    .nextLong(1, categoryRepository.count() + 1);
            categoryRepository.findById(randomId).ifPresent(categories::add);
        }

        return categories;
    }
}
