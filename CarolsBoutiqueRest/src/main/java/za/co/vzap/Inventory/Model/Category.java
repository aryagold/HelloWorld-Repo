package za.co.vzap.Inventory.Model;

import za.co.vzap.Interface.Model.IEntity;

public class Category implements IEntity {
    public String categoryId;
    private String name;

    public Category(String name) {
        this.name = name;
    }

    public Category(String categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public Category() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
