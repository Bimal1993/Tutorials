/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.entity;

import com.tutorial.enums.UserType;

/**
 *
 * @author Manoj
 * @since 18 Nov, 2018
 * we maintain usertype as enum preliminary we take only admin..
 */
public class User {

    private Long Id;
    private String Name;
    private String LoginId;
    private String Password;

    private UserType UserType;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getLoginId() {
        return LoginId;
    }

    public void setLoginId(String LoginId) {
        this.LoginId = LoginId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public UserType getUserType() {
        return UserType;
    }

    public void setUserType(UserType UserType) {
        this.UserType = UserType;
    }

}
