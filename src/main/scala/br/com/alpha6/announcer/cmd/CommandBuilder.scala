package br.com.alpha6.announcer.cmd

object CommandBuilder {
	def build(cmd: String, args: Seq[String]): Command = cmd match {
		case "mix" if args == 1 => MixCommand(args(0), List("1", "2"))
		case "sms" if args.size >= 2 => SmsCommand(args(0), args.drop(1).mkString(" "))
		case "add" if args.size == 2 => AddCommand(args(0), args(1))
		case "help" => HelpCommand
		case _ => UnknownCommand
	}
}