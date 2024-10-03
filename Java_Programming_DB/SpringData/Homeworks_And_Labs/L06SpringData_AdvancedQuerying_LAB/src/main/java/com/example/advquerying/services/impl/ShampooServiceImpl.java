package com.example.advquerying.services.impl;

import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import com.example.advquerying.repositories.ShampooRepository;
import com.example.advquerying.services.ShampooService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.advquerying.constants.Constants.BRAND_SIZE_PRICE_FORMAT;

@Service
public class ShampooServiceImpl implements ShampooService {
    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<String> findShampoosBySize(String size) {
        Size sizeEnum = getSizeEnum(size);
        return shampooRepository.findAllBySizeOrderById(sizeEnum)
                .stream()
                .map(this::mapShampoo)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findShampoosBySizeAndLabel(String size, Long id) {
        Size sizeEnum = getSizeEnum(size);

        return shampooRepository.findAllBySizeOrLabelIdOrderByPrice(sizeEnum, id)
                .stream()
                .map(this::mapShampoo)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findShampoosByPriceGreaterThan(BigDecimal price) {
        return shampooRepository
                .findAllByPriceGreaterThanOrderByPriceDesc(price)
                .stream()
                .map(this::mapShampoo)
                .collect(Collectors.toList());
    }

    @Override
    public Integer findShampooCountByPriceLessThan(BigDecimal price) {
        return shampooRepository.countAllByPriceLessThan(price);
    }

    @Override
    public List<String> findShampoosContainingIngredient(List<String> ingredients) {
       return shampooRepository
                .findAllByIngredientsNameIn(ingredients)
                .stream()
                .map(Shampoo::getBrand)
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findShampoosWithIngredientsCountLessThan(int count) {
        return shampooRepository.findAllByIngredientsLessThan(count)
                .orElseGet(Collections::emptySet)
                .stream()
                .map(Shampoo::getBrand)
                .toList();
    }

    private static Size getSizeEnum(String size) {
        return Size.valueOf(size.toUpperCase());
    }

    private String mapShampoo(Shampoo shampoo) {
        return String.format(BRAND_SIZE_PRICE_FORMAT,
                shampoo.getBrand(),
                shampoo.getSize(),
                shampoo.getPrice().doubleValue());
    }
}
