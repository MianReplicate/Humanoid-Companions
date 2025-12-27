package mc.mian.humanoidcompanions.datagen.worldgen.structure;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import mc.mian.humanoidcompanions.common.key.HCKeys;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class HCPoolProvider {
    public static void bootstrap(BootstrapContext<StructureTemplatePool> context){
        HolderGetter<StructureTemplatePool> templatePoolGetter = context.lookup(Registries.TEMPLATE_POOL);
        Holder<StructureTemplatePool> emptyHolder = templatePoolGetter.getOrThrow(Pools.EMPTY);

        context.register(HCKeys.ACACIA_POOL,
                new StructureTemplatePool(
                        emptyHolder,
                        ImmutableList.of(
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_mixed1"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_mixed2"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_mixed3"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_acacia_double"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:acaciah_house"), 1)
                        ),
                        StructureTemplatePool.Projection.RIGID
                        ));
        context.register(HCKeys.BIRCH_POOL,
                new StructureTemplatePool(
                        emptyHolder,
                        ImmutableList.of(
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_mixed1"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_mixed2"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_mixed3"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_mixed_double"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_birch_double"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:birch_house"), 2)
                        ),
                        StructureTemplatePool.Projection.RIGID
                ));
        context.register(HCKeys.DARK_OAK_POOL,
                new StructureTemplatePool(
                        emptyHolder,
                        ImmutableList.of(
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_spruce_darkoak"), 3),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_spruce_double"), 1)
                        ),
                        StructureTemplatePool.Projection.RIGID
                ));
        context.register(HCKeys.OAK_BIRCH_POOL,
                new StructureTemplatePool(
                        emptyHolder,
                        ImmutableList.of(
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_mixed1"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_mixed2"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_mixed3"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_oak_double"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_birch_double"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_mixed_double"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:oak_house"), 2),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:birch_house"), 2)
                        ),
                        StructureTemplatePool.Projection.RIGID
                ));
        context.register(HCKeys.OAK_POOL,
                new StructureTemplatePool(
                        emptyHolder,
                        ImmutableList.of(
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_mixed1"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_mixed2"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_mixed3"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_birch_double"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_mixed_double"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:cabin"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:cabin2"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_triple"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:oak_house"), 1)
                        ),
                        StructureTemplatePool.Projection.RIGID
                ));
        context.register(HCKeys.SANDSTONE_POOL,
                new StructureTemplatePool(
                        emptyHolder,
                        ImmutableList.of(
                                Pair.of(StructurePoolElement.single("humanoidcompanions:fortified_desert"), 2),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:sandstone_house"), 2),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:desert"), 2),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:desert_double"), 1)
                        ),
                        StructureTemplatePool.Projection.RIGID
                ));
        context.register(HCKeys.SPRUCE_POOL,
                new StructureTemplatePool(
                        emptyHolder,
                        ImmutableList.of(
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_mixed1"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_mixed2"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_mixed3"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_spruce_darkoak"), 3),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_mixed_double"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:cabin"), 3),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:cabin2"), 3),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_triple"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:medieval_spruce_double"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:spruce_house"), 2)
                        ),
                        StructureTemplatePool.Projection.RIGID
                ));
        context.register(HCKeys.TERRACOTTA_POOL,
                new StructureTemplatePool(
                        emptyHolder,
                        ImmutableList.of(
                                Pair.of(StructurePoolElement.single("humanoidcompanions:terracotta1"), 2),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:terracotta2"), 2),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:terracotta_double"), 1)
                        ),
                        StructureTemplatePool.Projection.RIGID
                ));
        context.register(HCKeys.COMPANION_POOL,
                new StructureTemplatePool(
                        emptyHolder,
                        ImmutableList.of(
                                Pair.of(StructurePoolElement.single("humanoidcompanions:companions/knight"), 2),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:companions/archer"), 2),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:companions/arbalist"), 1),
                                Pair.of(StructurePoolElement.single("humanoidcompanions:companions/axeguard"), 1)
                        ),
                        StructureTemplatePool.Projection.RIGID
                ));
    }
}
