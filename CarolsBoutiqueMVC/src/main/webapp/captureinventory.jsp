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
        <title>Replenish</title>
    </head>
    <body>
        <div>
            
            <div class="row">
                <div class="col-sm">
                    
                </div>

                <form action="InventoryServlet" method="post">
                <div class="col-sm">
                    <label>Replenish Stock</label>
                    <div id="qr-reader" style="width: 600px"></div>
                    <label>Barcode: </label>
                    <input type="text" id="barcode" name="barcode">
                    <br>
                    <label>Quantity: </label>
                    <select id="quantity "name="quantity">
                        <option name="quantity" value="1">1</option>
                        <option name="quantity" value="2">2</option>
                        <option name="quantity" value="3">3</option>
                        <option name="quantity" value="4">4</option>
                        <option name="quantity" value="5">5</option>
                        <option name="quantity" value="6">6</option>
                        <option name="quantity" value="7">7</option>
                        <option name="quantity" value="8">8</option>
                        <option name="quantity" value="9">9</option>
                        <option name="quantity" value="10">10</option>
                    </select>
                    <br>
                    <div class="d-flex justify-content-center"><button class="add" type="submit" name="submit" value="captureInventory">Add</button></div>

                </form>




                </div>
                <div class="col-sm">
                    

                </div>
            </div>
        </div>

        <script src= "webcam.js"></script>

    </body>
</html>
