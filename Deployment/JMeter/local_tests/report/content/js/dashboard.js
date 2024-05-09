/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 100.0, "KoPercent": 0.0};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.967741935483871, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [1.0, 500, 1500, "Post Logout-0"], "isController": false}, {"data": [1.0, 500, 1500, "Get Homepage-0"], "isController": false}, {"data": [1.0, 500, 1500, "Post Login Admin"], "isController": false}, {"data": [1.0, 500, 1500, "Post Logout-1"], "isController": false}, {"data": [1.0, 500, 1500, "Get Homepage-1"], "isController": false}, {"data": [1.0, 500, 1500, "Get New User Profile-0"], "isController": false}, {"data": [1.0, 500, 1500, "Get Trails Page"], "isController": false}, {"data": [1.0, 500, 1500, "Get New User Profile-1"], "isController": false}, {"data": [1.0, 500, 1500, "Get Admin Add Trail"], "isController": false}, {"data": [1.0, 500, 1500, "Get Admin Add Trail-1"], "isController": false}, {"data": [1.0, 500, 1500, "Get Scan QR Page-0"], "isController": false}, {"data": [1.0, 500, 1500, "Get Scan QR Page-1"], "isController": false}, {"data": [1.0, 500, 1500, "Get Scan QR Page"], "isController": false}, {"data": [1.0, 500, 1500, "Post Register New User-0"], "isController": false}, {"data": [1.0, 500, 1500, "Post Register New User-1"], "isController": false}, {"data": [1.0, 500, 1500, "Get Register Page"], "isController": false}, {"data": [1.0, 500, 1500, "Post Login New User"], "isController": false}, {"data": [1.0, 500, 1500, "Post Logout"], "isController": false}, {"data": [1.0, 500, 1500, "Post Register New User"], "isController": false}, {"data": [0.5, 500, 1500, "Get Homepage"], "isController": false}, {"data": [1.0, 500, 1500, "Get Login Page"], "isController": false}, {"data": [1.0, 500, 1500, "Get Leaderboard Page"], "isController": false}, {"data": [1.0, 500, 1500, "Get Admin Add Trail-0"], "isController": false}, {"data": [1.0, 500, 1500, "Get New User Profile"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 31, 0, 0.0, 95.45161290322582, 2, 515, 10.0, 373.40000000000003, 515.0, 515.0, 33.36921420882669, 68.38670613562971, 11.061751547362755], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["Post Logout-0", 2, 0, 0.0, 3.0, 3, 3, 3.0, 3.0, 3.0, 3.0, 37.03703703703704, 12.18894675925926, 13.563368055555555], "isController": false}, {"data": ["Get Homepage-0", 2, 0, 0.0, 161.0, 161, 161, 161.0, 161.0, 161.0, 161.0, 12.422360248447204, 5.058715062111801, 2.341323757763975], "isController": false}, {"data": ["Post Login Admin", 1, 0, 0.0, 379.0, 379, 379, 379.0, 379.0, 379.0, 379.0, 2.638522427440633, 6.658146437994723, 0.6364404683377308], "isController": false}, {"data": ["Post Logout-1", 2, 0, 0.0, 7.0, 7, 7, 7.0, 7.0, 7.0, 7.0, 34.48275862068965, 79.97710129310344, 8.149245689655173], "isController": false}, {"data": ["Get Homepage-1", 2, 0, 0.0, 351.0, 351, 351, 351.0, 351.0, 351.0, 351.0, 5.698005698005698, 13.961226851851853, 1.3744212962962963], "isController": false}, {"data": ["Get New User Profile-0", 1, 0, 0.0, 4.0, 4, 4, 4.0, 4.0, 4.0, 4.0, 250.0, 83.49609375, 60.791015625], "isController": false}, {"data": ["Get Trails Page", 1, 0, 0.0, 45.0, 45, 45, 45.0, 45.0, 45.0, 45.0, 22.22222222222222, 61.15451388888889, 5.381944444444445], "isController": false}, {"data": ["Get New User Profile-1", 1, 0, 0.0, 6.0, 6, 6, 6.0, 6.0, 6.0, 6.0, 166.66666666666666, 408.3658854166667, 40.201822916666664], "isController": false}, {"data": ["Get Admin Add Trail", 1, 0, 0.0, 6.0, 6, 6, 6.0, 6.0, 6.0, 6.0, 166.66666666666666, 464.02994791666663, 82.19401041666667], "isController": false}, {"data": ["Get Admin Add Trail-1", 1, 0, 0.0, 4.0, 4, 4, 4.0, 4.0, 4.0, 4.0, 250.0, 612.548828125, 60.302734375], "isController": false}, {"data": ["Get Scan QR Page-0", 1, 0, 0.0, 3.0, 3, 3, 3.0, 3.0, 3.0, 3.0, 333.3333333333333, 111.328125, 80.078125], "isController": false}, {"data": ["Get Scan QR Page-1", 1, 0, 0.0, 5.0, 5, 5, 5.0, 5.0, 5.0, 5.0, 200.0, 490.0390625, 48.2421875], "isController": false}, {"data": ["Get Scan QR Page", 1, 0, 0.0, 9.0, 9, 9, 9.0, 9.0, 9.0, 9.0, 111.1111111111111, 309.35329861111114, 53.493923611111114], "isController": false}, {"data": ["Post Register New User-0", 1, 0, 0.0, 102.0, 102, 102, 102.0, 102.0, 102.0, 102.0, 9.803921568627452, 3.465839460784314, 5.170036764705882], "isController": false}, {"data": ["Post Register New User-1", 1, 0, 0.0, 17.0, 17, 17, 17.0, 17.0, 17.0, 17.0, 58.8235294117647, 136.43152573529412, 13.901654411764705], "isController": false}, {"data": ["Get Register Page", 1, 0, 0.0, 36.0, 36, 36, 36.0, 36.0, 36.0, 36.0, 27.777777777777775, 113.09136284722223, 6.890190972222222], "isController": false}, {"data": ["Post Login New User", 1, 0, 0.0, 80.0, 80, 80, 80.0, 80.0, 80.0, 80.0, 12.5, 31.54296875, 3.01513671875], "isController": false}, {"data": ["Post Logout", 2, 0, 0.0, 10.0, 10, 10, 10.0, 10.0, 10.0, 10.0, 32.786885245901644, 86.83401639344262, 19.755379098360656], "isController": false}, {"data": ["Post Register New User", 1, 0, 0.0, 119.0, 119, 119, 119.0, 119.0, 119.0, 119.0, 8.403361344537815, 22.4609375, 6.417410714285714], "isController": false}, {"data": ["Get Homepage", 2, 0, 0.0, 515.0, 515, 515, 515.0, 515.0, 515.0, 515.0, 3.883495145631068, 11.096783980582524, 1.6686893203883495], "isController": false}, {"data": ["Get Login Page", 2, 0, 0.0, 7.5, 6, 9, 7.5, 9.0, 9.0, 9.0, 8.403361344537815, 20.589876575630253, 2.026982668067227], "isController": false}, {"data": ["Get Leaderboard Page", 1, 0, 0.0, 22.0, 22, 22, 22.0, 22.0, 22.0, 22.0, 45.45454545454545, 122.11470170454547, 11.23046875], "isController": false}, {"data": ["Get Admin Add Trail-0", 1, 0, 0.0, 2.0, 2, 2, 2.0, 2.0, 2.0, 2.0, 500.0, 166.9921875, 125.9765625], "isController": false}, {"data": ["Get New User Profile", 1, 0, 0.0, 11.0, 11, 11, 11.0, 11.0, 11.0, 11.0, 90.9090909090909, 253.10724431818184, 44.034090909090914], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": []}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 31, 0, "", "", "", "", "", "", "", "", "", ""], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
