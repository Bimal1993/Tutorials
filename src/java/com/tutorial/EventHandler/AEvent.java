/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.EventHandler;

import CrsCde.CODE.Common.Utils.JSONUtil;
import com.tutorial.enums.ResponseCode;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Debashis
 */
public abstract class AEvent
{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final int respCode;
    private final String respText;
    private String respMessage;

    private String cmd;
    private String sessId;
    protected JSONArray _resultset;

    protected final Map<String, String> _params = new HashMap<>();

    public AEvent(ResponseCode code)
    {
        this.respCode = code.getCode();
        this.respText = code.toString();
    }

    /**
     * Convert Event Object to Json String
     *
     * @return Json
     */
    public String ToJson()
    {
        JSONObject jObj = new JSONObject();

        try
        {
            //Reqeust
            jObj.put("Cmd", cmd);
            jObj.put("SessionId", sessId);
            //Response
            jObj.put("ResponseCode", respCode);
            jObj.put("ResponseText", respText);
            if (_resultset != null)
            {
                jObj.put("ResultSet", _resultset);
            }
            if (respMessage != null)
            {
                jObj.put("Message", respMessage);
            }
            jObj.put("Params", JSONUtil.ToJSON(_params));
        }
        catch (Exception jex)
        {
            logger.error(jex.getMessage(), jex);
        }
        return jObj.toString();
    }

    public void setCmd(String cmd)
    {
        this.cmd = cmd;
    }

    public void setSessionId(String reqId)
    {
        this.sessId = reqId;
    }

    public String getRespMessage()
    {
        return respMessage;
    }

    public void setRespMessage(String _respMessage)
    {
        this.respMessage = _respMessage;
    }

}
