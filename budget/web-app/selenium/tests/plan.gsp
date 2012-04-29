<sel:test name="View My Plans">

   <g:render template="steps/loginuser"/>
   <sel:row line="clickAndWait|id=plan-link" />
   <sel:row line="verifyTextPresent|Test Plan" />
   <sel:row line="clickAndWait|id=logout" />

    <g:render template="steps/loginadmin" />
    <sel:row line="clickAndWait|id=plan-link" />
   <sel:row line="verifyTextNotPresent|Test Plan" />
   <sel:row line="clickAndWait|id=logout" />

</sel:test>