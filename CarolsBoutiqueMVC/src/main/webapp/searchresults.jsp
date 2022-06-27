<%-- 
    Document   : searchresults
    Created on : 22 Jun 2022, 20:51:41
    Author     : aryagoldridge
--%>

<%@page import="za.co.vzap.Model.Inventory.InventoryDto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <script src="https://unpkg.com/html5-qrcode@2.0.9/dist/html5-qrcode.min.js"></script>
        <title>Results</title>
    </head>
    <body>
        <h1>Search Results</h1>
        
        <% List<InventoryDto> items = (List<InventoryDto>) request.getAttribute("items");%>

        <table>
            <tr>
                <th>Product</th>
                <th>Size</th>
                <th>Price</th>
                <th>Branch</th>
                <th>Quantity</th>
            </tr>
            
            
                <%for(InventoryDto item1 : items) {%>
                <tr><td class=""><%=item1.productName%></td><td><%=item1.sizeName%></td><td><%=item1.price%></td><td><%=item1.branchName%></td><td><%=item1.quantity%></td></tr>

                <%}%>
              
               
            
        </table>

    </body>
</html>
