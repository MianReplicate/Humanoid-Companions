package mc.mian.humanoidcompanions.common.tab;

import dev.architectury.injectables.annotations.ExpectPlatform;
import mc.mian.humanoidcompanions.common.item.TemplateItems;
import mc.mian.humanoidcompanions.registry.DeferredRegistry;
import mc.mian.humanoidcompanions.registry.RegistrySupplier;
import mc.mian.humanoidcompanions.util.HCConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class HCTabs {
    public static final DeferredRegistry<CreativeModeTab> TABS = DeferredRegistry.create(HCConstants.MOD_ID, Registries.CREATIVE_MODE_TAB);
    public static final RegistrySupplier<CreativeModeTab> TEMPLATE_MOD = TABS.register("template_mod", () -> createTab("template_mod"));
    public static ItemStack makeIcon() {
        return new ItemStack(TemplateItems.TEMPLATE_ITEM.get());
    }
    @ExpectPlatform
    public static CreativeModeTab createTab(String title){
        throw new RuntimeException("fuck off");
    }
}
