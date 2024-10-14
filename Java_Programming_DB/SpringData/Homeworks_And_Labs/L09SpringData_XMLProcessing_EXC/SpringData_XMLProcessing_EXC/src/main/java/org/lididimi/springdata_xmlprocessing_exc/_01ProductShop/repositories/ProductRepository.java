package org.lididimi.springdata_xmlprocessing_exc._01ProductShop.repositories;

import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<List<Product>> findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal minRange, BigDecimal maxRange);
}