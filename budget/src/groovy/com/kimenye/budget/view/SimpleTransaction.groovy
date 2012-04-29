package com.kimenye.budget.view

/**
 * 
 * User: trevor
 * Date: 15/07/11
 * Time: 2:19 PM
 * 
 */
class SimpleTransaction {
    String transactionName
    String transactionType
    Date transactionDate
//    BigDecimal balance
//    BigDecimal debit
//    BigDecimal credit
    CurrencyView balance
    CurrencyView debit
    CurrencyView credit
    String toAccount
    String fromAccount
}
