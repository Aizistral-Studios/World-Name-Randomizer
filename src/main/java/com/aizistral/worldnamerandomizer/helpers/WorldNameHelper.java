package com.aizistral.worldnamerandomizer.helpers;

import java.util.Random;

public class WorldNameHelper {
	public static final char[] symbols = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
	public static final Random theySeeMeRollin = new Random();

	public static String generateRandomWorldNumber() {

		String number = "";

		while (number.length() < 4) {
			number = number.concat(""+theySeeMeRollin.nextInt(10));
		}

		number = number.concat("-");

		while (number.length() < 7) {
			number = number.concat(""+symbols[theySeeMeRollin.nextInt(symbols.length)]);
		}

		while (number.length() < 9) {
			number = number.concat(""+theySeeMeRollin.nextInt(10));
		}

		return number;
	}
}
