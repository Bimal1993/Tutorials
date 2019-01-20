/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.processors;

import com.tutorial.EventHandler.AEvent;
import com.tutorial.app.AppConfig;
import com.tutorial.db.DBManager;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Manoj
 * @since 18 Nov, 2018
 */
public abstract class RequestProcessor
{

    protected static HttpSession hSess;
    protected DBManager db;
    HashMap<String, String> requestparams;

    public RequestProcessor(HashMap<String, String> requestparams)
    {
        this.db = AppConfig.db;
        this.requestparams = requestparams;
    }

    public abstract AEvent DoProcess();

}
