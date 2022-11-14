package com.example.api.services.models;

public class User {

    String names;
    String username;

    public User(String names, String username, String password, String rol) {
        this.names = names;
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    String password;
    String rol;
}
