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
    TEMPORARY_CART("temporaryCart"),
    COOKIE_LOGIN_CHECKPOINT("CookieLoginCheckPoint"),
    ADD_TEMP_CART_COOKIE_CHECKPOINT("addCartCookieCheckPoint"),
    INVOICE_HISTORY("invoices"),;
    
    private String name;
    
    private SupportEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
