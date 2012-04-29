package com.kimenye.budget

class UserFilters {

    def springSecurityService
    def filters = {
        all(controller: '*', action: '*') {
//        all(uri: '/**') {
            before = {

            }
            after = { model ->
                if (model && springSecurityService.isLoggedIn()) {
//                    println "Currently logged in user is ${springSecurityService.principal.id}"

                    model['user'] = SecUser.get(springSecurityService.principal.id)
                }
//                println "User is ${model['user']}"
            }
            afterView = {

            }
        }
    }

}
