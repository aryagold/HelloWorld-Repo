/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.Model.Requests;

import java.util.List;

/**
 *
 * @author macpe
 */
public class AddProductRequest {
    
    public String name;
    public double price;
    public List<String> categoryIds;
    
    public AddProductRequest(String name, double price, List<String> categoryIds){
        
        this.name = name;
        this.categoryIds = categoryIds;
        this.price = price;
        
    }
    
}
