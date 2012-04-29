<%@ page import="com.kimenye.budget.Account" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'account.label', default: 'Account')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                               args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
            <tr>

                <g:sortableColumn property="id" title="${message(code: 'account.id.label', default: 'Id')}"/>

                <g:sortableColumn property="accountName"
                                  title="${message(code: 'account.accountName.label', default: 'Account Name')}"/>

                <g:sortableColumn property="accountType"
                                  title="${message(code: 'account.accountType.label', default: 'Account Type')}"/>

                <g:sortableColumn property="cashCreditLimit"
                                  title="${message(code: 'account.cashCreditLimit.label', default: 'Cash Credit Limit')}"/>

                <g:sortableColumn property="creditLimit"
                                  title="${message(code: 'account.creditLimit.label', default: 'Credit Limit')}"/>

                <g:sortableColumn property="openingBalance"
                                  title="${message(code: 'account.openingBalance.label', default: 'Opening Balance')}"/>

                <th></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${accountInstanceList}" status="i" var="accountInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show"
                                id="${accountInstance.id}">${fieldValue(bean: accountInstance, field: "id")}</g:link></td>

                    <td>${fieldValue(bean: accountInstance, field: "accountName")}</td>

                    <td>${fieldValue(bean: accountInstance, field: "accountType")}</td>

                    <td>${fieldValue(bean: accountInstance, field: "cashCreditLimit")}</td>

                    <td>${fieldValue(bean: accountInstance, field: "creditLimit")}</td>

                    <td>${fieldValue(bean: accountInstance, field: "openingBalance")}</td>

                    <td><g:link action="details" id="${accountInstance.id}">Details</g:link></td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${accountInstanceTotal}"/>
    </div>
</div>
</body>
</html>
