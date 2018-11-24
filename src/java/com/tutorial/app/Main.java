/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.app;

import CrsCde.CODE.Common.Consts.OSConst;
import CrsCde.CODE.Common.Utils.TypeUtil;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Manoj
 * @since 18 Nov, 2018
 */
public class Main
{

    static org.slf4j.Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args)
    {

    }

    private static void InitAppProps() throws Exception
    {
        logger.info("Initializing AppProps...");

        String cfgName = "Tutorials.properties";

        String cfgDir = OSConst.Type().equals(OSConst.OSType.Windows)
                ? ("E:\\RADIUS_Project\\RADIUS\\conf")
                : ("E:\\RADIUS_Project\\RADIUS\\conf");
        AppConst.ConfigFile = cfgDir + OSConst.FileSep() + cfgName;

        Properties props = new Properties();
        props.load(new FileInputStream(AppConst.ConfigFile));
        Field[] allFields = AppConst.class.getDeclaredFields();

        for (Field field : allFields)
        {
            field.setAccessible(true);
            if (props.getProperty(field.getName()) != null)
            {
                field.set(null, TypeUtil.ValueOf(field.getType(), props.getProperty(field.getName())));
                logger.info(field.getName() + ": " + props.getProperty(field.getName()));
            }
        }
    }
}
