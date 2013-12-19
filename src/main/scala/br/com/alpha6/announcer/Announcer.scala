package br.com.alpha6.announcer

import br.com.alpha6.announcer.cmd._
import com.typesafe.scalalogging.slf4j.Logging
import net.mtgto.irc.{Bot, Client}
import net.mtgto.irc.event._

class Announcer(config: TwilioKeywordStrategy) extends Bot with Logging {

	val cmdRE = "!([\\w]+)\\s*(.*)".r

	override def onMessage(client: Client, message: Message) = {
		logger.debug(s"Processing message: ${message.text}!")

		val cmd = message.text match {
			case cmdRE(cmd, args) => CommandBuilder.build(cmd, args.split(" ")) 
			case _ => EmptyCommand
		}

		logger.debug(s"Message generated command ${cmd}")
		cmd.run(client, message)
	}
}