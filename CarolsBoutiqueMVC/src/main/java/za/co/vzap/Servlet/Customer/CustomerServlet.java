/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.vzap.Servlet.Customer;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.vzap.ClientService.Customer.CustomerService;
import za.co.vzap.ClientService.User.UserService;
import za.co.vzap.Interface.Service.ICustomerService;
import za.co.vzap.Interface.Service.IUserService;
import za.co.vzap.Model.Branch.Branch;
import za.co.vzap.Model.Customer.Review;
import za.co.vzap.Model.User.User;

/**
 *
 * @author macpe
 */
@WebServlet(name = "CustomerServlet", urlPatterns = {"/CustomerServlet"})
public class CustomerServlet extends HttpServlet {

    private IUserService userService;
    private ICustomerService customerService;

    public CustomerServlet() {

        userService = new UserService();
        customerService = new CustomerService();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("submit")) {

            case "getBranches":

                List<Branch> branches = null;//get all branches method needed.

                request.setAttribute("branches", branches);
                request.getRequestDispatcher("addreview.jsp").forward(request, response);

                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("submit")) {

            case "addreview":

                Integer rating = Integer.parseInt(request.getParameter("rating"));
                String comment = request.getParameter("comment");

                User user = (User) request.getSession().getAttribute("user");

                Review review = new Review("Not bad", 5, user.getBranchId());
                customerService.addReview(review);

                request.getRequestDispatcher("home.jsp").forward(request, response);

                break;

        }

    }

    @Override
    public String getServletInfo() {
        return "This Servlet handles the Customer concern";
    }

}
