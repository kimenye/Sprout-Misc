package com.kimenye.budget

import com.kimenye.budget.view.PlanView
import com.kimenye.budget.view.MonthlyView
import com.kimenye.budget.view.TransactionView
import com.kimenye.budget.view.MonthlyTransaction
import com.kimenye.budget.view.TimelineView
import com.kimenye.budget.view.DistributionView
import com.kimenye.budget.view.TransactionSummary
import com.kimenye.budget.view.AccountView
import com.kimenye.budget.view.CurrencyView

class BudgetViewService {

    static transactional = true
    def accountViewService

    def monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov","Dec"]

    /**
     *
     * @param plan
     * @return
     */
    DistributionView getDistribution(Plan plan) {
        def view = new DistributionView(planName: plan.planName, startDate: AccountViewService.getFirstDayOfMonth(plan.planStartDate),
                endDate: AccountViewService.getLastDayOfMonth(plan.planEndDate))

        def expenses = []
        def incomes = []
        def expenseTotal = 0
        def incomeTotal = 0
        plan.transactions.each{ trans ->
            if (!trans.inActive) {
                def amount = 0
                if (trans.recurrenceType == Transaction.TYPE_RECURRENCE_NONE) {
                    amount = trans.amount
                } else {
                    //def endDate = (trans.r)
                    def recurrentDates = AccountViewService.calculateRecurrentTransactions(trans.transactionDate, trans.recurrenceEndDate, plan.planEndDate)
//                    for(i in trans.transactionDate.getMonth()..trans.recurrenceEndDate.getMonth()) {
//                        amount += trans.amount
//                    }
                    recurrentDates.each {
                        amount += trans.amount
                    }
                }

                def summary = new TransactionSummary(transactionName: trans.transactionName, amount: amount)
                if (trans.transactionType  == Transaction.TYPE_INCOME) {
                    incomes.add(summary)
                    incomeTotal += amount
                }
                else if(trans.transactionType == Transaction.TYPE_EXPENSE)
                {
                    expenses.add(summary)
                    expenseTotal += amount
                }
                else {
                    //if we are dealing with a credit transfer
                    if (trans.toAccount.accountType == Account.TYPE_CREDIT) {
                        expenses.add(summary)
                        expenseTotal += amount
                    }
                }
            }
        }

        incomes.each { income ->
            income.percentage = income.amount / incomeTotal * 100
        }

        expenses.each { expense ->
            expense.percentage = expense.amount / expenseTotal * 100
        }

        view.income = incomes
        view.expenses = expenses

        return view
    }

    /**
     *
     * @param plan
     * @param includeCredit
     * @return
     */
    TimelineView getTimeline(Plan plan) {
        def view = new TimelineView(planName: plan.planName,
                startDate: AccountViewService.getFirstDayOfMonth(plan.planStartDate),
                endDate: AccountViewService.getLastDayOfMonth(plan.planEndDate))

        def openingBalance = 0
        def closingBalance = 0

        //TODO: What if different accounts have different currencies?

        def accounts = Account.findAll()
        accounts.each { account ->
            def accountView = accountViewService.getAccount(account, plan)

            openingBalance += accountView.openingBalance.amount
            closingBalance += accountView.closingBalance.amount

            view.accountBreakdown.add(accountView)


            accountView.transactions.each { total ->
                def monthlyTotal = view.totals.find {
                    it.monthDate == total.monthDate
                } as MonthlyTransaction

                if (!monthlyTotal) {
                    monthlyTotal = new MonthlyTransaction(monthDate: total.monthDate, runningTotal: new CurrencyView(amount: 0, baseCurrency: account.baseCurrency))
                    view.totals.add(monthlyTotal)
                }

                monthlyTotal.runningTotal.amount += total.runningTotal.amount
            }
        }

        view.openingBalance = new CurrencyView(amount: openingBalance, baseCurrency: plan.baseCurrency)
        view.closingBalance = new CurrencyView(amount: closingBalance, baseCurrency: plan.baseCurrency)

        return view
    }

    /**
     * Utility method to build a date.
     * Note that this uses the first of the month month to avoid crap like 31st feb
     * e.g. 31st Feb...
     *
     * @param month
     * @param year
     * @return
     */
    Date fromMonth(String month, int year) {
        def monthIdx = monthNames.indexOf(month)

        def date = 30
        if (monthIdx == 1)
            date = 28

        return new Date(month: monthIdx, year: year, date: date)
    }

