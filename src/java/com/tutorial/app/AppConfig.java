/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.app;

import com.tutorial.db.DBManager;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Manoj
 * @since 18 Nov, 2018
 */
public class AppConfig
{
    public static DBManager db;
    public static void StartApplication() throws Exception
    {
        System.out.println("Need  to initialize the database here...");
        //here we will initialize the database connection and ll use this db object for future usage..
        db = DBManager.InitDB();
    }
    
}
