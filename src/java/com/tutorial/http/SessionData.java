/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.http;

import com.tutorial.entity.User;

/**
 *
 * @author Debashis we will just maintain the login id and user type in this
 * class..
 */
public class SessionData
{

    private String LoggedIn = null;
    private User user = null;

    public SessionData(String LoggedIn)
    {
        this.LoggedIn = LoggedIn;

    }

    public SessionData(String loginId, User person)
    {

    }

    public String getLoggedInUser()
    {
        return this.LoggedIn;
    }

    public User getPerson()
    {
        return this.user;
    }

}
