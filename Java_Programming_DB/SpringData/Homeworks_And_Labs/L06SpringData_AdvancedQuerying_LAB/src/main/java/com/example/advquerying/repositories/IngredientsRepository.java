package com.example.advquerying.repositories;

import com.example.advquerying.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredient, Long> {

    Optional<List<Ingredient>> findAllByNameStartingWith(String string);

    Optional<List<Ingredient>> findAllByNameInOrderByPrice(List<String> names);

    @Transactional
    @Modifying
    @Query("DELETE Ingredient WHERE LOWER(name) = LOWER(:name)")
    void deleteByName(String name);

    @Transactional
    @Modifying
    @Query("UPDATE Ingredient SET price = price * :percentage")
    void increasePriceByPercent(BigDecimal percentage);

    @Transactional
    @Modifying
    @Query("UPDATE Ingredient i SET i.price = i.price * :percentage WHERE i.name IN :ingredients")
    void updatePriceByName(List<String> ingredients, BigDecimal percentage);

}
