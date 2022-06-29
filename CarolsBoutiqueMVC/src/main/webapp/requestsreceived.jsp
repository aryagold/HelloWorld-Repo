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
        <title>New Requests</title>
    </head>
    <body style="background-color:#f5f5dc;">
        <h1>New Requests</h1>
        <%List<IbtDto> ibts = (List<IbtDto>) request.getAttribute("ibts");%>
        <% request.setAttribute("ibts", ibts);%>
        
        <div class="col-sm">
        <%for(IbtDto ibt : ibts) {%>
        <form action="POSServlet" method="post">
            <label> <%=ibt.branchNameTo%> &nbsp;&nbsp; <%=ibt.productName%> &nbsp;&nbsp; Size: <%=ibt.sizeName%> &nbsp;&nbsp;</label>

        <input type="hidden" name="ibtId" value="<%=ibt.Id%>">
        <button id="home" type="submit" name="submit" value="approveIbt">Accept</button>
        <button id="home" type="submit" name="submit" value="declineIbt">Decline</button>
        </form>
        <%}%>    
        
        <img id="logo" src="Caol_s_Botique-removebg-preview.png" alt="Logo">
        </div>
        
        <div class="col-sm" id="homebtn">
            <div><a href="home.jsp"><button id="home">Home</button></a></div>
        </div>
    </body>
</html>
