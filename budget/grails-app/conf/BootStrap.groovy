import grails.util.Environment
import com.kimenye.budget.Currency
import com.kimenye.budget.Account
import com.kimenye.budget.ExchangeRate
import com.kimenye.budget.*
import java.text.SimpleDateFormat

class BootStrap {
    def springSecurityService

    def init = { servletContext ->
        switch(Environment.current) {
            case Environment.DEVELOPMENT:
            case Environment.TEST:
//                createCurrencyIfRequired()
//                createProfileIfRequired()
//                createAccountIfRequired()
//                createPlanIfRequired()
//                createTransactionsIfRequired()
                setup()
                break;
        }
    }
    def destroy = {
    }

    def setup() {
        //setup currencies
        def aud = Currency.findByCurrencySymbol("AUD") ?: new Currency(currencyName: "Australian Dollar", currencySymbol: "AUD").save()
        def kes = Currency.findByCurrencySymbol("KES") ?: new Currency(currencyName: "Kenyan Shilling", currencySymbol: "KES").save()

        //setup exchange rates
        def dollarToShilling = ExchangeRate.findByName("AUD to KES") ?: new ExchangeRate(baseCurrency: Currency.findByCurrencySymbol("AUD"), toCurrency: Currency.findByCurrencySymbol("KES"), name: "AUD to KES", rate: 95.22).save()
        def shillingToDollar = ExchangeRate.findByName("KES to AUD") ?: new ExchangeRate(baseCurrency: Currency.findByCurrencySymbol("KES"), toCurrency: Currency.findByCurrencySymbol("AUD"), name: "KES to AUD", rate: 0.0103).save()

        //setup roles
        def userRole = SecRole.findByAuthority("ROLE_USER") ?: new SecRole(authority: "ROLE_USER").save()
        def adminRole = SecRole.findByAuthority("ROLE_ADMIN") ?: new SecRole(authority: "ROLE_ADMIN").save()

        //setup users and profiles
        def testProfile = Profile.findByPreferredName("Test User") ?: new Profile(preferredName: "Test User", email: "testuser@gmail.com", currency: kes).save()
        def testUser = User.findByUsername("test") ?:
            new User(username: "test",
                    password: springSecurityService.encodePassword("password"), enabled: true, profile: testProfile).save()

        SecUserSecRole.create(testUser, userRole)

        def adminProfile = Profile.findByPreferredName("Admin User") ?: new Profile(preferredName: "Admin User", email: "adminuser@gmail.com", currency: aud).save()
        def adminUser = User.findByUsername("admin") ?:
            new User(username: "admin",
                    password: springSecurityService.encodePassword("password"), enabled: true, profile: adminProfile).save()

         SecUserSecRole.create(adminUser, adminRole)

        //setup accounts
        def anz = Account.findByAccountName("ANZ") ?: new Account(accountName: "ANZ", accountType: "Savings", cashCreditLimit: 0, creditLimit: 0, baseCurrency: aud, openingBalance: 30000, owner: testUser).save()
        def cba = Account.findByAccountName("CBA") ?: new Account(accountName: "CBA", accountType: "Credit", cashCreditLimit: 12500, creditLimit: 12500, baseCurrency: aud, openingBalance: 5000, owner: testUser).save()
        def saver = Account.findByAccountName("Online Saver") ?: new Account(accountName: "Online Saver", accountType: "Savings", baseCurrency: aud, openingBalance: 0, owner: testUser).save()

        //setup plan
        def plan = Plan.findByPlanName("Test Plan") ?: new Plan(planName: "Test Plan", planStartDate: new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-11"),
                planEndDate: new SimpleDateFormat("yyyy-MM-dd").parse("2012-01-11"),
                owner: testUser, defaultAccount: anz, baseCurrency: aud, planType: Plan.PLAN_TYPE_BUDGET).save()

        //setup transactions
        def purchase = Transaction.findByTransactionName("Buying iPad") ?: new Transaction(transactionName: "Buying iPad", amount: 500,
                transactionType: "Expense", transactionDate: new SimpleDateFormat("yyyy-MM-dd").parse("2011-02-11"), recurrenceType: "None", account: cba,
                plan: plan, transactionCurrency: aud).save()



        def repayment = Transaction.findByTransactionName("iPad Part 1") ?: new Transaction(transactionName: "iPad Part 1", amount: 250,
                transactionType: "Transfer", transactionDate: new SimpleDateFormat("yyyy-MM-dd").parse("2011-03-11"), recurrenceType: "None", account: anz,
                toAccount: cba, plan: plan, transactionCurrency: aud).save()


         def transfer = Transaction.findByTransactionName("Transfer to Savings") ?: new Transaction(transactionName: "Transfer to Savings", amount: 500,
                transactionType: "Transfer", transactionDate: new SimpleDateFormat("yyyy-MM-dd").parse("2011-02-01"), recurrenceType: Transaction.TYPE_RECURRENCE_MONTHLY, account: anz,
                toAccount: saver, plan: plan, transactionCurrency: aud).save()

    }

