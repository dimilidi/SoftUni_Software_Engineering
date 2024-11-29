package org.lididimi.nltworkshop.service.impl;

import org.lididimi.nltworkshop.data.entities.User;
import org.lididimi.nltworkshop.repositories.UserRepository;
import org.lididimi.nltworkshop.service.UserService;
import org.lididimi.nltworkshop.web.models.UserLoginModel;
import org.lididimi.nltworkshop.web.models.UserRegisterModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean validateRegisterUserModel(UserRegisterModel userRegisterModel) {
        return userRegisterModel.getPassword().equals(userRegisterModel.getConfirmPassword()) &&
                userRepository.findByUsername(userRegisterModel.getUsername()).isEmpty() &&
                userRepository.findByEmail(userRegisterModel.getEmail()).isEmpty();
    }

    @Override
    public void registerUser(UserRegisterModel userRegisterModel) {
        userRepository.saveAndFlush(modelMapper.map(userRegisterModel, User.class));
    }

    @Override
    public boolean loggedIn(UserLoginModel userLoginModel) {
        return userRepository.findByUsernameAndPassword(userLoginModel.getUsername(), userLoginModel.getPassword()).isPresent();
    }
}
