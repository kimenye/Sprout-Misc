package com.kimenye.budget

import grails.plugins.springsecurity.ui.RegisterCommand
import org.codehaus.groovy.grails.plugins.springsecurity.NullSaltSource
import org.codehaus.groovy.grails.plugins.springsecurity.ui.RegistrationCode
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class RegisterController extends grails.plugins.springsecurity.ui.RegisterController {

    def index = {
		[command: new CustomRegisterCommand()]
	}

    def register = { CustomRegisterCommand command ->

		if (command.hasErrors()) {
			render view: 'index', model: [command: command]
			return
		}

		String salt = saltSource instanceof NullSaltSource ? null : command.username
		String password = springSecurityService.encodePassword(command.password, salt)
//		def user = lookupUserClass().newInstance(email: command.email, username: command.username,
//				password: password, accountLocked: true, enabled: true)

        def profile = new Profile(preferredName: command.preferredName, email: command.email, currency: Currency.findByCurrencySymbol(Currency.DEF)).save()
        def user = new User(username: command.username, password: password, accountLocked: true, enabled: true, profile: profile)

		if (!user.validate() || !user.save()) {
			// TODO
		}



		def registrationCode = new RegistrationCode(username: user.username).save()
		String url = generateLink('verifyRegistration', [t: registrationCode.token])

		def conf = SpringSecurityUtils.securityConfig
		def body = conf.ui.register.emailBody
		if (body.contains('$')) {
			body = evaluate(body, [user: user, url: url])
		}
		mailService.sendMail {
			to command.email
			from conf.ui.register.emailFrom
			subject conf.ui.register.emailSubject
			html body.toString()
		}

		render view: 'index', model: [emailSent: true]
	}

    def forgotPassword = {

		if (!request.post) {
			// show the form
			return
		}

		String username = params.username
		if (!username) {
			flash.error = message(code: 'spring.security.ui.forgotPassword.username.missing')
			return
		}

		def user = lookupUserClass().findByUsername(username)
		if (!user) {
			flash.error = message(code: 'spring.security.ui.forgotPassword.user.notFound')
			return
		}

		def registrationCode = new RegistrationCode(username: user.username).save()

		String url = generateLink('resetPassword', [t: registrationCode.token])

		def conf = SpringSecurityUtils.securityConfig
		def body = conf.ui.forgotPassword.emailBody
		if (body.contains('$')) {
			body = evaluate(body, [user: user, url: url])
		}
		mailService.sendMail {
			to user.profile.email
			from conf.ui.forgotPassword.emailFrom
			subject conf.ui.forgotPassword.emailSubject
			html body.toString()
		}

		[emailSent: true]
	}
}

class CustomRegisterCommand extends RegisterCommand {
    String preferredName

    static constraints = {
        preferredName(blank: false, email: false)
    }
}
