/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.support;

/**
 *
 * @author This PC
 */
public enum TimeEnum {    
    MAX_COOKIE_TIME(TimeEnum._3DAYS),
    FULL_DAY(TimeEnum._24HOURS),
    AN_HOUR(TimeEnum._1HOUR),
    A_30_MINUTES(30 * 60),
    A_15_MINUTES(15 * 60);
    
    private final static int _3DAYS = 3 * 24 * 60 * 60;
    private final static int _24HOURS = 24 * 60 * 60;
    private final static int _1HOUR = 3600;
    private int value;

    private TimeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
