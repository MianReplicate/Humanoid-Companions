package mc.mian.humanoidcompanions.fabric;

import fuzs.forgeconfigapiport.fabric.api.forge.v4.ForgeConfigRegistry;
import mc.mian.humanoidcompanions.platform.FabricPlatformHelper;
import mc.mian.humanoidcompanions.platform.Services;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.fabricmc.api.ModInitializer;
import mc.mian.humanoidcompanions.HumanoidCompanions;
import mc.mian.humanoidcompanions.common.config.ConfigHolder;
import net.neoforged.fml.config.ModConfig;

public class TemplateModFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ForgeConfigRegistry.INSTANCE.register(HCConstants.MOD_ID, ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);
        ((FabricPlatformHelper) Services.PLATFORM).init();
        HumanoidCompanions.init();
    }
}