<%@page import="za.co.vzap.Model.Inventory.InventoryDto"%>
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
        <title>Transfer Request</title>
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
                    <form action="POSServlet" method="post">
                        <div class="d-flex justify-content-center"><h2>Request Inter Branch Transfer for Item/s</h2></div>
                        <label>Customer Phone Number:</label>
                        <input type="text" class="checkboxstyle" name="phoneNumber"/>
                        <br>
                        <label>Customer Email:</label>
                        <input type="text" class="checkboxstyle" name="customerEmail"/>
                        <br>

                        <div class="d-flex justify-content-center"><button class="barcodegen" name="submit" value="addIbt">Send Request</button></div>
                        <img src="Caol_s_Botique-removebg-preview.png"/>
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

        <script src= "name"></script>

    </body>
</html>
