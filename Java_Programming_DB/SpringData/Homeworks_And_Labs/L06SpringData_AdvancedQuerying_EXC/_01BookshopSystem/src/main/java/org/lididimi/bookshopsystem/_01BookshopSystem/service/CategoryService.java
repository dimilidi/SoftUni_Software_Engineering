package org.lididimi.bookshopsystem._01BookshopSystem.service;

import org.lididimi.bookshopsystem._01BookshopSystem.entitty.Category;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface CategoryService {

    void seedCategories(List<Category> categories)throws IOException;

    boolean isDataSeeded();

    Set<Category> getRandomCategories();
}
