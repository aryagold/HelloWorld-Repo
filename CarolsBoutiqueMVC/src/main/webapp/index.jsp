<%@page import="za.co.vzap.Model.User.UserDto"%>
<%@page import="za.co.vzap.Model.User.User"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <link rel="stylesheet" href="styles.css" />
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" 
              rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Cedarville+Cursive&display=swap" rel="stylesheet"> 
        <title>Login</title>
    </head>
    <body>
        
        
            <%UserDto user = (UserDto) request.getSession(false).getAttribute("loggedInUser");%>
            
          <%  if(user != null) {%>
               <% String redirectURL = "home.jsp"; %>
               <% response.sendRedirect(redirectURL); %> 
           <% } %>
       
        <div class="d-flex justify-content-center"><img alt="" class="logo" src="Caol_s_Botique-removebg-preview.png"/></div>
        
        <style>
            body {
                background-image: url('istockphoto-184640309-612x612.jpg');
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: cover;
            }
        </style>
        
                
                        <form action="UserServlet" method ="post">
                            <div class="d-flex justify-content-center"><label>User ID: <br></div>
                            <div class="d-flex justify-content-center"><input type ="text" name ="userId"></label><br></div>
                            <div class="d-flex justify-content-center"><label>Password: <br></div>
                            <div class="d-flex justify-content-center"><input type ="password" name ="password"></label><br></div>
                            <br>
                            <br>
                            <div class="d-flex justify-content-center"><button id="submit" name="submit" value="login">Login</button></div>
                        </form>
                        
                        
                        
    </body>
</html>
