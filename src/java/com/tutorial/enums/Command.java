/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tutorial.enums;

/**
 *
 * @author Debashis
 */
public enum Command {
    None,
    Login,
    Logout,
    Admin,;

    public static Command ToUserCommand(String command) {
        try {
            return valueOf(command);
        } catch (Exception ex) {
            return None;
        }
    }
}
