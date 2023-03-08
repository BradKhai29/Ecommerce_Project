package controller.user;

public enum UpdateCommandEnum {
    UPDATE_PROFILE("UPDATE_PROFILE"),
    CONFIRM_UPDATE("CONFIRM_UPDATE"),
    UPDATE_PASSWORD("UPDATE_PASSWORD"),
    NOT_FOUND("NOT_FOUND");
    
    private String command;

    private UpdateCommandEnum(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
    
    public static UpdateCommandEnum getCommandEnum(String command)
    {
        boolean isFound = false;
        UpdateCommandEnum resultCommandEnum = NOT_FOUND;
        
        UpdateCommandEnum[] enums = UpdateCommandEnum.values();
        for(int i = 0; i < enums.length && !isFound; i++)
        {
            UpdateCommandEnum commandEnum = enums[i];
            if(commandEnum.getCommand().equals(command)) {
                resultCommandEnum = commandEnum;
                isFound = true;
            }
        }
        return resultCommandEnum;
    }
}
