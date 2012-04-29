package com.kimenye.budget

import grails.converters.JSON

class PlanController extends BaseController {
//    def scaffold = true

    def index = {

    }

    def begin = {
        //if there is a
//        def planInstance = Plan.get(params.id)

        def planInstance = Plan.get(1)
        if (!planInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'plan.label', default: 'Plan'), params.id])}"
            redirect(action: "list")
        }
        else {
            [planInstance: planInstance]
        }
    }

    def transactionsAsJson = {
        def planInstance = Plan.get(params.id)
        if(planInstance) {
            def data = [totalRecords: planInstance.transactions.count(), resultsList: planInstance.transactions.toArray()]
            render data as JSON
        }
    }

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

//    def index = {
//        redirect(action: "list", params: params)
//    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
//        [planInstanceList: Plan.list(params), planInstanceTotal: Plan.count()]
//        Plan.list
        [planInstanceList: Plan.findAllByOwner(currentUser())]
    }

    def create = {
        def endOfThisYear = new Date()
        endOfThisYear.setMonth(12)
        endOfThisYear.setDate(31)
        def planInstance = new Plan(planEndDate: endOfThisYear)
        planInstance.properties = params
        return [planInstance: planInstance]
    }

    def save = {
        def planInstance = new Plan(params)
        if (planInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'plan.label', default: 'Plan'), planInstance.id])}"
            redirect(action: "show", id: planInstance.id)
        }
        else {
            render(view: "create", model: [planInstance: planInstance])
        }
    }

    def show = {
        def planInstance = Plan.get(params.id)
        if (!planInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'plan.label', default: 'Plan'), params.id])}"
            redirect(action: "list")
        }
        else {
            [planInstance: planInstance]
        }
    }

    def edit = {
        def planInstance = Plan.get(params.id)
        if (!planInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'plan.label', default: 'Plan'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [planInstance: planInstance]
        }
    }

    def update = {
        def planInstance = Plan.get(params.id)
        if (planInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (planInstance.version > version) {

                    planInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'plan.label', default: 'Plan')] as Object[], "Another user has updated this Plan while you were editing")
                    render(view: "edit", model: [planInstance: planInstance])
                    return
                }
            }
            planInstance.properties = params
            if (!planInstance.hasErrors() && planInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'plan.label', default: 'Plan'), planInstance.id])}"
                redirect(action: "show", id: planInstance.id)
            }
            else {
                render(view: "edit", model: [planInstance: planInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'plan.label', default: 'Plan'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def planInstance = Plan.get(params.id)
        if (planInstance) {
            try {
                planInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'plan.label', default: 'Plan'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'plan.label', default: 'Plan'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'plan.label', default: 'Plan'), params.id])}"
            redirect(action: "list")
        }
    }
}
