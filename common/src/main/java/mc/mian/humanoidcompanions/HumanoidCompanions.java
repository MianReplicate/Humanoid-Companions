package mc.mian.humanoidcompanions;

import mc.mian.humanoidcompanions.common.block.TemplateBlocks;
import mc.mian.humanoidcompanions.common.network.HCNetwork;
import mc.mian.humanoidcompanions.common.tab.HCTabs;
import mc.mian.humanoidcompanions.common.item.TemplateItems;
import mc.mian.humanoidcompanions.common.sound.TemplateSoundEvents;
import mc.mian.humanoidcompanions.config.HCConfiguration;
import mc.mian.humanoidcompanions.util.HCConstants;

public class HumanoidCompanions {
    public static HCConfiguration config;

    public static void init() {
        HCConstants.LOGGER.info("Meow? MEOW!!");
        HCTabs.TABS.register();
        TemplateBlocks.BLOCKS.register();
        TemplateItems.ITEMS.register();
        TemplateSoundEvents.SOUND_EVENTS.register();
        HCNetwork.register();
    }
}