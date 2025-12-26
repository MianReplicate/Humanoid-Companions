package mc.mian.humanoidcompanions.datagen;

import mc.mian.humanoidcompanions.common.item.HCItems;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class TemplateItemModelProvider extends ItemModelProvider {
    public TemplateItemModelProvider(PackOutput output, ExistingFileHelper efh) {
        super(output, HCConstants.MOD_ID, efh);
    }

    @Override
    protected void registerModels() {
        basicItem(HCItems.TEMPLATE_ITEM.get());
    }
}
