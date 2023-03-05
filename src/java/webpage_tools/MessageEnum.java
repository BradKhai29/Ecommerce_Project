/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webpage_tools;

/**
 *
 * @author This PC
 */
public enum MessageEnum {
    CONFIRM_PASSWORD_ERROR("confirmPasswordError", "MẬT KHẨU NHẬP LẠI KHÔNG TRÙNG KHỚP, VUI LÒNG NHẬP LẠI"),
    EXIST_USERNAME("existUsername", "TÊN ĐĂNG NHẬP ĐÃ TỒN TẠI VUI LÒNG CHỌN TÊN KHÁC"),
    EXIST_EMAIL("existEmail", "EMAIL ĐÃ TỒN TẠI VUI LÒNG CHỌN EMAIL KHÁC"),
    EXIST_PHONENUMBER("existPhoneNumber", "SỐ ĐIỆN THOẠI ĐÃ TỒN TẠI"),
    INVALID_PHONENUMBER("phoneNumberError", "SỐ ĐIỆN THOẠI KHÔNG HỢP LỆ, VUI LÒNG NHẬP LẠI"),
    LOGIN_ERROR("loginError", "TÊN ĐĂNG NHẬP HOẶC MẬT KHẨU KHÔNG ĐÚNG"),
    LOGIN_REQUIRED("requiredLogin","YÊU CẦU ĐĂNG NHẬP ĐỂ THỰC HIỆN THANH TOÁN"),
    PAYMENT_SUCCESS("paymentSuccess", "CẢM ƠN QUÝ KHÁCH ĐÃ MUA HÀNG, ĐƠN HÀNG CỦA QUÝ KHÁCH ĐANG ĐƯỢC XỬ LÝ"),
    REGISTER_SUCCESS("registerSuccess", "ĐĂNG KÝ TÀI KHOẢN THÀNH CÔNG"),
    ;
    
    private String name;
    private String message;

    private MessageEnum() {
    }

    private MessageEnum(String name, String message) {
        this.name = name;
        this.message = message;
    }

    /**
     * Return the name of this MessageEnum
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * Return the message belongs to this MessageEnum
     * @return 
     */
    public String getMessage() {
        return message;
    }
}
