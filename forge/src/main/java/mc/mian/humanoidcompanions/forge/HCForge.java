package mc.mian.humanoidcompanions.forge;

import mc.mian.humanoidcompanions.common.HumanoidCompanions;
import mc.mian.humanoidcompanions.common.config.ConfigHolder;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HCConstants.MOD_ID)
public class HCForge {
    public static final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    public static final IEventBus commonEventBus = MinecraftForge.EVENT_BUS;
    public HCForge() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);

        HumanoidCompanions.init();
    }
}