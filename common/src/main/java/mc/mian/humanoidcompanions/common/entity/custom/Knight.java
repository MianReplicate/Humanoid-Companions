package mc.mian.humanoidcompanions.common.entity.custom;

import mc.mian.humanoidcompanions.common.key.HCKeys;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.Level;


public class Knight extends AbstractHumanCompanionEntity {

    public Knight(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
    }

    @Override
    public boolean isValidWeapon(ItemStack stack) {
    	return stack.is(HCKeys.SWORDS) || (!stack.is(HCKeys.AXES) && stack.getItem() instanceof SwordItem);
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
    }

    public void tick() {
        super.tick();
    }

    @Override
    public ItemStack getSpawnWeapon() {
        float materialFloat = this.random.nextFloat();
        if(materialFloat < 0.5F) {
            return Items.WOODEN_SWORD.getDefaultInstance();
        } else if(materialFloat < 0.90F) {
            return Items.STONE_SWORD.getDefaultInstance();
        } else {
            return Items.IRON_SWORD.getDefaultInstance();
        }
    }
}