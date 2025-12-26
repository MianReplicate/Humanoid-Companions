package mc.mian.humanoidcompanions.datagen;

import mc.mian.humanoidcompanions.common.key.HCKeys;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

public class HCItemTagsProvider {
    TagProvider<Item> itemTags;

    public HCItemTagsProvider(GatherDataEvent event, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        itemTags = TagProvider.create(event, Registries.ITEM, lookupProvider);
    }

    public TagProvider<Item> provide() {
        itemTags.tag(HCKeys.AXES).addTag(ItemTags.AXES).addOptionalTag(ResourceLocation.fromNamespaceAndPath("forge", "tools/axes"));
        itemTags.tag(HCKeys.SWORDS).addTag(ItemTags.SWORDS).addOptionalTag(ResourceLocation.fromNamespaceAndPath("forge", "tools/swords"));

        return itemTags;
    }
}
