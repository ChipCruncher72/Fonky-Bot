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
		cmds.add(Commands.slash("repeat", "Will repeat the string provided.").addOption(OptionType.STRING, "say", "repeat", true).addOption(OptionType.BOOLEAN, "bold", "If it will repeat your message boldly"));
		cmds.add(Commands.slash("time", "Time in provided timezone. Type 'help' for help.").addOption(OptionType.STRING, "timezone", "said timezone", true));
		cmds.add(Commands.slash("color-give", "Gives you a colored role.").addOption(OptionType.STRING, "color", "The color. Defaults to black", true));
		cmds.add(Commands.slash("color-remove", "Removes a colored role.").addOption(OptionType.STRING, "color", "The color. Defaults to black", true));
		cmds.add(Commands.slash("colors", "Shows all the color roles."));
		cmds.add(Commands.slash("ideas", "Ideas for the discord bot!"));
		return cmds;
	}
}
