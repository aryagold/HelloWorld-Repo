<%-- 
    Document   : responsepage
    Created on : 23 Jun 2022, 10:32:22
    Author     : aryagoldridge
--%>

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
        <style>
            body {
              background-image: url('Caol_s_Botique-removebg-preview.png');

              background-attachment: fixed;

            }
        </style>
        <title></title>
    </head>
    <body>
        
        <%String responseTo = (String) request.getAttribute("response");%>

        <div class="container">
            <div class="row">
                <div class="col-sm">

                </div>
                <div class="col-sm">
                    <br>
                    <div><h2 id="response"><%=responseTo%></h2></div>
                    <ul id="inventoryButtons">
                        <li><a href="addinventory.jsp"><button class="inventory">Add Inventory</button></a></li>
                        <li><a href="captureinventory.jsp"><button class="inventory">Capture Stock</button></a></li>
                    </ul>

                </div>
                <div class="col-sm" id="homebtn">
                    <div><a href="home.jsp"><button id="home">Home</button></a></div>
                </div>
            </div>
        </div>

    </body>
</html>
