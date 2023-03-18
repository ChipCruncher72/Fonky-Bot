package com.fonkyclubhouse.bot;

import com.fonkyclubhouse.bot.data.StoredData;
import com.fonkyclubhouse.bot.listeners.Listener;
import com.fonkyclubhouse.bot.listeners.ServerCommands;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Random;

public class BotMain {

	private final ShardManager sm;

	// Initialize bot
	public BotMain() throws LoginException, InterruptedException {
		Random random = new Random(timeAsLong());
		Dotenv dotenv = Dotenv.load();
		String token = dotenv.get("TOKEN");
		var builder = DefaultShardManagerBuilder.createDefault(token);
		builder.setStatus(OnlineStatus.ONLINE);
		//                    Allow the config of members | Allow the bot to read messages
		builder.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT);

		// Create bot
		sm = builder.build();

		// Initialize the bots hearing
		sm.addEventListener(new Listener(), new ServerCommands());
		while (true) {
			int randNum = random.nextInt(1, 8);
			switch (randNum) {
				case 1 -> sm.setActivity(Activity.listening("zendon180's beautiful speech"));
				case 2 -> sm.setActivity(Activity.watching("Fonky's server"));
				case 3 -> sm.setActivity(Activity.listening("the screams of the damned"));
				case 4 -> sm.setActivity(Activity.streaming("fortnite \uD83D\uDE0E", "https://www.twitch.tv/mrfonkgt"));
				case 5 -> sm.setActivity(Activity.watching(StoredData.getData.messages + "+ messages"));
				case 6 -> sm.setActivity(Activity.watching("you write the most stupidest thing ever"));
				case 7 -> sm.setActivity(Activity.listening("/info and /ideas"));
				default -> sm.setActivity(Activity.watching("if you're seeing this, The bot broke."));
			}
			Thread.sleep(10000);
		}
	}

	private long timeAsLong() {
		String ltStr = LocalTime.now(ZoneId.of("UTC")).toString();
		ltStr = ltStr.replace(":", "");
		ltStr = ltStr.replace(".", "");
		return Long.parseLong(ltStr);
	}

	public ShardManager getSm() {
		return sm;
	}

	// Init the code
	public static void main(String[] args) throws InterruptedException {
		try {
			BotMain main = new BotMain();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}
}
