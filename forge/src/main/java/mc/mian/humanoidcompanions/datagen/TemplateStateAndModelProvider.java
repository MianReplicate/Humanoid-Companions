package mc.mian.humanoidcompanions.datagen;

import mc.mian.humanoidcompanions.common.block.TemplateBlocks;
import mc.mian.humanoidcompanions.util.HCConstants;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class TemplateStateAndModelProvider extends BlockStateProvider {
    public TemplateStateAndModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, HCConstants.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        ModelFile crystal_block_model = cubeAll(TemplateBlocks.TEMPLATE_BLOCK.get());
        simpleBlockWithItem(TemplateBlocks.TEMPLATE_BLOCK.get(), crystal_block_model);
    }
}
