package org.lididimi.bookshopsystem._02UserSystem.services.impl;

import org.lididimi.bookshopsystem._02UserSystem.entities.User;
import org.lididimi.bookshopsystem._02UserSystem.repositories.UserRepository;
import org.lididimi.bookshopsystem._02UserSystem.services.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import static org.lididimi.bookshopsystem._02UserSystem.constants.Constants.USERS_DELETED;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllByEmailLike(String host) {
        return this.userRepository.findAllByEmailLike(host).orElseThrow(NoSuchElementException::new);

    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public void deleteUsers(List<User> users) {
        this.userRepository.deleteAll(users);
        System.out.println(USERS_DELETED);
    }

    @Override
    public boolean isDataSeeded() {
        return this.userRepository.count() > 0;
    }

    @Override
    public List<User> findAllByLastTimeLoggedInBefore(LocalDate date) {
        return this.userRepository.findAllByLastTimeLoggedInBefore(date)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void seedUsers(List<User> users) {
        this.userRepository.saveAll(users);
    }


}
