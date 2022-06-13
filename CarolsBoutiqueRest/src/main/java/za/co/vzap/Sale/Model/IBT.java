package za.co.vzap.Sale.Model;

import za.co.vzap.Interface.Model.IEntity;

public class IBT implements IEntity {
    public int Id;
    private int inventoryIdFrom;
    private String branchIdTo;
    private int quantity;
    private String phoneNumber;
    private String emailAddress;
    private IBTStatusEnum status;

    public IBT(int inventoryIdFrom, String branchIdTo, int quantity, String phoneNumber, String emailAddress, IBTStatusEnum status) {
        this.inventoryIdFrom = inventoryIdFrom;
        this.branchIdTo = branchIdTo;
        this.quantity = quantity;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.status = status;
    }

    public IBT() {
    }

    public int getInventoryIdFrom() {
        return inventoryIdFrom;
    }

    public void setInventoryIdFrom(int inventoryIdFrom) {
        this.inventoryIdFrom = inventoryIdFrom;
    }

    public String getBranchIdTo() {
        return branchIdTo;
    }

    public void setBranchIdTo(String branchIdTo) {
        this.branchIdTo = branchIdTo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public IBTStatusEnum getStatus() {
        return status;
    }

    public void setStatus(IBTStatusEnum status) {
        this.status = status;
    } 
}
