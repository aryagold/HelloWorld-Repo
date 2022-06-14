/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.Model.Inventory;

import java.sql.Timestamp;

/**
 *
 * @author aryagoldridge
 */
public class InventoryControlDto {
    public int Id;
    public String userId;
    public int inventoryId;
    public Timestamp date;
    public int quantityBefore;
    public int incomingQuantity;
    public int newStockQuantity;
    public boolean posted;
    
    public String productName;
    public String sizeName;
}
