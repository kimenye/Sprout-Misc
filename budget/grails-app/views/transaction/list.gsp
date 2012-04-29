
<%@ page import="com.kimenye.budget.Transaction" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'transaction.label', default: 'Transaction')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'transaction.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="transactionType" title="${message(code: 'transaction.transactionType.label', default: 'Transaction Type')}" />
                        
                            <g:sortableColumn property="recurrenceType" title="${message(code: 'transaction.recurrenceType.label', default: 'Recurrence Type')}" />
                        
                            <g:sortableColumn property="recurrenceEndDate" title="${message(code: 'transaction.recurrenceEndDate.label', default: 'Recurrence End Date')}" />
                        
                            <th><g:message code="transaction.toAccount.label" default="To Account" /></th>
                        
                            <th><g:message code="transaction.account.label" default="Account" /></th>

                            <th><g:message code="transaction.amount.label" default="Amount" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${transactionInstanceList}" status="i" var="transactionInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${transactionInstance.id}">${fieldValue(bean: transactionInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: transactionInstance, field: "transactionType")}</td>
                        
                            <td>${fieldValue(bean: transactionInstance, field: "recurrenceType")}</td>
                        
                            <td><g:formatDate date="${transactionInstance.recurrenceEndDate}" /></td>
                        
                            <td>${fieldValue(bean: transactionInstance, field: "toAccount")}</td>
                        
                            <td>${fieldValue(bean: transactionInstance, field: "account")}</td>

                            <td>${fieldValue(bean: transactionInstance, field: "amount")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${transactionInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
