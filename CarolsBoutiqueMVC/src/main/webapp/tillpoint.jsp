<%@page import="java.time.LocalDateTime"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Index</title>

        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">

    </head>

    <body>


        <div class="bg-dark p-3">
            <div class="row mx-0 py-3" style="background-color: #fd9100;"> 
                <!-- LEFT PANEL   -->
                <div class="col-sm-8">
                    <p>Order #88 <small class="text-muted"><%=LocalDateTime.now()%></small></p>

                    <!-- Tabs -->
                    <div class="card mb-3">
                        <div class="card-body">
                            <ul class="nav nav-pills" id="pills-tab" role="tablist">
                                <li class="nav-item" role="presentation">
                                    <button class="nav-link active rounded-pill" id="pills-scan-tab" data-bs-toggle="pill" data-bs-target="#pills-scan" type="button" role="tab" aria-controls="pills-scan" aria-selected="true">Scan</button>
                                </li>

                                <li class="nav-item" role="presentation">
                                    <button class="nav-link rounded-pill" id="pills-items-tab" data-bs-toggle="pill" data-bs-target="#pills-items" type="button" role="tab" aria-controls="pills-items" aria-selected="false">Items</button>
                                </li>

                                <li class="nav-item" role="presentation">
                                    <button class="nav-link btn-danger rounded-pill" id="pills-exit-tab" data-bs-toggle="pill" data-bs-target="#pills-exit" type="button" role="tab" aria-controls="pills-exit" aria-selected="false">Exit</button>
                                </li>                    

                                <li class="nav-item d-none" role="presentation">
                                    <button class="nav-link rounded-pill" id="pills-checkout-tab" data-bs-toggle="pill" data-bs-target="#pills-checkout" type="button" role="tab" aria-controls="pills-checkout" aria-selected="false">Checkout</button>
                                </li>

                            </ul>
                        </div>
                    </div>

                    <!-- Tab Contents -->
                    <div class="tab-content" id="pills-tabContent">

                        <!-- Scanning Panel -->
                        <div class="tab-pane fade show active" id="pills-scan" role="tabpanel" aria-labelledby="pills-scan-tab" tabindex="0" >

                            <!-- Start Here  -->

                            <script src="/html5-qrcode.min.js"></script>
                            <style>
                                .result{
                                    background-color: green;
                                    color:#fff;
                                    padding:10px;

                                }
                                .row{
                                    display:flex;
                                }
                                #reader__scan_region{
                                    visibility: hidden;
                                }
                            </style>


                            <div class="row">
                                <div class="col">
                                    <div style="width:500px;" id="reader"></div>              

                                    <div class="col" style="padding:30px;">
                                        <div><h4>SCAN RESULT</h4></div>
                                        <div id="result" onclick="scanToCart(document.getElementById('result').innerHTML, 10.00);">Result Here</div>
                                    </div>
                                </div>

                            </div>


                            <script type="text/javascript">
                                function onScanSuccess(qrCodeMessage) {
                                    document.getElementById('result').innerHTML = qrCodeMessage;
                                }

                                function onScanError(errorMessage) {
                                    //handle scan error
                                }

                                var html5QrcodeScanner = new Html5QrcodeScanner(
                                        "reader", {fps: 10, qrbox: 250});
                                html5QrcodeScanner.render(onScanSuccess, onScanError);

                                const cam = document.getElementById("reader__scan_region");

                            </script>


                            <!-- End Here -->

                        </div>

                        <!-- Items Panel -->
                        <div class="tab-pane fade" id="pills-items" role="tabpanel" aria-labelledby="pills-items-tab" tabindex="0">

                            <div class="row row-cols-1 row-cols-md-5 g-4">
                                <div class="col">
                                    <div class="card" onclick="orderbasket('Black Cotton Crop', 700.0);" style="max-height: 310px; max-width: 180px;">
                                        <img src="Caol_s_Botique-removebg-preview.jpg" class="card-img-top" alt="...">
                                        <div class="card-body">
                                            <h5 class="card-title">Black Cotton Crop</h5>
                                            <p style="font-weight: bold;">R 700.0 </p>
                                            <p></p>
                                        </div>
                                    </div>
                                </div>

                                <div class="col">
                                    <div class="card" onclick="orderbasket('Black Ultra Flex Jogger', 900.0);" style="max-height: 310px; max-width: 180px;">
                                        <img src="Caol_s_Botique-removebg-preview.jpg" class="card-img-top" alt="...">
                                        <div class="card-body">
                                            <h5 class="card-title">Black Ultra Flex Jogger</h5>
                                            <p style="font-weight: bold;">R 900.0 </p>
                                        </div>
                                    </div>
                                </div>

                                <div class="col">
                                    <div class="card" onclick="orderbasket('White Ultra Flex Jogger', 900.0);" style="max-height: 310px; max-width: 180px;">
                                        <img src="Caol_s_Botique-removebg-preview.jpg" class="card-img-top" alt="...">
                                        <div class="card-body">
                                            <h5 class="card-title">White Ultra Flex Jogger</h5>
                                            <p style="font-weight: bold;">R 900.0 </p>

                                        </div>
                                    </div>
                                </div>
                                <div class="col">

                                    <div class="card" onclick="orderbasket('Canvas Puffer Coat', 1000.0);" style="max-height: 310px; max-width: 180px;">
                                        <img src="Caol_s_Botique-removebg-preview.jpg" class="card-img-top" alt="...">
                                        <div class="card-body">
                                            <h5 class="card-title">Canvas Puffer Coat</h5>
                                            <p style="font-weight: bold;">R 1000.0 </p>
                                        </div>
                                    </div>
                                </div>

                                <div class="col">
                                    <div class="card" onclick="orderbasket('Blue Canvas Active Jacket', 1000.0);" style="max-height: 310px; max-width: 180px;">
                                        <img src="Caol_s_Botique-removebg-preview.jpg" class="card-img-top" alt="...">
                                        <div class="card-body">
                                            <h5 class="card-title">Blue Canvas Active Jacket</h5>
                                            <p style="font-weight: bold;">R 1000.0</p>
                                        </div>
                                    </div>
                                </div>

                                <div class="col">
                                    <div class="card" onclick="orderbasket('High Waisted Leather Trouser', 1000.0);" style="max-height: 310px; max-width: 180px;">
                                        <img src="Caol_s_Botique-removebg-preview.jpg" class="card-img-top" alt="...">
                                        <div class="card-body">
                                            <h5 class="card-title">High Waisted Leather Trouser</h5>
                                            <p style="font-weight: bold;">R 1000.0 </p>
                                        </div>
                                    </div>
                                </div>

                                <div class="col">
                                    <div class="card" onclick="orderbasket('Black High Waisted Tapered Yoga Pants', 1000.0);" style="max-height: 310px; max-width: 180px;">
                                        <img src="Caol_s_Botique-removebg-preview.jpg" class="card-img-top" alt="...">
                                        <div class="card-body">
                                            <h5 class="card-title">Black High Waisted Tapered Yoga Pants</h5>
                                            <p style="font-weight: bold;">R 1000.0 </p>
                                        </div>
                                    </div>
                                </div>                        

                                <div class="col">
                                    <div class="card" onclick="orderbasket('White Canvas Active Jacket', 1000.0);" style="max-height: 310px; max-width: 180px;">
                                        <img src="Caol_s_Botique-removebg-preview.jpg" class="card-img-top" alt="...">
                                        <div class="card-body">
                                            <h5 class="card-title">White Canvas Active Jacket</h5>
                                            <p style="font-weight: bold;">R 1000.0 </p>
                                        </div>
                                    </div>
                                </div>                        

                                <div class="col">
                                    <div class="card" onclick="orderbasket('Black Leather Jacket', 1000.0);" style="max-height: 310px; max-width: 180px;">
                                        <img src="Caol_s_Botique-removebg-preview.jpg" class="card-img-top" alt="...">
                                        <div class="card-body">
                                            <h5 class="card-title">Black Leather Jacket</h5>
                                            <p style="font-weight: bold;">R 1000.0 </p>
                                        </div>
                                    </div>
                                </div>

                                <div class="col">
                                    <div class="card" onclick="orderbasket('Black Leather Jacket', 1000.0);" style="max-height: 310px; max-width: 180px;">
                                        <img src="Caol_s_Botique-removebg-preview.jpg" class="card-img-top" alt="...">
                                        <div class="card-body">
                                            <h5 class="card-title">Black Leather Jacket</h5>
                                            <p style="font-weight: bold;">R 1000.0 </p>
                                        </div>
                                    </div>
                                </div>





                            </div>

                        </div>

                        <!-- EXIT -->
                        <div class="tab-pane fade" id="pills-exit" role="tabpanel" aria-labelledby="pills-exit-tab" tabindex="0">

                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                                Exit
                            </button>

                            <!-- Modal -->
                            <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="staticBackdropLabel"></h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <h4>Would you like to exit?</h4>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                            <button type="button" class="btn btn-danger" onclick="window.location.href = 'home.jsp'">Exit</button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <!-- Checkout -->
                        <div class="tab-pane fade" id="pills-checkout" role="tabpanel" aria-labelledby="pills-checkout-tab" tabindex="0">
                            <h4>Capture customer details:</h4>
                            <hr>
                            <form>

                                <div class="mb-3">
                                    <label for="exampleInputPassword1" class="form-label">Full name</label>
                                    <input type="text" class="form-control" id="customerName">
                                </div>
                                
                                <div class="mb-3">
                                    <label for="exampleInputCard" class="form-label">Card Number</label>
                                    <input type="text" class="form-control" id="customerCard">
                                </div>

                                <div class="mb-3">
                                    <label for="exampleInputEmail1" class="form-label">Email address</label>
                                    <input type="email" class="form-control" id="customerEmail" aria-describedby="emailHelp">
                                    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                                </div>


                            </form>

                            <button name="submit" type="submit" value="findInventory" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#paymentStatus" onclick="paymentMethod();">
                                Pay
                            </button>
                            <!-- Button trigger modal -->

                            <!-- Modal -->
                            <div class="modal fade" id="paymentStatus" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="staticBackdropLabel">Payment Status</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            Payment Successful.
                                            <!-- <ul class="list-unstyled" id="paymentStatus"> </ul> -->
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Done</button>

                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>


                    </div>
                </div>

                <!-- RIGHT PANEL -->
                <div class="col-sm-4 pt-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="d-flex justify-content-between align-items-center">
                                <span>Cart</span>
                                <button onclick="orderbasketClear()" class="btn btn-sm btn-danger rounded-pill">Clear</button>
                            </h5>
                            <!-- CART -->
                            <hr>
                            <ul id="cart" class="list-unstyled" style="height: 50vh; overflow-y: auto;">


                            </ul>
                            <!-- TOTALS -->
                            <hr>
                            <div class="d-flex justify-content-between align-items-center">
                                <span>Quantity: </span>
                                <big id="totalItems" class="card-text fw-bold">0</big>

                            </div>

                            <div class="d-flex justify-content-between align-items-center">
                                <span>Total Cost: </span>
                                <big class="fw-bold">R <span id="totalCost" class="card-text">0</span></big>

                            </div>

                            <div>
                                <hr>
                                <button class="btn btn-primary btn-lg w-100 rounded-pill" onclick="goToCheckoutTab();">
                                    CHECKOUT
                                </button>


                            </div>

                        </div>
                    </div>
                </div>

            </div>


        </div>






        <script>
            const orderIdArray = [];
            const totalItemsArray = [];
            const totalPriceArray = [];
            const orderArray = [];
            let i = 0;

            const getProducts_url = 'http://localhost:8080/CarolsBoutiqueRest/rest/product/product';



            function scanToCart(itemName, itemPrice) {
                orderIdArray.push(i);
                totalItemsArray.push(itemName);
                totalPriceArray.push(itemPrice);
                orderArray.push(itemName, itemPrice);



                let orderList = document.getElementById("cart");

                //Create the li tag
                const orderItem = document.createElement("li");
                orderItem.className = 'd-flex justify-content-between align-items-center';

                //create larger span to hold it all
                const largerSpan = document.createElement('span');

                //Create a span for red color
                const orderSpan = document.createElement('span');

                //Create the text node with item name and price
                const orderItemName = document.createTextNode(itemName);
                const orderItemPrice = document.createTextNode(' R ' + itemPrice);

                //Adjust text color to text-danger
                orderSpan.className = 'text-danger';

                //Add pricetextnode into span
                orderSpan.appendChild(orderItemPrice);

                //create delete button
                const deleteButton = document.createElement('button');
                const deleteButtonText = document.createTextNode('X');

                deleteButton.setAttribute('onclick', 'deleteItem(' + i + ', this)');

                //Appand the deleteButtonText to the deleteButton
                deleteButton.appendChild(deleteButtonText);
                deleteButton.className = 'btn btn-danger rounded-pill btn-sm';
                // deleteButton.style = 'height: 10px; width: 10px;';


                //Attach the text node to the li tag
                largerSpan.appendChild(orderItemName);
                largerSpan.appendChild(orderSpan);
                orderItem.appendChild(largerSpan);

                //Attach delete button into the li tag
                orderItem.appendChild(deleteButton);

                //Attach the li tag(child) to the orderList(parent)
                orderList.appendChild(orderItem);

                totalItemsQuantity();
                totalItemsPrice();

                i++;

                console.log(orderIdArray);
            }
            ;

            function orderbasket(itemName, itemPrice) {
                orderIdArray.push(i);
                totalItemsArray.push(itemName);
                totalPriceArray.push(itemPrice);
                orderArray.push(itemName, itemPrice);



                let orderList = document.getElementById("cart");

                //Create the li tag
                const orderItem = document.createElement("li");
                orderItem.className = 'd-flex justify-content-between align-items-center';

                //create larger span to hold it all
                const largerSpan = document.createElement('span');

                //Create a span for red color
                const orderSpan = document.createElement('span');

                //Create the text node with item name and price
                const orderItemName = document.createTextNode(itemName);
                const orderItemPrice = document.createTextNode(' R ' + itemPrice);

                //Adjust text color to text-danger
                orderSpan.className = 'text-danger';

                //Add pricetextnode into span
                orderSpan.appendChild(orderItemPrice);

                //create delete button
                const deleteButton = document.createElement('button');
                const deleteButtonText = document.createTextNode('X');

                deleteButton.setAttribute('onclick', 'deleteItem(' + i + ', this)');

                //Appand the deleteButtonText to the deleteButton
                deleteButton.appendChild(deleteButtonText);
                deleteButton.className = 'btn btn-danger rounded-pill btn-sm';
                // deleteButton.style = 'height: 10px; width: 10px;';


                //Attach the text node to the li tag
                largerSpan.appendChild(orderItemName);
                largerSpan.appendChild(orderSpan);
                orderItem.appendChild(largerSpan);

                //Attach delete button into the li tag
                orderItem.appendChild(deleteButton);

                //Attach the li tag(child) to the orderList(parent)
                orderList.appendChild(orderItem);

                totalItemsQuantity();
                totalItemsPrice();

                i++;

                console.log(orderIdArray);

            }
            ;

            function totalItemsQuantity() {
                document.getElementById('totalItems').innerText = totalItemsArray.length;
            }
            ;

            function totalItemsPrice() {
                if (totalPriceArray.length === 0) {
                    document.getElementById('totalCost').innerText = 0;
                } else {
                    document.getElementById('totalCost').innerText = totalPriceArray.reduce(sumarray).toFixed(2);

                    function sumarray(total, sum) {
                        return total + sum;
                    }
                    ;
                }
            }
            ;

            function orderbasketClear() {
                let orderList = document.getElementById('cart');
                orderList.innerHTML = '';
                totalItemsArray.length = 0;
                totalPriceArray.length = 0;
                orderArray.length = 0;
                orderIdArray.length = 0;
                i = 0;
                totalItemsQuantity();
                totalItemsPrice();

            }

            function deleteItem(orderId, button) {
                let orderList = document.getElementById("cart");
                const indexNum = orderIdArray.indexOf(orderId);
                orderIdArray.splice(indexNum, 1);
                totalItemsArray.splice(indexNum, 1);
                totalPriceArray.splice(indexNum, 1);
                console.log(orderIdArray);

                totalItemsQuantity();
                totalItemsPrice();
                console.log(button.parentElement);
                orderList.removeChild(button.parentElement);

            }
            ;

            function goToCheckoutTab() {
                const firstTabEl = document.getElementById('pills-checkout-tab');
                const firstTab = new bootstrap.Tab(firstTabEl);

                firstTab.show();
            }


            function paymentMethod() {
                let x = Math.floor((Math.random() * 10) + 1);
                console.log(x);
                const s = document.createElement('span');
                const screen = document.getElementsByClassName('modal-body');

                const unsuccessfulPayment = document.createTextNode('The payment was unsuccessful. Try again.');
                const successfulPayment = document.createTextNode('The payment was successful. Thank you.');

                if (x >= 7) {
                    s.appendChild(successfulPayment);
                    screen.appendChild(s).innerText;

                } else {
                    s.appendChild(unsuccessfulPayment);
                    screen.appendChild(s).innerHTML;

                }
            }
            ;

        </script>

        <script src="/index.js"></script>

        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

    </body>

</html>