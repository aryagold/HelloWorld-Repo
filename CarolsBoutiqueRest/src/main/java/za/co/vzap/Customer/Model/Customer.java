package za.co.vzap.Customer.Model;

import za.co.vzap.Sale.Model.IEntity;

public class Customer implements IEntity {
    public int Id;
    private String email;
    private String phoneNumber;

    public Customer(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Customer() {
    }
    
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
