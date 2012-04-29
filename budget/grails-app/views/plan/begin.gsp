    <%@ page import="com.kimenye.budget.Plan" %>
    <html>
    <head>
        <gui:resources components="['tabView','dataTable']" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main"/>
        <g:set var="entityName" value="${message(code: 'plan.label', default: 'Plan')}"/>
        <title><g:message code="default.edit.label" args="[entityName]"/></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
            </span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                                   args="[entityName]"/></g:link></span>
        </div>

        <br /><br />
        <gui:tabView>
            <gui:tab label="Summary" active="false">
                <div class="dialog">
                        <table>
                            <tbody>

                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="plan.id.label" default="Id"/></td>

                                <td valign="top" class="value">${fieldValue(bean: planInstance, field: "id")}</td>

                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="plan.planStartDate.label"
                                                                         default="Plan Start Date"/></td>

                                <td valign="top" class="value"><g:formatDate date="${planInstance?.planStartDate}"/></td>

                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="plan.baseCurrency.label" default="Base Currency"/></td>

                                <td valign="top" class="value"><g:link controller="currency" action="show"
                                                                       id="${planInstance?.baseCurrency?.id}">${planInstance?.baseCurrency?.encodeAsHTML()}</g:link></td>

                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="plan.dateCreated.label" default="Date Created"/></td>

                                <td valign="top" class="value"><g:formatDate date="${planInstance?.dateCreated}"/></td>

                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="plan.defaultAccount.label"
                                                                         default="Default Account"/></td>

                                <td valign="top" class="value"><g:link controller="account" action="show"
                                                                       id="${planInstance?.defaultAccount?.id}">${planInstance?.defaultAccount?.encodeAsHTML()}</g:link></td>

                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="plan.lastUpdated.label" default="Last Updated"/></td>

                                <td valign="top" class="value"><g:formatDate date="${planInstance?.lastUpdated}"/></td>

                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="plan.planEndDate.label" default="Plan End Date"/></td>

                                <td valign="top" class="value"><g:formatDate date="${planInstance?.planEndDate}"/></td>

                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="plan.planName.label" default="Plan Name"/></td>

                                <td valign="top" class="value">${fieldValue(bean: planInstance, field: "planName")}</td>

                            </tr>

                            %{--
                            <tr class="prop">
                                <td valign="top" class="name"><g:message code="plan.transactions.label" default="Transactions"/></td>

                                <td valign="top" style="text-align: left;" class="value">
                                    <ul>
                                        <g:each in="${planInstance.transactions}" var="t">
                                            <li><g:link controller="transaction" action="show"
                                                        id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
                                        </g:each>
                                    </ul>
                                </td>

                            </tr>
                            --}%

                            </tbody>
                        </table>
                    </div>
            </gui:tab>

            <gui:tab label="Transactions" active="true">
               <gui:dataTable
                   columnDefs="[
                        [transactionName:'Name'],
                        [recurrenceType: 'Recurrence'],
                        [amount: 'Amount', type: 'currency', formatter:'currency', sortable:'true'],
                        [transactionDate: 'When', formatter:'date', type: 'date'],
                        [recurrenceEndDate: 'To'],
                        [key: 'account.name', label: 'Account']
                    ]"
                    allowExclusiveSort="true"
                    params="[id: 1]"
                    resultsList="resultsList"
                    draggableColumns="false"
                    controller="plan" action="transactionsAsJson" />
            </gui:tab>


        </gui:tabView>
    </div>
    </body>
    </html>
