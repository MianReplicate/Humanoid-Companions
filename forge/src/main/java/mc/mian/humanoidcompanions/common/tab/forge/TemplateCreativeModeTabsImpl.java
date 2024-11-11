package mc.mian.humanoidcompanions.common.tab.forge;

import mc.mian.humanoidcompanions.common.tab.HCTabs;
import mc.mian.humanoidcompanions.common.item.TemplateItems;
import mc.mian.humanoidcompanions.util.HCConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;

public class TemplateCreativeModeTabsImpl {
    public static CreativeModeTab createTab(String title){
        return CreativeModeTab.builder()
                .icon(HCTabs::makeIcon)
                .title(Component.translatable("itemGroup."+ HCConstants.MOD_ID+"."+title))
                .displayItems((itemDisplayParameters, output) -> TemplateItems.ITEMS.getEntries().forEach(item -> output.accept(item.get())))
                .build();
    }
}
