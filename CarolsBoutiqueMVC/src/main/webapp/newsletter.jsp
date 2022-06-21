<%-- 
    Document   : newsletter
    Created on : 20 Jun 2022, 11:44:31
    Author     : aryagoldridge
--%>

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
        <title>Newsletter</title>
    </head>
    <body>
        <h1>Join Our Newsletter!</h1>
        
        <br>
        <form action="CustomerServlet" method="post">
            <div class="d-flex justify-content-center"><label>E-mail:</label></div>
            <div class="d-flex justify-content-center"><input type="text" name="customerEmail"/></div>
            <div class="d-flex justify-content-center"><label>Phone Number:</label></div>
            <div class="d-flex justify-content-center"><input type="text" name="phoneNumber"/></div>
            <br>
            <div class="d-flex justify-content-center"><button id="submit" name="submit" value="addCustomer">Join</button></div>
            <div class="d-flex justify-content-center"><img src="Caol_s_Botique-removebg-preview.png"/></div>
        </form>
    </body>
</html>
