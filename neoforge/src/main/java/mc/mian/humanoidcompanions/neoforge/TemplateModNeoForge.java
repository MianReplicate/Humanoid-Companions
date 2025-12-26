package mc.mian.humanoidcompanions.neoforge;

import fuzs.forgeconfigapiport.neoforge.api.forge.v4.ForgeConfigRegistry;
import mc.mian.humanoidcompanions.HumanoidCompanions;
import mc.mian.humanoidcompanions.common.config.ConfigHolder;
import mc.mian.humanoidcompanions.datagen.TemplateDataGenerators;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;

@Mod(HCConstants.MOD_ID)
public class TemplateModNeoForge {
    public static IEventBus modEventBus;
    public static final IEventBus commonEventBus = NeoForge.EVENT_BUS;
    public TemplateModNeoForge(IEventBus modEventBusParam) {
        modEventBus = modEventBusParam;

        ForgeConfigRegistry.INSTANCE.register(ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);
//        ModLoadingContext.get().getActiveContainer().registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

        HumanoidCompanions.init();

        modEventBus.register(TemplateDataGenerators.class);
    }
}