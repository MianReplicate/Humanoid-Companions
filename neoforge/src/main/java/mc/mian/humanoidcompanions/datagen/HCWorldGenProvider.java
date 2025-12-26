package mc.mian.humanoidcompanions.datagen;

import mc.mian.humanoidcompanions.common.util.HCConstants;
import mc.mian.humanoidcompanions.datagen.worldgen.structure.HCPoolProvider;
import mc.mian.humanoidcompanions.datagen.worldgen.structure.HCSetProvider;
import mc.mian.humanoidcompanions.datagen.worldgen.structure.HCStructureProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class HCWorldGenProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.TEMPLATE_POOL, HCPoolProvider::bootstrap)
            .add(Registries.STRUCTURE_SET, HCSetProvider::bootstrap)
            .add(Registries.STRUCTURE, HCStructureProvider::bootstrap);

    public HCWorldGenProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, BUILDER, Set.of(HCConstants.MOD_ID));
    }
}
