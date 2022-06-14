package za.co.vzap.Model.Inventory;

import za.co.vzap.Interface.Model.IEntity;

public class Size implements IEntity { // created this model for LOOKUP purposes. CRUD only takes place database side.
    public int Id;
    private String size;

    public Size(String size) {
        this.size = size;
    }

    public Size() {
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    
    
}
