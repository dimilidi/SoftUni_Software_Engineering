package com.example.advquerying.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface ShampooService {
    List<String> findShampoosBySize(String size);

    List<String> findShampoosBySizeAndLabel(String size, Long id);

    List<String> findShampoosByPriceGreaterThan(BigDecimal price);

    Integer findShampooCountByPriceLessThan(BigDecimal price);

    List<String> findShampoosContainingIngredient(List<String> ingredients);

    List<String> findShampoosWithIngredientsCountLessThan(int count);

}
