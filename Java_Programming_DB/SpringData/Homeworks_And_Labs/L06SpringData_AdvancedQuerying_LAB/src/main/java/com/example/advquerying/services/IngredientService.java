package com.example.advquerying.services;

import com.example.advquerying.entities.Ingredient;

import java.math.BigDecimal;
import java.util.List;

public interface IngredientService {
    List<String> findIngredientsByNameStartLetters(String string);

    List<String> findIngredientsByName(List<String> name);

    void deleteByName(String name);

    void increasePrice(BigDecimal percentage);

    void updatePriceByName(List<String> ingredients, BigDecimal percentage);

}
