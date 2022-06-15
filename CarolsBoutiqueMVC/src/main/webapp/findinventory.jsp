<%@page import="za.co.vzap.Model.Inventory.InventoryDto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Stock</title>
    </head>
    <body style="background-color:#f5f5dc;">
        <% List<InventoryDto> items = (List<InventoryDto>) request.getAttribute("items");
        
        %>
        <h1>Search Stores</h1>
        <form action="InventoryServlet" method ="get">
            <label>Bar code: <input type="text" name="barcode" ></label>
            <label>OR</label>
            <label>Password: <input type="text" name="productId" ></label>
            <input type="submit" name="submit" value="findProduct">
        </form> 
        
        <%if(items != null) { %>
            <div id = "categories"> 
            <label>Select Categories</label>
            <ul>
                        
              <% for(InventoryDto dto : items) {%>
                    <li><input type="checkbox"><%=dto.productName%> <%=dto.sizeName%> <%=dto.branchName%> </li>
                <%}%>

            </ul>
            </div>
           }%>
    </body>
</html>
