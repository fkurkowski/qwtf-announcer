package br.com.alpha6.announcer

import net.mtgto.irc.config.BotConfig

case class TwilioKeywordStrategy(accountSid: String, authToken: String) 
	extends BotConfig