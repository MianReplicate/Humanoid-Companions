package mc.mian.humanoidcompanions.forge.event;

import mc.mian.humanoidcompanions.common.client.renderer.CompanionRenderer;
import mc.mian.humanoidcompanions.common.entity.HCEntities;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class HCClientEvents {
    @net.minecraftforge.fml.common.Mod.EventBusSubscriber(modid = HCConstants.MOD_ID, bus = net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class Mod{
        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(HCEntities.KNIGHT.get(), CompanionRenderer::new);
            event.registerEntityRenderer(HCEntities.ARCHER.get(), CompanionRenderer::new);
            event.registerEntityRenderer(HCEntities.ARBALIST.get(), CompanionRenderer::new);
            event.registerEntityRenderer(HCEntities.AXEGUARD.get(), CompanionRenderer::new);
        }
    }
}
