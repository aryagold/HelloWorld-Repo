<%-- 
    Document   : reporthome
    Created on : 21 Jun 2022, 10:10:38
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
        <title>Carol's Boutique Reports</title>
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
    </head>
    <body>
        <div>

            <div class="row">
                <div class="col-sm">
                    <ul>
                        <a href="ReportServlet?submit=topachievingstores"><li><button>Top Achieving Stores</button></li></a>
                        <a href="ReportServlet?submit=customerreports"><li><button>Customer Reports</button></li></a>
                        <a href="ReportServlet?submit=topsellingemployees"><li><button>Top Selling Employees</button></li></a>
                        <a href="ReportServlet?submit=storesattarget"><li><button>Stores at Target</button></li></a>

                    </ul>
                </div>

                <div class="col-sm">



                    <img id="logo" src="Caol_s_Botique-removebg-preview.png" alt="Logo">



                </div>
                <div class="col-sm">
                    <ul>
                        
                        <a href="ReportServlet?submit=leastperformingstores"><li><button>Least Performing Stores</button></li></a>
                        <a href="ReportServlet?submit=salesformonth"><li><button>Month's Sales Report</button></li></a>
                        <a href="ReportServlet?submit=salesfortheday"><li><button>Day's Sales Report</button></li></a>
                        <a href="home.jsp"><li><button>Home</button></li></a>
                    </ul>

                </div>
            </div>
        </div>



    </body>
</html>
