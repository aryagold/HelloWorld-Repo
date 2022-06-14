/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.ClientService.Inventory;

import java.util.List;
import za.co.vzap.Interface.Service.IInventoryService;
import za.co.vzap.Model.Inventory.InventoryControlDto;
import za.co.vzap.Model.Inventory.InventoryDto;

/**
 *
 * @author macpe
 */
public class InventoryService implements IInventoryService{

    @Override
    public List<InventoryDto> getBranchInventory(String arg0) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public InventoryControlDto captureInventory(String arg0, String arg1, int arg2) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public InventoryDto addInventory(String arg0, String arg1, int arg2, String arg3) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<InventoryDto> findStockWithProductId(String arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<InventoryDto> findStockWithBarcode(String arg0) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
