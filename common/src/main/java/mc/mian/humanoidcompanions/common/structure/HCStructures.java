package mc.mian.humanoidcompanions.common.structure;

import com.mojang.serialization.MapCodec;
import mc.mian.humanoidcompanions.common.registry.DeferredRegistry;
import mc.mian.humanoidcompanions.common.registry.RegistrySupplier;
import mc.mian.humanoidcompanions.common.structure.custom.CompanionHouseStructure;
import mc.mian.humanoidcompanions.common.util.HCConstants;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class HCStructures {
    public static final DeferredRegistry<StructureType<?>> STRUCTURE_TYPES = DeferredRegistry.create(HCConstants.MOD_ID, Registries.STRUCTURE_TYPE);

    public static final RegistrySupplier<StructureType<CompanionHouseStructure>> COMPANION_HOUSE = STRUCTURE_TYPES.register("companion_house", () -> typeConvert(CompanionHouseStructure.CODEC));

    private static <S extends Structure> StructureType<S> typeConvert(MapCodec<S> codec) {
        return () -> codec;
    }
}
