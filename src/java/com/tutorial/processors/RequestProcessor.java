/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.processors;

import CrsCde.CODE.Common.Utils.ReflUtils;
import com.tutorial.EventHandler.AEvent;
import com.tutorial.app.AppConfig;
import com.tutorial.db.DBManager;
import com.tutorial.entity.Slides;
import com.tutorial.enums.EN;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    protected Object getEntityObj(HashMap<String, String> reqparam)
    {
        Class cls = EN.valueOf(requestparams.get("Entity")).getEntityClass();
        Object obj = null;
        try
        {
            Method[] m = cls.getDeclaredMethods();
            Field[] f = cls.getDeclaredFields();
            obj = cls.newInstance();
            requestparams.remove("Entity");
            requestparams.remove("cmd");
            for (String key : requestparams.keySet())
            {

                ReflUtils.InvokeSetter(obj, key, requestparams.get(key));

            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return obj;
    }

    protected Object getEntityObjFrmRs(Class cls, ResultSet rs) throws SQLException, InstantiationException, IllegalAccessException, Exception
    {
        Field[] fld = cls.getDeclaredFields();
        Object obj = cls.newInstance();
        Slides sld = new Slides();

        while (rs.next())
        {
            sld.setId(rs.getInt("Id"));
            sld.setCode(rs.getString("Code"));
            sld.setName(rs.getString("Name"));
            sld.setLocation(rs.getString("Location"));
        }

        return sld;
    }

}
