package org.lididimi.bookshopsystem.service;

import org.lididimi.bookshopsystem.entitty.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {

   void seedAuthors(List<Author> authors);

   boolean isDataSeeded();

   Author getRandomAuthor();


   Author findAuthorByFirstNameAndLastName(String firstName, String lastName);

   List<Author> findAllAuthorsByNumberOfTheirBooks();

   List<Author> findAllByBooksReleaseDateBefore(LocalDate bookBeforeYear);
}
