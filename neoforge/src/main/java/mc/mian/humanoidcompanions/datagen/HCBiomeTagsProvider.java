package mc.mian.humanoidcompanions.datagen;

import mc.mian.humanoidcompanions.common.key.HCKeys;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

public class HCBiomeTagsProvider {
    TagProvider<Biome> biomeTags;

    public HCBiomeTagsProvider(GatherDataEvent event, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        biomeTags = TagProvider.create(event, Registries.BIOME, lookupProvider);
    }

    public TagProvider<Biome> provide() {
        biomeTags.tag(HCKeys.ACACIA_HOUSE).addTag(BiomeTags.IS_SAVANNA);
        biomeTags.tag(HCKeys.BIRCH_HOUSE).add(Biomes.BIRCH_FOREST, Biomes.OLD_GROWTH_BIRCH_FOREST).addOptionalTag(Tags.Biomes.IS_BIRCH_FOREST);
        biomeTags.tag(HCKeys.DARK_OAK_HOUSE).add(Biomes.DARK_FOREST);
        biomeTags.tag(HCKeys.OAK_BIRCH_HOUSE).addTag(BiomeTags.IS_FOREST).addOptionalTag(Tags.Biomes.IS_FOREST);
        biomeTags.tag(HCKeys.OAK_HOUSE).addTags(BiomeTags.IS_JUNGLE, BiomeTags.IS_FOREST, BiomeTags.IS_HILL).addOptionalTags(Tags.Biomes.IS_HILL, Tags.Biomes.IS_FOREST, Tags.Biomes.IS_PLAINS, Tags.Biomes.IS_JUNGLE);
        biomeTags.tag(HCKeys.SANDSTONE_HOUSE).addTags(BiomeTags.IS_BEACH, BiomeTags.HAS_DESERT_PYRAMID).addOptionalTags(Tags.Biomes.IS_BEACH, Tags.Biomes.IS_DESERT);
        biomeTags.tag(HCKeys.SPRUCE_HOUSE).addTags(BiomeTags.SPAWNS_SNOW_FOXES, BiomeTags.HAS_VILLAGE_SNOWY, BiomeTags.IS_TAIGA).addOptionalTags(Tags.Biomes.IS_TAIGA, Tags.Biomes.IS_SNOWY, Tags.Biomes.IS_WINDSWEPT);
        biomeTags.tag(HCKeys.TERRACOTTA_HOUSE).addTags(BiomeTags.IS_BADLANDS).addOptionalTags(Tags.Biomes.IS_BADLANDS);

        return biomeTags;
    }
}
