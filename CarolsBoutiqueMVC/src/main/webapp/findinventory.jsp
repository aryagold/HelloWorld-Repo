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
        <title>Search</title>
    </head>
    <body>
        <div>
            <header><h1>Parktown</h1></header>
            <form action="InventoryServlet" method="get">
            <div class="d-flex justify-content-center"><h2>Search</h2></div>
            <div class="row">
                <div class="col-sm">
                    <div id="qr-reader" style="width: 600px"></div>
                </div>

                <div class="col-sm">
                    <label class="checkboxstyle">Search By Barcode:</label>
                    <input type="text" id="barcode" name="barcode"/>
                    <br>
                    <h3>Or</h3>
                    <label class="checkboxstyle">Search By Product ID:</label>
                    <input type="text" name="productId">









                </div>
                <div class="col-sm">
                    <button class="stock" name="submit" value="findInventory">Search</button>
                    <a href="home.html"><button class="stock">Home</button></a>

                </div>
            </div>
        </div>

        <script src= "webcam.js"></script>

    </body>
</html>
