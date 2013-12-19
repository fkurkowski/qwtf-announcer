package br.com.alpha6.announcer.cmd

import net.mtgto.irc.Client
import net.mtgto.irc.event._

trait Command {
	def run(client: Client, message: Message): Unit
}

case object EmptyCommand extends Command {
	def run(client: Client, message: Message) = {}
}

case object UnknownCommand extends Command {
	def run(client: Client, message: Message) = {
		client.sendNotice(message.channel, "Comando desconhecido :(. Digite !help para ver a ajuda!")
	}
}

case object HelpCommand extends Command {

	val Help = List(
		"Comandos disponíveis:", 
		"!add <player> <telefone>: adiciona um jogador e seu número nos registros",
		"!mix <#canal>: convida os jogadores armazenados no registro para um mix no #canal",
		"!sms <telefone> <texto>: envia um sms para o número com o respectivo texto"
	)

	def run(client: Client, message: Message) = {
		Help foreach { msg => client.sendNotice(message.channel, msg) }
	}
}

case class MixCommand(mixChannel: String, numbers: Seq[String]) extends Command {
	def run(client: Client, message: Message) = {
		client.sendNotice(message.channel, "Convidando " + numbers.size + " players para " + mixChannel +  " via SMS.")
	}
}

case class SmsCommand(phone: String, body: String) extends Command {
	def run(client: Client, message: Message) = {
		client.sendNotice(message.channel, "Enviando SMS para " + phone + " com texto \"" + body + "\"")
	}
}

case class AddCommand(player: String, phone: String) extends Command {
	def run(client: Client, message: Message) = {
		client.sendNotice(message.channel, "Adicionando número de " + player + "..")
	}
}

