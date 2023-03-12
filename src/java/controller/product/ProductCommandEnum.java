package controller.product;

/**
 *
 * @author This PC
 */
public enum ProductCommandEnum {
    LOAD_PRODUCT(""),
    DETAIL_PRODUCT("DETAIL"),
    DETAIL_PRODUCT_WITH_ADMIN_PERMISSION("DETAIL_ADMIN"),
    DELETE_PRODUCT("DELETE"),
    UPDATE_PRODUCT_INFO("UPDATE"),
    CONFIRM_UPDATE("CONFIRM_UPDATE"),
    ADD_PRODUCT("ADD")
    ;
    
    private String command;

    private ProductCommandEnum(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
    
    public static ProductCommandEnum getCommandEnum(String command)
    {
        command = String.valueOf(command).toUpperCase();
        boolean isFound = false;
        ProductCommandEnum resultCommandEnum = LOAD_PRODUCT;
        if(command == null) return resultCommandEnum;
        
        ProductCommandEnum[] enums = ProductCommandEnum.values();
        for(int i = 0; i < enums.length && !isFound; i++)
        {
            ProductCommandEnum commandEnum = enums[i];
            if(commandEnum.getCommand().equals(command))
            {
                resultCommandEnum = commandEnum;
                isFound = true;
            }
        }
        return resultCommandEnum;
    }
}
