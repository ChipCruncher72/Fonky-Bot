package com.fonkyclubhouse.bot.listeners;

import com.fonkyclubhouse.bot.commandlist.CommandList;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.zone.ZoneRulesException;
import java.util.List;
import java.util.Random;

public class ServerCommands extends ListenerAdapter {

	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
		String command = event.getName();
		Random random = new Random();
		// I am aware of switch statements. I'm just too lazy to refactor the code.
		if (command.equals("info")) {
			event.reply("Bot was created on the 26th of February 2023\n\nCreated by ChipCruncher72#8376\n\nWith the JDA (https://github.com/DV8FromTheWorld/JDA)").setEphemeral(true).queue();
		} else if (command.equals("fun-fact")) {
			String fact = switch (random.nextInt(1, 6)) {
				case 1 -> "American English speakers engaged in a friendly conversation speak at a rate of approximately 110–150 wpm.";
				case 2 -> "A shrimp's heart is in its head.";
				case 3 -> "The majority of your brain is fat.";
				case 4 -> "Stop signs used to be yellow.";
				case 5 -> "A human body sheds more than 30,000 dead skin cells every minute.";
				default -> "Couldn't generate a fact";
			};
			event.reply(fact).queue();
		} else if (command.equals("repeat")) {
			if (event.getOption("bold") != null) {
				if (event.getOption("bold").getAsBoolean()) {
					event.reply(event.getMember().getAsMention() + " sent **" + event.getOption("say").getAsString() + "**").queue();
				} else {
					event.reply(event.getMember().getAsMention() + " sent " + event.getOption("say").getAsString()).queue();
				}
			} else {
				event.reply(event.getMember().getAsMention() + " sent **" + event.getOption("say").getAsString() + "**").queue();
			}
		} else if (command.equals("time")) {
			if (event.getOption("timezone").getAsString().equals("help")) {
				event.reply("Here are all the available timezones.\nhttps://garygregory.wordpress.com/2013/06/18/what-are-the-java-timezone-ids/").setEphemeral(true).queue();
			} else {
				String timezone = event.getOption("timezone").getAsString();
				ZoneId zoneId = ZoneId.of("UTC");
				try {
					zoneId = ZoneId.of(timezone);
				} catch (ZoneRulesException ignore) {}
				String fullTime = String.valueOf(LocalTime.now(zoneId));
				String neededTime = fullTime.substring(0, 8);
				event.reply("The current " + timezone + " time is: **" + LocalDate.now(zoneId) + " " + neededTime + "**").queue();
			}
		}
	}

	@Override
	public void onGuildReady(GuildReadyEvent event) {
		List<CommandData> commands = CommandList.commands();
		event.getGuild().updateCommands().addCommands(commands).queue();
	}

	@Override
	public void onGuildJoin(GuildJoinEvent event) {
		List<CommandData> commands = CommandList.commands();
		event.getGuild().updateCommands().addCommands(commands).queue();
	}
}
