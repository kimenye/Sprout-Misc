package com.kimenye.budget

class CurrencyService {

    static transactional = true

    BigDecimal getAmount(BigDecimal baseAmount, Currency from, Currency to) {
        return getAmount(baseAmount, from, to, new Date(), new Date())
    }

    BigDecimal getAmount(BigDecimal baseAmount, Currency from, Currency to, Date dFrom, Date dTo) {
        def exchangeRate = findAppropriateExchangeRate(from, to, dFrom, dTo)
        return baseAmount * ((exchangeRate) ? exchangeRate.rate : 1.0)
    }

    /**
     * Get the appropriate exchange rate
     *
     * TODO: deal with dates
     *
     * @param from
     * @param to
     * @param dFrom
     * @param dTo
     * @return
     */
    private ExchangeRate findAppropriateExchangeRate(Currency from, Currency to, Date dFrom, Date dTo) {
//        def exchangeRates = ExchangeRate.findAllByBaseCurrencyAndToCurrency(from, to)
//        def bestRate = exchangeRates.find {
//
//        }
        def rate = ExchangeRate.createCriteria().get {
            and {
                eq('baseCurrency', from)
                eq('toCurrency', to)
            }
        }
        return rate
    }
}
