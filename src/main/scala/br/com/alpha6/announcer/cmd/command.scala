package br.com.alpha6.announcer.cmd

import br.com.alpha6.announcer.sms.TwilioService
import net.mtgto.irc.Client
import net.mtgto.irc.event._

/**
 * A command is nothing more than a Function2[Client, Message, Unit]
 */
trait Command extends ((Client, Message) => Unit)

/**
 * Empty commands do nothing
 */
case object EmptyCommand extends Command {
	def apply(client: Client, message: Message) = {}
}

/**
 * A command that could not be parsed, presents an error message.
 */
case object UnknownCommand extends Command {
	def apply(client: Client, message: Message) = {
		client.sendNotice(message.channel, "Comando desconhecido :(. Digite !help para ver a ajuda!")
	}
}

/**
 * HelpCommand displays how to use the bot.
 */
case object HelpCommand extends Command {

	/**
	 * Each line of our help message
	 */
	val Help = List(
		"Comandos disponíveis:", 
		"!add <player> <telefone>: adiciona um jogador e seu número nos registros",
		"!mix <#canal>: convida os jogadores armazenados no registro para um mix no #canal",
		"!sms <player> <texto>: envia um sms para o player com o respectivo texto"
	)

	def apply(client: Client, message: Message) = {
		Help foreach { msg => client.sendNotice(message.channel, msg) }
	}
}

/**
 * Sends a SMS to every known player with a invite to a game channel
 */
case class MixCommand(mixChannel: String, numbers: Seq[String]) extends Command {
	def apply(client: Client, message: Message) = {
		client.sendNotice(message.channel, "Convidando " + numbers.size + " players para " + mixChannel +  " via SMS.")
	}
}

/**
 * Sends a SMS to a player
 */
case class SmsCommand(twilioService: TwilioService, phone: String, body: String) extends Command {
	def apply(client: Client, message: Message) = {
		client.sendNotice(message.channel, "Enviando SMS para " + phone + " com texto \"" + body + "\"")
	}
}

/**
 * Adds a new players into our database
 */
case class AddCommand(player: String, phone: String) extends Command {
	def apply(client: Client, message: Message) = {
		client.sendNotice(message.channel, "Adicionando número de " + player + "..")
	}
}

