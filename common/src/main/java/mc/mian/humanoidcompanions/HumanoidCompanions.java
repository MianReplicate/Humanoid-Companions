package mc.mian.humanoidcompanions;

import mc.mian.humanoidcompanions.common.network.HCNetwork;
import mc.mian.humanoidcompanions.common.item.HCItems;
import mc.mian.humanoidcompanions.config.HCConfiguration;
import mc.mian.humanoidcompanions.util.HCConstants;

public class HumanoidCompanions {
    public static HCConfiguration config;

    public static void init() {
        HCConstants.LOGGER.info("Meow? MEOW!!");
        HCItems.ITEMS.register();
        HCNetwork.register();
    }
}