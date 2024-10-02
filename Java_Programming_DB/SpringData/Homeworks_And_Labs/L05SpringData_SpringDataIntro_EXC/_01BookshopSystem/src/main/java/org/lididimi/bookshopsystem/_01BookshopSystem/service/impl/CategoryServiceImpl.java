package org.lididimi.bookshopsystem._01BookshopSystem.service.impl;

import org.lididimi.bookshopsystem._01BookshopSystem.entitty.Category;
import org.lididimi.bookshopsystem._01BookshopSystem.repository.CategoryRepository;
import org.lididimi.bookshopsystem._01BookshopSystem.service.CategoryService;
import org.springframework.stereotype.Service;

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
