/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tutorial.processors;

import com.tutorial.EventHandler.AEvent;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Manoj
 * @since 20 Jan, 2019
 */
public class RequestAddProcessor extends RequestProcessor
{

    
    public RequestAddProcessor(HashMap<String, String> requestparams)
    {
        super(requestparams);
    }

    @Override
    public AEvent DoProcess()
    {
        db.Insert(db);
        return null;
    }
    

}
