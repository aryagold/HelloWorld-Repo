
<%@page import="za.co.vzap.Model.User.User"%>
<%@page import="za.co.vzap.Model.Inventory.Category"%>
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
        <title>Add to Catalogue</title>
    </head>
    <body>
        <div>

            <div class="row">
                <div class="col-sm">
                    <ul>
                        <a href="management.jsp"><li><button>Management</button></li></a>
                        <a href="stockhome.jsp"><li><button>Stock</button></li></a>
                        <a href="/aryatest/stock.html"><li><button>Point of sale till</button></li>
                        <a href="transferhome.jsp"><li><button>Transfers</button></li>

                    </ul>
                </div>

                <div class="col-sm">


                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <form action="ProductServlet" method="post">
                                <div class="d-flex justify-content-center"><label class="labels">Full product name:</label><br></div>

                                <input type="text" class="price" name="name"><br>


                                <div class="d-flex justify-content-center" >
                                    <label class="labels">Price:</label><br>
                                </div>  

                                <input type="number" class="price" name="price">
                            </div>

                        </div>
                        <div class="d-flex justify-content-center" ><label class="labels">Categories:</label></div>


                        <div class="row" id="products">
                            <div class="col">
                                <div class="col"></div>

                                <label>Women's Shoes</label>       
                                <input type="checkbox"  name="categoryId" value="CA006" class="checkboxstyle">
                                <label>Women's Accessories</label>
                                <input type="checkbox" name="categoryId" value="CA004" class="checkboxstyle">
                                <label>Women's Athleisure Tops</label>
                                <input type="checkbox" name="categoryId" value="CA002" class="checkboxstyle">
                                <label>Women's Athleisure Bottoms</label>
                                <input type="checkbox" name="categoryId" value="CA003" class="checkboxstyle">
                                <label>Women's Casual Bottoms</label>
                                <input type="checkbox" name="categoryId" value="CA009" class="checkboxstyle">
                                <label>Women's Casual Tops</label>
                                <input type="checkbox" name="categoryId" value="CA005" class="checkboxstyle">

                            </div>
                            <div class="col">
                                <label>Men's Athleisure Bottoms</label>
                                <input type="checkbox" name="categoryId" value="CA011" class="checkboxstyle">
                                <label>Men's Athleisure Tops</label>
                                <input type="checkbox" name="categoryId" value="CA010" class="checkboxstyle" >
                                <label>Men's Casual Tops</label>
                                <input type="checkbox" name="categoryId" value="CA012" class="checkboxstyle" >
                                <label>Men's Casual Bottoms</label>
                                <input type="checkbox" name="categoryId" value="CA013" class="checkboxstyle" >
                                <label>Men's Accessories</label>
                                <input type="checkbox" name="categoryId" value="CA014" class="checkboxstyle" >
                                <label>Men's Shoes</label>
                                <input type="checkbox" name="categoryId" value="CA015" class="checkboxstyle" >

                            </div>

                        </div>
                        <div class="d-flex justify-content-center" ><button class="interaction" name="submit" value="addProduct">Add</button></div>
                    </div>
                    <img src="Caol_s_Botique-removebg-preview.png"/>
</form>

                </div>
                <div class="col-sm">
                    <ul>
                        <a href="reporthome.jsp"><li><button>Reports</button></li>
                        <a href="findinventory,jsp"><li><button>Search</button></li></a>
                        <a href="addtocatalogue.jsp"><li><button>Add to catalogue</button></li></a> 
                        <a href="index.jsp"><li><button>Logout </button></li>
                    </ul>

                </div>
            </div>
        </div>

        <script src= "name"></script>

    </body>
</html>
