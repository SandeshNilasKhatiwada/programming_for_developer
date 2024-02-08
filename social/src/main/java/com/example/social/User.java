package com.example.social;

public class User {
    public User(int userId, String fullname, String email, String password) {
        this.userId = userId;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int userId;
    private String fullname;
    private String email;
    private String password;

    public User(String fullname, String email, String password) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                "fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
