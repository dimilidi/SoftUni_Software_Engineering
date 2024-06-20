package bg.softuni.shoppinglist.service;

import bg.softuni.shoppinglist.model.dto.UserLoginDTO;
import bg.softuni.shoppinglist.model.dto.UserRegisterDTO;
import bg.softuni.shoppinglist.model.entity.UserEntity;

import java.util.Optional;

public interface UserService {

    void register(UserRegisterDTO registerData);

    void login(UserLoginDTO loginData);

    UserEntity findByUsernameAndPassword(String username, String password);
}
