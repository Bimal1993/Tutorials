/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.tutorial.app.Main;

/**
 *
 * @author Manoj
 * @since 18 Nov, 2018
 */
public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            System.out.println("============Context Initialised::In Tutorial Application...============\n");
            ServletContext appObj = sce.getServletContext();
            Main.InitAppProps();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext appObj = sce.getServletContext();
        System.out.println("============Context contextDestroyed::Tutorial Application ...============\n");
    }

}
