package org.lididimi.bookshopsystem._01BookshopSystem.service;

import org.lididimi.bookshopsystem._01BookshopSystem.entitty.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {

   void seedAuthors(List<Author> authors);

   boolean isDataSeeded();

   Author getRandomAuthor();

   List<String> findAuthorsByFirstNameEnding(String string);

}
