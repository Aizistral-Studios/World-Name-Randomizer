package com.integral.worldnamerandomizer.handlers;

import java.util.Random;

import com.integral.worldnamerandomizer.helpers.WorldNameHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.CreateWorldScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import static com.integral.worldnamerandomizer.handlers.ClientConfigHandler.*;

public class WNREventHandler {

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void onWorldCreation(GuiScreenEvent.InitGuiEvent.Post event) {
		if (event.getGui() instanceof CreateWorldScreen && randomizerEnabled.get()) {
			CreateWorldScreen screen = (CreateWorldScreen) event.getGui();

			try {
				String localizedWorld = I18n.get("world.worldnamerandomizer.name");
				String number = WorldNameHelper.generateRandomWorldNumber();
				String name = localizedWorld + number;

				TextFieldWidget nameWidget = screen.nameEdit;
				TextFieldWidget seedWidget = screen.worldGenSettingsComponent.seedEdit;

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
