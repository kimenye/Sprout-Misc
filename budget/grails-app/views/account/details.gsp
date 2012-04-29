<%@ page import="com.kimenye.budget.Account" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="styled"/>
    <g:set var="entityName" value="${message(code: 'account.label', default: 'Account')}"/>
    <gui:resources components="['tabView']" />
    <title><g:message code="default.show.label" args="[entityName]"/></title>
    <jqplot:plugin name="dateAxisRenderer" />
    <g:javascript>
        var planStartDate = '${view.startDate}';

        $(document).ready( function () {

                var series = [];
                <g:each in="${view.transactions}" var="trans">
                    series.push(['<g:formatDate date="${trans.monthDate}" format="yyyy-MM-dd"/>', <budget:displayCurrency amount="${trans.runningTotal}" toCurrencyCode="${currencyCode}" raw="${true}"/>]);
                </g:each>

                %{--var line1=[['2008-06-30 8:00AM',4], ['2008-7-30 8:00AM',6.5], ['2008-8-30 8:00AM',5.7], ['2008-9-30 8:00AM',9], ['2008-10-30 8:00AM',8.2]];--}%
                var timeline = $.jqplot('chart', [series], {
                      title:'Timeline',
//                      gridPadding:{right:35},
                      axes:{
                        xaxis:{
                          renderer:$.jqplot.DateAxisRenderer,
                          tickOptions:{formatString:'%b, %y'},
                          tickInterval:'1 month',
                          min: planStartDate
                        },
                        yaxis:{
//                          max: 0,
                          tickInterval: 1000
                        }
                      },
                      series:[{lineWidth:4, markerOptions:{style:'square'}}]
                  });

				$('#example').dataTable();

			});
    </g:javascript>
</head>

<body class="body">
    %{--<h1><g:message code="default.details.label" args="[entityName]"/></h1>--}%
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    %{--<br />--}%
        <h2>${view.accountName}</h2>
        <b>Account Type: </b> ${view.accountType} <br />
        <b>Opening Balance: </b><budget:displayCurrency amount="${view.openingBalance}" toCurrencyCode="${currencyCode}" /><br/>
        <b>Closing Balance: </b><budget:displayCurrency amount="${view.closingBalance}" toCurrencyCode="${currencyCode}" /><br />
        %{--<b>Opening Balance: </b><g:formatNumber number="${view.openingBalance}" type="currency" currencyCode="USD" /> <br />--}%
        %{--<b>Closing Balance: </b><g:formatNumber number="${view.closingBalance}" type="currency" currencyCode="USD"/><br />--}%
        <b>${view.planName} From: </b><g:formatDate date="${view.startDate}" format="dd/MM/yyyy"/> <b> to </b> <g:formatDate date="${view.endDate}" format="dd/MM/yyyy"/> </b><br />
        <br /> <br />

        <gui:tabView>
            <gui:tab label="Transactions" active="true">
                <br />
                <table cellpadding="0" cellspacing="0" border="0" class="display plain" id="example">
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Transaction</th>
                            <th>Debit</th>
                            <th>Credit</th>
                            <th>Balance</th>
                        </tr>
                    </thead>
                    <tbody>
                        <g:each in="${view.simpleTransactions}" var="trans">
                            <tr>
                                <td><g:formatDate date="${trans.transactionDate}" format="dd/MM/yyyy"/></td>
                                <td>${trans.transactionName}</td>
                                <td><budget:displayCurrency amount="${trans.debit}" toCurrencyCode="${currencyCode}" /></td>
                                <td><budget:displayCurrency amount="${trans.credit}" toCurrencyCode="${currencyCode}" /></td>
                                <td><budget:displayCurrency amount="${trans.balance}" toCurrencyCode="${currencyCode}" /></td>
                                %{--<td><g:formatNumber number="${trans.debit}" type="currency" currencyCode="USD"/></td>--}%
                                %{--<td><g:formatNumber number="${trans.credit}" type="currency" currencyCode="USD"/></td>--}%
                                %{--<td><g:formatNumber number="${trans.balance}" type="currency" currencyCode="USD"/></td>--}%
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </gui:tab>
            <gui:tab label="Timeline" active="false">
                <br />
                <div id="chart" style="height:400px;width:1000px;"></div>
            </gui:tab>
        </gui:tabView>
        <br /><br />
</body>
</html>