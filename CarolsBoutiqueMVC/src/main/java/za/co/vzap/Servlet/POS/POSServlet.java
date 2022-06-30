/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.vzap.Servlet.POS;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import za.co.vzap.ClientService.POS.POSService;
import za.co.vzap.ClientService.Product.ProductService;
import za.co.vzap.Interface.Service.IInventoryService;
import za.co.vzap.Interface.Service.IPOSService;
import za.co.vzap.Interface.Service.IProductService;
import za.co.vzap.Model.Inventory.InventoryDto;
import za.co.vzap.Model.Sale.IBTStatusEnum;
import za.co.vzap.Model.Sale.IbtDto;
import za.co.vzap.Model.Sale.Payment;
import za.co.vzap.Model.Sale.PaymentTypeEnum;
import za.co.vzap.Model.Sale.RefundDto;
import za.co.vzap.Model.Sale.SaleDto;
import za.co.vzap.Model.Sale.SaleLineItemDto;
import za.co.vzap.Model.Sale.SaleStatusEnum;
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
                
                HttpSession session = request.getSession(false);
                UserDto userDto = (UserDto) session.getAttribute("loggedInUser");
                String userId = userDto.userId;
                
                List<IbtDto> ibtDtos = posService.listIbt(userId, 1);
                List<IbtDto> ibtDtosRequest = new ArrayList<>();
                
                for(IbtDto dto: ibtDtos) {
                    if(dto.status != IBTStatusEnum.REQUESTED) continue;
                    
                    ibtDtosRequest.add(dto);
                }
                
                request.setAttribute("ibts", ibtDtosRequest);
                request.getRequestDispatcher("requestsreceived.jsp").forward(request, response);
                
            break;
            case "requeststatus":
                session = request.getSession(true);
                userDto = (UserDto) session.getAttribute("loggedInUser");
                userId = userDto.userId;

                ibtDtos = posService.listIbt(userId, 0);
                List<IbtDto> ibtDtosStatus = new ArrayList<>();

                for (IbtDto dto : ibtDtos) {
                    if (dto.status == IBTStatusEnum.RECEIVED) continue;
                    
                    ibtDtosStatus.add(dto);   
                }

                request.setAttribute("ibts", ibtDtosStatus);
                request.getRequestDispatcher("requestssent.jsp").forward(request, response);
            break;
                
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("submit")) {
            case "continueSale" :
                HttpSession session = request.getSession(true);
                SaleDto saleDto = (SaleDto) session.getAttribute("sale");
                request.setAttribute("sale", saleDto);
                
                request.getRequestDispatcher("completesale.jsp").forward(request, response);
            break;
            
            case "cancelSale":
                session = request.getSession(true);
                saleDto = (SaleDto) session.getAttribute("sale");
                session.removeAttribute("sale");

                String redirectURL = "tillhome.jsp";

                response.sendRedirect(redirectURL);
            break;
            
            case "continueRefund":
                session = request.getSession(true);
                RefundDto refundDto = (RefundDto) session.getAttribute("refund");
                request.setAttribute("refund", refundDto);

                request.getRequestDispatcher("completerefund.jsp").forward(request, response);
                break;

            case "cancelRefund":
                session = request.getSession(true);
                refundDto = (RefundDto) session.getAttribute("refund");
                session.removeAttribute("refund");

                redirectURL = "tillhome.jsp";

                response.sendRedirect(redirectURL);
            break;
            
            case "addPayment" :
                boolean success = false;
                PaymentTypeEnum type = PaymentTypeEnum.valueOf(Integer.parseInt(request.getParameter("paymentType")));
                
                if(type == PaymentTypeEnum.CARD) {
                    int i = (int)((Math.random() * 5 - 1) + 1);
                    
                    if(i > 1) {
                        success = true;
                    }
                } else {
                    success = true;
                }
                
                if(success) {
                    session = request.getSession(true);
                    saleDto = (SaleDto) session.getAttribute("sale");
                    
                    Payment payment = new Payment();
                    payment.setType(type);
                    payment.setCardNumber(request.getParameter("cardNumber"));
                    payment.setApproved(success);
                    
                    saleDto.payment = payment;
                    
                    responseTo = "Payment successful.";
                } else {
                    responseTo = "Payment unsuccessful. Please try again.";
                }
                
                request.setAttribute("response", responseTo);
                request.getRequestDispatcher("completesale.jsp").forward(request, response);
            break;
            
            case "addSale":
                session = request.getSession(true);
                UserDto userDto = (UserDto) session.getAttribute("loggedInUser");
                String userId = userDto.userId;
                
                HttpSession saleSession = request.getSession(true);
                saleDto = (SaleDto) session.getAttribute("sale");
                
                saleDto.userId = userId;
                saleDto.customerEmail = request.getParameter("customerEmail");
                saleDto.status = SaleStatusEnum.COMPLETED;
                
                SaleDto saleReturn = posService.addSale(saleDto);
                
                if (saleReturn != null) {          
                    responseTo = "Sale " + saleReturn.saleId + " has been completed. Receipt emailed to " + saleReturn.customerEmail;
                }

                request.setAttribute("response", responseTo);
                request.getRequestDispatcher("responsepage.jsp").forward(request, response);
                break;
                
            case "addRefund" :
                session = request.getSession(true);
                userDto = (UserDto) session.getAttribute("loggedInUser");
                userId = userDto.userId;

                HttpSession refundSession = request.getSession(true);
                refundDto = (RefundDto) session.getAttribute("refund");
                
                refundDto.saleId = request.getParameter("saleId");
                refundDto.date = Timestamp.valueOf(LocalDateTime.now());
                
                RefundDto refundReturn = posService.addRefund(refundDto);
                
                if (refundReturn != null) {
                    responseTo = "Items from sale " + refundReturn.saleId + " have been successfully refunded. Amended receipt emailed to customer";
                }

                request.setAttribute("response", responseTo);
                request.getRequestDispatcher("responsepage.jsp").forward(request, response);
                break;
                
            case "addReserve":
                session = request.getSession(true);
                userDto = (UserDto) session.getAttribute("loggedInUser");
                userId = userDto.userId;

                saleSession = request.getSession(true);
                saleDto = (SaleDto) session.getAttribute("sale");

                saleDto.userId = userId;
                saleDto.customerEmail = request.getParameter("customerEmail");
                saleDto.status = SaleStatusEnum.RESERVED;

                SaleDto reserveReturn = posService.addSale(saleDto);

                if (reserveReturn != null) {
                    responseTo = "Reserve " + reserveReturn.saleId + " has been completed. Customer payment due within 48 hours.";
                    
                }

                request.setAttribute("response", responseTo);
                request.getRequestDispatcher("responsepage.jsp").forward(request, response);
                break;    
                
            case "addIbt":

                session = request.getSession(true);
                userDto = (UserDto) session.getAttribute("loggedInUser");
                
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
                userId = userDto.userId;

                List<IbtDto> ibtDtosRequest = posService.listIbt(userId, 1);
                List<IbtDto> ibtDtos = new ArrayList<>();

                for (IbtDto dto_1 : ibtDtosRequest) {
                    if (dto_1.status != IBTStatusEnum.REQUESTED) continue;
                    
                    ibtDtos.add(dto_1);
                }

                request.setAttribute("ibts", ibtDtos);
                request.getRequestDispatcher("requestsreceived.jsp").forward(request, response);
                
            break;
            
            case "declineIbt":
                id = request.getParameter("ibtId");
                posService.declineIbt(Integer.parseInt(id));

                session = request.getSession(true);
                userDto = (UserDto) session.getAttribute("loggedInUser");
                userId = userDto.userId;

                ibtDtos = posService.listIbt(userId, 1);
                ibtDtosRequest = new ArrayList<>();

                for (IbtDto dto_1 : ibtDtos) {
                    if (dto_1.status != IBTStatusEnum.REQUESTED) continue;
                    
                    ibtDtosRequest.add(dto_1);
                }

                request.setAttribute("ibts", ibtDtosRequest);
                request.getRequestDispatcher("requestsreceived.jsp").forward(request, response);
            break;
            
            case "receivedIbt":
                id = request.getParameter("ibtId");
                posService.receivedIbt(Integer.parseInt(id));

                session = request.getSession(true);
                userDto = (UserDto) session.getAttribute("loggedInUser");
                userId = userDto.userId;

                ibtDtosRequest = posService.listIbt(userId, 0);
                List<IbtDto> ibtDtosStatus = new ArrayList<>();

                for (IbtDto dto_1 : ibtDtosRequest) {
                    if (dto_1.status == IBTStatusEnum.RECEIVED) continue;
                    
                    ibtDtosStatus.add(dto_1);
                }

                request.setAttribute("ibts", ibtDtosStatus);
                request.getRequestDispatcher("requestssent.jsp").forward(request, response);
            break;
        }

    }

    @Override
    public String getServletInfo() {
        return "This Servlet handles the POS concern";
    }

}
