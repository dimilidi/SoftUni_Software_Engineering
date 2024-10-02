package org.lididimi.bookshopsystem._02UserSystem.services;

import org.lididimi.bookshopsystem._02UserSystem.entities.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    void seedUsers(List<User> users);

    List<User> findAllByLastTimeLoggedInBefore(LocalDate date);

    List<User> findAllByEmailLike(String host);

    void deleteById(Long id);

    void deleteUsers(List<User> users);

    boolean isDataSeeded();
}