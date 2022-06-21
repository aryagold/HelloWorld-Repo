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
import za.co.vzap.Model.Report.CustomerReports;
import za.co.vzap.Model.Report.LeastPerformingStores;
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
    private Branch branch;

    public ReportServlet(Branch branch) {

        reportService = new ReportService();
        this.branch = branch;

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("submit")) {

            case "TopAchievingStores":

                TopAchievingStoresDto dto = reportService.topAchievingStores();

                request.setAttribute("dto", dto);
                request.getRequestDispatcher("report.jsp").forward(request, response);

                break;

            case "CustomerReports":

                CustomerReports cr = reportService.getCustomerReport();

                request.setAttribute("CustomerReports", cr);
                request.getRequestDispatcher("report.jsp").forward(request, response);

                break;

            case "SalesForMonth":

                StoreSalesDto dto_2 = reportService.storeSalesByMonth(branch.branchId, "January");//ask where the month is comming from

                request.setAttribute("dto", dto_2);
                request.getRequestDispatcher("report.jsp").forward(request, response);

                break;

            case "TopSellingEmployees":

                TopEmployeesDto dto_3 = reportService.topSellingEmployees(branch.branchId);

                request.setAttribute("dto", dto_3);
                request.getRequestDispatcher("report.jsp").forward(request, response);

                break;

            case "StoresAtTarget":

                StoresAtTargetDto dto_4 = reportService.storesAtTarget();

                request.setAttribute("dto", dto_4);
                request.getRequestDispatcher("report.jsp").forward(request, response);

                break;

            case "TopProducts":

                TopFourtyProducts top = reportService.getTop40Products();

                request.setAttribute("top", top);
                request.getRequestDispatcher("report.jsp").forward(request, response);

                break;

            case "LeastPerformingStores":

                LeastPerformingStores least = reportService.getLeastPerforming();

                request.setAttribute("least", least);
                request.getRequestDispatcher("report.jsp").forward(request, response);

                break;

            case "ProductStats":

                ProductSales sales = reportService.getProductSales();

                request.setAttribute("ProductSales", sales);
                request.getRequestDispatcher("report.jsp").forward(request, response);

                break;

            case "SalesForTheDay":

                StoreSalesDto dto_5 = reportService.storeDailySales(branch.branchId);

                request.setAttribute("dto", dto_5);
                request.getRequestDispatcher("report.jsp").forward(request, response);

                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("submit")) {

            case "download":

                String responseTo = reportService.downloadCurrentReport();

                request.setAttribute("response", responseTo);

                break;

        }

    }

    @Override
    public String getServletInfo() {
        return "This Servlet handles the Repost concern";
    }

}
