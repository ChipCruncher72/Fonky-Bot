package com.fonkyclubhouse.bot.listeners;

import com.fonkyclubhouse.bot.data.storedData;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Listener extends ListenerAdapter {
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (!event.getAuthor().isBot()) {
			try {
				event.getGuild().getChannelById(TextChannel.class, "1080353098192654410").sendMessage("Message sent by " + event.getAuthor().getAsTag() + " in " + event.getChannel().getAsMention() + ": **" + event.getMessage().getContentRaw() + "**").queue();
			} catch (IllegalStateException ignore) {
			}
			storedData.getData.messages++;
			storedData.assignNewData();
		}
	}

	@Override
	public void onMessageUpdate(MessageUpdateEvent event) {
		if (!event.getAuthor().isBot()) {
			try {
				event.getGuild().getChannelById(TextChannel.class, "1080353098192654410").sendMessage(event.getAuthor().getAsTag() + " edited a message in " + event.getChannel().getAsMention() + ": **" + event.getMessage().getContentRaw() + "**").queue();
			} catch (IllegalStateException ignore) {
			}
		}
	}

	// Give user "Member" role when joined and welcome user
	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		JDA jda = event.getJDA();
		event.getGuild().addRoleToMember(event.getMember(), jda.getRoleById("1079200021502775387")).queue();
		event.getGuild().getChannelById(TextChannel.class, "1079233593609560094").sendMessage("Welcome to the clubhouse " + event.getMember().getAsMention() + "!").queue();
	}
}
