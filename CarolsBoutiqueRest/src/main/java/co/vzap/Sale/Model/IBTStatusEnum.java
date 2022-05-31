package co.vzap.Sale.Model;

public enum IBTStatusEnum {
    REQUESTED(0),
    APPROVED(1),
    REJECTED(2),
    COMPLETED(3);

    int value;

    IBTStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
    
    public static IBTStatusEnum ofStatusCode(int value) {
        return IBTStatusEnum.ofStatusCode(value);
    }
}
