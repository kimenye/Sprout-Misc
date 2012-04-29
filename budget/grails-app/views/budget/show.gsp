<%@ page import="com.kimenye.budget.Plan" %>
    <html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="styled"/>
        <g:set var="entityName" value="${message(code: 'plan.label', default: 'Plan')}"/>
        <title>Budget View</title>

        <g:javascript>
            %{--var incomeTotals = [];--}%
            %{--var expenseTotals = [];--}%
            %{--var transferTotals = [];--}%

            %{--var hasTransfers = false;--}%
            %{--var hasIncome = false;--}%
            %{--var hasExpenses = false;--}%

            %{--<g:each in="${view.incomeTotals}" var="total">--}%
                %{--if (${total.amount} < 0 || ${total.amount} > 0) {--}%
                    %{--hasIncome = true;--}%
                %{--}--}%
                %{--incomeTotals[incomeTotals.length] =  "<g:formatNumber number="${total.amount}" type="currency" currencyCode="USD" />";--}%
            %{--</g:each>--}%
            %{--<g:each in="${view.expenseTotals}" var="total">--}%
                %{--if (${total.amount} < 0 || ${total.amount} > 0) {--}%
                    %{--hasExpenses = true;--}%
                %{--}--}%
                %{--expenseTotals[expenseTotals.length] = "<g:formatNumber number="${total.amount}" type="currency" currencyCode="USD" />";--}%
            %{--</g:each>--}%
            %{--<g:each in="${view.transferTotals}" var="total">--}%
                %{--if (${total.amount} < 0 || ${total.amount} > 0) {--}%
                    %{--hasTransfers = true;--}%
                %{--}--}%
                %{--transferTotals.push("<g:formatNumber number="${total.amount}" type="currency" currencyCode="USD" />");--}%
            %{--</g:each>--}%


            %{--var groupFooters = {}; //--}%
            %{--if(hasIncome)--}%
                %{--groupFooters['Income'] = incomeTotals;--}%
            %{--if(hasTransfers)--}%
                %{--groupFooters['Transfer'] = transferTotals;--}%
            %{--if(hasExpenses)--}%
                %{--groupFooters['Expense'] = expenseTotals;--}%


            %{--$(document).ready( function () {--}%
				%{--$('#example').dataTable().rowGrouping({--}%
            							%{--iGroupingColumnIndex: 1,--}%
            							%{--sGroupingColumnSortDirection: "asc",--}%
            							%{--iGroupingOrderByColumnIndex: 0//,--}%
%{--//            							aGroupFooters: groupFooters--}%
								%{--});--}%

			%{--});--}%

            $(document).ready( function () {
				$('#example').dataTable().rowGrouping({
            							iGroupingColumnIndex: 1,
            							sGroupingColumnSortDirection: "asc",
            							iGroupingOrderByColumnIndex: 0
								});

			});
        </g:javascript>
    </head>
    <body>
        <br />
         <h2>${view.planName} From: </b><g:formatDate date="${view.startDate}" format="dd/MM/yyyy"/>  to <g:formatDate date="${view.endDate}" format="dd/MM/yyyy"/> </h2>

        %{--<budget:displayCurrency amount="${todaysRate}" toCurrencyCode="${currencyCode}" />--}%

        <br /> <br />
        %{--
        <gui:tabView>
            <gui:tab label="Transactions" active="false">

            </gui:tab>
        </gui:tabView>--}%

        <table cellpadding="0" cellspacing="0" border="0" class="display plain" id="example">
            <thead>
                <tr>
                    <th>Group order by</th>
                    <th>Transaction Type</th>
                    <th>Transactions</th>
                     <g:each in="${view.months}" var="month">
                         <th><g:formatDate date="${month}" format="MMM-yyyy" /></th>
                     </g:each>
                </tr>
            </thead>
            <tbody>
                <g:each in="${view.transactions}" var="trans">
                      <tr>
                          <td>
                            <g:if test="${trans.transactionType == 'Income'}">
                                10
                            </g:if>
                            <g:elseif test="${trans.transactionType == 'Transfer'}">
                                20
                            </g:elseif>
                            <g:else>
                                30
                            </g:else>
                          </td>
                          <td>${trans.transactionType}</td>
                          <td>${trans.transactionName}</td>
                          <g:each in="${view.months}" var="month">
                              <g:each in="${trans.monthlyTransactions}" var="monthlyTrans">
                                  %{--<g:if test="${monthlyTrans.month == monthName}">--}%
                                  <g:if test="${monthlyTrans.monthDate.format('MMM-yyyy') == month.format('MMM-yyyy')}">
                                    %{--<td><g:formatNumber number="${monthlyTrans.amount}" type="currency" currencyCode="USD"/></td>--}%
                                      <td><budget:displayCurrency amount="${monthlyTrans.amount}" toCurrencyCode="${currencyCode}" raw="true" /></td>
                                  </g:if>
                              </g:each>
                          </g:each>
                      </tr>
                </g:each>
            </tbody>
            <tfoot>
                <tr>
                    <th></th>
                    <th></th>
                    <th>Total</th>

                    <g:each in="${view.totals}" var="trans">
                        <th class="money totals">
                            %{--<g:formatNumber number="${trans.amount}" type="currency" currencyCode="KES" />--}%
                            <budget:displayCurrency amount="${trans.amount}" toCurrencyCode="${currencyCode}" />
                        </th>
                    </g:each>
                </tr>
            </tfoot>
        </table>


        %{--<table cellpadding="0" cellspacing="0" border="0" class="display" id="example">--}%
            %{--<thead>--}%
                %{--<tr>--}%
                    %{--<th>Group order by</th>--}%
                    %{--<th>Transaction Type</th>--}%
                    %{--<th>Transactions</th>--}%
                    %{--<g:each in="${view.monthNames}" status="i" var="monthName">--}%
                        %{--<th class="money">--}%
                            %{--${monthName}--}%
                        %{--</th>--}%
                    %{--</g:each>--}%
                %{--</tr>--}%
            %{--</thead>--}%
            %{--<tbody>--}%
                %{--<g:each in="${view.transactions}" var="trans">--}%
                    %{--<tr>--}%
                        %{--<td>--}%
                            %{--<g:if test="${trans.transactionType == 'Income'}">--}%
                                %{--10--}%
                            %{--</g:if>--}%
                            %{--<g:elseif test="${trans.transactionType == 'Transfer'}">--}%
                                %{--20--}%
                            %{--</g:elseif>--}%
                            %{--<g:else>--}%
                                %{--30--}%
                            %{--</g:else>--}%
                        %{--</td>--}%
                        %{--<td>${trans.transactionType}</td>--}%
                        %{--<td>${trans.transactionName}</td>--}%
                        %{--<g:each in="${view.monthNames}" status="i" var="monthName">--}%
                            %{--<g:each in="${trans.monthlyTransactions}" var="monthlyTrans">--}%
                                %{--<g:if test="${monthlyTrans.month == monthName}">--}%
                                    %{--<td class="money"><g:formatNumber number="${monthlyTrans.amount}" type="currency" currencyCode="USD"/></td>--}%
                                %{--</g:if>--}%
                            %{--</g:each>--}%
                        %{--</g:each>--}%
                    %{--</tr>--}%
                %{--</g:each>--}%
            %{--</tbody>--}%
            %{--<tfoot>--}%
                %{--<tr>--}%
                    %{--<th></th>--}%
                    %{--<th></th>--}%
                    %{--<th>Total</th>--}%

                        %{--<g:each in="${view.totals}" var="trans">--}%
                            %{--<th class="money totals">--}%
                                %{--<g:formatNumber number="${trans.amount}" type="currency" currencyCode="USD" />--}%
                            %{--</th>--}%
                        %{--</g:each>--}%
                %{--</tr>--}%
            %{--</tfoot>--}%
        %{--</table>--}%

        <br />

        <br />
    </body>
</html>