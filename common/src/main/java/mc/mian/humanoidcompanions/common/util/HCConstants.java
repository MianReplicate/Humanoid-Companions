package mc.mian.humanoidcompanions.common.util;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.JukeboxSong;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HCConstants {
    public static final Logger LOGGER = LogManager.getLogger(HCConstants.MOD_ID);

    public static final String MOD_ID = "humanoidcompanions";
    public static final String MOD_DISPLAY_NAME = "Humanoid Companions";

    public static final ResourceKey<JukeboxSong> TEMPLATE_JUKEBOX_SONG = HCUtil.createJukeboxSong(MOD_ID, "template_jukebox_song");
    public static final ResourceLocation TEMPLATE_SOUND = HCUtil.modLoc("scratch");
    public static final ResourceLocation TEMPLATE_SOUND_2 = HCUtil.modLoc("meow");
    public static final ResourceLocation TEMPLATE_SOUND_3 = HCUtil.modLoc("fail");
}
