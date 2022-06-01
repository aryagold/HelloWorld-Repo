package za.co.vzap.Customer.Model;

import za.co.vzap.Sale.Model.IEntity;

public class Review implements IEntity {
    public int Id;
    private String comment;
    private int rating;
    private String saleId;

    public Review(String comment, int rating, String saleId) {
        this.comment = comment;
        this.rating = rating;
        this.saleId = saleId;
    }

    public Review() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }
}
