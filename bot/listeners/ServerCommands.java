package com.fonkyclubhouse.bot.listeners;

import com.fonkyclubhouse.bot.commandlist.CommandList;
import com.fonkyclubhouse.bot.data.storedData;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
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
				String fact = switch (random.nextInt(1, 11)) {
					case 1 ->
							"American English speakers engaged in a friendly conversation speak at a rate of approximately 110–150 wpm.";
					case 2 -> "A shrimp's heart is in its head.";
					case 3 -> "The majority of your brain is fat.";
					case 4 -> "Stop signs used to be yellow.";
					case 5 -> "A human body sheds more than 30,000 dead skin cells every minute.";
					case 6 -> "You should like all of mrfonkgt's videos! ***RIGHT. NOW.***";
					case 7 -> "Spinal fluid tastes like a mixture of bananas and a 9-volt battery.";
					case 8 -> "Tarantulas taste like shrimp.";
					case 9 -> "Alcoholics decompose faster when they die.";
					case 10 -> "There is an ancient book full of strange symbols no one can translate";
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
				event.getGuild().getChannelById(TextChannel.class, "1080353098192654410").sendMessage("Message sent by " + event.getMember().getUser().getAsTag() + " in " + event.getChannel().getAsMention() + ": **" + event.getOption("say").getAsString() + "**").queue();
				storedData.getData.messages++;
				storedData.assignNewData();
			}
			case "time" -> {
				if (event.getOption("timezone").getAsString().equalsIgnoreCase("help")) {
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
					case "red" -> jda.getRoleById("1079686196394983454");
					case "orange" -> jda.getRoleById("1079686252938395709");
					case "yellow" -> jda.getRoleById("1079686310350045244");
					case "green" -> jda.getRoleById("1079686348312682516");
					case "blue" -> jda.getRoleById("1079686435306750001");
					case "purple" -> jda.getRoleById("1079686515006914580");
					case "white" -> jda.getRoleById("1079687122132414485");
					default -> jda.getRoleById("1079687019388731444");
				};
				event.getGuild().addRoleToMember(event.getMember(), role).queue();
				event.reply("Role given: " + role.getName() + "!").setEphemeral(true).queue();
			}
			case "color-remove" -> {
				JDA jda = event.getJDA();
				Role role = switch (event.getOption("color").getAsString().toLowerCase()) {
					case "red" -> jda.getRoleById("1079686196394983454");
					case "orange" -> jda.getRoleById("1079686252938395709");
					case "yellow" -> jda.getRoleById("1079686310350045244");
					case "green" -> jda.getRoleById("1079686348312682516");
					case "blue" -> jda.getRoleById("1079686435306750001");
					case "purple" -> jda.getRoleById("1079686515006914580");
					case "white" -> jda.getRoleById("1079687122132414485");
					default -> jda.getRoleById("1079687019388731444");
				};
				event.getGuild().removeRoleFromMember(event.getMember(), role).queue();
				event.reply("Role removed: " + role.getName() + "!").setEphemeral(true).queue();
			}
			case "ideas" ->
					event.reply("Please refer to this google form: https://forms.gle/udccFRrjuA9Gzr389").setEphemeral(true).queue();
			case "random" -> {
				try {
					int min = event.getOption("min").getAsInt();
					int max = event.getOption("max").getAsInt();
					event.reply("Random number: " + random.nextInt(min, (max + 1))).queue();
				} catch (IllegalArgumentException e) {
					int min = event.getOption("min").getAsInt();
					int max = event.getOption("max").getAsInt();
					event.reply("Random number: " + random.nextInt(max, (min + 1))).queue();
				}
			}
			case "purge" -> {
				if (event.getOption("messages").getAsInt() < 1 || event.getOption("messages").getAsInt() > 100) {
					event.reply("Message retrieval limit is between 1 and 100 messages. No more, no less. Limit provided: " + event.getOption("messages").getAsInt()).setEphemeral(true).queue();
				} else {
					event.getChannel().getHistory().retrievePast(event.getOption("messages").getAsInt()).complete().forEach((i) -> i.delete().queue());
					event.reply("Purging all " + event.getOption("messages").getAsInt() + " messages").setEphemeral(true).queue();
				}
			}
			case "messages" ->
					event.reply(storedData.getData.messages + " messages have been sent in the server. (Excluding bots)").queue();
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
