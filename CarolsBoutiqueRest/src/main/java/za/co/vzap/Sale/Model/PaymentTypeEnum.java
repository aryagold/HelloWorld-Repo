package za.co.vzap.Sale.Model;

public enum PaymentTypeEnum {
    CARD(0),
    CASH(1);
    
    int value;
    
    PaymentTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
    
    public static PaymentTypeEnum ofStatusCode(int value) {
        return PaymentTypeEnum.ofStatusCode(value);
    }
    
}
