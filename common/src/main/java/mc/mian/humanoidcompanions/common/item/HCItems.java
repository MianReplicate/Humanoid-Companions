package mc.mian.humanoidcompanions.common.item;

import mc.mian.humanoidcompanions.common.entity.HCEntities;
import mc.mian.humanoidcompanions.common.registry.DeferredRegistry;
import mc.mian.humanoidcompanions.common.registry.RegistrySupplier;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import mc.mian.humanoidcompanions.platform.Services;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import java.util.ArrayList;
import java.util.function.Supplier;

public class HCItems {
    public static final DeferredRegistry<Item> ITEMS = DeferredRegistry.create(HCConstants.MOD_ID, Registries.ITEM);
    public static final ArrayList<Supplier<? extends SpawnEggItem>> SPAWN_EGGS = new ArrayList<>();
    public static final RegistrySupplier<SpawnEggItem> ARBALIST_SPAWN_EGG = registerSpawnEgg("arbalist_spawn_egg",
            () -> Services.EGG.createSpawnEgg(HCEntities.ARBALIST,0xE8AF5A, 0xFF0000,
                    new Item.Properties().stacksTo(64)));

    public static final RegistrySupplier<SpawnEggItem> ARCHER_SPAWN_EGG = registerSpawnEgg("archer_spawn_egg",
            () -> Services.EGG.createSpawnEgg(HCEntities.ARCHER,0xE8AF5A, 0x0000FF,
                    new Item.Properties().stacksTo(64)));

    public static final RegistrySupplier<SpawnEggItem> AXE_GUARD_SPAWN_EGG = registerSpawnEgg("axeguard_spawn_egg",
            () -> Services.EGG.createSpawnEgg(HCEntities.AXEGUARD, 0xE8AF5A, 0x00FF00,
                    new Item.Properties().stacksTo(64)));

    public static final RegistrySupplier<SpawnEggItem> KNIGHT_SPAWN_EGG = registerSpawnEgg("knight_spawn_egg",
            () -> Services.EGG.createSpawnEgg(HCEntities.KNIGHT,0xE8AF5A, 0xFFFF00,
                    new Item.Properties().stacksTo(64)));

    public static <T extends SpawnEggItem> RegistrySupplier<T> registerSpawnEgg(String id, Supplier<T> supplier){
        SPAWN_EGGS.add(supplier);
        return ITEMS.register(id, supplier);
    }
}
