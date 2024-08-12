package com.resellerapp.util;

import com.resellerapp.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {
    private String username;

    public void login(User user) {
        this.username = user.getUsername();
    }

    public void logout() {
        this.username = null;
    }

    public boolean isLogged() {
        return username != null;
    }

    public String username() {
        return username;
    }

}
