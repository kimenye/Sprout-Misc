

<%@ page import="com.kimenye.budget.Plan" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'plan.label', default: 'Plan')}" />
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
            <g:hasErrors bean="${planInstance}">
            <div class="errors">
                <g:renderErrors bean="${planInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="planStartDate"><g:message code="plan.planStartDate.label" default="Plan Start Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: planInstance, field: 'planStartDate', 'errors')}">
                                    <g:datePicker name="planStartDate" precision="day" value="${planInstance?.planStartDate}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="planName"><g:message code="plan.planName.label" default="Plan Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: planInstance, field: 'planName', 'errors')}">
                                    <g:textField name="planName" value="${planInstance?.planName}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="inActive"><g:message code="plan.inActive.label" default="In Active" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: planInstance, field: 'inActive', 'errors')}">
                                    <g:checkBox name="inActive" value="${planInstance?.inActive}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="planType"><g:message code="plan.planType.label" default="Plan Type" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: planInstance, field: 'planType', 'errors')}">
                                    <g:select name="planType" from="${planInstance.constraints.planType.inList}" value="${planInstance?.planType}" valueMessagePrefix="plan.planType"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="baseCurrency"><g:message code="plan.baseCurrency.label" default="Base Currency" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: planInstance, field: 'baseCurrency', 'errors')}">
                                    <g:select name="baseCurrency.id" from="${com.kimenye.budget.Currency.list()}" optionKey="id" value="${planInstance?.baseCurrency?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="defaultAccount"><g:message code="plan.defaultAccount.label" default="Default Account" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: planInstance, field: 'defaultAccount', 'errors')}">
                                    <g:select name="defaultAccount.id" from="${com.kimenye.budget.Account.list()}" optionKey="id" value="${planInstance?.defaultAccount?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="planEndDate"><g:message code="plan.planEndDate.label" default="Plan End Date" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: planInstance, field: 'planEndDate', 'errors')}">
                                    <g:datePicker name="planEndDate" precision="day" value="${planInstance?.planEndDate}"  />
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
