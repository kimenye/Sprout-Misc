package com.kimenye.budget

import grails.test.*
import com.kimenye.budget.view.AccountView
import com.kimenye.budget.view.SimpleTransaction
import java.text.SimpleDateFormat

class AccountViewServiceIntegrationTests extends GrailsUnitTestCase {
    def accountViewService
    def plan = Plan.findByPlanName("Test Plan")
    def cba = Account.findByAccountName("CBA")
    def anz = Account.findByAccountName("ANZ")

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testTrueOpeningBalance() {

        assertEquals(0-cba.openingBalance, accountViewService.getTrueOpeningBalance(cba))
        assertNotSame(cba.openingBalance, accountViewService.getTrueOpeningBalance(cba))

        assertEquals(anz.openingBalance, accountViewService.getTrueOpeningBalance(anz))
        assertNotSame(0-anz.openingBalance, accountViewService.getTrueOpeningBalance(anz))
    }

    void testBalance() {
        def view = accountViewService.getAccount(cba, plan)

        assertEquals(0-cba.openingBalance, view.openingBalance.amount)

        assertEquals(-5250, view.closingBalance.amount)
        assertNotSame(5250, view.closingBalance.amount)
    }

    void testEndMonthsBetween() {
        def startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-11")

        def endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2011-12-11")

        def dates = accountViewService.calculateEndMonthsBetween(startDate, endDate)

//        dates.each {
//            println it
//        }

        assertEquals(12, dates.length)
    }

    void testMonthlyTransactions() {
        def view = accountViewService.getAccount(cba, plan)

        assertNotNull(view.transactions)
        assertEquals(13, view.transactions.size())

//        view.transactions.each {
//            println "${it.monthDate}  ${it.amount}  ${it.runningTotal}"
//        }

        assertEquals(view.closingBalance.amount, view.transactions[12].runningTotal.amount)

    }

//    void testGetTimelineStartMonth() {
////        def aPlan = new Plan(planName: "Test", planStartDate: new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-11"))
//
//        def expectedFirstOfMonth = new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01")
//
//        assertEquals(expectedFirstOfMonth, accountViewService.getAccount(Account.findByAccountName("ANZ"), plan).planStartingMonthDate)
//    }

//    void testGetLastDayOfMonth() {
//        def startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-11")
//
//        def expectedOfTheMonth = new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-31")
//        assertEquals(expectedOfTheMonth, accountViewService.getLastDayOfMonth(startDate))
//
//        startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2011-02-11")
//        expectedOfTheMonth = new SimpleDateFormat("yyyy-MM-dd").parse("2011-02-28")
//        assertEquals(expectedOfTheMonth, accountViewService.getLastDayOfMonth(startDate))
//    }
//
//    void testIsInSameMonth() {
//        def startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-11")
//
//        def expectedOfTheMonth = new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-31")
//        def testDate = new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-31")
//
//        assertEquals(true, accountViewService.isInMonth(expectedOfTheMonth, testDate))
//
//        testDate = new SimpleDateFormat("yyyy-MM-dd").parse("2011-02-28")
//        assertEquals(false, accountViewService.isInMonth(expectedOfTheMonth, testDate))
//
//    }
//

//
//    void testAccountType() {
//        def anAccount = new Account(accountName: "Test")
//        def anotherAccount = new Account(accountName: "Another A/c")
//        def buyIpad = new Transaction(account: anAccount, amount: 300, transactionType: Transaction.TYPE_EXPENSE)
//
//        assertEquals(Transaction.TYPE_AC_DEBIT, accountViewService.getTransactionType(buyIpad, anAccount))
//
//        def transfer = new Transaction(account: anotherAccount, toAccount: anAccount, transactionType: Transaction.TYPE_TRANSFER)
//
//        assertEquals(Transaction.TYPE_AC_DEBIT, accountViewService.getTransactionType(transfer, anotherAccount))
//        assertEquals(Transaction.TYPE_AC_CREDIT, accountViewService.getTransactionType(transfer, anAccount))
//    }
//

//
//    void testNumberOfTransactions() {
//        def dates = accountViewService.calculateRecurrentTransactions(new Date(),new Date() + 60, null)
//
//        assertEquals(dates.length, 2)
//
//        dates = accountViewService.calculateRecurrentTransactions(new Date(),null, new Date() + 365)
//        assertEquals(dates.length, 12)
//    }
//
//    void testNumberOfTransactionsInAccount() {
//        def credit = Account.findByAccountName("CBA")
//
//        def view = accountViewService.getAccount(credit, plan)
//
//        assertEquals(2, view.simpleTransactions.size())
//
////        def ctFromTransactions = Transaction.findAllByAccount(credit)
//    }
}
