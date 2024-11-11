package mc.mian.humanoidcompanions.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class HCTags {
    public static final TagKey<Item> AXES = makeItem(HCUtil.modLoc("axes"));
    public static final TagKey<Item> SWORDS = makeItem(HCUtil.modLoc("swords"));

    private static TagKey<Item> makeItem(String domain, String path) {
        return TagKey.create(Registries.ITEM, new ResourceLocation(domain, path));
    }

    private static TagKey<Item> makeItem(ResourceLocation resourceLocation) {
        return TagKey.create(Registries.ITEM, resourceLocation);
    }
}
