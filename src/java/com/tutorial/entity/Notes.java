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
public class Notes 
{
    private Integer Id;
    private String Code;
    private String Name;
    private String Location;
    private String Topic;
    private String Subject;
    private String Author;

    public Integer getId()
    {
        return Id;
    }

    public void setId(Integer Id)
    {
        this.Id = Id;
    }

    public String getCode()
    {
        return Code;
    }

    public void setCode(String Code)
    {
        this.Code = Code;
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

    public String getTopic()
    {
        return Topic;
    }

    public void setTopic(String Topic)
    {
        this.Topic = Topic;
    }

    public String getSubject()
    {
        return Subject;
    }

    public void setSubject(String Subject)
    {
        this.Subject = Subject;
    }

    public String getAuthor()
    {
        return Author;
    }

    public void setAuthor(String Author)
    {
        this.Author = Author;
    }
        
}
