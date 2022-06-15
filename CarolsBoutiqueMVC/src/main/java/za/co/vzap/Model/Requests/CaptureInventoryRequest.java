/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.Model.Requests;

/**
 *
 * @author macpe
 */
public class CaptureInventoryRequest {
    
    public String userId ;
    public String barcode;
    public int quantity;
    
    public CaptureInventoryRequest(String userId , String barcode, int quantity){

        this.userId = userId;
        this.barcode = barcode;
        this.quantity = quantity;
        
    }
    
}
