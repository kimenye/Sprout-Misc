<html>
    <head>
        <title><g:layoutTitle default="Budget" /></title>
        %{--<link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />--}%
        <link rel="stylesheet" href="${resource(dir:'css', file:'budget.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body class="yui-skin-sam">
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>
        <div id="grailsLogo" class="logo"><a>Fancy Logo Here</a></div>
        <g:layoutBody />
    </body>
</html>
