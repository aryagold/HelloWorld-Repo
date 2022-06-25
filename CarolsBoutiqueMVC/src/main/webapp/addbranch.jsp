<%-- 
    Document   : addbranch
    Created on : 20 Jun 2022, 17:26:01
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
        <title>Add Store</title>
    </head>
    <body>
        
        <div>
            
            <div class="row">
                <div class="col-sm">
                    
                </div>

                <div class="col-sm">
                
                    <form action="UserServlet" method="post">    
                        <div class="d-flex justify-content-center"><h2>Add Store</h2></div>
                        <label>Store Name:</label>
                        <input type="text" class="checkboxstyle" name="branchName"/>
                        <br>
                        <label>Input Monthly Target:</label>
                        <input type="number" class="checkboxstyle" name="monthlyTarget"/>
                        <br>
                        <label>Input Daily Target:</label>
                        <input type="number" class="checkboxstyle" name="dailyTarget"/>
                        <br>
                        <div class="d-flex justify-content-center"><button class="barcodegen" id="submit" name="submit" value="addBranch">Add</button></div>
                        <img src="Caol_s_Botique-removebg-preview.png"/>
                    </form>
                </div>
                
            </div>
        </div>

        <script src= "name"></script>

    </body>
</html>
