package com.fonkyclubhouse.bot.data;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class storedData {
	public long messages;
	private static final Scanner sc;

	static {
		try {
			sc = new Scanner(new File("src/main/java/com/fonkyclubhouse/bot/data/storedData.json"));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private static final StringBuilder sb = new StringBuilder();

	private static String jsonData() {
		while (sc.hasNextLine()) {
			sb.append(sc.nextLine());
		}
		return sb.toString();
	}

	public static storedData getData = new Gson().fromJson(jsonData(), storedData.class);

	public static void assignNewData() {
		try (FileWriter fw = new FileWriter("src/main/java/com/fonkyclubhouse/bot/data/storedData.json")) {
			String newData = new Gson().toJson(getData);
			fw.write(newData);
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
