package org.lididimi.bookshopsystem._01BookshopSystem.service.impl;

import org.lididimi.bookshopsystem._01BookshopSystem.entitty.Author;
import org.lididimi.bookshopsystem._01BookshopSystem.repository.AuthorRepository;
import org.lididimi.bookshopsystem._01BookshopSystem.service.AuthorService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors(List<Author> authors) {
        this.authorRepository.saveAllAndFlush(authors);
    }


    @Override
    public boolean isDataSeeded() {
        return this.authorRepository.count() > 0;
    }

    @Override
    public Author getRandomAuthor() {
        Long randomId = ThreadLocalRandom.current().nextLong(1, authorRepository.count() + 1);
        return authorRepository.findById(randomId).get();
    }

    @Override
    public Author findAuthorByFirstNameAndLastName(String firstName, String lastName) {

        return this.authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Author> findAllByBooksReleaseDateBefore(LocalDate date) {

        return this.authorRepository.findAllByBooksReleaseDateBefore(date)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Author> findAllAuthorsByNumberOfTheirBooks() {

        return this.authorRepository.findAllAuthorsByNumberOfTheirBooks()
                .orElseThrow(NoSuchElementException::new);
    }

}
