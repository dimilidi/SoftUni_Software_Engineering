package org.lididimi.bookshopsystem.repository;

import org.lididimi.bookshopsystem.entitty.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
