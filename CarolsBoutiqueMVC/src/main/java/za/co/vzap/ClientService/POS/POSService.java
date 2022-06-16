/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.vzap.ClientService.POS;

import java.util.List;
import za.co.vzap.Interface.Service.IPOSService;
import za.co.vzap.Model.Inventory.InventoryDto;
import za.co.vzap.Model.Sale.IbtDto;
import za.co.vzap.Model.Sale.Payment;
import za.co.vzap.Model.Sale.Refund;
import za.co.vzap.Model.Sale.RefundItemDto;
import za.co.vzap.Model.Sale.Sale;
import za.co.vzap.Model.Sale.SaleDto;
import za.co.vzap.Model.Sale.SaleLineItemDto;

/**
 *
 * @author macpe
 */
public class POSService implements IPOSService {

    @Override
    public String addSale(Sale arg0) {
     
        return null;
    
    }

    @Override
    public SaleDto voidSale(String arg0) {
        
        return null;
    
    }

    @Override
    public SaleLineItemDto addSaleLineItem(SaleLineItemDto arg0) throws Exception {
       
        return null;
    
    }

    @Override
    public List<SaleLineItemDto> getSaleLineItems(String arg0) {
       
        return null;
    
    }

    @Override
    public int addRefund(Refund arg0) {
        
        return 0;
    
    }

    @Override
    public RefundItemDto addRefundItem(RefundItemDto arg0) {
       
        return null;
    
    }

    @Override
    public int completeSale(Payment arg0, String arg1) {
       
        return 0;
    
    }

    @Override
    public boolean deleteSaleLineItem(int arg0) {
        
        return false;
    
    }

    @Override
    public boolean reserveSale(String arg0) {
       
        return false;
    
    }

    @Override
    public IbtDto requestIbt(int arg0, String arg1, int arg2, String arg3, String arg4) {
       
        return null;   
    
    }

    @Override
    public IbtDto approveIbt(int arg0) {
        
        return null;
        
    }

    @Override
    public IbtDto declineIbt(int arg0) {
        
        return null;
    
     }

    @Override
    public IbtDto ibtReceived(int arg0) {
    
        return null;
    
    }

    @Override
    public void payIBT(InventoryDto arg0, int arg1, Sale arg2, Payment arg3) {
          
    
    
    }

    @Override
    public List<IbtDto> viewIbt() {
        
         return null;
    
    }
    
}
