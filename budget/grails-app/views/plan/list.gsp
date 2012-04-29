<%@ page import="com.kimenye.budget.Plan" %>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="styled"/>
        %{--<g:set var="entityName" value="${message(code: 'plan.label', default: 'Plan')}"/>--}%
        <g:set var="entityName" value="${message(code: 'plan.label', default: 'Plan')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <table>
                    <thead>
                        <tr>

                            <g:sortableColumn property="id" title="${message(code: 'plan.id.label', default: 'Id')}" />

                            <g:sortableColumn property="planStartDate" title="${message(code: 'plan.planStartDate.label', default: 'Plan Start Date')}" />

                            <g:sortableColumn property="planName" title="${message(code: 'plan.planName.label', default: 'Plan Name')}" />

                            <g:sortableColumn property="inActive" title="${message(code: 'plan.inActive.label', default: 'In Active')}" />

                            <g:sortableColumn property="planType" title="${message(code: 'plan.planType.label', default: 'Plan Type')}" />

                            <th><g:message code="plan.baseCurrency.label" default="Base Currency" /></th>

                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${planInstanceList}" status="i" var="planInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                            <td><g:link action="show" id="${planInstance.id}">${fieldValue(bean: planInstance, field: "id")}</g:link></td>

                            <td><g:formatDate date="${planInstance.planStartDate}" /></td>

                            <td>${fieldValue(bean: planInstance, field: "planName")}</td>

                            <td><g:formatBoolean boolean="${planInstance.inActive}" /></td>

                            <td>${fieldValue(bean: planInstance, field: "planType")}</td>

                            <td>${fieldValue(bean: planInstance, field: "baseCurrency")}</td>

                        </tr>
                    </g:each>
                    </tbody>
                </table>
    </body>
</html>


%{--<%@ page import="com.kimenye.budget.Plan" %>--}%
%{--<html>--}%
    %{--<head>--}%
        %{--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />--}%
        %{--<meta name="layout" content="main" />--}%
        %{--<g:set var="entityName" value="${message(code: 'plan.label', default: 'Plan')}" />--}%
        %{--<title><g:message code="default.list.label" args="[entityName]" /></title>--}%
    %{--</head>--}%
    %{--<body>--}%
        %{--<div class="nav">--}%
            %{--<span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>--}%
            %{--<span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>--}%
        %{--</div>--}%
        %{--<div class="body">--}%
            %{--<h1><g:message code="default.list.label" args="[entityName]" /></h1>--}%
            %{--<g:if test="${flash.message}">--}%
            %{--<div class="message">${flash.message}</div>--}%
            %{--</g:if>--}%
            %{--<div class="list">--}%
                %{--<table>--}%
                    %{--<thead>--}%
                        %{--<tr>--}%
                        %{----}%
                            %{--<g:sortableColumn property="id" title="${message(code: 'plan.id.label', default: 'Id')}" />--}%
                        %{----}%
                            %{--<g:sortableColumn property="planStartDate" title="${message(code: 'plan.planStartDate.label', default: 'Plan Start Date')}" />--}%
                        %{----}%
                            %{--<g:sortableColumn property="planName" title="${message(code: 'plan.planName.label', default: 'Plan Name')}" />--}%
                        %{----}%
                            %{--<g:sortableColumn property="inActive" title="${message(code: 'plan.inActive.label', default: 'In Active')}" />--}%
                        %{----}%
                            %{--<g:sortableColumn property="planType" title="${message(code: 'plan.planType.label', default: 'Plan Type')}" />--}%
                        %{----}%
                            %{--<th><g:message code="plan.baseCurrency.label" default="Base Currency" /></th>--}%
                        %{----}%
                        %{--</tr>--}%
                    %{--</thead>--}%
                    %{--<tbody>--}%
                    %{--<g:each in="${planInstanceList}" status="i" var="planInstance">--}%
                        %{--<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">--}%
                        %{----}%
                            %{--<td><g:link action="show" id="${planInstance.id}">${fieldValue(bean: planInstance, field: "id")}</g:link></td>--}%
                        %{----}%
                            %{--<td><g:formatDate date="${planInstance.planStartDate}" /></td>--}%
                        %{----}%
                            %{--<td>${fieldValue(bean: planInstance, field: "planName")}</td>--}%
                        %{----}%
                            %{--<td><g:formatBoolean boolean="${planInstance.inActive}" /></td>--}%
                        %{----}%
                            %{--<td>${fieldValue(bean: planInstance, field: "planType")}</td>--}%
                        %{----}%
                            %{--<td>${fieldValue(bean: planInstance, field: "baseCurrency")}</td>--}%
                        %{----}%
                        %{--</tr>--}%
                    %{--</g:each>--}%
                    %{--</tbody>--}%
                %{--</table>--}%
            %{--</div>--}%
            %{--<div class="paginateButtons">--}%
                %{--<g:paginate total="${planInstanceTotal}" />--}%
            %{--</div>--}%
        %{--</div>--}%
    %{--</body>--}%
%{--</html>--}%
