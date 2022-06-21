/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package za.co.vzap.Servlet.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import za.co.vzap.Interface.Service.IUserService;
import za.co.vzap.Model.Branch.Branch;
import za.co.vzap.Model.User.User;

/**
 *
 * @author macpe
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    private IUserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("submit")) {

            case "addBranch":

                String branchName = request.getParameter("branchName");
                Double monthlyTarget = Double.parseDouble(request.getParameter("monthlyTarget"));

                Branch branch = new Branch(branchName, monthlyTarget, monthlyTarget / 30);
                String responseTo = userService.addBranch(branch);

                request.setAttribute("response", responseTo);
                request.getRequestDispatcher("addbranch.jsp").forward(request, response);

            break;

            case "updateTeller":

                String userId = request.getParameter("userId");

                Boolean result = userService.updateToTeller(userId);
                request.setAttribute("response", result);
                request.getRequestDispatcher("updatetoteller.jsp").forward(request, response);

            break;

            case "login":

                String userId_2 = request.getParameter("userId");
                String password = request.getParameter("password");

                User user = new User(userId_2, password);
                
                User loggedInUser = userService.login(user);

                request.getSession().setAttribute("loggedInUser", loggedInUser);
                request.getRequestDispatcher("home.jsp").forward(request, response);

            break;

        }

    }

    @Override
    public String getServletInfo() {
        return "This Servlet handles the User concern";
    }

}
