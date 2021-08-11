package com.whatsappnew.whatsappnewspringmongo.model.User;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

public abstract class User  {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName:'" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
