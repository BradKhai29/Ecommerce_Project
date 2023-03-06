/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author This PC
 */
public enum CookieEnum {
    REMEMBER_USER_COOKIE("rememberUser"),
    TEMPORARY_CART_COOKIE("temporaryCart"),;
    
    private String name;
    
    private CookieEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
