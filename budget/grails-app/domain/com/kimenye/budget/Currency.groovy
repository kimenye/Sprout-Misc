package com.kimenye.budget

class Currency {

    static constraints = {
        currencyName(blank:false)
        currencySymbol(blank: false)
        inActive(nullable: true)
    }

    String currencyName
    String currencySymbol
    Boolean inActive

    static DEF = "AUD"

    String toString() {
        return "${currencySymbol}"
    }
}
