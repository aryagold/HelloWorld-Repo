package za.co.vzap.Model.Sale;

import za.co.vzap.Interface.Model.IEntity;

public class Payment implements IEntity {

    public int Id;
    private PaymentTypeEnum type;
    private String cardNumber;
    private boolean approved;

    public Payment(PaymentTypeEnum type, String cardNumber, boolean approved) {
        this.type = type;
        this.cardNumber = cardNumber;
        this.approved = approved;
    }

    public Payment() {
    }

    public PaymentTypeEnum getType() {
        return type;
    }

    public void setType(PaymentTypeEnum type) {
        this.type = type;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
