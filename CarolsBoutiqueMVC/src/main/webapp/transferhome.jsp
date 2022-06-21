<%-- 
    Document   : transferhome
    Created on : 21 Jun 2022, 09:42:12
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
        <title>Transfers</title>
    </head>
    <body>
        <div>

            <div class="row">
                <div class="col-sm">
                    <ul>
                        <form action="POSServlet" method="get"></form>
                            <div><button name="submit" value="ibt/1">New Requests Received</button></div>
                            <div><button name="submit" value="ibt/0">Status On Requests</button></div>
                            <a href="transferrequest.jsp"><button>Request a Transfer</button></a>
                        </form>


                    </ul>
                </div>

                <div class="col-sm">







                </div>
                <div class="col-sm">


                </div>
            </div>
        </div>

        <script src= "name"></script>

    </body>
</html>
