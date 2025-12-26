package mc.mian.humanoidcompanions.datagen.bootstrap;

import mc.mian.humanoidcompanions.common.sound.TemplateSoundEvents;
import mc.mian.humanoidcompanions.common.registry.RegistrySupplier;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;

public class TemplateJukeboxSongsProvider {
    private static void register(
            BootstrapContext<JukeboxSong> context, ResourceKey<JukeboxSong> key, RegistrySupplier<SoundEvent> soundEvent, int lengthInSeconds, int comparatorOutput
    ) {
        context.register(
                key, new JukeboxSong(Holder.direct(soundEvent.get()), Component.translatable(Util.makeDescriptionId("jukebox_song", key.location())), (float)lengthInSeconds, comparatorOutput)
        );
    }

    public static void bootstrap(BootstrapContext<JukeboxSong> context) {
        register(context, HCConstants.TEMPLATE_JUKEBOX_SONG, TemplateSoundEvents.TEMPLATE_SOUND_EVENT, 156, 11);
    }
}
