package com.fonkyclubhouse.bot.commandlist;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.ArrayList;
import java.util.List;

public class CommandList {
	public static List<CommandData> commands() {
		List<CommandData> cmds = new ArrayList<>();
		cmds.add(Commands.slash("info", "Information about the bot!"));
		cmds.add(Commands.slash("fun-fact", "Generate a random fun fact!"));
		cmds.add(Commands.slash("repeat", "Will repeat the string provided.").addOption(OptionType.STRING, "say", "repeat").addOption(OptionType.BOOLEAN, "bold", "If it will repeat your message boldly"));
		cmds.add(Commands.slash("time", "time in provided timezone. type 'help' for help").addOption(OptionType.STRING, "timezone", "said timezone"));
		return cmds;
	}
}
