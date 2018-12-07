/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.app;

import CrsCde.CODE.Common.Consts.OSConst;
import CrsCde.CODE.Common.Utils.TypeUtil;
import com.tutorial.app.AppConfig;
import com.tutorial.entity.SessionData;
import com.tutorial.listener.SessionListener;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Manoj
 * @since 18 Nov, 2018
 */
public class Main {
    //we need to add the http session id with the request data as session data..in that 

    static org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);
    public static HashMap<String, SessionData> hmSession = null;

    public static void InitAppProps() throws Exception {
        logger.info("Initializing AppProps...");

        String cfgName = "Tutorials.properties";

//        String cfgDir = OSConst.Type().equals(OSConst.OSType.Windows)
//                ? ("E:\\RADIUS_Project\\RADIUS\\conf")
//                : ("E:\\RADIUS_Project\\RADIUS\\conf");
        String cfgDir = OSConst.Type().equals(OSConst.OSType.Windows)
                ? ("E:\\programs\\tutorials\\conf")
                : ("E:\\programs\\tutorials\\conf");
        AppConst.ConfigFile = cfgDir + OSConst.FileSep() + cfgName;

        Properties props = new Properties();
        PropertyConfigurator.configure(AppConst.ConfigFile);

        props.load(new FileInputStream(AppConst.ConfigFile));
        Field[] allFields = AppConst.class.getDeclaredFields();

        for (Field field : allFields) {
            field.setAccessible(true);
            if (props.getProperty(field.getName()) != null) {
                field.set(null, TypeUtil.ValueOf(field.getType(), props.getProperty(field.getName())));
                logger.info(field.getName() + ": " + props.getProperty(field.getName()));
            }
        }
        try {
            AppConfig.StartApplication();
            logger.info("Application constants initialized..");
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    //at the time of new user login add the session to the hashmap
    public static void AddSession(String sessId, SessionData sess) {
        hmSession.put(sessId, sess);
    }

    public static void EndSession(String sessId) {
        hmSession.remove(sessId);
    }

    public static Boolean IsSessionExist(String sessId) {
         hmSession = new HashMap();
       
        if (hmSession.containsKey(sessId)) {
            return true;
        }
        return false;
    }

    public static SessionData getSession(String sessId) {
        return hmSession.get(sessId);
    }

}
