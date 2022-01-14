package com.integral.worldnamerandomizer.handlers;

import java.util.Random;

import com.integral.worldnamerandomizer.helpers.WorldNameHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.resources.language.I18n;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import static com.integral.worldnamerandomizer.handlers.ClientConfigHandler.*;

public class WNREventHandler {

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void onWorldCreation(ScreenEvent.InitScreenEvent.Post event) {
		if (event.getScreen() instanceof CreateWorldScreen && randomizerEnabled.get()) {
			CreateWorldScreen screen = (CreateWorldScreen) event.getScreen();
			
			try {
				String localizedWorld = I18n.get("world.worldnamerandomizer.name");
				String number = WorldNameHelper.generateRandomWorldNumber();
				String name = localizedWorld + number;

				EditBox nameWidget = screen.nameEdit;
				EditBox seedWidget = screen.worldGenSettingsComponent.seedEdit;

				if ((nameWidget.getValue() == null || !nameWidget.getValue().startsWith(localizedWorld)) && (seedWidget == null || seedWidget.getValue() == null || seedWidget.getValue().isEmpty())) {
					nameWidget.setValue(name);

					if (randomizeSeed.get()) {
						if (seedWidget == null) {
							screen.worldGenSettingsComponent.init(screen, Minecraft.getInstance(), Minecraft.getInstance().font);
							seedWidget = screen.worldGenSettingsComponent.seedEdit;
						}

						seedWidget.setValue(number);
					}

					screen.nameEdit = nameWidget;
					screen.worldGenSettingsComponent.seedEdit = seedWidget;
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}


	public static boolean isEnigmaticLegacyLoaded() {
		return ModList.get().isLoaded("enigmaticlegacy");
	}

}
