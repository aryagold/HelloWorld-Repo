<%@page import="za.co.vzap.Model.Report.CustomerReportsDto"%>
<%@page import="za.co.vzap.Model.Report.ItemAmount"%>
<%@page import="java.util.List"%>
<%@page import="za.co.vzap.Model.Report.TopAchievingStoresDto"%>
<html>
    <head>
        <%CustomerReportsDto dto = (CustomerReportsDto) request.getAttribute("customerreports");%>
        <%List<ItemAmount> items = dto.storeRatings;%>

        <!--Load the AJAX API-->
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
            var data;
            var chart;

            // Load the Visualization API and the piechart package.
            google.charts.load('current', {'packages': ['corechart']});

            // Set a callback to run when the Google Visualization API is loaded.
            google.charts.setOnLoadCallback(drawChart);

            // Callback that creates and populates a data table,
            // instantiates the pie chart, passes in the data and
            // draws it.
            function drawChart() {

                // Create our data table.
                data = new google.visualization.DataTable();
                data.addColumn('string', 'Store Name');
                data.addColumn('number', 'Rating');

                data.addRows([
            <%for (int i = 0; i < items.size(); i++) {
                    ItemAmount item = items.get(i);

                    if (i == 0) {%>
                [' <%=item.description%> ', <%=item.amount%>]
            <%} else {%>
                , [' <%=item.description%> ', <%=item.amount%>]
            <%}
                      }%>

                ]);

                // Set chart options
                var options = {'title': '<%=dto.title%>',
                    'width': 1000,
                    'height': 600};

                // Instantiate and draw our chart, passing in some options.
                chart = new google.visualization.PieChart(document.getElementById('chart_div'));
                google.visualization.events.addListener(chart, 'select', selectHandler);
                chart.draw(data, options);
            }

            function selectHandler() {
                var selectedItem = chart.getSelection()[0];
                var value = data.getValue(selectedItem.row, 0);
                alert('The user selected ' + value);
            }

        </script>
    </head>
    <body>

        <div id="homebtn">
            <a href="http://localhost:8080/CarolsBoutiqueRest/rest/report/customerreport/download?month=<%=request.getAttribute("month")%>&resultAmount=<%=request.getAttribute("resultAmount")%>"><button class="downloadButton">Download</button></a>
        </div>

        <!--Div that will hold the pie chart-->
        <div id="chart_div" style="width:400; height:300"></div>


    </body>
</html>