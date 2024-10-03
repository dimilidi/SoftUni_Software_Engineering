package com.example.advquerying.services.impl;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.repositories.IngredientsRepository;
import com.example.advquerying.services.IngredientService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientsRepository ingredientRepository;

    public IngredientServiceImpl(IngredientsRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<String> findIngredientsByNameStartLetters(String string) {
        return ingredientRepository
                .findAllByNameStartingWith(string)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(Ingredient::getName)
                .toList();
    }

    @Override
    public List<String> findIngredientsByName(List<String> names) {
        return ingredientRepository
                .findAllByNameInOrderByPrice(names)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(Ingredient::getName)
                .toList();
    }

    @Override
    public void deleteByName(String name) {
        ingredientRepository.deleteByName(name);
    }

    @Override
    public void increasePrice(BigDecimal percentage) {
        ingredientRepository.increasePriceByPercent(percentage);
    }

    @Override
    public void updatePriceByName(List<String> ingredients, BigDecimal percentage) {
        ingredientRepository.updatePriceByName(ingredients, percentage);
    }
}
