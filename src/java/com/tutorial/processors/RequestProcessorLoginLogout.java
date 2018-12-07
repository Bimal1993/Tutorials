/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.processors;

import CrsCde.CODE.Common.Utils.JSONUtil;
import CrsCde.CODE.Common.Utils.ReflUtils;
import com.tutorial.EventHandler.Aevent;
import com.tutorial.EventHandler.EventFailed;
import com.tutorial.EventHandler.EventSuccess;
import com.tutorial.app.Main;
import com.tutorial.entity.SessionData;
import com.tutorial.entity.User;
import com.tutorial.enums.ResponseCode;
import com.tutorial.enums.UserType;
import java.util.HashMap;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Debashis
 */
public class RequestProcessorLoginLogout {

    public static Aevent LoginValidateProcess(HttpSession hSess, HashMap<String, String> hmReq) throws Exception {
        try {

            User person = null;
            //we check if request does not contain loginid and password for handling only the submit button
            if (!hmReq.containsKey("LoginId") || !hmReq.containsKey("Password")) {
                EventFailed evFail = new EventFailed(ResponseCode.Failed);
                //if this process fails then through the falure code
                evFail.setRespMessage("Incorrect loginid or password");
                //need to invalide this session ...
                hSess.invalidate();

                //get this message from here and show this text directly in ui
                return evFail;

            }
            //if failed here the fail object will returned.

            String loginId = hmReq.get("LoginId");
            System.out.println("login credentials::" + hmReq.get("LoginId"));

            String passwd = hmReq.get("Password");
            //hardcoded for our random usage
            if (loginId.equalsIgnoreCase("admin") && passwd.equals("admin")) {
                //set the data to User class sothat the object will be not null again we willl check that object 
                //if that object having some data then success....
                person = new User();
                person.setLoginId(loginId);
                person.setUserType(UserType.admin);
                person.setId(0L);
                Main.AddSession(hSess.getId(), new SessionData(loginId, person));
            } else if (!loginId.equalsIgnoreCase("admin")) {
                //for database validation we need to validate..
                String whrCls = "LoginId='" + loginId + "' And Password='" + MYSQLPassword(passwd) + "'";
                // User = Main.dbMgr.Find(User.class, whrCls);
                Main.AddSession(hSess.getId(), new SessionData(loginId, person));
            }
            if (person == null) {
                EventFailed evFail = new EventFailed(ResponseCode.Failed);
                evFail.setRespMessage("Incorrect loginid or password");
                hSess.invalidate();
                return evFail;
            }
            EventSuccess evSucc = new EventSuccess(ResponseCode.Success);
            evSucc.setResultSet(JSONUtil.ToJSON(person));
            evSucc.setRespMessage("Login Success");
            return evSucc;
//        return null;
        } catch (Exception ex) {
            EventFailed evFail = new EventFailed(ResponseCode.Failed);
            evFail.setRespMessage(ex.getMessage());
            return evFail;
        }

    }

    public static String MYSQLPassword(String password) throws Exception {
        byte[] utf8 = password.getBytes("UTF-8");
        return "*" + DigestUtils.shaHex(DigestUtils.sha(utf8)).toUpperCase();
    }

}
