package com.fonkyclubhouse.bot;

import com.fonkyclubhouse.bot.listeners.Listener;
import com.fonkyclubhouse.bot.listeners.ServerCommands;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class BotMain {

	private final ShardManager sm;

	// Initialize bot
	public BotMain() throws LoginException {
		String token = /* Bot Token */;
		var builder = DefaultShardManagerBuilder.createDefault(token);
		builder.setStatus(OnlineStatus.ONLINE);
		builder.setActivity(Activity.watching("Fonky's server"));
		//                    Allow the config of members | Allow the bot to read messages
		builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT);

		// Create bot
		sm = builder.build();

		// Initialize the bots hearing
		sm.addEventListener(new Listener(), new ServerCommands());
	}

	public ShardManager getSm() {
		return sm;
	}

	// Init the code
	public static void main(String[] args) {
		try {
			BotMain main = new BotMain();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}
}
