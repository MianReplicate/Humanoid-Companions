package mc.mian.humanoidcompanions.fabric;

import fuzs.forgeconfigapiport.fabric.api.forge.v4.ForgeConfigRegistry;
import mc.mian.humanoidcompanions.common.entity.HCEntities;
import mc.mian.humanoidcompanions.common.entity.custom.Arbalist;
import mc.mian.humanoidcompanions.common.entity.custom.Archer;
import mc.mian.humanoidcompanions.common.entity.custom.Axeguard;
import mc.mian.humanoidcompanions.common.entity.custom.Knight;
import mc.mian.humanoidcompanions.platform.FabricPlatformHelper;
import mc.mian.humanoidcompanions.platform.Services;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.fabricmc.api.ModInitializer;
import mc.mian.humanoidcompanions.HumanoidCompanions;
import mc.mian.humanoidcompanions.common.config.ConfigHolder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.neoforged.fml.config.ModConfig;

public class HCFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ForgeConfigRegistry.INSTANCE.register(HCConstants.MOD_ID, ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);
        ((FabricPlatformHelper) Services.PLATFORM).init();
        HumanoidCompanions.init();

        FabricDefaultAttributeRegistry.register(HCEntities.KNIGHT.get(), Knight.createAttributes());
        FabricDefaultAttributeRegistry.register(HCEntities.ARCHER.get(), Archer.createAttributes());
        FabricDefaultAttributeRegistry.register(HCEntities.ARBALIST.get(), Arbalist.createAttributes());
        FabricDefaultAttributeRegistry.register(HCEntities.AXEGUARD.get(), Axeguard.createAttributes());
    }
}