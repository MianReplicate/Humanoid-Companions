package mc.mian.humanoidcompanions.common.item;

import mc.mian.humanoidcompanions.registry.DeferredRegistry;
import mc.mian.humanoidcompanions.registry.RegistrySupplier;
import mc.mian.humanoidcompanions.util.HCConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.SpawnEggItem;

public class HCItems {
    public static final DeferredRegistry<Item> ITEMS = DeferredRegistry.create(HCConstants.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<SpawnEggItem> Arbalist_Spawn_Egg = ITEMS.register("arbalist_spawn_egg",
            () -> new SpawnEggItem(EntityInit.Arbalist,0xE8AF5A, 0xFF0000,
                    new Item.Properties().stacksTo(64).tab(CreativeModeTab.TAB_MISC)));
}
