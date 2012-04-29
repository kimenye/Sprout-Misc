
<%@ page import="com.kimenye.budget.Transaction" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'transaction.label', default: 'Transaction')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="transaction.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: transactionInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="transaction.transactionType.label" default="Transaction Type" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: transactionInstance, field: "transactionType")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="transaction.recurrenceType.label" default="Recurrence Type" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: transactionInstance, field: "recurrenceType")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="transaction.recurrenceEndDate.label" default="Recurrence End Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${transactionInstance?.recurrenceEndDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="transaction.toAccount.label" default="To Account" /></td>
                            
                            <td valign="top" class="value"><g:link controller="account" action="show" id="${transactionInstance?.toAccount?.id}">${transactionInstance?.toAccount?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="transaction.account.label" default="Account" /></td>
                            
                            <td valign="top" class="value"><g:link controller="account" action="show" id="${transactionInstance?.account?.id}">${transactionInstance?.account?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="transaction.amount.label" default="Amount" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: transactionInstance, field: "amount")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="transaction.inActive.label" default="In Active" /></td>
                            
                            <td valign="top" class="value"><g:formatBoolean boolean="${transactionInstance?.inActive}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="transaction.plan.label" default="Plan" /></td>
                            
                            <td valign="top" class="value"><g:link controller="plan" action="show" id="${transactionInstance?.plan?.id}">${transactionInstance?.plan?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="transaction.transactionCurrency.label" default="Transaction Currency" /></td>
                            
                            <td valign="top" class="value"><g:link controller="currency" action="show" id="${transactionInstance?.transactionCurrency?.id}">${transactionInstance?.transactionCurrency?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="transaction.transactionDate.label" default="Transaction Date" /></td>
                            
                            <td valign="top" class="value"><g:formatDate date="${transactionInstance?.transactionDate}" /></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="transaction.transactionName.label" default="Transaction Name" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: transactionInstance, field: "transactionName")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${transactionInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
