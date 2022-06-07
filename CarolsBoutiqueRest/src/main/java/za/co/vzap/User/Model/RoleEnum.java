package za.co.vzap.User.Model;

import java.util.HashMap;
import java.util.Map;

public enum RoleEnum {
    GENERAL_EMPLOYEE(0),
    TELLER(1),
    MANAGER(2),
    SYSTEM_ADMIN(3);

    private int value;
    private static Map map = new HashMap<>();

    RoleEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
    
    static {
        for (RoleEnum role : RoleEnum.values()) {
            map.put(role.value, role);
        }
    }

    public static RoleEnum valueOf(int role) {
        return (RoleEnum) map.get(role);
    }
}
