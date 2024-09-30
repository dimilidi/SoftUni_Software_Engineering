package org.lididimi._05springdataintroduction.services;

import org.lididimi._05springdataintroduction.models.Account;
import org.lididimi._05springdataintroduction.models.User;
import org.lididimi._05springdataintroduction.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(String username, int age) {

        if (username.isBlank() || age < 18) {
            throw new RuntimeException("Validation failed!");
        }

        Optional<User> byUsername = this.userRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            throw new RuntimeException("Username already in use");
        }

        Account account = new Account();
        User user = new User(username, age, account);

        this.userRepository.save(user);

    }
}