package mc.mian.humanoidcompanions.forge.event;

import mc.mian.humanoidcompanions.client.TemplateKeybinds;
import mc.mian.humanoidcompanions.util.HCConstants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class TemplateModClientEvents {
    @net.minecraftforge.fml.common.Mod.EventBusSubscriber(modid = HCConstants.MOD_ID, bus = net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class Common{
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent tickEvent){
            TemplateKeybinds.tickKeybinds();
        }
    }

    @net.minecraftforge.fml.common.Mod.EventBusSubscriber(modid = HCConstants.MOD_ID, bus = net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class Mod{
        @SubscribeEvent
        public static void registerKeybinds(RegisterKeyMappingsEvent event){
            TemplateKeybinds.getKeys().forEach(event::register);
        }
    }
}
