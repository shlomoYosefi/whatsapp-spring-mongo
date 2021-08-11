package com.whatsappnew.whatsappnewspringmongo.model.Login;

public class Login {

    private String username;
    public String password;

    private static Login login = new Login();

    public static Login getInstance(){
        return login;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
