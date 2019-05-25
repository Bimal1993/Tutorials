/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.EventHandler;

import com.tutorial.enums.ResponseCode;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Debashis
 */
public class EventSuccess extends AEvent
{

    public EventSuccess(ResponseCode code)
    {
        super(code);
        _resultset = new JSONArray();
    }

    public void setResultSet(JSONObject msg)
    {
        this._resultset.put(msg);
    }

    public void setResultSet(JSONArray arr)
    {
        this._resultset = arr;
    }

    public void setLimit(Long limit)
    {
        setParams("Limit", String.valueOf(limit));
    }

    public void setOffset(Long offset)
    {
        setParams("Offset", String.valueOf(offset));
    }

    public void setRcdCnt(Long rcdCnt)
    {
        setParams("RecordCount", String.valueOf(rcdCnt));

    }

    public void setRcdCnt(Integer rcdCnt)
    {
        setParams("RecordCount", String.valueOf(rcdCnt));
    }

    public void setParams(String paramname, String paramval)
    {
        _params.put(paramname, paramval);
    }

}
