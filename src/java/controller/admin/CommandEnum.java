/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin;

/**
 *
 * @author This PC
 */
public enum CommandEnum {
    LOGIN("LOGIN"),
    LOGOUT("LOGOUT"),
    SETTING("SETTING"),
    NOT_FOUND(""),
    ;
    
    private String command;

    private CommandEnum(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
    
    public static CommandEnum getCommandEnum(String command)
    {
        boolean isFound = false;
        CommandEnum resultCommandEnum = NOT_FOUND;
        if(command == null) return resultCommandEnum;
        
        CommandEnum[] enums = CommandEnum.values();
        for(int i = 0; i < enums.length && !isFound; i++)
        {
            CommandEnum commandEnum = enums[i];
            if(commandEnum.getCommand().equals(command)) {
                resultCommandEnum = commandEnum;
                isFound = true;
            }
        }
        return resultCommandEnum;
    }
}
