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
    private User user;

    public InventoryServlet() {

        inventoryService = new InventoryService();
        productService = new ProductService();
        categories = new ArrayList<>();
        sizes = new ArrayList<>();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("submit")) {

            case "getCategories":// this is to the addToCatelogue.jsp

                categories = null;
                categories = productService.getAllCategories();

                request.setAttribute("categories", categories);
                request.getRequestDispatcher("addtocatalogue.jsp").forward(request, response);

                break;

            case "getSizes":// this is to the addInventory.jsp

                sizes = null;
                sizes = null;//this is where the method to hit the getAllSizes end point should be inserted.

                request.setAttribute("sizes", sizes);
                request.getRequestDispatcher("addinventory.jsp").forward(request, response);

                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("submit")) {

            case "addToCatalogue"://this is from the addToCatelogue.jsp

                String productName = request.getParameter("productName");
                Double price = Double.parseDouble(request.getParameter("price"));

                List<String> categoryIds = new ArrayList<>();//storing category IDs returned from the jsp
                categories = productService.getAllCategories();// all categories in system.

                for (int i = 0; i < categories.size(); i++) {
                    try {

                        if (categories.get(i).categoryId == request.getParameter(i + "").toString()) {

                            categoryIds.add(categories.get(i).categoryId);

                        }

                    } catch (NullPointerException e) {
                        continue;
                    }
                }

                prodDto = productService.addProduct(productName, price, categoryIds);
                request.setAttribute("productDto", prodDto);
                request.getRequestDispatcher("addtocatalogue.jsp").forward(request, response);

                break;

            case "captureInventory":
                
                try {

                String barcode = request.getParameter("barcode");
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                invenCtrlDto = inventoryService.captureInventory(user.userId, barcode, quantity);

                request.setAttribute("inventoryControlDto", invenCtrlDto);
                request.getRequestDispatcher("captureinventory.jsp").forward(request, response);

            } catch (Exception ex) {
                Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;

            case "addInventory":

                String productID = request.getParameter("productId");
                String barcode = request.getParameter("barcode");

                sizes = null;//service method to receive a list of all sizes.
                int sizeId = 0;

                for (int i = 0; i < sizes.size(); i++) {

                    if (sizes.get(i).Id == Integer.parseInt(request.getAttribute("sizeId").toString())) {
                        sizeId = sizes.get(i).Id;
                    }

                }

                invenDto = inventoryService.addInventory(user.userId, productID, sizeId, barcode);

                request.setAttribute("inventoryDto", invenDto);
                request.getRequestDispatcher("addinventory.jsp").forward(request, response);

                break;

            case "findInventory":

                String searchTerm = request.getParameter("searchTerm");

                List<InventoryDto> dtos = null;

                try {
                    dtos = inventoryService.findInventory(searchTerm);
                } catch (Exception ex) {
                    Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.setAttribute("dtos", dtos);
                request.getRequestDispatcher("findinventory.jsp").forward(request, response);

                break;


        }

    }

    @Override
    public String getServletInfo() {
        return "This Servlet handles the Inventory concern";
    }

}
