package com.kimenye.budget

import com.kimenye.budget.view.AccountView
import com.kimenye.budget.view.SimpleTransaction
import groovy.time.TimeCategory
import com.kimenye.budget.view.MonthlyTransaction
import com.kimenye.budget.view.CurrencyView
import com.kimenye.budget.view.TransactionView

class AccountViewService {

    static transactional = true

    AccountView getAccount(Account account, Plan plan) {
        def view = new AccountView(accountName: account.accountName, accountType: account.accountType,
            openingBalance: new CurrencyView(amount: getTrueOpeningBalance(account), baseCurrency: account.baseCurrency),
            closingBalance: new CurrencyView(amount: getTrueOpeningBalance(account), baseCurrency: account.baseCurrency),
            startDate: getFirstDayOfMonth(plan.planStartDate),
            endDate: getLastDayOfMonth(plan.planEndDate),
            planName: plan.planName)

        def transactions = Transaction.findAllByAccountOrToAccount(account,account) as Transaction[]

        view.simpleTransactions = []
        //def plan

//        println "processing ${account}"
        transactions.each { trans ->
            if (trans.recurrenceType == Transaction.TYPE_RECURRENCE_MONTHLY) {
                def transactionDates = calculateRecurrentTransactions(trans.transactionDate, trans.recurrenceEndDate, trans.plan.planEndDate)
                transactionDates.each {
                    view.simpleTransactions.add(getTransaction(trans,it,account))
                }
            }
            else
            {
                def t = getTransaction(trans,trans.transactionDate,account)
//                println "Added : ${t.transactionName} Debit: ${t.debit} Credit: ${t.credit}"
                view.simpleTransactions.add(t)
            }
            if (!plan)
                plan = trans.plan
        }

        if (view.simpleTransactions) {
            view.simpleTransactions.sort {
                it.transactionDate
            }

            //calculate the balance
            def runningTotal = getTrueOpeningBalance(account)
//            println "Before ${runningTotal}"
            view.simpleTransactions.each {
                runningTotal = runningTotal + it.credit.amount - it.debit.amount
                it.balance.amount = runningTotal
            }
//            println "After ${runningTotal}"
            view.closingBalance.amount = runningTotal


            def monthEndDates = calculateEndMonthsBetween(plan.planStartDate, plan.planEndDate)
            runningTotal = getTrueOpeningBalance(account)
            monthEndDates.each {  month ->
                //go through all the transaction
                def monthTransaction = new MonthlyTransaction(monthDate: month, amount:new CurrencyView(amount:0, baseCurrency: plan.baseCurrency), runningTotal: new CurrencyView(amount:runningTotal,baseCurrency: plan.baseCurrency))
                view.simpleTransactions.each { trans ->
                    if (isInMonth(month, trans.transactionDate)) {
                        monthTransaction.amount.amount = monthTransaction.amount.amount + trans.credit.amount - trans.debit.amount
                        monthTransaction.runningTotal.amount = monthTransaction.runningTotal.amount + monthTransaction.amount.amount
                        runningTotal = monthTransaction.runningTotal.amount
                    }
                }

                view.transactions.add(monthTransaction)
            }
        }


        return view
    }

    BigDecimal getTrueOpeningBalance(Account account) {
        if (account.accountType == Account.TYPE_CREDIT)
            return 0 - account.openingBalance
        else if (account.accountType == Account.TYPE_SAVINGS)
            return account.openingBalance
        else
            return 0
    }

    /**
     * Calculate the number of months between a start date, and two optional end dates
     *
     * @param startDate
     * @param reccurrenceEndDate
     * @param planEndDate
     * @return
     */
    static Date[] calculateRecurrentTransactions (Date startDate, Date reccurrenceEndDate, Date planEndDate) {
        def dates = []

        def endDate = reccurrenceEndDate

        if (!endDate) {
            endDate =  planEndDate
        }

        def transactionDate = startDate

        dates.add(transactionDate)
        use(TimeCategory) {
            transactionDate = transactionDate + 1.month
            while(transactionDate < endDate) {
                dates.add(transactionDate)
                transactionDate = transactionDate + 1.month
            }
        }
        return dates
    }

    /**
     *
     * @param start
     * @param end
     * @return
     */
    static Date[] calculateEndMonthsBetween(Date start, Date end) {
        def dates = calculateRecurrentTransactions(getLastDayOfMonth(start),
            getLastDayOfMonth(end), null)

        def datesLastDays = []
        dates.each {
            datesLastDays.add(getLastDayOfMonth(it))
        }

        return datesLastDays;
    }

    /**
     * Is a specified test date within the same month?
     *
     * @param monthDate
     * @param testDate
     * @return
     */
    static boolean isInMonth(Date monthDate, Date testDate) {
        return (testDate >= getFirstDayOfMonth(monthDate) && testDate <= getLastDayOfMonth(monthDate))
    }

    /**
     * Get the first day of the month
     *
     * @param date
     * @return
     */
    static Date getFirstDayOfMonth(Date date) {
        def c = new GregorianCalendar()
        c.setTime(date)
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH))
        c.set(Calendar.HOUR, 0)
        c.set(Calendar.MINUTE, 0)
        c.set(Calendar.SECOND, 0)
        date = c.time
        return date
    }

    /**
     * Get the last day of a month
     *
     * @param date
     * @return
     */
    static Date getLastDayOfMonth(Date date) {
        def c = new GregorianCalendar()
        c.setTime(date)
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH))
        date = c.time
        return date;
    }

    /**
     * Finds out whether a transaction is a debit or a credit for the
     * respective account. Does not validate if the account is truly
     * active in the transaction
     *
     * @param transaction
     * @param account
     * @return
     */
    static String getTransactionType(Transaction transaction, Account account) {
//        println "${transaction.account.id} ${account.id}"
//        println "Account is ${account} ${account.id} and Transaction is ${transaction} ${transaction.account} ${transaction.account.id}"
        if (transaction.account.accountName == account.accountName) {
            if (transaction.transactionType == Transaction.TYPE_INCOME) {
                return Transaction.TYPE_AC_CREDIT
            }
            else
            {
                return Transaction.TYPE_AC_DEBIT
            }
        }
        else {
//            if (transaction.transactionType == Transaction.TYPE_CREDIT_TRANSFER || Transaction.TYPE_TRANSFER)
            return Transaction.TYPE_AC_CREDIT
        }
    }

    static SimpleTransaction getTransaction(Transaction transaction, Date transactionDate, Account account) {
         return new SimpleTransaction(transactionName: transaction.transactionName, transactionDate: transactionDate, transactionType: transaction.transactionType,
                 debit: new CurrencyView(amount:(getTransactionType(transaction,account) == Transaction.TYPE_AC_DEBIT)? transaction.amount : 0, baseCurrency: account.baseCurrency),
                 credit: new CurrencyView(amount:(getTransactionType(transaction, account) == Transaction.TYPE_AC_CREDIT)? transaction.amount : 0, baseCurrency: account.baseCurrency),
                 balance: new CurrencyView(amount: 0, baseCurrency: account.baseCurrency),
                 fromAccount: transaction.account.id, toAccount: (transaction.toAccount)? transaction.toAccount.id : null )
    }
}
