package mc.mian.humanoidcompanions.common.key;

import mc.mian.humanoidcompanions.common.util.HCUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class HCKeys {
    public static final TagKey<Item> AXES = createItem(HCUtil.modLoc("axes"));
    public static final TagKey<Item> SWORDS = createItem(HCUtil.modLoc("swords"));

    public static final TagKey<Biome> ACACIA_HOUSE = createBiome(HCUtil.modLoc("has_structure/acacia_house"));
    public static final TagKey<Biome> BIRCH_HOUSE = createBiome(HCUtil.modLoc("has_structure/birch_house"));
    public static final TagKey<Biome> DARK_OAK_HOUSE = createBiome(HCUtil.modLoc("has_structure/dark_oak_house"));
    public static final TagKey<Biome> OAK_BIRCH_HOUSE = createBiome(HCUtil.modLoc("has_structure/oak_birch_house"));
    public static final TagKey<Biome> OAK_HOUSE = createBiome(HCUtil.modLoc("has_structure/oak_house"));
    public static final TagKey<Biome> SANDSTONE_HOUSE = createBiome(HCUtil.modLoc("has_structure/sandstone_house"));
    public static final TagKey<Biome> SPRUCE_HOUSE = createBiome(HCUtil.modLoc("has_structure/spruce_house"));
    public static final TagKey<Biome> TERRACOTTA_HOUSE = createBiome(HCUtil.modLoc("has_structure/terracotta_house"));

    public static final ResourceKey<StructureTemplatePool> ACACIA_POOL = createPool(HCUtil.modLoc("acacia_pool"));
    public static final ResourceKey<StructureTemplatePool> BIRCH_POOL = createPool(HCUtil.modLoc("birch_pool"));
    public static final ResourceKey<StructureTemplatePool> DARK_OAK_POOL = createPool(HCUtil.modLoc("dark_oak_pool"));
    public static final ResourceKey<StructureTemplatePool> OAK_BIRCH_POOL = createPool(HCUtil.modLoc("oak_birch_pool"));
    public static final ResourceKey<StructureTemplatePool> OAK_POOL = createPool(HCUtil.modLoc("oak_pool"));
    public static final ResourceKey<StructureTemplatePool> SANDSTONE_POOL = createPool(HCUtil.modLoc("sandstone_pool"));
    public static final ResourceKey<StructureTemplatePool> SPRUCE_POOL = createPool(HCUtil.modLoc("spruce_pool"));
    public static final ResourceKey<StructureTemplatePool> TERRACOTTA_POOL = createPool(HCUtil.modLoc("terracotta_pool"));
    public static final ResourceKey<StructureTemplatePool> COMPANION_POOL = createPool(HCUtil.modLoc("companions"));

    public static final ResourceKey<StructureSet> COMPANION_HOUSE = createSet(HCUtil.modLoc("companion_house"));

    public static class Structures {
        public static final ResourceKey<Structure> ACACIA_HOUSE = createStructure(HCUtil.modLoc("acacia_house"));
        public static final ResourceKey<Structure> BIRCH_HOUSE = createStructure(HCUtil.modLoc("birch_house"));
        public static final ResourceKey<Structure> DARK_OAK_HOUSE = createStructure(HCUtil.modLoc("dark_oak_house"));
        public static final ResourceKey<Structure> OAK_BIRCH_HOUSE = createStructure(HCUtil.modLoc("oak_birch_house"));
        public static final ResourceKey<Structure> OAK_HOUSE = createStructure(HCUtil.modLoc("oak_house"));
        public static final ResourceKey<Structure> SANDSTONE_HOUSE = createStructure(HCUtil.modLoc("sandstone_house"));
        public static final ResourceKey<Structure> SPRUCE_HOUSE = createStructure(HCUtil.modLoc("spruce_house"));
        public static final ResourceKey<Structure> TERRACOTTA_HOUSE = createStructure(HCUtil.modLoc("terracotta_house"));

    }

    private static TagKey<Item> createItem(ResourceLocation location) {
        return TagKey.create(Registries.ITEM, location);
    }

    private static TagKey<Biome> createBiome(ResourceLocation location) {
        return TagKey.create(Registries.BIOME, location);
    }

    private static ResourceKey<StructureTemplatePool> createPool(ResourceLocation location) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, location);
    }

    private static ResourceKey<Structure> createStructure(ResourceLocation location) {
        return ResourceKey.create(Registries.STRUCTURE, location);
    }

    private static ResourceKey<StructureSet> createSet(ResourceLocation location) {
        return ResourceKey.create(Registries.STRUCTURE_SET, location);
    }
}
