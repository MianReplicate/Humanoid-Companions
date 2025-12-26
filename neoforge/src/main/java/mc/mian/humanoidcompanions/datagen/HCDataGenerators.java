package mc.mian.humanoidcompanions.datagen;

import mc.mian.humanoidcompanions.common.item.HCItems;
import mc.mian.humanoidcompanions.common.util.HCUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.server.packs.PackType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

public class HCDataGenerators {
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
            gen.addProvider(ev.includeServer(), new HCLangProvider(packOutput));
            gen.addProvider(ev.includeServer(), new HCItemModelProvider(packOutput, efh));
            gen.addProvider(ev.includeServer(), new HCItemTagsProvider(packOutput, provider));
            gen.addProvider(ev.includeServer(), new HCBiomeTagsProvider(packOutput, provider));
            gen.addProvider(ev.includeServer(), new HCWorldGenProvider(packOutput, provider));
        }
    }

    private static void addVirtualPackContents(ExistingFileHelper existingFileHelper) {
        existingFileHelper.trackGenerated(
                HCUtil.modLoc(HCItems.AXE_GUARD_SPAWN_EGG.getId().getPath()), PackType.CLIENT_RESOURCES, PATH_SUFFIX, PATH_ITEM_PREFIX
        );
        existingFileHelper.trackGenerated(
                HCUtil.modLoc(HCItems.ARCHER_SPAWN_EGG.getId().getPath()), PackType.CLIENT_RESOURCES, PATH_SUFFIX, PATH_ITEM_PREFIX
        );
        existingFileHelper.trackGenerated(
                HCUtil.modLoc(HCItems.ARBALIST_SPAWN_EGG.getId().getPath()), PackType.CLIENT_RESOURCES, PATH_SUFFIX, PATH_ITEM_PREFIX
        );
        existingFileHelper.trackGenerated(
                HCUtil.modLoc(HCItems.KNIGHT_SPAWN_EGG.getId().getPath()), PackType.CLIENT_RESOURCES, PATH_SUFFIX, PATH_ITEM_PREFIX
        );
    }
}
