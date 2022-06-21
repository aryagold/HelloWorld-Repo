/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.vzap.Servlet.POS;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.vzap.ClientService.POS.POSService;
import za.co.vzap.ClientService.Product.ProductService;
import za.co.vzap.Interface.Service.IPOSService;
import za.co.vzap.Interface.Service.IProductService;
import za.co.vzap.Model.Inventory.ProductDto;
import za.co.vzap.Model.Sale.IbtDto;

/**
 *
 * @author macpe
 */
@WebServlet(name = "POSServlet", urlPatterns = {"/POSServlet"})
public class POSServlet extends HttpServlet {

    private IPOSService posService;
    private IProductService productService;

    public POSServlet() {

        posService = new POSService();
        productService = new ProductService();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("submit")) {
            
            case "search":
                
                String productId = request.getParameter("productId");
                
                List<ProductDto> prodDtos = productService.getAllProducts();
                
                request.setAttribute("ListProductDtos", prodDtos);
                request.getRequestDispatcher("transferrequest.jsp").forward(request, response);
                
            break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("submit")) {

            case "requestIBT":

                IbtDto dto = (IbtDto)request.getAttribute("ibtDto");
                IbtDto ibtDto = posService.addIbt(dto);
                
                request.setAttribute("ibtDto", ibtDto);
                request.getRequestDispatcher("home.jsp").forward(request, response);
                
            break;

        }

    }

    @Override
    public String getServletInfo() {
        return "This Servlet handles the POS concern";
    }

}
