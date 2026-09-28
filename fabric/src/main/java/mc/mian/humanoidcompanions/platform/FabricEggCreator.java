package mc.mian.humanoidcompanions.platform;

import mc.mian.humanoidcompanions.common.registry.RegistrySupplier;
import mc.mian.humanoidcompanions.platform.services.IEggCreator;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class FabricEggCreator implements IEggCreator {
    @Override
    public <T extends Mob> SpawnEggItem createSpawnEgg(RegistrySupplier<EntityType<T>> supplier, int backgroundColor, int highlightColor, Item.Properties properties) {
        return new SpawnEggItem(supplier.get(), backgroundColor, highlightColor, properties);
    }
}
