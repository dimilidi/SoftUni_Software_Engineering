package com.plannerapp.util;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class LoggedUser {

    private String username;

    private boolean isLogged;

    public String getUsername() {
        return username;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void login(String username) {
        isLogged = true;
        this.username = username;
    }

    public void logout() {
        isLogged = false;
        this.username = null;
    }
}
