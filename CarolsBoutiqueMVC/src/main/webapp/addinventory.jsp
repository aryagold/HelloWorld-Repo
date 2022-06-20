<%@page import="za.co.vzap.Model.Inventory.Size"%>
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
        <title>Add Inventory</title>
    </head>
    <body>
        <div>
            <header><h1>Parktown</h1></header>

            <div class="row">
                <div class="col-sm">
                    <ul>
                        <a href="/aryatest/Management.html"><li><button>Management</button></li></a>
                        <a href="/aryatest/stock.html"><li><button>Stock</button></li></a>
                        <li><button>Point of sale till</button></li>
                        <li><button>transfers</button></li>

                    </ul>
                </div>

                <div class="col-sm">
                    <form action="InventoryServlet" method="post">
                        <div class="d-flex justify-content-center">
                            <label>Product ID:</label>
                            <input type="text" id="productID" name="productId"/>

                        </div>
                        <br>
                        <div class="d-flex justify-content-center">
                            <label id="options">Size:</label>
                            <select id="size" onchange="update()") name="size">
                                <option value="1">XS</option>
                                <option value="2">S</option>
                                <option value="3">M</option>
                                <option value="4">L</option>
                                <option value="5">XL</option>
                                <option value="6">3</option>
                                <option value="7">4</option>
                                <option value="8">5</option>
                                <option value="9">6</option>
                                <option value="10">7</option>
                                <option value="11">8</option>
                                <option value="12">9</option>
                                <option value="13">10</option>
                            </select></div>  
                        <br>

                        <div class="d-flex justify-content-center">
                            <label>Generated Bar Code:</label>
                            <input type="text" id="result" name="barcode"/>
                        </div>
                        <br>
                        <div class="d-flex justify-content-center"><button type="submit" name="submit" value="addInventory" class="barcodegen">Add Inventory</button> </div>
                    </form>








                </div>
                <div class="col-sm">
                    <ul>
                        <li><button>Reports</button></li>
                        <a href="/aryatest/search.html"><li><button>Search</button></li></a>
                        <a href="/aryatest/addToCatalogue.html"><li><button>Add to catalogue</button></li></a> 
                        <li><button>Logout </button></li>
                    </ul>

                </div>
            </div>
        </div>

        <script src= "padding.js"></script>

    </body>
</html>
