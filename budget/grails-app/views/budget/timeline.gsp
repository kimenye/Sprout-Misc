<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="styled"/>
        <g:set var="entityName" value="${message(code: 'plan.label', default: 'Plan')}"/>
        <title>Plan timeline</title>
        <jqplot:plugin name="dateAxisRenderer" />
        <g:javascript>
            var overallTotals = [];
            <g:each in="${view.totals}" var="total">
                overallTotals.push(['<g:formatDate date="${total.monthDate}" format="yyyy-MM-dd"/>',<budget:displayCurrency amount="${total.runningTotal}" toCurrencyCode="${currencyCode}" raw="${true}" />]);
            </g:each>


            var planStartDate = '${view.startDate}';
//            planStartDate = getLastDayOfPreviousMonth(planStartDate)
            var accounts = [];
            var series = [];
            series.push(overallTotals)

            var legends = [];
            legends.push({lineWidth:1, markerOptions:{style:'square'}, label: 'Overall : ' + '<budget:displayCurrency amount="${view.closingBalance}" toCurrencyCode="${currencyCode}"/>'});

            <g:each in="${view.accountBreakdown}" var="acc">
                var accTotals = [];
                <g:each in="${acc.transactions}" var="total">
                    accTotals.push(['<g:formatDate date="${total.monthDate}" format="yyyy-MM-dd"/>',<budget:displayCurrency amount="${total.runningTotal}" toCurrencyCode="${currencyCode}" raw="${true}" />]);
                </g:each>
                accounts.push({ accountName: '${acc.accountName}', transactions: accTotals});
                series.push(accTotals);
                %{--legends.push({lineWidth: 2, markerOptions: {style: 'circle'}, label: '${acc.accountName} : <g:formatNumber number="${acc.closingBalance.amount}" type="currency" currencyCode="USD"/>'});--}%
                legends.push({lineWidth: 2, markerOptions: {style: 'circle'}, label: '${acc.accountName} : <budget:displayCurrency amount="${acc.closingBalance}" toCurrencyCode="${currencyCode}" />'});
            </g:each>



            $(document).ready(function(){
                var plot = $.jqplot('chart', series, {
                    gridPadding:{right:35},
                    axes:{
                        xaxis:{
                          label: 'Plan timeline',
                          renderer:$.jqplot.DateAxisRenderer,
                          tickOptions:{formatString:'%b, %y'},
                          min:planStartDate,
                          tickInterval:'1 month'
                        },
                        yaxis: {
                          label: 'Balance',
                          formatString: '$###,##0'
                        }
                    },
                    legend: {
                        show: true,
                        location: 'ne'
                    },
                    series:legends
                });
            });
        </g:javascript>
    </head>
    <body>
        <br />
         <h2>${view.planName}</h2>
         <b>From: </b><g:formatDate date="${view.startDate}" format="dd/MM/yyyy"/> <b> to </b> <g:formatDate date="${view.endDate}" format="dd/MM/yyyy"/> </b><br />
         <b>Opening Balance: </b><budget:displayCurrency amount="${view.openingBalance}" toCurrencyCode="${currencyCode}" /><br/>
        <b>Closing Balance: </b><budget:displayCurrency amount="${view.closingBalance}" toCurrencyCode="${currencyCode}" /><br/>


         <br /><br />
        <div id="chart" style="height:400px;width:1000px;"></div>
        <br /><br />
    </body>
</html>