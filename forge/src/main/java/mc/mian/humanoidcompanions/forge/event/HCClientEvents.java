package mc.mian.humanoidcompanions.forge.event;

import mc.mian.humanoidcompanions.util.HCConstants;
import net.minecraftforge.api.distmarker.Dist;

public class HCClientEvents {
    @net.minecraftforge.fml.common.Mod.EventBusSubscriber(modid = HCConstants.MOD_ID, bus = net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class Common{

    }

    @net.minecraftforge.fml.common.Mod.EventBusSubscriber(modid = HCConstants.MOD_ID, bus = net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class Mod{

    }
}
