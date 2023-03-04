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
    TEMPORARY_CART_COOKIE("temporaryCart"),
    TEMPORARY_CART("temporaryCart"),
    COOKIE_LOGIN_CHECKPOINT("CookieLoginCheckPoint"),
    ADD_TEMP_CART_COOKIE_CHECKPOINT("addCartCookieCheckPoint");
    
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
