/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pack.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author Manoj
 * @since 1 Nov, 2018
 */
public class DB
{

    private final String DRIVER;
    private final String DB_URL;
    private final String User;
    private final String Password;

    private Connection con;

    public DB()
    {
        this.DRIVER = "com.mysql.jdbc.Driver";
        this.DB_URL = "jdbc:mysql://localhost:3303/system";
        this.User = "root";
        this.Password = "manoj";
    }

    public Connection getDBConnection() throws ClassNotFoundException, SQLException
    {
        Class.forName(DRIVER);
        return  DriverManager.getConnection(DB_URL, User, Password);
    }
    public int CreateStatement(Connection con, String Query) throws SQLException
    {
        Statement st = con.createStatement();
        System.out.println("");return st.executeUpdate(Query);
    }
    
    public ResultSet SelectAll(Connection con,Object obj) throws SQLException
    {
        Statement st = con.createStatement();
        return st.executeQuery("Select * from system."+obj.getClass().getSimpleName().toLowerCase());
    }
}
