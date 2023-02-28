package com.fonkyclubhouse.bot.listeners;

import com.fonkyclubhouse.bot.commandlist.CommandList;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;

public class ServerCommands extends ListenerAdapter {

	@Override
	public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
		String command = event.getName();
		Random random = new Random();
		switch (command) {
			case "info" ->
					event.reply("Bot was created on the 26th of February 2023\n\nCreated by ChipCruncher72#8376\n\nWith the JDA (https://github.com/DV8FromTheWorld/JDA)").setEphemeral(true).queue();
			case "fun-fact" -> {
				String fact = switch (random.nextInt(1, 7)) {
					case 1 ->
							"American English speakers engaged in a friendly conversation speak at a rate of approximately 110–150 wpm.";
					case 2 -> "A shrimp's heart is in its head.";
					case 3 -> "The majority of your brain is fat.";
					case 4 -> "Stop signs used to be yellow.";
					case 5 -> "A human body sheds more than 30,000 dead skin cells every minute.";
					case 6 -> "You should like all of mrfonkgt's videos! ***RIGHT. NOW.***";
					default -> "Couldn't generate a fact";
				};
				event.reply(fact).queue();
			}
			case "repeat" -> {
				if (event.getOption("bold") != null) {
					if (event.getOption("bold").getAsBoolean()) {
						event.reply(event.getMember().getAsMention() + " sent **" + event.getOption("say").getAsString() + "**").queue();
					} else {
						event.reply(event.getMember().getAsMention() + " sent " + event.getOption("say").getAsString()).queue();
					}
				} else {
					event.reply(event.getMember().getAsMention() + " sent **" + event.getOption("say").getAsString() + "**").queue();
				}
			}
			case "time" -> {
				if (event.getOption("timezone").getAsString().equals("help")) {
					event.reply("Here are all the available timezones.\nhttps://garygregory.wordpress.com/2013/06/18/what-are-the-java-timezone-ids/").setEphemeral(true).queue();
				} else {
					String timezone = event.getOption("timezone").getAsString();
					ZoneId zoneId = ZoneId.of("UTC");
					try {
						zoneId = ZoneId.of(timezone);
					} catch (DateTimeException ignore) {
					}
					String fullTime = String.valueOf(LocalTime.now(zoneId));
					String neededTime = fullTime.substring(0, 8);
					event.reply("The current " + timezone + " time is: **" + LocalDate.now(zoneId) + " " + neededTime + "**").queue();
				}
			}
			case "color-give" -> {
				JDA jda = event.getJDA();
				Role role = switch (event.getOption("color").getAsString().toLowerCase()) {
					case "red" -> jda.getRoleById(/* Color ID */);
					case "orange" -> jda.getRoleById(/* Color ID */);
					case "yellow" -> jda.getRoleById(/* Color ID */);
					case "green" -> jda.getRoleById(/* Color ID */);
					case "blue" -> jda.getRoleById(/* Color ID */);
					case "purple" -> jda.getRoleById(/* Color ID */);
					case "white" -> jda.getRoleById(/* Color ID */);
					default -> jda.getRoleById(/* Color ID */);
				};
				event.getGuild().addRoleToMember(event.getMember(), role).queue();
				event.reply("Role given: " + role.getName() + "!").setEphemeral(true).queue();
			}
			case "color-remove" -> {
				JDA jda = event.getJDA();
				Role role = switch (event.getOption("color").getAsString().toLowerCase()) {
					case "red" -> jda.getRoleById(/* Color ID */);
					case "orange" -> jda.getRoleById(/* Color ID */);
					case "yellow" -> jda.getRoleById(/* Color ID */);
					case "green" -> jda.getRoleById(/* Color ID */);
					case "blue" -> jda.getRoleById(/* Color ID */);
					case "purple" -> jda.getRoleById(/* Color ID */);
					case "white" -> jda.getRoleById(/* Color ID */);
					default -> jda.getRoleById(/* Color ID */);
				};
				event.getGuild().removeRoleFromMember(event.getMember(), role).queue();
				event.reply("Role removed: " + role.getName() + "!").setEphemeral(true).queue();
			}
			case "colors" -> {
				JDA jda = event.getJDA();
				event.reply("**Color Roles!**\n\n" + jda.getRoleById(/* Color ID */).getAsMention() +
						"\n" + jda.getRoleById(/* Color ID */).getAsMention() +
						"\n" + jda.getRoleById(/* Color ID */).getAsMention() +
						"\n" + jda.getRoleById(/* Color ID */).getAsMention() +
						"\n" + jda.getRoleById(/* Color ID */).getAsMention() +
						"\n" + jda.getRoleById(/* Color ID */).getAsMention() +
						"\n" + jda.getRoleById(/* Color ID */).getAsMention() +
						"\n" + jda.getRoleById(/* Color ID */).getAsMention()).setEphemeral(true).queue();
			}
			case "ideas" ->
					event.reply("Please refer to this google form: /* Google Form link */").setEphemeral(true).queue();
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
