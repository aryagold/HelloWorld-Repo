package za.co.vzap.Model.Customer;

import za.co.vzap.Interface.Model.IEntity;

public class Review implements IEntity {
    public int Id;
    private String comment;
    private int rating;
    private String branchId;

    public Review(String comment, int rating, String branchId) {
        this.comment = comment;
        this.rating = rating;
        this.branchId = branchId;
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

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}
