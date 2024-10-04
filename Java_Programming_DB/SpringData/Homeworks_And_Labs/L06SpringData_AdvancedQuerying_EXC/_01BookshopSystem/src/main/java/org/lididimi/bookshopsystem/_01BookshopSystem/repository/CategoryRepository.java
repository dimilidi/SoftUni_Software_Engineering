package org.lididimi.bookshopsystem._01BookshopSystem.repository;


import org.lididimi.bookshopsystem._01BookshopSystem.entitty.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
