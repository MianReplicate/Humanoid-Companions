package mc.mian.humanoidcompanions.neoforge.event;

import mc.mian.humanoidcompanions.common.entity.HCEntities;
import mc.mian.humanoidcompanions.common.entity.custom.Arbalist;
import mc.mian.humanoidcompanions.common.entity.custom.Archer;
import mc.mian.humanoidcompanions.common.entity.custom.Axeguard;
import mc.mian.humanoidcompanions.common.entity.custom.Knight;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

public class HCEvents {
    @EventBusSubscriber(modid = HCConstants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
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
