package org.lididimi.springdata_xmlprocessing_exc._01ProductShop.repositories;

import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.Category;
import org.lididimi.springdata_xmlprocessing_exc._01ProductShop.entities.dtos.category.CategoryByProductsSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

 @Query(nativeQuery = true, value = "SELECT * FROM product_shop.categories ORDER BY RAND () LIMIT 2")
    Optional<Set<Category>> getRandomEntity();

  @Query("""
            SELECT NEW org.lididimi.springdata_xmlprocessing_exc._01ProductShop
            .entities.dtos.category.CategoryByProductsSummaryDTO(c.name, COUNT(p.id), AVG(p.price), SUM(p.price))
            FROM Product p
            JOIN p.categories c
            GROUP BY c.id
            ORDER BY COUNT(p.id) DESC
                        """)
   Optional<List<CategoryByProductsSummaryDTO>> getCategoriesByProductSummary();
}