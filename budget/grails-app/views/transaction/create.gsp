

<%@ page import="com.kimenye.budget.Transaction" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'transaction.label', default: 'Transaction')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${transactionInstance}">
            <div class="errors">
                <g:renderErrors bean="${transactionInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="transactionType"><g:message code="transaction.transactionType.label" default="Transaction Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionInstance, field: 'transactionType', 'errors')}">
                                    <g:select name="transactionType" from="${transactionInstance.constraints.transactionType.inList}" value="${transactionInstance?.transactionType}" valueMessagePrefix="transaction.transactionType"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="recurrenceType"><g:message code="transaction.recurrenceType.label" default="Recurrence Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionInstance, field: 'recurrenceType', 'errors')}">
                                    <g:select name="recurrenceType" from="${transactionInstance.constraints.recurrenceType.inList}" value="${transactionInstance?.recurrenceType}" valueMessagePrefix="transaction.recurrenceType"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="recurrenceEndDate"><g:message code="transaction.recurrenceEndDate.label" default="Recurrence End Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionInstance, field: 'recurrenceEndDate', 'errors')}">
                                    <g:datePicker name="recurrenceEndDate" precision="day" value="${transactionInstance?.recurrenceEndDate}" default="none" noSelection="['': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="toAccount"><g:message code="transaction.toAccount.label" default="To Account" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionInstance, field: 'toAccount', 'errors')}">
                                    <g:select name="toAccount.id" from="${com.kimenye.budget.Account.list()}" optionKey="id" value="${transactionInstance?.toAccount?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="account"><g:message code="transaction.account.label" default="Account" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionInstance, field: 'account', 'errors')}">
                                    <g:select name="account.id" from="${com.kimenye.budget.Account.list()}" optionKey="id" value="${transactionInstance?.account?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="amount"><g:message code="transaction.amount.label" default="Amount" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionInstance, field: 'amount', 'errors')}">
                                    <g:textField name="amount" value="${fieldValue(bean: transactionInstance, field: 'amount')}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="inActive"><g:message code="transaction.inActive.label" default="In Active" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionInstance, field: 'inActive', 'errors')}">
                                    <g:checkBox name="inActive" value="${transactionInstance?.inActive}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="plan"><g:message code="transaction.plan.label" default="Plan" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionInstance, field: 'plan', 'errors')}">
                                    <g:select name="plan.id" from="${com.kimenye.budget.Plan.list()}" optionKey="id" value="${transactionInstance?.plan?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="transactionCurrency"><g:message code="transaction.transactionCurrency.label" default="Transaction Currency" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionInstance, field: 'transactionCurrency', 'errors')}">
                                    <g:select name="transactionCurrency.id" from="${com.kimenye.budget.Currency.list()}" optionKey="id" value="${transactionInstance?.transactionCurrency?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="transactionDate"><g:message code="transaction.transactionDate.label" default="Transaction Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionInstance, field: 'transactionDate', 'errors')}">
                                    <g:datePicker name="transactionDate" precision="day" value="${transactionInstance?.transactionDate}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="transactionName"><g:message code="transaction.transactionName.label" default="Transaction Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: transactionInstance, field: 'transactionName', 'errors')}">
                                    <g:textField name="transactionName" value="${transactionInstance?.transactionName}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
