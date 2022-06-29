/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.vzap.Servlet.Report;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.vzap.ClientService.Report.ReportService;
import za.co.vzap.Interface.Service.IReportService;
import za.co.vzap.Model.Branch.Branch;
import za.co.vzap.Model.Report.CustomerReportsDto;
import za.co.vzap.Model.Report.LeastPerformingStoresDto;
import za.co.vzap.Model.Report.ProductSales;
import za.co.vzap.Model.Report.StoreSalesDto;
import za.co.vzap.Model.Report.StoresAtTargetDto;
import za.co.vzap.Model.Report.TopAchievingStoresDto;
import za.co.vzap.Model.Report.TopEmployeesDto;
import za.co.vzap.Model.Report.TopFourtyProducts;

/**
 *
 * @author macpe
 */
@WebServlet(name = "ReportServlet", urlPatterns = {"/ReportServlet"})
public class ReportServlet extends HttpServlet {

    private IReportService reportService;

    public ReportServlet() {
        reportService = new ReportService();
       

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("submit")) {

            case "topachievingstores":

                TopAchievingStoresDto dto = reportService.topAchievingStores();

                request.setAttribute("dto", dto);
                request.getRequestDispatcher("topachievingstores.jsp").forward(request, response);

                break;

            case "customerreports":
                String month = request.getParameter("month");
                String resultAmount = request.getParameter("resultAmount");
                
                if(month == null) {
                    month = "June";
                    request.setAttribute("month", month);
                }
                
                if(resultAmount == null) {
                    resultAmount = "6";
                    request.setAttribute("resultAmount", resultAmount);
                }

                CustomerReportsDto cr = reportService.getCustomerReport(month, Integer.parseInt(resultAmount));

                request.setAttribute("customerreports", cr);
                request.getRequestDispatcher("customerreport.jsp").forward(request, response);

                break;

            case "salesformonth":
                String branchId = request.getParameter("branchId");
                month = request.getParameter("month");
                
                if (branchId == null) {
                    branchId = "BR001";
                    request.setAttribute("branchId", branchId);
                }
                
                if (month == null) {
                    month = "June";
                    request.setAttribute("month", month);
                }

                StoreSalesDto dto_2 = reportService.storeSalesByMonth(branchId, month);

                request.setAttribute("dto", dto_2);
                request.getRequestDispatcher("storemonthlysales.jsp").forward(request, response);

                break;

            case "topsellingemployees":
                branchId = request.getParameter("branchId");
                
                if(branchId == null) {
                    branchId = "BR001";
                    request.setAttribute("branchId", branchId);
                }

                TopEmployeesDto dto_3 = reportService.topSellingEmployees(branchId);

                request.setAttribute("dto", dto_3);
                request.getRequestDispatcher("topsellingemployees.jsp").forward(request, response);

                break;

            case "storesattarget":
                month = request.getParameter("month");
                
                if(month == null) {
                    month = "June";
                    request.setAttribute("month", month);
                }

                StoresAtTargetDto dto_4 = reportService.storesAtTarget(month);

                request.setAttribute("dto", dto_4);
                request.getRequestDispatcher("storesattarget.jsp").forward(request, response);

                break;

            case "topproducts":

                TopFourtyProducts top = reportService.getTop40Products();

                request.setAttribute("top", top);
                request.getRequestDispatcher("topproducts.jsp").forward(request, response);

                break;

            case "leastperformingstores":
                String interval = request.getParameter("interval");
                
                if(interval == null) {
                    interval = "3";
                    request.setAttribute("interval", interval);
                }

                LeastPerformingStoresDto least = reportService.getLeastPerforming(Integer.parseInt(interval));

                request.setAttribute("least", least);
                request.getRequestDispatcher("leastperformingstores.jsp").forward(request, response);

                break;

            case "productstats":

                ProductSales sales = reportService.getProductSales();

                request.setAttribute("productsales", sales);
                request.getRequestDispatcher("productstats.jsp").forward(request, response);

                break;

            case "salesfortheday":
                branchId = request.getParameter("branchId");
                
                if (branchId == null) {
                    branchId = "BR001";
                    request.setAttribute("branchId", branchId);
                }

                StoreSalesDto dto_5 = reportService.storeDailySales(branchId);

                request.setAttribute("dto", dto_5);
                request.getRequestDispatcher("storedailysales.jsp").forward(request, response);

                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("submit")) {

           

        }

    }

    @Override
    public String getServletInfo() {
        return "This Servlet handles the Repost concern";
    }

}
