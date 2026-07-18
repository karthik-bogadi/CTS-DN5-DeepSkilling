package org.karthik.jwt_working.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    public User() {
    }

    @Id
    private int id;
    private String userName;
    private String password;
    private String role;

    public User(int id, String role, String password, String userName) {
        this.id = id;
        this.role = role;
        this.password = password;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
