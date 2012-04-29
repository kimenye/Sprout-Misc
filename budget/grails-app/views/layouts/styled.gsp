<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Budget" /></title>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'reset-fonts-grids.css')}"/>

        <g:javascript library="application" />
        <g:javascript library="jquery" plugin="jquery"/>
        <jqui:resources />
        <jqDT:resources />
        <g:javascript library="jquery.dataTables.grouping" />
        <jqplot:resources/>
        <g:layoutHead />
        %{--<gui:resources components="['tabView','dataTable']" />--}%


        <link rel="stylesheet" href="${resource(dir: 'css', file: 'budget.css')}"/>
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'tables.css')}"/>

    </head>
    <body>
        <div id="doc3" class="yui-t5 yui-skin-sam">
            <div id="hd">
                Fancy Logo goes here
            </div>
            <div id="bd"><!-- start body -->
                <div id="yui-main">
                    <div class="yui-b">

                        <g:if test="${flash.message}">
                            <div class="flash">
                                ${flash.message}
                            </div>
                        </g:if>
                        <div class="nav">
                            <sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_USER">
                                <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
                                <span class="menuButton"><g:link class="create" controller="plan" action="list" elementId="plan-link">Plans</g:link></span>
                                %{--<span class="menuButton"><g:link class="create" controller="budget" action="show">Budget</g:link></span>--}%
                                %{--<span class="menuButton"><g:link class="create" controller="budget" action="timeline">Timeline</g:link></span>--}%
                                %{--<span class="menuButton"><g:link class="create" controller="budget" action="distribution">Distribution</g:link></span>--}%
                                %{--<span class="menuButton"><g:link class="create" controller="transaction" action="list">Transactions</g:link></span>--}%
                                %{--<span class="menuButton"><g:link class="create" controller="account" action="list">Accounts</g:link></span>--}%
                                <span class="menuButton"><g:link class="create" controller="profile" action="edit" id="${user.id}" elementId="profile-link">Profile</g:link></span>
                                <span class="menuButton"><g:link class="create" controller="logout" elementId="logout">Log out ${user.profile.preferredName}</g:link></span>
                            </sec:ifAnyGranted>
                            <sec:ifNotLoggedIn>
                                <span><g:link controller="login" action="auth" elementId="login">Login</g:link></span>
                                <span><g:link controller="register" elementId="register-link"><g:message code="spring.security.ui.login.register"/></g:link></span>
                            </sec:ifNotLoggedIn>
                        </div>
                        <g:layoutBody/>
                    </div>
                </div>
                <div class="yui-b">
                    My Sidebar
                </div>
            </div>
            <div id="ft">
                <div id="footerText">
                    App Name. Copyright &copy; 2011 Kimenye Integrated Software Solutions
                </div>
            </div>
        </div>
    </body>
</html>