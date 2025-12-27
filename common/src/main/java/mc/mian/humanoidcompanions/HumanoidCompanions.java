package mc.mian.humanoidcompanions;

import mc.mian.humanoidcompanions.common.entity.HCEntities;
import mc.mian.humanoidcompanions.common.network.HCNetwork;
import mc.mian.humanoidcompanions.common.item.HCItems;
import mc.mian.humanoidcompanions.common.config.HCConfiguration;
import mc.mian.humanoidcompanions.common.util.HCConstants;

// TODO: convert literals to translatables
// TODO: make companions do animation when they attack
// TODO: companion inventories arent always synced
// TODO: expand foods
// TODO: companions dont spawn at their houses?
public class HumanoidCompanions {
    public static void init() {
        HCConstants.LOGGER.info("I LOVE PEOPLE!");

        HCItems.ITEMS.register();
        HCEntities.ENTITIES.register();
        HCNetwork.register();
    }
}