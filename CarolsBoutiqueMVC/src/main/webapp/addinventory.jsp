<%@page import="za.co.vzap.Model.Inventory.Size"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Inventory</title>
    </head>
    <body>
        <h1>Add Inventory</h1>
        <%
            List<Size> sizes = (List<Size>) request.getAttribute("sizes");
        
        %>
        <form action="InventoryServlet" method ="post">
            <label>Product ID: <input type="text" name="productId" ></label><br>
            <label>Size</label><br>
            <input list="sizes" name="sizeName">
            <datalist id="sizes">

                <%for (Size size : sizes) {%>
                <option value="<%=size.Id%>"><%=size.getSize()%></option>  
                <%}%> 

            </datalist>
            <input type="submit" name="submit" value="addinventory" >
        </form>
    </body>
</html>
