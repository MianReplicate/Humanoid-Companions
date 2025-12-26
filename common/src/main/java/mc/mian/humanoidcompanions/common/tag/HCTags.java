package mc.mian.humanoidcompanions.common.tag;

import mc.mian.humanoidcompanions.common.util.HCUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class HCTags {
    public static final TagKey<Item> AXES = create(HCUtil.modLoc("axes"));
    public static final TagKey<Item> SWORDS = create(HCUtil.modLoc("swords"));

    private static TagKey<Item> create(ResourceLocation location) {
        return TagKey.create(Registries.ITEM, location);
    }
}
