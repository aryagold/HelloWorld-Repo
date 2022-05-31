package co.vzap.Inventory.Model;

import co.vzap.Sale.Model.IEntity;

public class Category implements IEntity {
    public String categoryId;
    private String name;

    public Category(String name) {
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
