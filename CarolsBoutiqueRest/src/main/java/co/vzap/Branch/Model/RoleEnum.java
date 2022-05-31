package co.vzap.Branch.Model;

public enum RoleEnum {
    GENERAL_EMPLOYEE(0),
    TELLER(1),
    MANAGER(2),
    SYSTEM_ADMIN(3);

    int value;

    RoleEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
