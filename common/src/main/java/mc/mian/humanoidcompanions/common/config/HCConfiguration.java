package mc.mian.humanoidcompanions.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class HCConfiguration {
    public static ForgeConfigSpec.IntValue AVERAGE_HOUSE_SEPARATION;
    public static ForgeConfigSpec.BooleanValue FRIENDLY_FIRE_COMPANIONS;
    public static ForgeConfigSpec.BooleanValue FRIENDLY_FIRE_PLAYER;
    public static ForgeConfigSpec.BooleanValue FALL_DAMAGE;
    public static ForgeConfigSpec.BooleanValue SPAWN_ARMOR;
    public static ForgeConfigSpec.BooleanValue SPAWN_WEAPON;
    public static ForgeConfigSpec.IntValue BASE_HEALTH;
    public static ForgeConfigSpec.BooleanValue LOW_HEALTH_FOOD;
    public static ForgeConfigSpec.BooleanValue CREEPER_WARNING;

    public HCConfiguration(final ForgeConfigSpec.Builder builder) {
        builder.comment("This category holds general values that most people will want to change.");
        builder.push("General Settings");
        builder.comment("Settings for world gen (Doesn't work in 1.18.2 and beyond. Use datapacks instead.)").push("World" +
                " Gen");
        AVERAGE_HOUSE_SEPARATION = builder
                .comment("Average chunk separation between companion houses")
                .defineInRange("averageHouseSeparation", 20, 11, Integer.MAX_VALUE);
        builder.pop();
        builder.push("Companion config");
        FRIENDLY_FIRE_COMPANIONS = builder
                .comment("Toggles friendly fire between companions")
                .define("friendlyFireCompanions", false);
        FRIENDLY_FIRE_PLAYER = builder
                .comment("Toggles friendly fire between player and companion")
                .define("friendlyFirePlayer", true);
        FALL_DAMAGE = builder
                .comment("Toggles fall damage for companions")
                .define("fallDamage", true);
        SPAWN_ARMOR = builder
                .comment("Toggles whether companions spawn with armor")
                .define("spawnArmor", true);
        SPAWN_WEAPON = builder
                .comment("Toggles whether companions spawn with a weapon")
                .define("spawnWeapon", true);
        BASE_HEALTH = builder
                .comment("Sets the base health of each companion. Companions spawn with up to +-4 from the base health")
                .defineInRange("baseHealth", 20, 5, Integer.MAX_VALUE);
        LOW_HEALTH_FOOD = builder
                .comment("Toggles whether companions ask for food if their health goes below half.")
                .define("lowHealthFood", true);
        CREEPER_WARNING = builder
                .comment("Toggles whether companions alert you if a creeper is nearby.")
                .define("creeperWarning", true);
    }
}
