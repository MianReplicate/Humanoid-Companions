package mc.mian.humanoidcompanions.common.menu;

import mc.mian.humanoidcompanions.common.registry.DeferredRegistry;
import mc.mian.humanoidcompanions.common.registry.RegistrySupplier;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public class HCMenus {
    public static final DeferredRegistry<MenuType<?>> MENU_TYPES = DeferredRegistry.create(HCConstants.MOD_ID, Registries.MENU);

    public static final RegistrySupplier<MenuType<CompanionMenu>> COMPANION =
            register("companion", CompanionMenu::new);

    private static <T extends AbstractContainerMenu> RegistrySupplier<MenuType<T>> register(String id, MenuType.MenuSupplier<T> supplier){
        return MENU_TYPES.register(id, () -> new MenuType(supplier, FeatureFlags.VANILLA_SET));
    }
}
