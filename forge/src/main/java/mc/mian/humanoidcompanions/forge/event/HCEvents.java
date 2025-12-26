package mc.mian.humanoidcompanions.forge.event;

import mc.mian.humanoidcompanions.common.client.renderer.CompanionRenderer;
import mc.mian.humanoidcompanions.common.entity.HCEntities;
import mc.mian.humanoidcompanions.common.entity.custom.Arbalist;
import mc.mian.humanoidcompanions.common.entity.custom.Archer;
import mc.mian.humanoidcompanions.common.entity.custom.Axeguard;
import mc.mian.humanoidcompanions.common.entity.custom.Knight;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class HCEvents {
    @net.minecraftforge.fml.common.Mod.EventBusSubscriber(modid = HCConstants.MOD_ID, bus = net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD)
    public static class Mod{
        @SubscribeEvent
        public static void registerAttributes(EntityAttributeCreationEvent event) {
            event.put(HCEntities.KNIGHT.get(), Knight.createAttributes().build());
            event.put(HCEntities.ARCHER.get(), Archer.createAttributes().build());
            event.put(HCEntities.ARBALIST.get(), Arbalist.createAttributes().build());
            event.put(HCEntities.AXEGUARD.get(), Axeguard.createAttributes().build());
        }
    }
}
