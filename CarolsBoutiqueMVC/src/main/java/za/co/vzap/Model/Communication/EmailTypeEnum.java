package za.co.vzap.Model.Communication;

import java.util.HashMap;
import java.util.Map;

public enum EmailTypeEnum {
    SALERECEIPT(0),
    REFUNDRECEIPT(1),
    DEPLETEDSTOCK(2),
    RESERVENOTIFICATION(3),
    SUBSCRIPTIONNOTIFICATION(4);
   
    private int value;
    private static Map map = new HashMap<>();

    EmailTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
    
    static {
        for (EmailTypeEnum type : EmailTypeEnum.values()) {
            map.put(type.value, type);
        }
    }

    public static EmailTypeEnum valueOf(int type) {
        return (EmailTypeEnum) map.get(type);
    }
}
