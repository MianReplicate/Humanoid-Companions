package mc.mian.humanoidcompanions;

import mc.mian.humanoidcompanions.common.network.HCNetwork;
import mc.mian.humanoidcompanions.common.item.HCItems;
import mc.mian.humanoidcompanions.common.config.HCConfiguration;
import mc.mian.humanoidcompanions.common.util.HCConstants;

public class HumanoidCompanions {
    public static void init() {
        HCConstants.LOGGER.info("I LOVE PEOPLE!");

        HCItems.ITEMS.register();
        HCNetwork.register();
    }
}