package mc.mian.humanoidcompanions.common.entity;

import mc.mian.humanoidcompanions.common.entity.custom.Arbalist;
import mc.mian.humanoidcompanions.common.entity.custom.Archer;
import mc.mian.humanoidcompanions.common.entity.custom.Axeguard;
import mc.mian.humanoidcompanions.common.entity.custom.Knight;
import mc.mian.humanoidcompanions.common.registry.DeferredRegistry;
import mc.mian.humanoidcompanions.common.registry.RegistrySupplier;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import mc.mian.humanoidcompanions.common.util.HCUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class HCEntities {
    public static final DeferredRegistry<EntityType<?>> ENTITIES = DeferredRegistry.create(HCConstants.MOD_ID, Registries.ENTITY_TYPE);

    public static final RegistrySupplier<EntityType<Knight>> KNIGHT =
            ENTITIES.register("knight", () -> EntityType.Builder.of(Knight::new, MobCategory.AMBIENT)
                    .sized(0.6F, 1.8F)
                    .build(HCUtil.modLoc("knight").toString()));

    public static final RegistrySupplier<EntityType<Archer>> ARCHER =
            ENTITIES.register("archer", () -> EntityType.Builder.of(Archer::new, MobCategory.AMBIENT)
                    .sized(0.6F, 1.8F)
                    .build(HCUtil.modLoc("archer").toString()));

    public static final RegistrySupplier<EntityType<Arbalist>> ARBALIST =
            ENTITIES.register("arbalist", () -> EntityType.Builder.of(Arbalist::new, MobCategory.AMBIENT)
                    .sized(0.6F, 1.8F)
                    .build(HCUtil.modLoc("arbalist").toString()));

    public static final RegistrySupplier<EntityType<Axeguard>> AXEGUARD =
            ENTITIES.register("axeguard", () -> EntityType.Builder.of(Axeguard::new, MobCategory.AMBIENT)
                    .sized(0.6F, 1.8F)
                    .build(HCUtil.modLoc("axeguard").toString()));
}
