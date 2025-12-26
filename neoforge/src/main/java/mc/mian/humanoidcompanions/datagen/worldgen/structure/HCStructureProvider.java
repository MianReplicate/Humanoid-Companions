package mc.mian.humanoidcompanions.datagen.worldgen.structure;

import mc.mian.humanoidcompanions.common.key.HCKeys;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;

import java.util.Map;

public class HCStructureProvider {
    public static void bootstrap(BootstrapContext<Structure> context){
        HolderGetter<Biome> biomeGetter = context.lookup(Registries.BIOME);
        HolderGetter<StructureTemplatePool> poolGetter = context.lookup(Registries.TEMPLATE_POOL);

        context.register(HCKeys.Structures.ACACIA_HOUSE, new JigsawStructure(
                new Structure.StructureSettings(
                        biomeGetter.getOrThrow(HCKeys.ACACIA_HOUSE),
                        Map.of(),
                        GenerationStep.Decoration.SURFACE_STRUCTURES,
                        TerrainAdjustment.BEARD_THIN
                ),
                poolGetter.getOrThrow(HCKeys.ACACIA_POOL),
                2,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                false,
                Heightmap.Types.WORLD_SURFACE_WG
        ));

        context.register(HCKeys.Structures.BIRCH_HOUSE, new JigsawStructure(
                new Structure.StructureSettings(
                        biomeGetter.getOrThrow(HCKeys.BIRCH_HOUSE),
                        Map.of(),
                        GenerationStep.Decoration.SURFACE_STRUCTURES,
                        TerrainAdjustment.BEARD_THIN
                ),
                poolGetter.getOrThrow(HCKeys.BIRCH_POOL),
                2,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                false,
                Heightmap.Types.WORLD_SURFACE_WG
        ));

        context.register(HCKeys.Structures.DARK_OAK_HOUSE, new JigsawStructure(
                new Structure.StructureSettings(
                        biomeGetter.getOrThrow(HCKeys.DARK_OAK_HOUSE),
                        Map.of(),
                        GenerationStep.Decoration.SURFACE_STRUCTURES,
                        TerrainAdjustment.BEARD_THIN
                ),
                poolGetter.getOrThrow(HCKeys.DARK_OAK_POOL),
                2,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                false,
                Heightmap.Types.WORLD_SURFACE_WG
        ));

        context.register(HCKeys.Structures.OAK_BIRCH_HOUSE, new JigsawStructure(
                new Structure.StructureSettings(
                        biomeGetter.getOrThrow(HCKeys.OAK_BIRCH_HOUSE),
                        Map.of(),
                        GenerationStep.Decoration.SURFACE_STRUCTURES,
                        TerrainAdjustment.BEARD_THIN
                ),
                poolGetter.getOrThrow(HCKeys.OAK_BIRCH_POOL),
                2,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                false,
                Heightmap.Types.WORLD_SURFACE_WG
        ));

        context.register(HCKeys.Structures.OAK_HOUSE, new JigsawStructure(
                new Structure.StructureSettings(
                        biomeGetter.getOrThrow(HCKeys.OAK_HOUSE),
                        Map.of(),
                        GenerationStep.Decoration.SURFACE_STRUCTURES,
                        TerrainAdjustment.BEARD_THIN
                ),
                poolGetter.getOrThrow(HCKeys.OAK_POOL),
                2,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                false,
                Heightmap.Types.WORLD_SURFACE_WG
        ));

        context.register(HCKeys.Structures.SANDSTONE_HOUSE, new JigsawStructure(
                new Structure.StructureSettings(
                        biomeGetter.getOrThrow(HCKeys.SANDSTONE_HOUSE),
                        Map.of(),
                        GenerationStep.Decoration.SURFACE_STRUCTURES,
                        TerrainAdjustment.BEARD_THIN
                ),
                poolGetter.getOrThrow(HCKeys.SANDSTONE_POOL),
                2,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                false,
                Heightmap.Types.WORLD_SURFACE_WG
        ));

        context.register(HCKeys.Structures.SPRUCE_HOUSE, new JigsawStructure(
                new Structure.StructureSettings(
                        biomeGetter.getOrThrow(HCKeys.SPRUCE_HOUSE),
                        Map.of(),
                        GenerationStep.Decoration.SURFACE_STRUCTURES,
                        TerrainAdjustment.BEARD_THIN
                ),
                poolGetter.getOrThrow(HCKeys.SPRUCE_POOL),
                2,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                false,
                Heightmap.Types.WORLD_SURFACE_WG
        ));

        context.register(HCKeys.Structures.TERRACOTTA_HOUSE, new JigsawStructure(
                new Structure.StructureSettings(
                        biomeGetter.getOrThrow(HCKeys.TERRACOTTA_HOUSE),
                        Map.of(),
                        GenerationStep.Decoration.SURFACE_STRUCTURES,
                        TerrainAdjustment.BEARD_THIN
                ),
                poolGetter.getOrThrow(HCKeys.TERRACOTTA_POOL),
                2,
                ConstantHeight.of(VerticalAnchor.absolute(0)),
                false,
                Heightmap.Types.WORLD_SURFACE_WG
        ));
    }
}
