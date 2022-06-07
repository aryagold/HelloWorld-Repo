package za.co.vzap.Sale.Model;

import java.util.HashMap;
import java.util.Map;

public enum SaleStatusEnum {
    RESERVED(0),
    COMPLETED(1),
    CANCELLED(2);
    
    private int value;
    private static Map map = new HashMap<>();

    SaleStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
    
    static {
        for (SaleStatusEnum status : SaleStatusEnum.values()) {
            map.put(status.value, status);
        }
    }

    public static SaleStatusEnum valueOf(int status) {
        return (SaleStatusEnum) map.get(status);
    }
}
