/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.product;

/**
 *
 * @author This PC
 */
public enum ProductTypeEnum {
    KATANA(1),
    ONEPIECE(2),
    CUNG_HOANG_DAO(3);
    
    private int typeID;

    private ProductTypeEnum(int typeID) {
        this.typeID = typeID;
    }
    
    public String getTypeName(){
        return "";
    }
}
