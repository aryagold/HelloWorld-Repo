/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.Model.Requests;

/**
 *
 * @author macpe
 */
public class AddInventoryRequest {
    
    public String userId;
    public String productId;
    public int sizeId;
    public String barcode;

    public AddInventoryRequest(String userId, String productId, int sizeId, String barcode) {
        this.userId = userId;
        this.productId = productId;
        this.sizeId = sizeId;
        this.barcode = barcode;
    }
}
