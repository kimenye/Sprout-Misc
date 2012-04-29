package com.kimenye.budget

class Transaction {

    static constraints = {
        transactionType(inList: [TYPE_INCOME,TYPE_EXPENSE,TYPE_TRANSFER])
//        recurrenceType(inList: ['None', 'Daily', 'Weekly', 'Monthly'])
        recurrenceType(inList: [TYPE_RECURRENCE_NONE,TYPE_RECURRENCE_MONTHLY])
        recurrenceEndDate(nullable: true,blank: true)
        toAccount(nullable: true)
        account(nullable: false)
        inActive(nullable: true)
        amount(nullable: false, blank: false)
    }

    String transactionName
    String transactionType
    String recurrenceType
    BigDecimal amount
    static belongsTo = [transactionCurrency : Currency, plan: Plan, account: Account, toAccount: Account]

    Date transactionDate
    Date recurrenceEndDate
    Boolean inActive

    static final TYPE_INCOME = 'Income'
    static final TYPE_EXPENSE = 'Expense'
    static final TYPE_TRANSFER = 'Transfer'
    static final TYPE_CREDIT_TRANSFER = 'Credit Transfer'

    static final TYPE_RECURRENCE_NONE = 'None'
    static final TYPE_RECURRENCE_MONTHLY = 'Monthly'

    static final TYPE_AC_DEBIT = 'Debit'
    static final TYPE_AC_CREDIT = 'Credit'


    String toString() {
        return "${transactionName}"
    }
}
