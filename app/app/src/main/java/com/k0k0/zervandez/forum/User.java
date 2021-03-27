package com.k0k0.zervandez.forum;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String username = "None";
    private String name = "None";
    private String birthday = "None";
    private String phoneNumber = "None";
    private String email;
    private String userId;
    private String password;

    public User() {};


    public User(String ID, String email,String password) {
        this.userId = ID;
        this.email = email;
        this.password = password;
    }

    // Copy Constructor
    public User(User user) {
        this.username = user.username;
        this.name = user.name;
        this.birthday = user.birthday;
        this.phoneNumber = user.phoneNumber;
        this.email = user.email;
        this.userId = user.userId;
        this.password = user.password;
    }

    // Getter
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    // Setter
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // toString
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User copy() {
        return new User(this);
    }
}
