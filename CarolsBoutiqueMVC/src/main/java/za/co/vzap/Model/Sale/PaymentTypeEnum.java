package za.co.vzap.Model.Sale;

import java.util.HashMap;
import java.util.Map;

public enum PaymentTypeEnum {
    CARD(0),
    CASH(1);

    private int value;
    private static Map map = new HashMap<>();

    PaymentTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    static {
        for (PaymentTypeEnum method : PaymentTypeEnum.values()) {
            map.put(method.value, method);
        }
    }

    public static PaymentTypeEnum valueOf(int method) {
        return (PaymentTypeEnum) map.get(method);
    }

}
