package com.aizistral.worldnamerandomizer.handlers;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfigHandler {
	public static ForgeConfigSpec clientConfig;

	public static ForgeConfigSpec.BooleanValue randomizerEnabled;
	public static ForgeConfigSpec.BooleanValue randomizeSeed;

	public static void constructConfig() {
		final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

		builder.comment("General mod config").push("Generic Config");

		randomizerEnabled = builder
				.comment("If set to false, the World Name Randomizer won't do anything at all.")
				.define("randomizerEnabled", true);

		randomizeSeed = builder
				.comment("If set to false, the World Name Randomizer will not touch upon the world's "
						+ "seed field, only altering the name.")
				.define("randomizeSeed", true);

		builder.pop();

		clientConfig = builder.build();
	}
}
