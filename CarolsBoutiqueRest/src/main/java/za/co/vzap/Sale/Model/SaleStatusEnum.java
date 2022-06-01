package za.co.vzap.Sale.Model;

public enum SaleStatusEnum {
    RESERVED(0),
    COMPLETED(1),
    CANCELLED(2);
    
    int value;

    SaleStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
    
    public static SaleStatusEnum ofStatusCode(int value) {
        return SaleStatusEnum.ofStatusCode(value);
    }
}