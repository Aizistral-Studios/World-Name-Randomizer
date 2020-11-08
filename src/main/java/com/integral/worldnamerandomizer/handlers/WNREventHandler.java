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
				String localizedWorld = I18n.format("world.worldnamerandomizer.name");
				String number = WorldNameHelper.generateRandomWorldNumber();
				String name = localizedWorld + number;

				TextFieldWidget nameWidget = screen.worldNameField;
				TextFieldWidget seedWidget = screen.field_238934_c_.field_239033_g_;

				if ((nameWidget.getText() == null || !nameWidget.getText().startsWith(localizedWorld)) && (seedWidget == null || seedWidget.getText() == null || seedWidget.getText().isEmpty())) {
					nameWidget.setText(name);

					if (randomizeSeed.get()) {
						if (seedWidget == null) {
							screen.field_238934_c_.func_239048_a_(screen, Minecraft.getInstance(), Minecraft.getInstance().fontRenderer);
							seedWidget = screen.field_238934_c_.field_239033_g_;
						}

						seedWidget.setText(number);
					}

					screen.worldNameField = nameWidget;
					screen.field_238934_c_.field_239033_g_ = seedWidget;
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
