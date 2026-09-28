package mc.mian.humanoidcompanions.platform.services;

import mc.mian.humanoidcompanions.common.registry.RegistrySupplier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public interface IEggCreator {
    <T extends Mob> SpawnEggItem createSpawnEgg(RegistrySupplier<EntityType<T>> supplier, int backgroundColor, int highlightColor, Item.Properties properties);
}
