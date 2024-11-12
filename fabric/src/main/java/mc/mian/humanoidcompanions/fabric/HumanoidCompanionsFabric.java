package mc.mian.humanoidcompanions.fabric;

import fuzs.forgeconfigapiport.api.config.v2.ForgeConfigRegistry;
import mc.mian.humanoidcompanions.common.item.HCItems;
import mc.mian.humanoidcompanions.util.HCConstants;
import mc.mian.humanoidcompanions.util.fabric.HCPlatformImpl;
import net.fabricmc.api.ModInitializer;
import mc.mian.humanoidcompanions.HumanoidCompanions;
import mc.mian.humanoidcompanions.config.ConfigHolder;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.fml.config.ModConfig;

public class HumanoidCompanionsFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ForgeConfigRegistry.INSTANCE.register(HCConstants.MOD_ID, ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);
        HumanoidCompanions.config = ConfigHolder.SERVER;
        HCPlatformImpl.init();
        HumanoidCompanions.init();

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(modifyEntries ->
                HCItems.ITEMS.getEntries().forEach(item -> modifyEntries.accept(item.get())));
    }
}