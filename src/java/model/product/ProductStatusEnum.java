package model.product;


public enum ProductStatusEnum {
    IN_STOCK(1, "Còn hàng"),
    OUT_OF_STOCK(2, "Hết hàng"),
    COMING_SOON(3, "Hàng sắp về"),
    NOT_AVAILABLE(4, "Không còn tồn tại");
    
    private int code;
    private String statusMessage;

    private ProductStatusEnum(int code) {
        this.code = code;
    }

    private ProductStatusEnum(int code, String statusMessage) {
        this.code = code;
        this.statusMessage = statusMessage;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public static String getStatusMessage(int inputCode){
        String message = "";
        boolean isFound = false;
        
        ProductStatusEnum[] enums = ProductStatusEnum.values();
        for(int i = 0; i < enums.length && !isFound; i++)
        {
            if(enums[i].code == inputCode) 
            {
                message = enums[i].statusMessage;
                isFound = true;
            }
        }
        
        return message;
    }
}
