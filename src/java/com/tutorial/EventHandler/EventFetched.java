/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.EventHandler;

import com.tutorial.enums.ResponseCode;

/**
 *
 * @author Manoj
 * @since 22 Jan, 2019
 */
public class EventFetched extends EventSuccess
{

    String Entity;

    public EventFetched(String entity)
    {
        super(ResponseCode.Success);
        this.Entity = entity;
        _resultset.put(Entity);
    }

}
