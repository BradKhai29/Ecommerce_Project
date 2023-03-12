/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.support;

/**
 *
 * @author This PC
 */
public enum SupportEnum {
    CUSTOMER("customer"),
    REGISTER_CUSTOMER("registerUser"),
    TEMPORARY_CART("temporaryCart"),
    COOKIE_LOGIN_CHECKPOINT("CookieLoginCheckPoint"),
    ADD_TEMP_CART_COOKIE_CHECKPOINT("addCartCookieCheckPoint"),
    UPDATE_PROFILE_CHECKPOINT("updateProfile"),
    UPDATE_PASSWORD_CHECKPOINT("updatePassword"),
    INVOICE_HISTORY("invoices"),
    ADD_INVOICES_HISTORY_CHECKPOINT("addInvoices");
    
    private String name;
    
    private SupportEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
