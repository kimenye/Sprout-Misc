<sel:row command="open" target="${request.contextPath}" value=""/>
<sel:row line="clickAndWait|id=login" />
<sel:row command="type" target="username" value="${username}" />
<sel:row command="type" target="password" value="${password}" />
<sel:row line="clickAndWait|css=input[type=submit]" />
<sel:row line="verifyTextPresent|Home"/>