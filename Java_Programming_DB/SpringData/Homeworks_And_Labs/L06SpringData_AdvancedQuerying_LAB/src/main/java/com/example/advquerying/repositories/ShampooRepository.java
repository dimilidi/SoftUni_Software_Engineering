package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import com.example.advquerying.entities.Shampoo;
import com.example.advquerying.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    Set<Shampoo> findAllBySizeOrderById(Size size);

    Set<Shampoo>  findAllBySizeOrLabelIdOrderByPrice(Size size, Long id);

    Set<Shampoo>  findAllByPriceGreaterThanOrderByPriceDesc(BigDecimal price);

    Integer countAllByPriceLessThan(BigDecimal price);

    Set<Shampoo> findAllByIngredientsNameIn(List<String> ingredientNames);

    @Query("FROM Shampoo s WHERE SIZE(s.ingredients) < :count")
    Optional<Set<Shampoo>> findAllByIngredientsLessThan(int count);

}
