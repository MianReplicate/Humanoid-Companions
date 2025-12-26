package mc.mian.humanoidcompanions.datagen;

import mc.mian.humanoidcompanions.common.entity.HCEntities;
import mc.mian.humanoidcompanions.common.item.HCItems;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

// TODO: translate everything :D
public class HCLangProvider extends LanguageProvider {
    public static final String MOD_ID = HCConstants.MOD_ID;
    
    public HCLangProvider(PackOutput output) {
        super(output, MOD_ID, "en_us");
    }


    @Override
    protected void addTranslations() {
        addItem(HCItems.ARBALIST_SPAWN_EGG, "Arbalist Spawn Egg");
        addItem(HCItems.AXE_GUARD_SPAWN_EGG, "Axeguard Spawn Egg");
        addItem(HCItems.ARCHER_SPAWN_EGG, "Archer Spawn Egg");
        addItem(HCItems.KNIGHT_SPAWN_EGG, "Knight Spawn Egg");

        addEntityType(HCEntities.ARBALIST, "Arbalist");
        addEntityType(HCEntities.AXEGUARD, "Axeguard");
        addEntityType(HCEntities.ARCHER, "Archer");
        addEntityType(HCEntities.KNIGHT, "Knight");

        add("container.humanoidcompanions.companion_inventory", "Companion Inventory");

        add("title.humanoidcompanions.class", "Class");
        add("title.humanoidcompanions.health", "Health");

//        add("title.humanoidcompanions.health", "Health");
    }
}
