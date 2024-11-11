package mc.mian.humanoidcompanions.config;

import net.minecraftforge.common.ForgeConfigSpec;

import javax.annotation.Nullable;

public class HCConfiguration {
    public final ForgeConfigSpec.BooleanValue friendlyFireCompanions;
    public final ForgeConfigSpec.BooleanValue friendlyFirePlayer;
    public final ForgeConfigSpec.BooleanValue fallDamage;
    public final ForgeConfigSpec.BooleanValue spawnWithArmor;
    public final ForgeConfigSpec.BooleanValue spawnWithWeapon;
    public final ForgeConfigSpec.IntValue baseHealth;
    public final ForgeConfigSpec.BooleanValue lowHealthFood;
    public final ForgeConfigSpec.BooleanValue creeperWarning;

    public HCConfiguration(final ForgeConfigSpec.Builder builder) {
        builder.push("Companion Settings");
        this.friendlyFireCompanions = buildBoolean(builder, "Friendly Fire:", true, "Toggles friendly fire between companions.");
        this.friendlyFirePlayer = buildBoolean(builder, "Player Friendly Fire:", true, "Toggles friendly fire when companions hit players.");
        this.fallDamage = buildBoolean(builder, "Fall Damage:", true, "Toggles fall damage for companions.");
        this.spawnWithArmor = buildBoolean(builder, "Spawn With Armor:", true, "Toggles whether companions spawn with armor.");
        this.spawnWithWeapon = buildBoolean(builder, "Spawn With Weapon:", true, "Toggles whether companions spawn with weapons.");
        this.baseHealth = buildInt(builder, "Base Health:", 20, 5, Integer.MAX_VALUE, "Sets the base health of the companion. Companions can spawn with a -+4 range from this value.");
        this.lowHealthFood = buildBoolean(builder, "Low Health Food Warning:", true, "Toggles whether companions ask for food if their health is below half.");
        this.creeperWarning = buildBoolean(builder, "Creeper Warning:", true, "Toggles whether companions alert you if a creeper is nearby.");
    }

    private static ForgeConfigSpec.IntValue buildInt(final ForgeConfigSpec.Builder builder, String translationPath, int defaultValue, int min, int max, @Nullable String comment) {
        return comment == null ? builder.translation(translationPath).defineInRange(translationPath, defaultValue, min, max) : builder.comment(comment).translation(translationPath).defineInRange(translationPath, defaultValue, min, max);
    }

    private static ForgeConfigSpec.BooleanValue buildBoolean(final ForgeConfigSpec.Builder builder, String translationPath, boolean defaultValue, String comment) {
        return builder.comment(comment).translation(translationPath).define(translationPath, defaultValue);
    }
}
