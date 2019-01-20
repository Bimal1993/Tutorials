/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.processors;

import com.tutorial.EventHandler.AEvent;
import com.tutorial.enums.Command;
import java.util.HashMap;

/**
 *
 * @author Manoj
 * @since 20 Jan, 2019
 */
public class ProcessorManager
{

    public static RequestProcessor FindProcessor(Command usrCommand, HashMap<String, String> requestparams)
    {
        switch (usrCommand)
        {
            case Add:
                return new RequestAddProcessor(requestparams);
            case Edit:
                return new RequestEditProcessor(requestparams);
            case Delete:
                return new RequestDeleteProcessor(requestparams);
            case Fetch:
                return new RequestFetchProcessor(requestparams);
            default:
                return null;
        }
    }

}
