package mc.mian.humanoidcompanions.fabric;

import mc.mian.humanoidcompanions.client.TemplateKeybinds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public class TemplateModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register(mc -> TemplateKeybinds.tickKeybinds());

        TemplateKeybinds.getKeys().forEach(KeyBindingHelper::registerKeyBinding);
    }
}