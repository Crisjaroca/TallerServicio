package com.example.api.services.models;

import java.util.Date;

public class InfoApi {

    int id;
    String names, username, rol;
    Date created_at, updated_at;

    public String toString() {
        return id + " - " + names + " - " + username + " - " + rol;
    }

    public InfoApi(int id, String names, String username, String rol) {
        this.id = id;
        this.names = names;
        this.username = username;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
