<%-- 
    Document   : completerefund
    Created on : 30 Jun 2022, 23:57:03
    Author     : aryagoldridge
--%>

<%@page import="za.co.vzap.Model.Sale.RefundItemDto"%>
<%@page import="za.co.vzap.Model.Sale.RefundDto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
        <title>Sale</title>
    </head>

    <%RefundDto dto = (RefundDto) request.getSession().getAttribute("refund");%>
    <body>
        <h1>Complete Sale or Reserve</h1>

        <label>Items: </label></div><br>
        <%for (RefundItemDto item : dto.refundItems) {%>
    <label><%=item.productName%> &nbsp; <%=item.sizeName%> &nbsp; <%=item.price%></label></div><br>

<%}%>
<label>Total :<%=dto.getTotal()%></label>

<form action="POSServlet" method="post">
    
    <div class="d-flex justify-content-center"><label>Enter Sale ID:</label></div><br>
    <div class="d-flex justify-content-center"><input type="text" name="saleId"></div><br>

    <div class="d-flex justify-content-center"><button name="submit" type="submit" value="addRefund" id="home">Complete</button></div>
</form>
</body>
</html>
