package br.com.alpha6.announcer.sms

trait TwilioService {
	def sms(phone: String, text: String)
}

class FinagleTwilioService(accountSid: String, authToken: String) extends TwilioService {
	def sms(phone: String, text: String) = {}	
}