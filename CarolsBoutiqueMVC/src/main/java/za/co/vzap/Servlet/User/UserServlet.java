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
import javax.servlet.http.HttpSession;
import za.co.vzap.ClientService.User.UserService;
import za.co.vzap.Interface.Service.IUserService;
import za.co.vzap.Model.Branch.Branch;
import za.co.vzap.Model.User.TellerRequest;
import za.co.vzap.Model.User.User;
import za.co.vzap.Model.User.UserDto;

/**
 *
 * @author macpe
 */
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

    private IUserService userService;
    
    private String responseTo;
    
    public UserServlet(){
        userService = new UserService();
    }

    public UserServlet(IUserService userService) {
        this.userService = new UserService();
    }
    
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        switch (request.getParameter("submit")) {

            case "addBranch":

                String branchName = request.getParameter("branchName");
                Double monthlyTarget = Double.parseDouble(request.getParameter("monthlyTarget"));
                Double dailyTarget = Double.parseDouble(request.getParameter("dailyTarget"));

                Branch branch = new Branch(branchName, monthlyTarget, dailyTarget);
                String branchId = userService.addBranch(branch);
                
                if(branchId != null) {
                    responseTo = "Store Added";
                    
                } else {
                    responseTo = "Store Add Failed";
                }

                request.setAttribute("response", responseTo);
                request.getRequestDispatcher("responsepage.jsp").forward(request, response);
                
                

            break;

            case "updateTeller":

                String userId = request.getParameter("userId");
                
                TellerRequest tellerRequest = new TellerRequest();
                tellerRequest.userId = userId;

                Boolean result = userService.updateToTeller(tellerRequest);
                
                if(result) {
                    responseTo = "Employee Registered As Teller";
                } else {
                    responseTo = "Registration Failed";
                }
                
                request.setAttribute("response", responseTo);
                request.getRequestDispatcher("responsepage.jsp").forward(request, response);

            break;

            case "login":

                String userId_2 = request.getParameter("userId");
                String password = request.getParameter("password");

                User user = new User(userId_2, password);
                
                UserDto loggedInUser = userService.login(user);

                if (loggedInUser != null) {

                    HttpSession session = request.getSession();

                    session.setAttribute("loggedInUser", loggedInUser);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
               
            break;
            
            case "logout":
                HttpSession session = request.getSession();
                session.invalidate();

                response.sendRedirect("index.jsp");
        }

    }

    @Override
    public String getServletInfo() {
        return "This Servlet handles the User concern";
    }

}
