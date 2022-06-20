<%-- 
    Document   : review
    Created on : 20 Jun 2022, 11:48:41
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
        <title>Reviews</title>
    </head>
    <body>
        <h1>Review Carol's Boutique</h1>
        <form action="CustomerServlet" method="post">
        <div class="d-flex justify-content-center"> <label>Select Rating:</label></div>
        <div class="d-flex justify-content-center"><select name="rating">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
            </select></div>
        <br>
        <div class="d-flex justify-content-center"><label>Add A Comment:</label></div>
        <div class="d-flex justify-content-center"><textarea name="comment" rows="4" cols="40"></textarea></div>
        <div class="d-flex justify-content-center"><label>Select Branch:</label></div>
        <div class="d-flex justify-content-center">
            <select name="branchId">
                <option value="BR001">Parktown</option>
                <option value="2">Sandton</option>
                <option value="3">Pietermaritzburg</option>
                <option value="4">Durban</option>
                <option value="5">Alberton</option>
                <option value="6">Bedfordview</option>
                <option value="7">Midrand</option>
                <option value="8">Upington</option>
                <option value="9">Magaliesburg</option>
                <option value="10">Benoni</option>
            </select>
        </div>
        <br>
        <div class="d-flex justify-content-center"><a href="/aryatest/newsletter.html"><button id="submit" name="submit" value="addReview">Submit</button></a></div>
    </form>
        <div class="d-flex justify-content-center"><img src="Caol_s_Botique-removebg-preview.png"/></div>

    </body>
</html>
