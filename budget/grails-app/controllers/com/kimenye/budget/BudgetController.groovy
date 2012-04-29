package com.kimenye.budget

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

class BudgetController extends BaseController {

    def budgetViewService
    def springSecurityService

    @Secured(['ROLE_USER', 'IS_AUTHENTICATED_FULLY'])
    def show = {
        def view = budgetViewService.getPlan(Plan.get(1))

        [view : view]
    }

    def distribution = {
        def view = budgetViewService.getDistribution(Plan.get(1))

        [view : view]
    }

    def timeline = {

        def plan = Plan.get(1)
//        def includeCredit = plan.planType ==
        def view = budgetViewService.getTimeline(plan)

        [view : view]
    }



//    def planDetailsAsJson = {
//        def view = budgetViewService.getPlan(Plan.get(params.id), true)
//
//        def data = [totalRecords: view.months.length, resultsList: view.months]
//        render data as JSON
//    }
}
