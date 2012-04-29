<sel:test name="Only admin can edit other peoples profiles">

    <g:render template="steps/loginuser"/>

    <sel:row line="clickAndWait|id=profile-link" />
    <sel:row line="verifyValue|id=preferredName|Test User" />
    <sel:row line="verifyValue|id=currency.id|2" />
    <sel:row command="open" target="${request.contextPath}/profile/edit/2" value="" />
    <sel:row line="verifyTextPresent|${message(code: 'default.not.allowed.to.update.other.users.objects.message', args: ['Profile'])}" />
    <sel:row line="clickAndWait|id=logout" />


    <g:render template="steps/loginadmin" />
    <sel:row command="open" target="${request.contextPath}/profile/edit/1" value="" />
    <sel:row line="verifyTextNotPresent|${message(code: 'default.not.allowed.to.update.other.users.objects.message', args: ['Profile'])}" />
    <sel:row line="clickAndWait|id=logout" />
</sel:test>
