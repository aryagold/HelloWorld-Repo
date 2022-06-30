<%-- 
    Document   : makerefund
    Created on : 30 Jun 2022, 23:38:25
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
    
    <%RefundDto refundDto = (RefundDto) request.getSession().getAttribute("refund");%>
    
    <body>
        <h1>Refund</h1>
        <form action="InventoryServlet" method="get">

            <div class="col-sm">    
                <div id="qr-reader" style="width: 600px"></div>
                <label>Barcode: </label>
                <input type="text" id="barcode" name="barcode">

                <button type="submit" name="submit" value="addItemRefund" id="home">Add to Refund</button>
            </div>

            <div class="col-sm">

                <%if (refundDto != null) {
                for (RefundItemDto itemDto : refundDto.refundItems) {%>
                <label><%=itemDto.productName%> &nbsp; <%=itemDto.sizeName%> &nbsp; <%=itemDto.price%></label><br>
                <%}%>
                <div>
                    <label>Total: <%=refundDto.getTotal()%></label>
                </div>
                <%}%>


            </div> 
        </form>

        <form action="POSServlet" method="post">
            <div class="col-sm" id="homebtn">
                <button name="submit" type="submit" value="continueRefund" id="home">Continue</button>
                <button name="submit" type="submit" value="cancelRefund" id="home">Cancel</button>
            </div>
        </form>  




        <script src= "webcam.js"></script>
    </body>
</html>
