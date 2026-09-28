package mc.mian.humanoidcompanions.common;

import mc.mian.humanoidcompanions.common.entity.HCEntities;
import mc.mian.humanoidcompanions.common.menu.HCMenus;
import mc.mian.humanoidcompanions.common.network.HCNetwork;
import mc.mian.humanoidcompanions.common.item.HCItems;
import mc.mian.humanoidcompanions.common.util.HCConstants;

// TODO: convert literals to translatables
// TODO: companion inventories arent always synced
public class HumanoidCompanions {
    public static void init() {
        HCConstants.LOGGER.info("I LOVE PEOPLE!");

        HCMenus.MENU_TYPES.register();
        HCEntities.ENTITIES.register();
        HCItems.ITEMS.register();
        HCNetwork.register();
    }
}