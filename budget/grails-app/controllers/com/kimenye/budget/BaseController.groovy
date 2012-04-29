package com.kimenye.budget

class BaseController {

    def springSecurityService

    protected User currentUser() {
        return User.get(springSecurityService.principal.id)
    }

    protected boolean currentUserIsAdmin() {
        def user = currentUser()

        println "User is ${user}"
        if (user)
        {
            def role = SecRole.findByAuthority('ROLE_ADMIN')
            println "Role is ${role}"
            def userRole = SecUserSecRole.get(user.id,role.id)

            return (userRole != null)
        }
        return false
    }
}
