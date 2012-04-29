package com.kimenye.budget

import grails.test.*

class CurrencyServiceIntegrationTests extends GrailsUnitTestCase {
    def currencyService
    def kshs = Currency.findByCurrencySymbol("KES")
    def aud = Currency.findByCurrencySymbol("AUD")

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testAudToKshs () {
        def baseAmount = 1.0
        def expectedAmount = 95.22

        def actualAmount = currencyService.getAmount(baseAmount, aud, kshs)
        assertEquals(expectedAmount, actualAmount)
    }

    void testKshsToAud() {
        def baseAmount = 1.0
        def expectedAmount = 0.0103

        def actualAmount = currencyService.getAmount(baseAmount, kshs, aud)
        assertEquals(expectedAmount, actualAmount)
    }
}
