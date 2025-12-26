package mc.mian.humanoidcompanions.neoforge.event;

import mc.mian.humanoidcompanions.common.client.renderer.CompanionRenderer;
import mc.mian.humanoidcompanions.common.entity.HCEntities;
import mc.mian.humanoidcompanions.common.item.HCItems;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class HCClientEvents {
    @EventBusSubscriber(modid = HCConstants.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class Mod{
        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerEntityRenderer(HCEntities.KNIGHT.get(), CompanionRenderer::new);
            event.registerEntityRenderer(HCEntities.ARCHER.get(), CompanionRenderer::new);
            event.registerEntityRenderer(HCEntities.ARBALIST.get(), CompanionRenderer::new);
            event.registerEntityRenderer(HCEntities.AXEGUARD.get(), CompanionRenderer::new);
        }

        @SubscribeEvent
        public static void editTabs(BuildCreativeModeTabContentsEvent event){
            if(event.getTabKey() == CreativeModeTabs.SPAWN_EGGS){
                event.accept(HCItems.ARBALIST_SPAWN_EGG.get());
                event.accept(HCItems.ARCHER_SPAWN_EGG.get());
                event.accept(HCItems.KNIGHT_SPAWN_EGG.get());
                event.accept(HCItems.AXE_GUARD_SPAWN_EGG.get());
            }
        }
    }
}
