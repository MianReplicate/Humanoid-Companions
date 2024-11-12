package mc.mian.humanoidcompanions.forge;

import mc.mian.humanoidcompanions.HumanoidCompanions;
import mc.mian.humanoidcompanions.config.ConfigHolder;
import mc.mian.humanoidcompanions.datagen.TemplateDataGenerators;
import mc.mian.humanoidcompanions.util.HCConstants;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(HCConstants.MOD_ID)
public class HumanoidCompanionsForge {
    public static final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    public static final IEventBus eventBus = MinecraftForge.EVENT_BUS;
    public HumanoidCompanionsForge() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);

        HumanoidCompanions.config = ConfigHolder.SERVER;
        HumanoidCompanions.init();

        modEventBus.register(TemplateDataGenerators.class);
    }
}