package mc.mian.humanoidcompanions.datagen;

import mc.mian.humanoidcompanions.datagen.bootstrap.TemplateJukeboxSongsProvider;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class TemplateBootstrapProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.JUKEBOX_SONG, TemplateJukeboxSongsProvider::bootstrap);

    public TemplateBootstrapProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, BUILDER, Set.of(HCConstants.MOD_ID));
    }
}
