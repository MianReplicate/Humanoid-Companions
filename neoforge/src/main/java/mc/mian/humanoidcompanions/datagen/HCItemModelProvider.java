package mc.mian.humanoidcompanions.datagen;

import mc.mian.humanoidcompanions.common.item.HCItems;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class HCItemModelProvider extends ItemModelProvider {
    public HCItemModelProvider(PackOutput output, ExistingFileHelper efh) {
        super(output, HCConstants.MOD_ID, efh);
    }

    @Override
    protected void registerModels() {
        spawnEggItem(HCItems.ARBALIST_SPAWN_EGG.get());
        spawnEggItem(HCItems.KNIGHT_SPAWN_EGG.get());
        spawnEggItem(HCItems.ARCHER_SPAWN_EGG.get());
        spawnEggItem(HCItems.AXE_GUARD_SPAWN_EGG.get());
    }
}
