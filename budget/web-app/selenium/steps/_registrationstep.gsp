<sel:row command="open" target="${request.contextPath}" value=""/>
<sel:row line="verifyTextPresent|${message(code:'spring.security.ui.login.register')}"/>
<sel:row line="clickAndWait|id=register-link" />
<sel:row command="type" target="username" value="${username}" />
<sel:row command="type" target="preferredName" value="${preferredName}" />
<sel:row command="type" target="email" value="${email}" />
<sel:row command="type" target="password" value="${password}" />
<sel:row command="type" target="password2" value="${password2}" />
<sel:row line="clickAndWait|id=create" />
