package mc.mian.humanoidcompanions.fabric;

import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import mc.mian.humanoidcompanions.util.HCConstants;
import mc.mian.humanoidcompanions.util.fabric.TemplatePlatformImpl;
import net.fabricmc.api.ModInitializer;
import mc.mian.humanoidcompanions.HumanoidCompanions;
import mc.mian.humanoidcompanions.config.ConfigHolder;
import net.minecraftforge.fml.config.ModConfig;

public class TemplateModFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ForgeConfigRegistry.INSTANCE.register(HCConstants.MOD_ID, ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);
        HumanoidCompanions.config = ConfigHolder.SERVER;
        TemplatePlatformImpl.init();
        HumanoidCompanions.init();
    }
}