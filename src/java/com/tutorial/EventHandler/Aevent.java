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
public abstract class Aevent {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final int _respCode;
    private final String _respText;
    private String _respMessage;

    private String _cmd;
    private String _sessId;
    protected JSONArray _resultset;

    protected final Map<String, String> _params = new HashMap<>();

    public Aevent(ResponseCode code) {
        this._respCode = code.getCode();
        this._respText = code.toString();
    }

    /**
     * Convert Event Object to Json String
     *
     * @return Json
     */
    public String ToJson() {
        JSONObject jObj = new JSONObject();

        try {
            //Reqeust
            jObj.put("Cmd", _cmd);
            jObj.put("SessionId", _sessId);
            //Response
            jObj.put("ResponseCode", _respCode);
            jObj.put("ResponseText", _respText);
            if (_resultset != null) {
                jObj.put("ResultSet", _resultset);
            }
            if (_respMessage != null) {
                jObj.put("Message", _respMessage);
            }
            jObj.put("Params", JSONUtil.ToJSON(_params));
        } catch (Exception jex) {
            logger.error(jex.getMessage(), jex);
        }
        return jObj.toString();
    }

    public void setCmd(String cmd) {
        this._cmd = cmd;
    }

    public void setSessionId(String reqId) {
        this._sessId = reqId;
    }

    public String getRespMessage() {
        return _respMessage;
    }

    public void setRespMessage(String _respMessage) {
        this._respMessage = _respMessage;
    }

}
