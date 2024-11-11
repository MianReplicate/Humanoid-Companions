package mc.mian.humanoidcompanions.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ConfigHolder {
    public static final ForgeConfigSpec SERVER_SPEC;
    public static final HCConfiguration SERVER;

    static{
        final Pair<HCConfiguration, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(HCConfiguration::new);
        SERVER = specPair.getLeft();
        SERVER_SPEC = specPair.getRight();
    }
}
