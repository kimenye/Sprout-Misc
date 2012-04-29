<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="styled"/>
        <g:set var="entityName" value="${message(code: 'plan.label', default: 'Plan')}"/>
        <title>Plan timeline</title>
        <jqplot:plugin name="pieRenderer" />
        <g:javascript>

            var incomes = [];
            <g:each in="${view.income}" var="inc">
                incomes.push(['${inc.transactionName}', ${inc.percentage}]);
            </g:each>

            var expenses = [];
            <g:each in="${view.expenses}" var="exp">
                expenses.push(['${exp.transactionName}', ${exp.percentage}]);
            </g:each>



            $(document).ready(function(){
              var data = [
                ['Heavy Industry', 12],['Retail', 9], ['Light Industry', 14],
                ['Out of home', 16],['Commuting', 7], ['Orientation', 9]
              ];

              if (incomes.length > 0) {
                  var incomeChart = jQuery.jqplot ('income-chart', [expenses],
                    {
                        title: 'Income',
                      seriesDefaults: {
                        renderer: jQuery.jqplot.PieRenderer,
                        rendererOptions: {
                          // Turn off filling of slices.
                          fill: true,
                          showDataLabels: true,
                          // Add a margin to seperate the slices.
                          sliceMargin: 4,
                          // stroke the slices with a little thicker line.
                          lineWidth: 5
                        }
                      },
                      legend: { show:true, location: 'e' }
                    }
                  );
                  $('#income-chart-empty-text').addClass("hide");
              }

              if (expenses.length > 0) {
                  var expenseChart = jQuery.jqplot ('expense-chart', [expenses],
                    {
                        title: 'Expenses',
                      seriesDefaults: {
                        renderer: jQuery.jqplot.PieRenderer,
                        rendererOptions: {
                          // Turn off filling of slices.
                          fill: true,
                          showDataLabels: true,
                          // Add a margin to seperate the slices.
                          sliceMargin: 4,
                          // stroke the slices with a little thicker line.
                          lineWidth: 5
                        }
                      },
                      legend: { show:true, location: 'e' }
                    }
                  );
                  $('#expense-chart-empty-text').addClass("hide");
               }
            });
            %{--var overallTotals = [];--}%
            %{--<g:each in="${view.totals}" var="total">--}%
                %{--overallTotals.push(['${total.month}',${total.amount}]);--}%
            %{--</g:each>--}%



            %{--$(document).ready(function(){--}%
                %{--var plot = $.jqplot('chart', [overallTotals], {--}%
                    %{--gridPadding:{right:35},--}%
                    %{--axes:{--}%
                        %{--xaxis:{--}%
                          %{--label: 'Plan timeline',--}%
                          %{--renderer:$.jqplot.DateAxisRenderer,--}%
                          %{--tickOptions:{formatString:'%b'},--}%
                          %{--min:'${view.startDate}',--}%
                          %{--tickInterval:'1 month'--}%
                        %{--},--}%
                        %{--yaxis: {--}%
                          %{--label: 'Balance'--}%
                        %{--}--}%
                    %{--},--}%
                %{--series:[{lineWidth:4, markerOptions:{style:'square'}}]--}%
                %{--});--}%
            %{--});--}%
        </g:javascript>
    </head>
    <body>
        <br />
         <h2>${view.planName} From: <g:formatDate date="${view.startDate}" format="dd/MM/yyyy"/> to <g:formatDate date="${view.endDate}" format="dd/MM/yyyy"/> </h2>



         <br /><br />
        <div id="income-chart" style="height:400px;width:400px;float:left;">
            <div id="income-chart-empty-text">
                <h2>No income recorded</h2>
                <g:link action="create" controller="transaction" params="['transactionType':'Income']">${message(code: 'default.create.label', args: [message(code: 'income.label', default: 'Income')])}</g:link>
            </div>
        </div>
        <div id="expense-chart" style="height:400px;width:400px;float:right;">
            <div id="expense-chart-empty-text">
                <h2>No expenses recorded</h2>
                <g:link action="create" controller="transaction" params="['transactionType':'Expense']">${message(code: 'default.create.label', args: [message(code: 'expense.label', default: 'Expense')])}</g:link>
            </div>
        </div>
        <br /><br />
    </body>
</html>