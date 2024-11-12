package mc.mian.humanoidcompanions.datagen;

import mc.mian.humanoidcompanions.common.block.TemplateBlocks;
import mc.mian.humanoidcompanions.common.item.HCItems;
import mc.mian.humanoidcompanions.util.HCUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.concurrent.CompletableFuture;

public class TemplateDataGenerators {
    private static final String PATH_ITEM_PREFIX = "textures/item";
    private static final String PATH_BLOCK_PREFIX = "textures/block";
    private static final String PATH_SUFFIX = ".png";

    @SubscribeEvent
    public static void generateData(GatherDataEvent ev) {
        final CompletableFuture<HolderLookup.Provider> provider = ev.getLookupProvider();
        final DataGenerator gen = ev.getGenerator();
        final PackOutput packOutput = gen.getPackOutput();
        final ExistingFileHelper efh = ev.getExistingFileHelper();

        addVirtualPackContents(efh);

        if (ev.includeServer()) {
            gen.addProvider(ev.includeServer(), new TemplateLangProvider(packOutput));
            gen.addProvider(ev.includeServer(), new TemplateItemModelProvider(packOutput, efh));
            gen.addProvider(ev.includeServer(), new TemplateStateAndModelProvider(packOutput, efh));
        }
    }

    private static void addVirtualPackContents(ExistingFileHelper existingFileHelper) {
        existingFileHelper.trackGenerated(
                HCUtil.modLoc(HCItems.TEMPLATE_ITEM.getId().getPath()), PackType.CLIENT_RESOURCES, PATH_SUFFIX, PATH_ITEM_PREFIX
        );
        existingFileHelper.trackGenerated(
                HCUtil.modLoc(TemplateBlocks.TEMPLATE_BLOCK.getId().getPath()), PackType.CLIENT_RESOURCES, PATH_SUFFIX, PATH_BLOCK_PREFIX
        );
    }
}