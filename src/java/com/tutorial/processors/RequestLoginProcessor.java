/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.processors;

import CrsCde.CODE.Common.Utils.JSONUtil;
import com.tutorial.EventHandler.AEvent;
import com.tutorial.EventHandler.EventFailed;
import com.tutorial.EventHandler.EventSuccess;
import com.tutorial.app.Main;
import com.tutorial.http.SessionData;
import com.tutorial.entity.User;
import com.tutorial.enums.ResponseCode;
import com.tutorial.enums.UserType;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
//import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Debashis
 */
public class RequestLoginProcessor 
{

    private HttpSession hSess; 
    public AEvent DoLogin(HttpServletRequest req, HashMap<String, String> hmReq)
    {
        try
        {
            User person = null;
            /**
             * we check if request does not contain loginid and password for handling only the submit button
             */
            if (!hmReq.containsKey("LoginId") || !hmReq.containsKey("Password"))
            {
                EventFailed evFail = new EventFailed(ResponseCode.Failed);
                //if this process fails then through the falure code
                evFail.setRespMessage("Incorrect loginid or password");

                //get this message from here and show this text directly in ui
                return evFail;

            }
            //if failed here the fail object will returned.

            String loginId = hmReq.get("LoginId");
            String passwd = hmReq.get("Password");

            if (loginId.equalsIgnoreCase("admin") && passwd.equals("admin"))
            {
                /**
                 * set the data to User class so that the object will be not null again we willl check that object <br>
                 * if that object having some data then success....<br>
                 */

                hSess = req.getSession();
                /**
                 * We set the Session in Parent Processor(RequestProcessor) so that we can use this in child processors.
                 */
                RequestProcessor.hSess = hSess;
                System.out.println("Session Id-" + hSess.getId());
                
                person = new User();
                person.setLoginId(loginId);
                person.setUserType(UserType.Admin);

                Main.AddSession(hSess.getId(), new SessionData(loginId, person));
            }
            else if (person == null)
            {
                EventFailed evFail = new EventFailed(ResponseCode.Failed);
                evFail.setRespMessage("Incorrect loginid or password");
                return evFail;
            }
            else 
            {
                EventFailed ev = new EventFailed(ResponseCode.Failed);
                ev.setRespMessage("UserId or Password incorrect.");
                return ev;
            }
            /**
             * It is not used now but helpful for our future road map. (When we implement Customer in this project).
             */
//            else if (!loginId.equalsIgnoreCase("admin"))
//            {
////for database validation we need to validate..
//                String whrCls = "LoginId='" + loginId + "' And Password='" + passwd + "'";
//// User = Main.dbMgr.Find(User.class, whrCls);
//                Main.AddSession(hSess.getId(), new SessionData(loginId, person));
//            }
            
            EventSuccess evSucc = new EventSuccess(ResponseCode.Success);
            evSucc.setResultSet(JSONUtil.ToJSON(person));
            evSucc.setRespMessage("Login Success");
            return evSucc;
// return null;
        }
        catch (Exception ex)
        {
            EventFailed evFail = new EventFailed(ResponseCode.Failed);
            System.out.println(ex.getMessage());
            evFail.setRespMessage(ex.getMessage());
            return evFail;
        }
    }

}
