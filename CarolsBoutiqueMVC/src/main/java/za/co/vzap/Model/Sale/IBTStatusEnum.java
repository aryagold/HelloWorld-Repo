package za.co.vzap.Model.Sale;

import java.util.HashMap;
import java.util.Map;

public enum IBTStatusEnum {
    REQUESTED(0),
    APPROVED(1),
    DECLINED(2),
    COMPLETED(3);

    private int value;
    private static Map map = new HashMap<>();

    IBTStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
    
    static {
        for (IBTStatusEnum status : IBTStatusEnum.values()) {
            map.put(status.value, status);
        }
    }

    public static IBTStatusEnum valueOf(int status) {
        return (IBTStatusEnum) map.get(status);
    }
     
     
}
