package mc.mian.humanoidcompanions.common.structure.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import mc.mian.humanoidcompanions.common.structure.HCStructures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.DimensionPadding;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasBinding;
import net.minecraft.world.level.levelgen.structure.pools.alias.PoolAliasLookup;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.List;
import java.util.Optional;

public class CompanionHouseStructure extends Structure {
    public static final MapCodec<CompanionHouseStructure> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(CompanionHouseStructure.settingsCodec(instance),
                    StructureTemplatePool.CODEC.fieldOf("start_pool").forGetter(structure -> structure.startPool),
                    ResourceLocation.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(structure -> structure.startJigsawName),
                    Codec.intRange(0, 7).fieldOf("size").forGetter(structure -> structure.size),
                    HeightProvider.CODEC.fieldOf("start_height").forGetter(structure -> structure.startHeight),
                    Heightmap.Types.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(structure -> structure.projectStartToHeightmap),
                    Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(structure -> structure.maxDistanceFromCenter),
                    Codec.list(PoolAliasBinding.CODEC).optionalFieldOf("pool_aliases", List.of()).forGetter(p_309350_ -> p_309350_.poolAliases),
                    DimensionPadding.CODEC.optionalFieldOf("dimension_padding", JigsawStructure.DEFAULT_DIMENSION_PADDING).forGetter(p_341952_ -> p_341952_.dimensionPadding),
                    LiquidSettings.CODEC.optionalFieldOf("liquid_settings", JigsawStructure.DEFAULT_LIQUID_SETTINGS).forGetter(p_341953_ -> p_341953_.liquidSettings)
            ).apply(instance, CompanionHouseStructure::new));

    private final Holder<StructureTemplatePool> startPool;
    private final Optional<ResourceLocation> startJigsawName;
    private final int size;
    private final HeightProvider startHeight;
    private final Optional<Heightmap.Types> projectStartToHeightmap;
    private final int maxDistanceFromCenter;
    private final List<PoolAliasBinding> poolAliases;
    private final DimensionPadding dimensionPadding;
    private final LiquidSettings liquidSettings;

    public CompanionHouseStructure(Structure.StructureSettings config,
                                   Holder<StructureTemplatePool> startPool,
                                   Optional<ResourceLocation> startJigsawName,
                                   int size,
                                   HeightProvider startHeight,
                                   Optional<Heightmap.Types> projectStartToHeightmap,
                                   int maxDistanceFromCenter,
                                   List<PoolAliasBinding> poolAliases,
                                   DimensionPadding dimensionPadding,
                                   LiquidSettings liquidSettings) {
        super(config);
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
        this.poolAliases = poolAliases;
        this.dimensionPadding = dimensionPadding;
        this.liquidSettings = liquidSettings;
    }

    public CompanionHouseStructure(Structure.StructureSettings config,
                                   Holder<StructureTemplatePool> startPool) {
        this(config, startPool, Optional.empty(), 1, ConstantHeight.of(VerticalAnchor.absolute(0)),
                Optional.of(Heightmap.Types.WORLD_SURFACE_WG), 50, List.of(), JigsawStructure.DEFAULT_DIMENSION_PADDING, JigsawStructure.DEFAULT_LIQUID_SETTINGS);
    }

    private static boolean isFeatureChunk(Structure.GenerationContext context) {
        BlockPos blockPos = context.chunkPos().getWorldPosition();

        int landHeight = context.chunkGenerator().getFirstOccupiedHeight(blockPos.getX(), blockPos.getZ(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());

        NoiseColumn columnOfBlocks = context.chunkGenerator().getBaseColumn(blockPos.getX(), blockPos.getZ(),
                context.heightAccessor(), context.randomState());

        BlockState topBlock = columnOfBlocks.getBlock(landHeight);

        return topBlock.getFluidState().isEmpty();
    }

    @Override
    public Optional<Structure.GenerationStub> findGenerationPoint(Structure.GenerationContext context) {

        if (!CompanionHouseStructure.isFeatureChunk(context)) {
            return Optional.empty();
        }

        BlockPos blockPos = context.chunkPos().getMiddleBlockPosition(0);

        Optional<Structure.GenerationStub> structurePiecesGenerator =
                JigsawPlacement.addPieces(
                        context,
                        this.startPool,
                        this.startJigsawName,
                        this.size,
                        blockPos,
                        false,
                        this.projectStartToHeightmap,
                        this.maxDistanceFromCenter,
                        PoolAliasLookup.create(this.poolAliases, blockPos, context.seed()),
                        this.dimensionPadding,
                        this.liquidSettings
                );

        return structurePiecesGenerator;
    }

    @Override
    public StructureType<?> type() {
        return HCStructures.COMPANION_HOUSE.get();
    }
}
