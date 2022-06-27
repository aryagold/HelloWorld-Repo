<%@page import="za.co.vzap.Model.Report.ItemAmount"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <% Boolean pieChart = false;%>
        <%List<ItemAmount> items = (List<ItemAmount>) request.getAttribute("Sales");%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            google.charts.load('current', {'packages': ['corechart']});
            google.charts.setOnLoadCallback(drawChart1);
            google.charts.setOnLoadCallback(drawChart);

            function drawChart1() {
 
                  var data = google.visualization.arrayToDataTable([<%--report data bar graph--%>
                    ["Branch Name", "Total Sales", {role: "style"} ],
            <%for (ItemAmount item : items) {%>
                    [' <%=item.description%> ', ' <%=item.amount%>'],
            <%}%>
                ]);

                var view = new google.visualization.DataView(data);
                view.setColumns([0, 1,
                    {calc: "stringify",
                        sourceColumn: 1,
                        type: "string",
                        role: "annotation"},
                    2]);

                var options = {
                    title: "Top achieving stores in the country",
                    width: 600,
                    height: 400,
                    bar: {groupWidth: "95%"},
                    legend: {position: "none"}
                };
                var chart = new google.visualization.BarChart(document.getElementById("barchart_values"));
                chart.draw(view, options);
            }

            function drawChart() {

                var data = google.visualization.arrayToDataTable([<%--report data pie chart--%>
                    ['Branch Name', 'Total sales'],
            <%for (ItemAmount item : items) {%>
                    [' <%=item.description%> ', ' <%=item.amount%>'],
            <%}%>
                ]);

                var options = {
                    title: 'Top achiving stores in the country'
                };

                var chart = new google.visualization.PieChart(document.getElementById('piechart'));

                chart.draw(data, options);
            }
        </script>
    </head>
    <body>
        <%if (pieChart) {%>
        <div id="piechart" style="width: 900px; height: 500px;"></div>
        <fieldset>
            <legend>Select a graph type:</legend>
            <div>
                <input onClick="window.location.reload();" onClick = "<% pieChart = true;%>"type="radio" id="piechart" name="graph" value="pie" checked>
                <label for="pie">Pie Chart</label>
            </div>
            <div>
                <input onClick="window.location.reload();" onClick = " <%pieChart = true;%>"type="radio" id="barchart_values" name="graph" value="bar">
                <label for="bar">Bar Graph</label>
            </div>
        </fieldset>
        <%} else {%>    
        <div id ="barchart_values" style="width: 900px; height: 500px"></div>
        <fieldset>
            <legend>Select a graph type:</legend>
            <div>
                <input onClick="window.location.reload();" onClick = "<% pieChart = true;%>"type="radio" id="piechart" name="graph" value="pie" >
                <label for="pie">Pie Chart</label>
            </div>
            <div>
                <input onClick="window.location.reload();" onClick = " <%pieChart = true;%>"type="radio" id="barchart_values" name="graph" value="bar" checked>
                <label for="bar">Bar Graph</label>
            </div>
        </fieldset>
        <%};%>


    </body>

</html>
