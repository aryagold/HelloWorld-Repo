package za.co.vzap.Model.Sale;

import java.util.HashMap;
import java.util.Map;

public enum RefundStatusEnum {
    NEW(0),
    COMPLETED(1),
    CANCELLED(2);
    
    private int value;
    private static Map map = new HashMap<>();

    RefundStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    static {
        for (RefundStatusEnum status : RefundStatusEnum.values()) {
            map.put(status.value, status);
        }
    }

    public static RefundStatusEnum valueOf(int status) {
        return (RefundStatusEnum) map.get(status);
    }
}
