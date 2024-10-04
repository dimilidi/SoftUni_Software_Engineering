package org.lididimi.bookshopsystem._01BookshopSystem.repository;

import org.lididimi.bookshopsystem._01BookshopSystem.entitty.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<List<Author>> findAllByFirstNameEndingWith(String string);


}