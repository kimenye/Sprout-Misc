<sel:test name="Register new user">
<g:set var="username" value="kimenye" scope="request" />
<g:set var="preferredName" value="Trevor Kimenye" scope="request" />
<g:set var="password" value="p@ssw0rd" scope="request" />
<g:set var="email" value="kimenye@gmail.com" scope="request" />
<g:set var="password2" value="p@ssw0rd" scope="request" />
<g:render template="steps/registrationstep" />
<sel:row line="verifyTextPresent|Your account registration email was sent - check your mail!" />
</sel:test>