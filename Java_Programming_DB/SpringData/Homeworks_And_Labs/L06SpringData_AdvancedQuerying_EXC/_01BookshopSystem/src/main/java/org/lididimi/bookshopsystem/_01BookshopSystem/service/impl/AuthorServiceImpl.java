package org.lididimi.bookshopsystem._01BookshopSystem.service.impl;

import org.lididimi.bookshopsystem._01BookshopSystem.entitty.Author;
import org.lididimi.bookshopsystem._01BookshopSystem.repository.AuthorRepository;
import org.lididimi.bookshopsystem._01BookshopSystem.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.lididimi.bookshopsystem._01BookshopSystem.constants.Constants.AUTHOR_FIRST_LAST_NAME_FORMAT;

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
    public List<String> findAuthorsByFirstNameEnding(String string) {
        return authorRepository.findAllByFirstNameEndingWith(string)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(a -> String.format(AUTHOR_FIRST_LAST_NAME_FORMAT,
                        a.getFirstName(),
                        a.getLastName()))
                .toList();
    }

}
