package com.kimenye.budget


class Account {
    static constraints = {
        accountName(blank:false, unique: true)  //can only be unique for a given user
        accountType(inList: [TYPE_SAVINGS, TYPE_CREDIT])
        cashCreditLimit(blank: true, nullable: true)
        creditLimit(blank: true, nullable: true)
        openingBalance(blank:true, nullable: true)
        interestFreePeriodDays(blank:true, nullable: true)
        inActive(blank: true, nullable: true)
        owner(nullable: false)
    }

    String accountName
    String accountType
    static belongsTo = [baseCurrency: Currency, owner: User]
    static hasMany = [interestRates: InterestRate, transactions: Transaction]
    BigDecimal cashCreditLimit
    BigDecimal creditLimit
    BigDecimal openingBalance
    BigDecimal interestFreePeriodDays
    Boolean inActive

    String toString() {
        return "${accountName}"
    }

    final static TYPE_SAVINGS = 'Savings'
    final static TYPE_CREDIT = 'Credit'
}
