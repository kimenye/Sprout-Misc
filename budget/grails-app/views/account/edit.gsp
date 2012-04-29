<%@ page import="com.kimenye.budget.Account" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'account.label', default: 'Account')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
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
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${accountInstance}">
        <div class="errors">
            <g:renderErrors bean="${accountInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form method="post">
        <g:hiddenField name="id" value="${accountInstance?.id}"/>
        <g:hiddenField name="version" value="${accountInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="accountName"><g:message code="account.accountName.label"
                                                            default="Account Name"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: accountInstance, field: 'accountName', 'errors')}">
                        <g:textField name="accountName" value="${accountInstance?.accountName}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="accountType"><g:message code="account.accountType.label"
                                                            default="Account Type"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: accountInstance, field: 'accountType', 'errors')}">
                        <g:select name="accountType" from="${accountInstance.constraints.accountType.inList}"
                                  value="${accountInstance?.accountType}" valueMessagePrefix="account.accountType"/>
                    </td>
                </tr>



                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="openingBalance"><g:message code="account.openingBalance.label"
                                                               default="Opening Balance"/></label>
                    </td>
                    <td valign="top"
                        class="value ${hasErrors(bean: accountInstance, field: 'openingBalance', 'errors')}">
                        <g:textField name="openingBalance"
                                     value="${fieldValue(bean: accountInstance, field: 'openingBalance')}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="baseCurrency"><g:message code="account.baseCurrency.label"
                                                             default="Base Currency"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: accountInstance, field: 'baseCurrency', 'errors')}">
                        <g:select name="baseCurrency.id" from="${com.kimenye.budget.Currency.list()}" optionKey="id"
                                  value="${accountInstance?.baseCurrency?.id}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="inActive"><g:message code="account.inActive.label" default="In Active"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: accountInstance, field: 'inActive', 'errors')}">
                        <g:checkBox name="inActive" value="${accountInstance?.inActive}"/>
                    </td>
                </tr>
                <g:if test="${accountInstance?.accountType == 'Credit'}">
                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="cashCreditLimit"><g:message code="account.cashCreditLimit.label"
                                                                    default="Cash Credit Limit"/></label>
                        </td>
                        <td valign="top"
                            class="value ${hasErrors(bean: accountInstance, field: 'cashCreditLimit', 'errors')}">
                            <g:textField name="cashCreditLimit"
                                         value="${fieldValue(bean: accountInstance, field: 'cashCreditLimit')}"/>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="interestFreePeriodDays"><g:message code="account.interestFreePeriodDays.label"
                                                                    default="Interest Free Period Days"/></label>
                        </td>
                        <td valign="top"
                            class="value ${hasErrors(bean: accountInstance, field: 'interestFreePeriodDays', 'errors')}">
                            <g:textField name="interestFreePeriodDays"
                                         value="${fieldValue(bean: accountInstance, field: 'interestFreePeriodDays')}"/>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="creditLimit"><g:message code="account.creditLimit.label"
                                                                default="Credit Limit"/></label>
                        </td>
                        <td valign="top" class="value ${hasErrors(bean: accountInstance, field: 'creditLimit', 'errors')}">
                            <g:textField name="creditLimit"
                                         value="${fieldValue(bean: accountInstance, field: 'creditLimit')}"/>
                        </td>
                    </tr>

                    <tr class="prop">
                        <td valign="top" class="name">
                            <label for="interestRates"><g:message code="account.interestRates.label"
                                                                  default="Interest Rates"/></label>
                        </td>
                        <td valign="top"
                            class="value ${hasErrors(bean: accountInstance, field: 'interestRates', 'errors')}">

                            <ul>
                                <g:each in="${accountInstance?.interestRates?}" var="i">
                                    <li><g:link controller="interestRate" action="show"
                                                id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
                                </g:each>
                            </ul>
                            <g:link controller="interestRate" action="create"
                                    params="['account.id': accountInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'interestRate.label', default: 'InterestRate')])}</g:link>

                        </td>
                    </tr>
                </g:if>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="transactions"><g:message code="account.transactions.label"
                                                             default="Transactions"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: accountInstance, field: 'transactions', 'errors')}">

                        <ul>
                            <g:each in="${accountInstance?.transactions?}" var="t">
                                <li><g:link controller="transaction" action="show"
                                            id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
                            </g:each>
                        </ul>
                        <g:link controller="transaction" action="create"
                                params="['account.id': accountInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'transaction.label', default: 'Transaction')])}</g:link>

                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button"><g:actionSubmit class="save" action="update"
                                                 value="${message(code: 'default.button.update.label', default: 'Update')}"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete"
                                                 value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                                 onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
