
<%@page import="za.co.vzap.Model.User.User"%>
<%@page import="za.co.vzap.Model.Inventory.Category"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add to Catalogue</title>
    </head>
    <body style="background-color:#f5f5dc;">
        <h1>Add To Catalogue</h1>
        <%
            List<Category> categories = (List<Category>) request.getAttribute("categories");
            String responseAdd = (String) request.getAttribute("response");
            User user = (User) request.getSession(false).getAttribute("user");
            int i = 1;
        %>
        <h2></h2>
        
        <h3>Select a Category: </h3>

        <form action ="ProductServlet" method="post">
            
                <div id="categories">
                    <label>Select Categories</label>
                    <ul>
                        <%for (Category cat : categories) {%>
                        <li><input type="checkbox"><%=cat.getName()%></li>
                        <%}%>
                         
                    </ul>
                </div>
                
                <br>
                <br>
                <br>
                
                <label>Product Name:</label><br>
                <textarea name="name" rows="10" cols="30"></textarea>
                <label>Price:</label><br>
                <textarea name="price" rows="10" cols="30"></textarea>
                <input type="submit" name="submit" value="addProduct">
        </form>
                
    </body>
</html>