    void createTransactionsIfRequired() {
        def account = Account.findByAccountName("ANZ")
        def creditAccount = Account.findByAccountName("CBA")
        def savings = Account.findByAccountName("Online Saver")
        def plan = Plan.findByPlanName("Test Plan")
        def aud = Currency.findByCurrencyName("Australian Dollar")

        if (!Transaction.findByTransactionName("Buying iPad")) {
            def purchase = new Transaction(transactionName: "Buying iPad", amount: 500,
                transactionType: "Expense", transactionDate: new SimpleDateFormat("yyyy-MM-dd").parse("2011-02-11"), recurrenceType: "None", account: creditAccount,
                plan: plan, transactionCurrency: aud).save()
        }

        if (!Transaction.findByTransactionName("iPad Part 1")) {
            def repayment = new Transaction(transactionName: "iPad Part 1", amount: 250,
                transactionType: "Transfer", transactionDate: new SimpleDateFormat("yyyy-MM-dd").parse("2011-03-11"), recurrenceType: "None", account: account,
                toAccount: creditAccount, plan: plan, transactionCurrency: aud).save()
        }

        if (!Transaction.findByTransactionName("Transfer to Savings")) {
            def transfer = new Transaction(transactionName: "Transfer to Savings", amount: 500,
                transactionType: "Transfer", transactionDate: new SimpleDateFormat("yyyy-MM-dd").parse("2011-02-01"), recurrenceType: Transaction.TYPE_RECURRENCE_MONTHLY, account: account,
                toAccount: savings, plan: plan, transactionCurrency: aud).save()
        }
    }

    void createPlanIfRequired() {
        def account = Account.findByAccountName("CBA")
        def curr = Currency.findByCurrencyName("Australian Dollar")


        if (!Plan.findByPlanName("Test Plan")) {
            def plan = new Plan(planName: "Test Plan", planStartDate: new SimpleDateFormat("yyyy-MM-dd").parse("2011-01-11"),
                planEndDate: new SimpleDateFormat("yyyy-MM-dd").parse("2012-01-11"),
                defaultAccount: account, baseCurrency: curr, planType: Plan.PLAN_TYPE_BUDGET).save()
        }
    }

    void createProfileIfRequired() {
        if (!Profile.findByPreferredName("Test")) {
            def profile = new Profile(preferredName: "Test", currency: Currency.findByCurrencySymbol("KES")).save()
        }
    }

    void createCurrencyIfRequired() {
        if(!Currency.findByCurrencyName("Australian Dollar")) {
            def currency = new Currency(currencyName: "Australian Dollar", currencySymbol: "AUD").save()
        }

        if(!Currency.findByCurrencyName("Kenya Shilling")) {
            def currency = new Currency(currencyName: "Kenya Shilling", currencySymbol: "KES").save()
        }

        //set up exchange rates
        if (!ExchangeRate.findByName("AUD to KES")) {
            def dollarToShilling = new ExchangeRate(baseCurrency: Currency.findByCurrencySymbol("AUD"), toCurrency: Currency.findByCurrencySymbol("KES"), name: "AUD to KES", rate: 95.22).save()
        }

        if (!ExchangeRate.findByName("KES to AUD")) {
            def shillingToDollar = new ExchangeRate(baseCurrency: Currency.findByCurrencySymbol("KES"), toCurrency: Currency.findByCurrencySymbol("AUD"), name: "KES to AUD", rate: 0.0103).save()
        }
    }

    void createAccountIfRequired() {
        if(!Account.findByAccountName("ANZ")) {
            def account = new Account(accountName: "ANZ", accountType: "Savings", cashCreditLimit: 0, creditLimit: 0, baseCurrency: Currency.findByCurrencyName("Australian Dollar"), openingBalance: 30000).save()
        }

        if(!Account.findByAccountName("CBA")) {
            def cbaAccount = new Account(accountName: "CBA", accountType: "Credit", cashCreditLimit: 12500, creditLimit: 12500, baseCurrency: Currency.findByCurrencyName("Australian Dollar"), openingBalance: 5000).save()
        }

        if(!Account.findByAccountName("Online Saver")) {
            def account = new Account(accountName: "Online Saver", accountType: "Savings", baseCurrency: Currency.findByCurrencyName("Australian Dollar"), openingBalance: 0).save()
        }
    }
}
