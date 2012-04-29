package com.kimenye.budget

class InterestRate {

    static constraints = {
        interestRateType(inList: ['Purchases', 'Cash Advance'])
    }

    static belongsTo = [account: Account]

    String interestRateType
    BigDecimal annualRate
    BigDecimal dailyRate
    Date fromDate
    Date toDate
    Boolean inActive

    String toString() {

        return "${interestRateType} ${annualRate}%"
    }
}
