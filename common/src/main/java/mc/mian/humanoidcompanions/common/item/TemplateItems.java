package mc.mian.humanoidcompanions.common.item;

import mc.mian.humanoidcompanions.registry.DeferredRegistry;
import mc.mian.humanoidcompanions.registry.RegistrySupplier;
import mc.mian.humanoidcompanions.util.HCConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;

public class TemplateItems {
    public static final DeferredRegistry<Item> ITEMS = DeferredRegistry.create(HCConstants.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> TEMPLATE_ITEM = ITEMS.register("template_item", () ->
            new RecordItem(11, TemplateSoundEvents.TEMPLATE_SOUND_EVENT.get(), new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 156));
}
