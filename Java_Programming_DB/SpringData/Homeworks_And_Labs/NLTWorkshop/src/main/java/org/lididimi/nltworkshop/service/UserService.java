package org.lididimi.nltworkshop.service;

import org.lididimi.nltworkshop.web.models.UserLoginModel;
import org.lididimi.nltworkshop.web.models.UserRegisterModel;

public interface UserService {
    boolean validateRegisterUserModel(UserRegisterModel userRegisterModel);

    void registerUser(UserRegisterModel userRegisterModel);

    boolean loggedIn(UserLoginModel userLoginModel);
}
