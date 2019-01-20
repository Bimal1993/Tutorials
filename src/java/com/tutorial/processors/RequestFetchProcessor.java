/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tutorial.processors;

import com.tutorial.EventHandler.AEvent;
import java.util.HashMap;

/**
 *
 * @author Manoj
 * @since 20 Jan, 2019
 */
public class RequestFetchProcessor extends RequestProcessor
{

    public RequestFetchProcessor(HashMap<String, String> requestparams)
    {
        super(requestparams);
    }

    @Override
    public AEvent DoProcess()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
