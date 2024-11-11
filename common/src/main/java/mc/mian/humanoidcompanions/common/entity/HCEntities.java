package mc.mian.humanoidcompanions.common.entity;

import mc.mian.humanoidcompanions.registry.DeferredRegistry;
import mc.mian.humanoidcompanions.util.HCConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;

public class HCEntities {
    public static final DeferredRegistry<EntityType<?>> ENTITIES = DeferredRegistry.create(HCConstants.MOD_ID, Registries.ENTITY_TYPE);


}
