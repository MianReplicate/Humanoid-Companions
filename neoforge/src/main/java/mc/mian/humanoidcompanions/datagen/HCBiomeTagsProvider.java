package mc.mian.humanoidcompanions.datagen;

import mc.mian.humanoidcompanions.common.key.HCKeys;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class HCBiomeTagsProvider extends BiomeTagsProvider {
    public HCBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(HCKeys.ACACIA_HOUSE).addTag(BiomeTags.IS_SAVANNA);
        tag(HCKeys.BIRCH_HOUSE).add(Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST).addOptionalTag(Tags.Biomes.IS_BIRCH_FOREST);
        tag(HCKeys.DARK_OAK_HOUSE).add(Biomes.DARK_FOREST);
        tag(HCKeys.OAK_BIRCH_HOUSE).addTag(BiomeTags.IS_FOREST).addOptionalTag(Tags.Biomes.IS_FOREST);
        tag(HCKeys.OAK_HOUSE).addTags(BiomeTags.IS_JUNGLE, BiomeTags.IS_FOREST, BiomeTags.IS_HILL).addOptionalTags(Tags.Biomes.IS_HILL, Tags.Biomes.IS_FOREST, Tags.Biomes.IS_PLAINS, Tags.Biomes.IS_JUNGLE);
        tag(HCKeys.SANDSTONE_HOUSE).addTags(BiomeTags.IS_BEACH, BiomeTags.HAS_DESERT_PYRAMID).addOptionalTags(Tags.Biomes.IS_BEACH, Tags.Biomes.IS_DESERT);
        tag(HCKeys.SPRUCE_HOUSE).addTags(BiomeTags.SPAWNS_SNOW_FOXES, BiomeTags.HAS_VILLAGE_SNOWY, BiomeTags.IS_TAIGA).addOptionalTags(Tags.Biomes.IS_TAIGA, Tags.Biomes.IS_SNOWY, Tags.Biomes.IS_WINDSWEPT);
        tag(HCKeys.TERRACOTTA_HOUSE).addTags(BiomeTags.IS_BADLANDS).addOptionalTags(Tags.Biomes.IS_BADLANDS);
    }
}
