package com.openclassrooms.mddapi.dto.request;

public class SaveUser {
    private String login;
    private String email;
    private String token;

    public SaveUser(String login, String email, String token) {
        this.login = login;
        this.email = email;
        this.token = token;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
