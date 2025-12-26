package mc.mian.humanoidcompanions.common.item;

import mc.mian.humanoidcompanions.common.entity.HCEntities;
import mc.mian.humanoidcompanions.common.registry.DeferredRegistry;
import mc.mian.humanoidcompanions.common.registry.RegistrySupplier;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Function;

public class HCItems {
    public static final DeferredRegistry<Item> ITEMS = DeferredRegistry.create(HCConstants.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<SpawnEggItem> ARBALIST_SPAWN_EGG = ITEMS.register("arbalist_spawn_egg",
            () -> new SpawnEggItem(HCEntities.ARBALIST.get(),0xE8AF5A, 0xFF0000,
                    new Item.Properties().stacksTo(64)));

    public static final RegistrySupplier<SpawnEggItem> ARCHER_SPAWN_EGG = ITEMS.register("archer_spawn_egg",
            () -> new SpawnEggItem(HCEntities.ARCHER.get(),0xE8AF5A, 0x0000FF,
                    new Item.Properties().stacksTo(64)));

    public static final RegistrySupplier<SpawnEggItem> AXE_GUARD_SPAWN_EGG = ITEMS.register("axeguard_spawn_egg",
            () -> new SpawnEggItem(HCEntities.AXEGUARD.get(), 0xE8AF5A, 0x00FF00,
                    new Item.Properties().stacksTo(64)));

    public static final RegistrySupplier<SpawnEggItem> KNIGHT_SPAWN_EGG = ITEMS.register("knight_spawn_egg",
            () -> new SpawnEggItem(HCEntities.KNIGHT.get(),0xE8AF5A, 0xFFFF00,
                    new Item.Properties().stacksTo(64)));

    public static RegistrySupplier<Item> registerItem(String name, Function<Item.Properties, Item> itemFunc){
        return ITEMS.register(name, () -> itemFunc.apply(new Item.Properties()));
    }
}
