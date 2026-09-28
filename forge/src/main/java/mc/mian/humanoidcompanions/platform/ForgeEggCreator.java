package mc.mian.humanoidcompanions.platform;

import mc.mian.humanoidcompanions.common.registry.RegistrySupplier;
import mc.mian.humanoidcompanions.platform.services.IEggCreator;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;

public class ForgeEggCreator implements IEggCreator {
    @Override
    public <T extends Mob> SpawnEggItem createSpawnEgg(RegistrySupplier<EntityType<T>> supplier, int backgroundColor, int highlightColor, Item.Properties properties) {
        return new ForgeSpawnEggItem(supplier, backgroundColor, highlightColor, properties);
    }
}
