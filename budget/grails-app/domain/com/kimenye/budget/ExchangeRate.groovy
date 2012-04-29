package com.kimenye.budget

class ExchangeRate {

    static constraints = {
        name(nullable: false)
        fromDate(nullable: true)
        toDate(nullable: true, blank:true)
        rate(nullable: false, blank: false)
        inActive(nullable:true)
    }

    String name
    Date fromDate
    Date toDate
    BigDecimal rate
    Boolean inActive
    static belongsTo = [baseCurrency: Currency, toCurrency: Currency]

}
