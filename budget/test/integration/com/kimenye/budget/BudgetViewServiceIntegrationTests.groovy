package com.kimenye.budget

import grails.test.*
import com.kimenye.budget.view.AccountView
import java.text.SimpleDateFormat

class BudgetViewServiceIntegrationTests extends GrailsUnitTestCase {
    def budgetViewService
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

    void testOverallBalance() {
        def view = budgetViewService.getTimeline(plan)

        assertNotNull(view)
        assertEquals(25000, view.openingBalance.amount)
        assertEquals(24500, view.closingBalance.amount)
    }

    void testAccountClosingBalance() {


        def view = budgetViewService.getTimeline(plan)

        view.accountBreakdown.each {
            if (it.accountName == "CBA")
            {
                assertEquals(-5250, it.closingBalance.amount)
            }
        }
    }

    void testOverallTotals() {

        def view = budgetViewService.getTimeline(plan)

        assertNotNull(view.totals)
        assertEquals(13,view.totals.size())
        assertNotNull(view.accountBreakdown)
        assertEquals(3, view.accountBreakdown.size())
    }

    void testBudgetView() {
        def view = budgetViewService.getPlan(plan)

        assertNotNull(view.months)
        assertEquals(13, view.months.size())
    }

    void testCreateTransactions() {
        def startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-01")
        def endDate = new SimpleDateFormat("yyyy-MM-dd").parse("2012-01-31")
        def aTrans = new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-11")

        def toInclude = [aTrans]

        def transactions = BudgetViewService.createTransactions(AccountViewService.calculateRecurrentTransactions(startDate, endDate, null).toList(),
            toInclude, 1000, Currency.findByCurrencySymbol("AUD"))

        assertNotNull(transactions)
        assertEquals(1000, transactions.first().amount.amount)

    }
}
