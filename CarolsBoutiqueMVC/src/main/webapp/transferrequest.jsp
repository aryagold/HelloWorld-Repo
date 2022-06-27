<%-- 
    Document   : transferrequest
    Created on : 25 Jun 2022, 19:44:40
    Author     : aryagoldridge
--%>

<%@page import="java.util.List"%>
<%@page import="za.co.vzap.Model.Inventory.InventoryDto"%>
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
        <title>Transfer Request</title>
    </head>
    
        <%List<InventoryDto> foundItems = (List<InventoryDto>) request.getAttribute("items");%>
       
        <body style="background-color:#f5f5dc;">
        <h1>Request Transfer</h1>
        <form action="POSServlet" method="post">
        <div class="col-sm">

            <% if (foundItems != null) {%>
            <%for (InventoryDto item : foundItems) {%>
            <input type="checkbox" name="inventoryIdFrom" value="<%=item.Id%>"><%=item.productName%>    <%=item.sizeName%>    <%=item.branchName%></input><br>
            <%}%>
            <%}%>

            <label>Customer Email:</label>
            <input type="text" class="checkboxstyle" name="customerEmail"/>
            <br>

        </div>
        <div class="col-sm">
            <label>Customer Phone Number:</label>
            <input type="text" class="checkboxstyle" name="phoneNumber"/>
            
            <div class="d-flex justify-content-center"><button class="add" id="submit" name="submit" type="submit" value="addIbt">Send Request</button></div>
            <br>
        </form>
        <img id="logo" src="Caol_s_Botique-removebg-preview.png" alt="Logo">
        </div>
    </body>
</html>
