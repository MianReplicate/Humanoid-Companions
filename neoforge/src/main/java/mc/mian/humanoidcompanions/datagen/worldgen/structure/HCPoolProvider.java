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
    }
}
