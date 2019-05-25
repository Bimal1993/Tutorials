/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.processors;

import com.tutorial.EventHandler.AEvent;
import com.tutorial.EventHandler.EventFailed;
import com.tutorial.EventHandler.EventSuccess;
import com.tutorial.app.Main;
import com.tutorial.enums.ResponseCode;
import java.util.HashMap;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Debashis
 */
public class RequestLogoutProcess
{

    public static AEvent DoLogout(HttpSession hSess, HashMap<String, String> hmReq) throws Exception
    {
        try
        {
            AEvent event = null;
            //need to change in real scenario after ui part done...
            if (!Main.IsSessionExist(hSess.getId()))
            {
                //Main.EndSession(hSess.getId());
                event = new EventSuccess(ResponseCode.Success);
                event.setRespMessage("Logout Success");
                hSess.invalidate();
            }
            return event;
        }
        catch (Exception ex)
        {
            EventFailed evFail = new EventFailed(ResponseCode.Failed);
            evFail.setRespMessage(ex.getMessage());
            return evFail;
        }
    }

}
