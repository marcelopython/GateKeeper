package com.gateKeeper.manager.service;

import com.gateKeeper.manager.model.User;

import javax.security.auth.login.LoginException;

public abstract class Auth {

    private static User user;

    public static void setUser(User user){
        Auth.user = user;
    }

    public static User getUser() {
        return Auth.user;
    }

}
