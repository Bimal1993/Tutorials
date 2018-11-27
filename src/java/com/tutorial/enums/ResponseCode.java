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
public enum ResponseCode {

    Success(200),
    NoContent(204),
    BadRequest(400),
    Unauthorized(401),
    CommandNotFound(404),
    InvalidClient(405),
    SessionExpired(440),
    Failed(501);

    private final int code;

    private ResponseCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
