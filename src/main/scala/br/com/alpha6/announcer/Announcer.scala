package br.com.alpha6.announcer

import com.typesafe.scalalogging.slf4j.Logging
import net.mtgto.irc.{Bot, Client}
import net.mtgto.irc.event._

class Announcer(config: KeywordConfig) extends Bot with Logging {
	override def onMessage(client: Client, message: Message) = {
		logger.info(s"We've got a new message: ${message.text}!")
	}
}