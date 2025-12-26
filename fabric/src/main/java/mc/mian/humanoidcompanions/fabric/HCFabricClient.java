package mc.mian.humanoidcompanions.fabric;

import mc.mian.humanoidcompanions.common.client.renderer.CompanionRenderer;
import mc.mian.humanoidcompanions.common.entity.HCEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class HCFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(HCEntities.KNIGHT.get(), CompanionRenderer::new);
        EntityRendererRegistry.register(HCEntities.ARCHER.get(), CompanionRenderer::new);
        EntityRendererRegistry.register(HCEntities.ARBALIST.get(), CompanionRenderer::new);
        EntityRendererRegistry.register(HCEntities.AXEGUARD.get(), CompanionRenderer::new);
    }
}