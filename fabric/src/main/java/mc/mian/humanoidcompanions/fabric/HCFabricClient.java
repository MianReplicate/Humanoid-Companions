package mc.mian.humanoidcompanions.fabric;

import mc.mian.humanoidcompanions.common.client.renderer.CompanionRenderer;
import mc.mian.humanoidcompanions.common.entity.HCEntities;
import mc.mian.humanoidcompanions.common.entity.custom.AbstractHumanCompanionEntity;
import mc.mian.humanoidcompanions.platform.FabricNetworkRegistry;
import mc.mian.humanoidcompanions.platform.FabricPlatformHelper;
import mc.mian.humanoidcompanions.platform.Services;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.world.entity.EntityType;

public class HCFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ((FabricNetworkRegistry) Services.NETWORK).registerClient();

        HCEntities.ENTITIES.getEntries().forEach(entityType -> EntityRendererRegistry.register(
                (EntityType<? extends AbstractHumanCompanionEntity>) entityType.get(), CompanionRenderer::new));
    }
}