package com.integral.worldnamerandomizer;

import com.integral.worldnamerandomizer.handlers.ClientConfigHandler;
import com.integral.worldnamerandomizer.handlers.WNREventHandler;

import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(WorldNameRandomizer.MODID)
public class WorldNameRandomizer {
	public static final String MODID = "worldnamerandomizer";

	public WorldNameRandomizer() {
		
		/*
		 * Subscribing our setup methods to ModEventBus, so that they actually
		 * do something.
		 */

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onLoadComplete);

		MinecraftForge.EVENT_BUS.register(this);

		/*
		 * We could do all event stuff in our main class to be honest, but whoever does that
		 * will be forever cursed, so let's register discrete event handler for that sake.
		 */

		WNREventHandler eventHandler = new WNREventHandler();

		MinecraftForge.EVENT_BUS.register(eventHandler);
		//FMLJavaModLoadingContext.get().getModEventBus().register(eventHandler);

		ClientConfigHandler.constructConfig();
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ClientConfigHandler.clientConfig);
	}

	private void onLoadComplete(final FMLLoadCompleteEvent event) {
		ClientConfigHandler.clientConfig.isLoaded();
	}

	private void setup(final FMLCommonSetupEvent event) {
		// NO-OP
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		// NO-OP
	}

	private void enqueueIMC(final InterModEnqueueEvent event) {
		// NO-OP
	}

	private void processIMC(final InterModProcessEvent event) {
		// NO-OP
	}
}
