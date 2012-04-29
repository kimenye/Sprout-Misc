package budget

import com.kimenye.budget.Currency
import com.kimenye.budget.User
import java.text.NumberFormat

class CurrencyTagLib {

    static namespace = "budget"
    def currencyService
    def springSecurityService

    def displayCurrency = { attributes ->

        def rawAmount = attributes.amount.amount
        def currency = attributes.amount.baseCurrency

        def toCurrency = attributes.toCurrencyCode
//        def toCurrency = attributes.
        if (!attributes.toCurrencyCode && springSecurityService.isLoggedIn()) {
            toCurrency = User.get(springSecurityService.principal.id).profile.currency.currencySymbol
        }
        else
            toCurrency = Currency.DEF
        def amount = rawAmount
        if (toCurrency != currency.currencySymbol) {

            amount = currencyService.getAmount(rawAmount,currency, Currency.findByCurrencySymbol(toCurrency))
            //out << formatNumber(currencyService.getAmount(rawAmount,currency, Currency.findByCurrencySymbol(toCurrency)), toCurrency)
        }
//        else
            //out << formatNumber(rawAmount,toCurrency)

        if (attributes.raw) {
            out << amount
        }
        else
            out << formatNumber(amount, toCurrency)

    }

    static String formatNumber(BigDecimal amount, String currencyCode) {
        NumberFormat nf = NumberFormat.getCurrencyInstance()
        nf.setCurrency(java.util.Currency.getInstance(currencyCode))
        return nf.format(amount)
    }
}
