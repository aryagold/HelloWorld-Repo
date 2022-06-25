/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.vzap.Servlet.Product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.vzap.ClientService.Product.ProductService;
import za.co.vzap.Interface.Service.IProductService;
import za.co.vzap.Model.Inventory.Category;
import za.co.vzap.Model.Inventory.ProductDto;

/**
 *
 * @author macpe
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/ProductServlet"})
public class ProductServlet extends HttpServlet {
    private IProductService productService;
    private List<Category> categories;
    private ProductDto prodDto;
    
    private String responseTo;

    public ProductServlet() {
        productService = new ProductService();
        categories = new ArrayList<>();
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        switch(request.getParameter("submit")) {
            case "addProduct":

                String productName = request.getParameter("name");
                Double price = Double.parseDouble(request.getParameter("price"));
                
                List<String> categoryIds = Arrays.asList(request.getParameterValues("categoryId"));
                

                prodDto = productService.addProduct(productName, price, categoryIds);
                
                if(prodDto != null) {
                    responseTo = prodDto.productId + " was added to catalogue.";
                } else {
                    responseTo = "Product was not added.";
                }
                
                request.setAttribute("response", responseTo);
                request.getRequestDispatcher("responsepage.jsp").forward(request, response);

                break;
        
        }
    }

    @Override
    public String getServletInfo() {
        return "This Servlet handles the Product concern";
    }

}
