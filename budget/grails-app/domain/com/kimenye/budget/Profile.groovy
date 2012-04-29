package com.kimenye.budget

class Profile {

    static constraints = {
        email(email: true, blank: false)
    }

    String preferredName
    String email
    static belongsTo = [currency : Currency]
}
