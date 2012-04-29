package com.kimenye.budget

class Plan {

    static constraints = {
        planStartDate(validator: { start, plan ->
            return start <= plan.planEndDate
        })
        planName(blank:false, nullable:false)
        inActive(nullable: true)
        planType(inList: [PLAN_TYPE_BUDGET,PLAN_TYPE_CARD_REPAYMENT])
        owner(nullable: false)

    }


    static hasMany = [transactions : Transaction]
    static belongsTo = [baseCurrency : Currency, defaultAccount : Account, owner: User]

    String planName
    Date dateCreated
    Date lastUpdated
    Date planStartDate
    Date planEndDate
    Boolean inActive
    String planType

    String toString() {
        return "${planName}"
    }

    final static PLAN_TYPE_BUDGET = 'Budget'
    final static PLAN_TYPE_CARD_REPAYMENT = 'Card Repayment'
}
