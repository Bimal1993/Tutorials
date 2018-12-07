/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.EventHandler;

import com.tutorial.enums.ResponseCode;

/**
 *
 * @author Debashis
 */
public class EventFailed extends Aevent {

    public EventFailed(ResponseCode code) {
        super(code);
    }

}
