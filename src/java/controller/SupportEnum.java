/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author This PC
 */
public enum SupportEnum {
    CUSTOMER("customer"),
    REMEMBER_USER_COOKIE("rememberUser"),
    COOKIE_LOGIN_CHECKPOINT("CookieLoginCheckPoint"),
    TEMPORARY_CART("temporaryCart");
    
    private String name;

    private SupportEnum() {
    }

    private SupportEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
