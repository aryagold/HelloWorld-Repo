<%-- 
    Document   : makesale
    Created on : 30 Jun 2022, 16:30:19
    Author     : aryagoldridge
--%>

<%@page import="za.co.vzap.Model.Sale.SaleLineItemDto"%>
<%@page import="za.co.vzap.Model.Sale.SaleDto"%>
<%@page import="za.co.vzap.Model.Inventory.InventoryDto"%>
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
        <%SaleDto saleDto = (SaleDto) request.getSession().getAttribute("sale");
//        if(saleDto == null) saleDto = new SaleDto();
//        request.getSession().setAttribute("sale", saleDto);
        %>
        
    <body>
        
        <h1>Sale</h1> 
        <form action="InventoryServlet" method="get">
        
        <div class="col-sm">    
            <div id="qr-reader" style="width: 600px"></div>
            <label>Barcode: </label>
            <input type="text" id="barcode" name="barcode">
            
            <button type="submit" name="submit" value="addItemSale" id="home">Add to Sale</button>
        </div>
            
        <div class="col-sm">
         
        <%if(saleDto != null) {   
            for(SaleLineItemDto itemDto : saleDto.lineitems) {%>
            <label><%=itemDto.productName%> &nbsp; <%=itemDto.sizeName%> &nbsp; <%=itemDto.price%></label><br>
            <%}%>
            <div>
                <label>Total: <%=saleDto.getTotal()%></label>
            </div>
        <%}%>
        
        
        </div> 
        </form>
            
        <form action="POSServlet" method="post">
        <div class="col-sm" id="homebtn">
            <button name="submit" type="submit" value="continueSale" id="home">Continue</button>
            <button name="submit" type="submit" value="cancelSale" id="home">Cancel</button>
        </div>
        </form>  
        
        
        
        
        <script src= "webcam.js"></script>
    </body>
</html>
