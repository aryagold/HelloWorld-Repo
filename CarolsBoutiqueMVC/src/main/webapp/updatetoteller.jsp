<%-- 
    Document   : updatetoteller
    Created on : 20 Jun 2022, 17:20:32
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
        <style>
           body {
             background-image: url('istockphoto-184640309-612x612.jpg');
             background-repeat: no-repeat;
             background-attachment: fixed;
             background-size: cover;
           }
        </style>
        <title>Register Teller</title>
    </head>
    <body>
        <div>

            <div class="row">
                <div class="col-sm">
                    
                </div>

                <div class="col-sm">

                    <form action="UserServlet" method="post">
                        <div class="d-flex justify-content-center"><h2>Register Teller</h2></div>
                        <label>Employee ID:</label>
                        <input type="text" class="checkboxstyle" name="userId"/>
                        <br>
                        <div class="d-flex justify-content-center"><button id="submit" name="submit" value="updateTeller">Register</button></div>
                        <img src="Caol_s_Botique-removebg-preview.png"/> 
                    </form>






                </div>
                <div class="col-sm">
                    

                </div>
            </div>
        </div>

        <script src= "name"></script>

    </body>
</html>
