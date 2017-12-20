<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>
                Atlantic Statistics
            </h2>
        </div>
        <div class="x_content">

            <script type="text/javascript">

                // Load the Visualization API and the corechart package.
                google.charts.load('current', {'packages':['corechart']});

                // Set a callback to run when the Google Visualization API is loaded.
                google.charts.setOnLoadCallback(drawChart);

                // Callback that creates and populates a data table,
                // instantiates the pie chart, passes in the data and
                // draws it.
                function drawChart() {

                    // Create the data table.
                    var data = new google.visualization.DataTable();
                    data.addColumn('string','Product');
                    data.addColumn('number','Units');
                    data.addRows([
                    <#list purchaseStatistics as purchase>
                        <#if purchase?is_last>
                                [${purchase}]
                        <#else>
                                [${purchase}],
                        </#if>
                    </#list>
                    ]);

                    // Set chart options
                    var options = {'title':'Units Purchased per Product',
                        'width':400,
                        'height':300};

                    // Instantiate and draw our chart, passing in some options.
                    var chart = new google.visualization.PieChart(document.getElementById('purchaseS'));
                    chart.draw(data, options);
                }
            </script>

            <div class="demo-charts mdl-color--white mdl-shadow--2dp mdl-cell mdl-cell--12-col mdl-grid">
                <div id="purchaseS"></div>
            </div>

            <br>

            <script type="text/javascript">

                // Load the Visualization API and the corechart package.
                google.charts.load('current', {'packages':['corechart']});

                // Set a callback to run when the Google Visualization API is loaded.
                google.charts.setOnLoadCallback(drawChart);

                // Callback that creates and populates a data table,
                // instantiates the pie chart, passes in the data and
                // draws it.
                function drawChart() {

                    // Create the data table.
                    var data = new google.visualization.DataTable();
                    data.addColumn('string','Dispatch');
                    data.addColumn('number','Amount');
                    data.addRows([
                    <#if (delivered)??>
                        ['Delivered',${delivered}],
                    <#else>
                        ['Delivered',0],
                    </#if>
                    <#if (delivered)??>
                            ['Pending',${pending}]
                    <#else>
                            ['Pending',0]
                    </#if>

                    ]);

                    // Set chart options
                    var options = {'title':'Done and pending dispatches',
                        'width':400,
                        'height':300};

                    // Instantiate and draw our chart, passing in some options.
                    var chart = new google.visualization.PieChart(document.getElementById('avePurchase'));
                    chart.draw(data, options);
                }
            </script>

            <div class="demo-charts mdl-color--white mdl-shadow--2dp mdl-cell mdl-cell--12-col mdl-grid">
                <div id="avePurchase"></div>
            </div>

            <br>

            <script type="text/javascript">

                // Load the Visualization API and the corechart package.
                google.charts.load('current', {'packages':['corechart']});

                // Set a callback to run when the Google Visualization API is loaded.
                google.charts.setOnLoadCallback(drawChart);


                // Callback that creates and populates a data table,
                // instantiates the pie chart, passes in the data and
                // draws it.
                function drawChart() {

                    // Create the data table.
                    var data = new google.visualization.DataTable();
                    data.addColumn('string','Product');
                    data.addColumn('number','Views');
                    data.addRows([
                    <#list productsView as product>
                        <#if product?is_last>
                                [${product}]
                        <#else>
                                [${product}],
                        </#if>
                    </#list>
                    ]);

                    // Set chart options
                    var options = {'title':'Views per Products',
                        'width':400,
                        'height':300};

                    // Instantiate and draw our chart, passing in some options.
                    var chart = new google.visualization.PieChart(document.getElementById('productV'));
                    chart.draw(data, options);
                }

            </script>

            <div class="demo-charts mdl-color--white mdl-shadow--2dp mdl-cell mdl-cell--12-col mdl-grid">
                <div id="productV"></div>
            </div>

            <br>

            <script type="text/javascript">

                // Load the Visualization API and the corechart package.
                google.charts.load('current', {'packages':['corechart']});

                // Set a callback to run when the Google Visualization API is loaded.
                google.charts.setOnLoadCallback(drawChart);

                // Callback that creates and populates a data table,
                // instantiates the pie chart, passes in the data and
                // draws it.
                function drawChart() {

                    // Create the data table.
                    var data = new google.visualization.DataTable();
                    data.addColumn('string','Supplier');
                    data.addColumn('number','Popularity');
                    data.addRows([
                    <#list supplierStatistics as supplier>
                        <#if supplier?is_last>
                                [${supplier}]
                        <#else>
                                [${supplier}],
                        </#if>
                    </#list>
                    ]);

                    // Set chart options
                    var options = {'title':'Supplier Popularity Among Buyers',
                        'width':400,
                        'height':300};

                    // Instantiate and draw our chart, passing in some options.
                    var chart = new google.visualization.PieChart(document.getElementById('suppliers'));
                    chart.draw(data, options);
                }
            </script>

            <div class="demo-charts mdl-color--white mdl-shadow--2dp mdl-cell mdl-cell--12-col mdl-grid">
                <div id="suppliers"></div>
            </div>


        </div>
    </div>
</div>
<!--I Know this is wrong, i just don't care-->
<br><br><br><br><br><br><br><br><br>

