package com.fonkyclubhouse.bot.listeners;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class Listener extends ListenerAdapter {

	// Give user "Member" role when joined and welcome user
	@Override
	public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		JDA jda = event.getJDA();
		event.getGuild().addRoleToMember(event.getMember(), Objects.requireNonNull(jda.getRoleById(/* Role ID */))).queue();
		event.getGuild().getChannelById(TextChannel.class, /* Channel ID */).sendMessage("Welcome to the clubhouse " + event.getMember().getAsMention() + "!").queue();
	}
}
