package com.kimenye.budget

class AccountController {
    def scaffold = true
    def accountViewService

    def details = {
        def account = Account.get(params.id)

        def view = accountViewService.getAccount(account, Plan.get(1))

        [view: view]
    }
}
