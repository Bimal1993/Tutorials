/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.processors;

import com.google.gson.JsonArray;
import com.tutorial.EventHandler.AEvent;
import com.tutorial.EventHandler.EventFetched;
import com.tutorial.EventHandler.EventSuccess;
import com.tutorial.enums.EN;
import com.tutorial.enums.ResponseCode;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

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
        EventFetched ev = null;
        try
        {
            Class cls = EN.valueOf(requestparams.get("Entity")).getEntityClass();
            JSONArray rs = db.Fetch(cls);
           // Object obj = getEntityObjFrmRs(cls, rs);
            ev = new EventFetched(cls.getSimpleName());
            ev.setResultSet(rs);
        }
        catch (Exception ex)
        {
            Logger.getLogger(RequestFetchProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ev;
    }

}
