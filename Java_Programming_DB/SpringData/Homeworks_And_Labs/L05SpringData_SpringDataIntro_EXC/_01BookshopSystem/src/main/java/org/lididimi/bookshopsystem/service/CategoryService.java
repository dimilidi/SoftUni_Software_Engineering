package org.lididimi.bookshopsystem.service;

import org.lididimi.bookshopsystem.entitty.Category;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface CategoryService {


    void seedCategories(List<Category> categories)throws IOException;

    boolean isDataSeeded();

    Set<Category> getRandomCategories();
}
