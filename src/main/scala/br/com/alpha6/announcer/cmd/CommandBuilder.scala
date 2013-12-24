package br.com.alpha6.announcer.cmd

import br.com.alpha6.announcer.sms.TwilioService

object CommandBuilder {
	def apply() = new CommandBuilder()
}

class CommandBuilder(twilioService: TwilioService = null) {
	def withTwilioService(twilioService: TwilioService) =
		new CommandBuilder(twilioService)

	def build(cmd: String, args: Seq[String]): Command = cmd match {
		case "mix" if args.size == 1 => MixCommand(args(0), List("1", "2"))
		case "sms" if args.size >= 2 => SmsCommand(twilioService, args(0), args.drop(1).mkString(" "))
		case "add" if args.size == 2 => AddCommand(args(0), args(1))
		case "help" => HelpCommand
		case _ => UnknownCommand
	}
}