    /**
     * Generate a plan view
     *
     * @param plan
     * @return
     */
    PlanView getPlan(Plan plan) {
        def view = new PlanView(planName: plan.planName, startDate: AccountViewService.getFirstDayOfMonth(plan.planStartDate),
                endDate: AccountViewService.getLastDayOfMonth(plan.planEndDate))

        view.months = AccountViewService.calculateRecurrentTransactions(view.startDate, view.endDate, null)


        plan.transactions.each { trans ->
            if (!trans.inActive) {
                def t = new TransactionView(transactionName: trans.transactionName, transactionType: trans.transactionType, account: trans.account.accountName)
                if (trans.toAccount) {
                    t.toAccount = trans.toAccount.accountName
                }

                def transViews = []
                if (trans.recurrenceType == Transaction.TYPE_RECURRENCE_NONE) {
                    t.monthlyTransactions = createTransactions(view.months.toList(),[trans.transactionDate],trans.amount, plan.baseCurrency)
                } else if (trans.recurrenceType == Transaction.TYPE_RECURRENCE_MONTHLY) {
                    def recurrentDates = AccountViewService.calculateRecurrentTransactions(trans.transactionDate, trans.recurrenceEndDate, plan.planEndDate)
                    t.monthlyTransactions = createTransactions(view.months.toList(),recurrentDates.toList(), trans.amount, plan.baseCurrency)
                }
                view.transactions.add(t)
            }
        }
        def totals = getTotals(view.months.toList(), view.transactions.toList(), plan.baseCurrency)
        view.incomeTotals = totals[0]
        view.expenseTotals = totals[1]
        view.transferTotals = totals[2]
        view.totals = totals[3]
        return view

////        println "Plan found ${plan}"
//        if (plan) {
//            def view = new PlanView(planName: plan.planName, startDate: plan.planStartDate, endDate: plan.planEndDate)
//
//            def startDate = plan.planStartDate
//            def endDate = plan.planEndDate
//
//
//            view.monthNames = monthNames
//
//            def monthTrans = []
//            plan.transactions.each { trans->
////                    println trans
//
//                if (!trans.inActive) {
//                    def t = new TransactionView(transactionName: trans.transactionName, transactionType: trans.transactionType, account: trans.account.accountName)
//                    if (trans.toAccount) {
//                        t.toAccount = trans.toAccount.accountName
//                    }
//
//                    def transViews = []
//                    if (trans.recurrenceType == 'None') {
//                        def monthIdx = trans.transactionDate.getMonth()
//                        transViews = createTransactions([monthIdx],trans.amount)
//                    }
//                    else if(trans.recurrenceType == 'Monthly') {
//                        //get start date
//                        def transStartMonth = trans.transactionDate.getMonth()
//
//                        //TODO: Fix indefinitely recurring months
//                        //we could have indefinite ending transactions
//                        def transEndMonth = plan.planEndDate.getMonth()
//                        if (trans.recurrenceEndDate)
//                            transEndMonth = trans.recurrenceEndDate.getMonth()
//
//                        def toInclude = []
//                        for(i in transStartMonth..transEndMonth) {
//                            toInclude << i
//                        }
//
//                        transViews = createTransactions(toInclude,trans.amount)
//                    }
//                    t.monthlyTransactions = transViews
//                    monthTrans.add(t)
//                }
//            }
//            view.transactions = monthTrans
//
//            //now add the totals
//            def totals = getTotals(view.transactions)
//            view.incomeTotals = totals[0]
//            view.expenseTotals = totals[1]
//            view.transferTotals = totals[2]
//            view.totals = totals[3]
//
//            return view
//        }
    }

    static MonthlyTransaction createTransaction(BigDecimal amount, Date monthDate, Currency currency) {
        return new MonthlyTransaction(amount: new CurrencyView(amount:amount, baseCurrency: currency), monthDate: monthDate)
    }

    static MonthlyTransaction[] createTransactions(ArrayList months, ArrayList include, BigDecimal amount, Currency currency) {
        def transactions = []
//        for(i in 0..11) {
//            if (include.find({ it == i})) {
//                transactions.add(new MonthlyTransaction(amount: amount, month: monthNames[i]))
//            }
//            else{
//                transactions.add(new MonthlyTransaction(amount: 0, month: monthNames[i]))
//            }
//        }
        months.each { month->
            if (include.find({ AccountViewService.isInMonth(month, it) })) {
                transactions.add(createTransaction(amount, month, currency))
            }
            else
            {
                transactions.add(createTransaction(0, month, currency))
            }
        }
        return transactions
    }

    /**
     * Get overall totals for the whole plan
     *
     * @param transactions
     * @return
     */
    MonthlyTransaction[][] getTotals(ArrayList toInclude, ArrayList transactions, Currency currency) {
//        def toInclude = []
//        for(i in 0..monthNames.size()) {
//            toInclude << i
//        }

        def incomeTotals = createTransactions(toInclude, toInclude, 0, currency)
        def expenseTotals = createTransactions(toInclude, toInclude, 0, currency)
        def transferTotals = createTransactions(toInclude, toInclude, 0, currency)

        def overallTotals = createTransactions(toInclude, toInclude, 0, currency)


        transactions.eachWithIndex { it, i ->
            it.monthlyTransactions.eachWithIndex { monthlyTrans, idx ->
                if(it.transactionType == Transaction.TYPE_INCOME)
                    incomeTotals[idx].amount.amount += monthlyTrans.amount.amount
                else if (it.transactionType == Transaction.TYPE_EXPENSE)
                    expenseTotals[idx].amount.amount += monthlyTrans.amount.amount
//                else
//                    transferTotals[idx].amount += monthlyTrans.amount
             }
        }

        incomeTotals.eachWithIndex { income, idx ->
            overallTotals[idx].amount.amount = income.amount.amount - expenseTotals[idx].amount.amount + transferTotals[idx].amount.amount
        }

        return [incomeTotals,expenseTotals,transferTotals, overallTotals]
    }
}
