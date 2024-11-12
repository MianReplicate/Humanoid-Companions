package mc.mian.humanoidcompanions.forge.event;

import mc.mian.humanoidcompanions.common.item.HCItems;
import mc.mian.humanoidcompanions.util.HCConstants;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class HCEvents {
    @net.minecraftforge.fml.common.Mod.EventBusSubscriber(modid = HCConstants.MOD_ID, bus = net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD, value = Dist.DEDICATED_SERVER)
    public static class Mod{
        @SubscribeEvent
        public static void modifyTabs(BuildCreativeModeTabContentsEvent event){
            if(event.getTabKey() == CreativeModeTabs.SPAWN_EGGS){
                HCItems.ITEMS.getEntries().forEach(item -> event.accept(item.get()));
            }
        }
    }
}
