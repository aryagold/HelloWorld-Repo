<%@page import="za.co.vzap.Model.User.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stock Home</title>
    </head>
    <body style="background-color:#f5f5dc;">

        <% User user = (User) request.getSession(false).getAttribute("user"); %>

        <h1>Select a Stock Control Option</h1>
        <a href="captureinventory.jsp">Capture Inventory</a><br>
        <a href="addinventory.jsp">Add new Inventory</a>
         
    </body>
</html>
