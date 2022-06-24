<%-- 
    Document   : responsepage
    Created on : 23 Jun 2022, 10:32:22
    Author     : aryagoldridge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <% String responseTo = (String) request.getAttribute("response"); %>
        
        <h1><%=responseTo%></h1>
        
        <a href="home.jsp"><li><button>Home</button></li></a>
    </body>
</html>
