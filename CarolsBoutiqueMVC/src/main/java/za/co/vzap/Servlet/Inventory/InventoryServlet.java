/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.vzap.Servlet.Inventory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import za.co.vzap.ClientService.Inventory.InventoryService;
import za.co.vzap.ClientService.Product.ProductService;
import za.co.vzap.Interface.Service.IInventoryService;
import za.co.vzap.Interface.Service.IProductService;
import za.co.vzap.Model.Inventory.Category;
import za.co.vzap.Model.Inventory.InventoryControlDto;
import za.co.vzap.Model.Inventory.InventoryDto;
import za.co.vzap.Model.Inventory.ProductDto;
import za.co.vzap.Model.Inventory.Size;
import za.co.vzap.Model.User.User;
import za.co.vzap.Model.User.UserDto;

/**
 *
 * @author macpe
 */
@WebServlet(name = "InventoryServlet", urlPatterns = {"/InventoryServlet"})
public class InventoryServlet extends HttpServlet {

    private IInventoryService inventoryService;
    private IProductService productService;
    private List<Category> categories;
    private List<Size> sizes;
    private ProductDto prodDto;
    private InventoryControlDto invenCtrlDto;
    private InventoryDto invenDto; 
    
    private String responseTo;

    public InventoryServlet() {

        inventoryService = new InventoryService();
        productService = new ProductService();
        categories = new ArrayList<>();
        sizes = new ArrayList<>();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("submit")) {

            case "getCategories":

                categories = null;
                categories = productService.getAllCategories();

                request.setAttribute("categories", categories);
                request.getRequestDispatcher("addtocatalogue.jsp").forward(request, response);

                break;

            case "getSizes":

                sizes = null;
                sizes = null;//this is where the method to hit the getAllSizes end point should be inserted.

                request.setAttribute("sizes", sizes);
                request.getRequestDispatcher("addinventory.jsp").forward(request, response);

                break;
            
            case "findInventory":

                String productID_2 = request.getParameter("productId");
                String barcode_2 = request.getParameter("barcode");
                System.out.println("Barcode: " + barcode_2);
                System.out.println("Product ID: " + productID_2);

                List<InventoryDto> dtos = null;

                try {
                    if (barcode_2.equals("")) {

                        dtos = inventoryService.findInventory(productID_2);

                    } else {

                        dtos = inventoryService.findInventory(barcode_2);

                    }

                } catch (Exception ex) {
                    Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if(dtos != null) {
                    
                    request.setAttribute("items", dtos);
                    request.getRequestDispatcher("searchresults.jsp").forward(request, response);
                } 

                break;
                
            case "getitem" :
                String barcodeScanned = request.getParameter("barcode");
                
                InventoryDto itemFound = inventoryService.getItem(barcodeScanned);
                
                request.setAttribute("item", itemFound);
                request.getRequestDispatcher("pointofsale.jsp").forward(request, response);
                
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("submit")) {

            case "captureInventory":
                
                try {

                String barcode = request.getParameter("barcode");
                String quantity = request.getParameter("quantity");
                
                HttpSession session = request.getSession(true);
                UserDto userDto = (UserDto) session.getAttribute("loggedInUser");
                String userId = userDto.userId;

                invenCtrlDto = inventoryService.captureInventory(userId, barcode, Integer.parseInt(quantity));
                
                if(invenCtrlDto != null) {
                    responseTo = "The quantity of " + quantity + " products of barcode:" + barcode + " has been captured."; 
                } else {
                    responseTo = quantity + " products not captured successfully.";
                }

                request.setAttribute("response", responseTo);
                request.getRequestDispatcher("responsepage.jsp").forward(request, response);

            } catch (Exception ex) {
                Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;

            case "addInventory":

                String productID = request.getParameter("productId");
                String barcode = request.getParameter("barcode");
                String sizeId = request.getParameter("size");
                
                HttpSession session = request.getSession(true);
                UserDto userDto = (UserDto) session.getAttribute("loggedInUser");
                String userId = userDto.userId;
                
                invenDto = inventoryService.addInventory(userId, productID, Integer.parseInt(sizeId), barcode);
                
                if(invenDto != null) {
                    responseTo = invenDto.productName + " in size " + invenDto.sizeName + " was added to inventory. Proceed to print barcode and capture stock.";
                } else {
                    responseTo = "Item was not successfully added to inventory.";
                }

                request.setAttribute("response", responseTo);
                request.getRequestDispatcher("responsepage.jsp").forward(request, response);

                break;

        }

    }

    @Override
    public String getServletInfo() {
        return "This Servlet handles the Inventory concern";
    }

}
