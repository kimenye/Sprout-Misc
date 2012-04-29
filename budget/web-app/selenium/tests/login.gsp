<sel:test name="Test User Login">
    <g:render template="steps/loginuser" />
    <sel:row line="clickAndWait|id=logout" />

    %{--<g:set var="username" value="admin" scope="request" />--}%
    %{--<g:set var="password" value="password" scope="request" />--}%
    %{--<g:render template="steps/loginstep"/>--}%
    <g:render template="steps/loginadmin" />
    <sel:row line="clickAndWait|id=logout" />
</sel:test>