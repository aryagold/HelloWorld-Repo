<%@page import="za.co.vzap.Model.Inventory.InventoryDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Request Transfer</title>
    </head>
    <body>
        <% 
            InventoryDto dto = (InventoryDto)request.getAttribute("item");
        
        %>
        <h1>Request Inter Branch Transfer for Item/s</h1>
        <form action="POSServlet" method ="post">
            <label>Customer Phone Number: <input type="text" name="phoneNumber" ></label><br>
            <label>Customer Email: <input type="text" name="email" ></label>
            <input type="submit" name="submit" value="addIbt">
        </form> 
    </body>
</html>
