<%@page import="za.co.vzap.Model.User.UserDto"%>
<%@page import="za.co.vzap.Model.User.User"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="style.css" type="text/css"/>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        
    </head>
    <body>
        <%
            UserDto user = (UserDto) request.getSession(false).getAttribute("loggedInUser");%>
            
          <%  if(user != null) {%>
               <% String redirectURL = "home.jsp"; %>
               <% response.sendRedirect(redirectURL); %>
           <% } %>
        %>
        <div class ="desktop">
            <div class ="frame-container">
                <div class ="frame-7">
                    <h1 class="title inter-medium-white-88px">
                        <span class="inter-medium-white-88px">Login</span>
                    </h1>
                    <div class ="frame-4">
                        <img alt="" class="logo" src="Caol_s_Botique-removebg-preview.png"/>
                    </div>
                </div>
                <div class="frame-8">
                    <div class="frame-3">
                        <form action="UserServlet" method ="post">
                            <label>User ID: <input type ="text" name ="userId"></label>
                            <label>Password: <input type ="password" name ="password"></label>
                            <input type="submit" name="submit" value="login">
                        </form>
                        
                        <a href="home.jsp">Home</a>
                        
                    </div>
                </div>
            </div>

        </div>
    </body>
</html>
