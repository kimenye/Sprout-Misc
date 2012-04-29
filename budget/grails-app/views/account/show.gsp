<%@ page import="com.kimenye.budget.Account" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'account.label', default: 'Account')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                           args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                               args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.show.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="account.id.label" default="Id"/></td>

                <td valign="top" class="value">${fieldValue(bean: accountInstance, field: "id")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="account.accountName.label" default="Account Name"/></td>

                <td valign="top" class="value">${fieldValue(bean: accountInstance, field: "accountName")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="account.accountType.label" default="Account Type"/></td>

                <td valign="top" class="value">${fieldValue(bean: accountInstance, field: "accountType")}</td>

            </tr>



            <tr class="prop">
                <td valign="top" class="name"><g:message code="account.openingBalance.label"
                                                         default="Opening Balance"/></td>

                <td valign="top" class="value">${fieldValue(bean: accountInstance, field: "openingBalance")}</td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="account.baseCurrency.label"
                                                         default="Base Currency"/></td>

                <td valign="top" class="value"><g:link controller="currency" action="show"
                                                       id="${accountInstance?.baseCurrency?.id}">${accountInstance?.baseCurrency?.encodeAsHTML()}</g:link></td>

            </tr>

            <g:if test="${accountInstance?.accountType == 'Credit'}">
                <tr class="prop">
                    <td valign="top" class="name"><g:message code="account.cashCreditLimit.label"
                                                             default="Cash Credit Limit"/></td>

                    <td valign="top" class="value">${fieldValue(bean: accountInstance, field: "cashCreditLimit")}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="account.creditLimit.label" default="Credit Limit"/></td>

                    <td valign="top" class="value">${fieldValue(bean: accountInstance, field: "creditLimit")}</td>

                </tr>


                <tr class="prop">
                    <td valign="top" class="name"><g:message code="account.interestRates.label"
                                                             default="Interest Rates"/></td>

                    <td valign="top" style="text-align: left;" class="value">
                        <ul>
                            <g:each in="${accountInstance.interestRates}" var="i">
                                <li><g:link controller="interestRate" action="show"
                                            id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
                            </g:each>
                            <li><g:link controller="interestRate" action="create"
                                    params="['account.id': accountInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'interestRate.label', default: 'Interest Rate')])}</g:link></li>
                        </ul>
                    </td>

                </tr>
            </g:if>



            <tr class="prop">
                <td valign="top" class="name"><g:message code="account.inActive.label" default="In Active"/></td>

                <td valign="top" class="value"><g:formatBoolean boolean="${accountInstance?.inActive}"/></td>

            </tr>

            <tr class="prop">
                <td valign="top" class="name"><g:message code="account.transactions.label" default="Transactions"/></td>

                <td valign="top" style="text-align: left;" class="value">
                    <ul>
                        <g:each in="${accountInstance.transactions}" var="t">
                            <li><g:link controller="transaction" action="show"
                                        id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
                        </g:each>
                    </ul>
                </td>

            </tr>

            </tbody>
        </table>
    </div>

    <div class="buttons">
        <g:form>
            <g:hiddenField name="id" value="${accountInstance?.id}"/>
            <span class="button"><g:actionSubmit class="edit" action="edit"
                                                 value="${message(code: 'default.button.edit.label', default: 'Edit')}"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete"
                                                 value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                                 onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
        </g:form>
    </div>
</div>
</body>
</html>
