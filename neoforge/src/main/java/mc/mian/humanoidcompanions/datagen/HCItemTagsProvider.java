package mc.mian.humanoidcompanions.datagen;

import mc.mian.humanoidcompanions.common.key.HCKeys;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

public class HCItemTagsProvider extends IntrinsicHolderTagsProvider<Item> {
    public HCItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.ITEM, lookupProvider, (item) -> item.builtInRegistryHolder().key(), HCConstants.MOD_ID, null);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(HCKeys.AXES).addTag(ItemTags.AXES).addOptionalTag(ResourceLocation.fromNamespaceAndPath("forge", "tools/axes"));
        tag(HCKeys.SWORDS).addTag(ItemTags.SWORDS).addOptionalTag(ResourceLocation.fromNamespaceAndPath("forge", "tools/swords"));
    }
}
