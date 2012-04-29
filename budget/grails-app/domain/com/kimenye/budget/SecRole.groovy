package com.kimenye.budget

class SecRole {

    String authority

    static mapping = {
        cache true
    }

    static constraints = {
        authority blank: false, unique: true
    }

    static ADMIN_ROLE = 'ROLE_ADMIN'
    static USER_ROLE = 'ROLE_USER'
}
