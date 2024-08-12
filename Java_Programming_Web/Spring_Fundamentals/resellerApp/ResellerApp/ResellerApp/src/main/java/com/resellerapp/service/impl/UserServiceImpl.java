package com.resellerapp.service.impl;

import com.resellerapp.model.UserLoginBindingModel;
import com.resellerapp.model.UserRegisterBindingModel;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.util.LoggedUser;
import com.resellerapp.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, LoggedUser loggedUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegisterBindingModel userRegisterBindingModel) {
        if(userRegisterBindingModel == null) {
            return false;
        }

        String username = userRegisterBindingModel.getUsername();
        if(findUserByUsername(username).isPresent()) {
            return false;
        }

        User user = new User();
        user.setUsername(username);
       user.setPassword(passwordEncoder.encode(userRegisterBindingModel.getPassword()));
        user.setEmail(userRegisterBindingModel.getEmail());

        this.userRepository.save(user);

        return true;
    }


    public boolean login(UserLoginBindingModel userLoginBindingModel) {
        return findUserByUsername(userLoginBindingModel.getUsername())
                .filter(user -> passwordEncoder.matches(userLoginBindingModel.getPassword(), user.getPassword()))
                .map(user -> {
                    loggedUser.login(user);
                    return true;
                })
                .orElse(false);
    }


    @Override
    public void logout() {
        loggedUser.logout();
    }

    @Override
    public boolean isLoggedIn() {
        return this.loggedUser.isLogged();
    }

    private Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
