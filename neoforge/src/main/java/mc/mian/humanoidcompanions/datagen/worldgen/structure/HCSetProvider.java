package mc.mian.humanoidcompanions.datagen.worldgen.structure;

import mc.mian.humanoidcompanions.common.key.HCKeys;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;

import java.util.List;

public class HCSetProvider {
    public static void bootstrap(BootstrapContext<StructureSet> context){
        HolderGetter<Structure> holderGetter = context.lookup(Registries.STRUCTURE);

        context.register(HCKeys.COMPANION_HOUSE, new StructureSet(
                List.of(
                        new StructureSet.StructureSelectionEntry(holderGetter.getOrThrow(HCKeys.Structures.OAK_HOUSE), 1),
                        new StructureSet.StructureSelectionEntry(holderGetter.getOrThrow(HCKeys.Structures.OAK_BIRCH_HOUSE), 1),
                        new StructureSet.StructureSelectionEntry(holderGetter.getOrThrow(HCKeys.Structures.BIRCH_HOUSE), 1),
                        new StructureSet.StructureSelectionEntry(holderGetter.getOrThrow(HCKeys.Structures.SANDSTONE_HOUSE), 1),
                        new StructureSet.StructureSelectionEntry(holderGetter.getOrThrow(HCKeys.Structures.ACACIA_HOUSE), 1),
                        new StructureSet.StructureSelectionEntry(holderGetter.getOrThrow(HCKeys.Structures.SPRUCE_HOUSE), 1),
                        new StructureSet.StructureSelectionEntry(holderGetter.getOrThrow(HCKeys.Structures.DARK_OAK_HOUSE), 1),
                        new StructureSet.StructureSelectionEntry(holderGetter.getOrThrow(HCKeys.Structures.TERRACOTTA_HOUSE), 1)
                ),
                new RandomSpreadStructurePlacement(
                        15,
                        10,
                        RandomSpreadType.LINEAR,
                        1234567895
                )
        ));
    }
}
