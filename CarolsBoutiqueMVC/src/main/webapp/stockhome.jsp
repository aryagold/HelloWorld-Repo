<%@page import="za.co.vzap.Model.User.User"%>
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
        <title>Stock Taking</title>
    </head>
    <body>
        <div>
            <header><h1>Parktown</h1></header>

            <div class="row">
                <div class="col-sm">
                    <ul>
                        <a href="management.jsp"><li><button>Management</button></li></a>
                        <a href="stockhome.jsp"><li><button>Stock</button></li></a>
                        <li><button>Point of sale till</button></li>
                        <a href="transferhome.jsp"><li><button>transfers</button></li>

                    </ul>
                </div>

                <div class="col-sm">
                    <div class="d-flex justify-content-center">
                        <a href="captureinventory.jsp"><button class="stock">Replenish</button></a>
                        <a href="addinventory.jsp"><button class="stock">New Inventory</button></a>
                    </div>




                    <img id="logo" src="Caol_s_Botique-removebg-preview.png" alt="Logo">



                </div>
                <div class="col-sm">
                    <ul>
                        <a href="reporthome.jsp"><li><button>Reports</button></li>
                        <a href="findinventory.jsp"><li><button>Search</button></li></a>
                        <a href="addtocatalogue.jsp"><li><button>Add to catalogue</button></li></a> 
                        <a href="index.jsp"><li><button>Logout </button></li>
                    </ul>

                </div>
            </div>
        </div>

        <script src= "name"></script>

    </body>
</html>