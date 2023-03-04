/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package webpage_tools;

/**
 *
 * @author This PC
 */
public final class CheckInputPhoneNumber {
    private static final int MAX_NUMBER_LENGTH = 12;
    private static final int MIN_NUMBER_LENGTH = 10;
    
    public static boolean check(String phoneNumber) {
        if(phoneNumber.length() > MAX_NUMBER_LENGTH || phoneNumber.length() < MIN_NUMBER_LENGTH) return false;
        boolean isValid = true;
        
        boolean isZeroFirst = Character.toString(phoneNumber.charAt(0)).equals("0");
        
        //Check if the First Character is zero or not
        isValid = isValid && isZeroFirst;
        for(int i = 1; i < phoneNumber.length(); i++)
        {
            isValid = isValid && Character.isDigit(phoneNumber.charAt(i));
        }
        
        return isValid;
    }
}
