/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.listener;

import com.tutorial.app.Main;
import com.tutorial.entity.User;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Manoj
 * @since 18 Nov, 2018
 *
 * HttpSession Listener class.<br>
 */
public class SessionListener implements HttpSessionListener {

    private final Logger logger = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        try {
            Main.EndSession(se.getSession().getId());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        System.out.println("HCL OUTAGE::SessionListener. SessionDestroyed success..Id: " + se.getSession().getId());
    }

}
