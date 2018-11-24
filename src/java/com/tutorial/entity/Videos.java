/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tutorial.entity;

/**
 *
 * @author Manoj
 * @since 18 Nov, 2018
 */
public class Videos 
{
    private Integer Id;
    private String Name;
    private String Location;

    public Integer getId()
    {
        return Id;
    }

    public void setId(Integer Id)
    {
        this.Id = Id;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String Name)
    {
        this.Name = Name;
    }

    public String getLocation()
    {
        return Location;
    }

    public void setLocation(String Location)
    {
        this.Location = Location;
    }
}
