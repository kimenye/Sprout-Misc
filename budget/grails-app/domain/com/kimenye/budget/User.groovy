package com.kimenye.budget

class User extends SecUser {

    Profile profile
    static constraints = {
        profile(nullable: true, unique: true)
    }

    String toString() {
        return "${username}"
    }
}
