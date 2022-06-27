/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.vzap.Servlet.POS;

import java.io.IOException;
import java.util.Arrays;
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
import za.co.vzap.ClientService.POS.POSService;
import za.co.vzap.ClientService.Product.ProductService;
import za.co.vzap.Interface.Service.IInventoryService;
import za.co.vzap.Interface.Service.IPOSService;
import za.co.vzap.Interface.Service.IProductService;
import za.co.vzap.Model.Inventory.InventoryDto;
import za.co.vzap.Model.Inventory.ProductDto;
import za.co.vzap.Model.Sale.IBTStatusEnum;
import za.co.vzap.Model.Sale.IbtDto;
import za.co.vzap.Model.User.UserDto;
import za.co.vzap.Servlet.Inventory.InventoryServlet;

/**
 *
 * @author macpe
 */
@WebServlet(name = "POSServlet", urlPatterns = {"/POSServlet"})
public class POSServlet extends HttpServlet {

    private IPOSService posService;
    private IProductService productService;
    private IInventoryService inventoryService;
    
    private String responseTo;

    public POSServlet() {

        posService = new POSService();
        productService = new ProductService();
        inventoryService = new InventoryService();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("submit")) {
            
            case "search":
                
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

                if (dtos != null) {

                    request.setAttribute("items", dtos);
                    request.getRequestDispatcher("transferrequest.jsp").forward(request, response);
                }

                break;
            case "newrequests":
                
                HttpSession session = request.getSession(true);
                UserDto userDto = (UserDto) session.getAttribute("loggedInUser");
                String userId = userDto.userId;
                
                List<IbtDto> ibtDtosRequest = posService.listIbt(userId, 1);
                
                request.setAttribute("ibts", ibtDtosRequest);
                request.getRequestDispatcher("requestsreceived.jsp").forward(request, response);
                
            break;
            case "requeststatus":
                session = request.getSession(true);
                userDto = (UserDto) session.getAttribute("loggedInUser");
                userId = userDto.userId;

                List<IbtDto> ibtDtosStatus = posService.listIbt(userId, 0);

                request.setAttribute("ibts", ibtDtosStatus);
                request.getRequestDispatcher("requestssent.jsp").forward(request, response);
            break;
                
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("submit")) {

            case "addIbt":

                HttpSession session = request.getSession(true);
                UserDto userDto = (UserDto) session.getAttribute("loggedInUser");
                
                String inventoryIdFrom = request.getParameter("inventoryIdFrom");
                String phoneNumber = request.getParameter("phoneNumber");
                String email = request.getParameter("customerEmail");
                
                String branchIdTo = userDto.branchId;
                
                IbtDto dto = new IbtDto();
                dto.inventoryIdFrom = Integer.parseInt(inventoryIdFrom);
                dto.branchIdTo = branchIdTo;
                dto.emailAddress = email;
                dto.phoneNumber = phoneNumber;
                IbtDto ibtDto = posService.addIbt(dto);
                
                if(ibtDto != null) {
                    responseTo = "A transfer of " + ibtDto.productName + " in size " + ibtDto.sizeName + " has been requested from "
                            + ibtDto.branchNameFrom;
                }
                
                request.setAttribute("response", responseTo);
                request.getRequestDispatcher("responsepage.jsp").forward(request, response);
                
            break;
          
            case "approveIbt":
                String id = request.getParameter("ibtId");
                posService.approveIbt(Integer.parseInt(id));
                
                session = request.getSession(true);
                userDto = (UserDto) session.getAttribute("loggedInUser");
                String userId = userDto.userId;

                List<IbtDto> ibtDtosRequest = posService.listIbt(userId, 1);

                request.setAttribute("ibts", ibtDtosRequest);
                request.getRequestDispatcher("requestsreceived.jsp").forward(request, response);
                
            break;
            case "declineIbt":
            break;

        }

    }

    @Override
    public String getServletInfo() {
        return "This Servlet handles the POS concern";
    }

}
