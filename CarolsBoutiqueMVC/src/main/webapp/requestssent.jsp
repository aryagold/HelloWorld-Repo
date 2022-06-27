<%-- 
    Document   : newrequests.jsp
    Created on : 25 Jun 2022, 14:27:20
    Author     : aryagoldridge
--%>

<%@page import="za.co.vzap.Model.Sale.IbtDto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css" />
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" 
              rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Cedarville+Cursive&display=swap" rel="stylesheet"> 
        <title>Request Status</title>
    </head>
    <body style="background-color:#f5f5dc;">
        <h1>Request Status</h1>
        <%List<IbtDto> ibts = (List<IbtDto>) request.getAttribute("ibts");%>
        
        
        <form action="POSServlet" method="post">
            <%for (IbtDto ibt : ibts) {%>
            <label><%=ibt.branchNameFrom%>  <%=ibt.productName%> <%=ibt.sizeName%>  <%=ibt.statusName%></label><label>Received<input type="checkbox" name="ibtId" value="<%=ibt.Id%>" class="checkboxstyle">
            <%}%>  
            <br>
            <br>
            <br>
            <div class="col-sm" id="homebtn">
                <button id="home" type="submit" name="submit" value="updateIbt">Confirm</button>
            </div>
        </form>
    </body>
</html>