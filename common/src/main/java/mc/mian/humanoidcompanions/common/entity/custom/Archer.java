package mc.mian.humanoidcompanions.common.entity.custom;

import mc.mian.humanoidcompanions.common.entity.custom.ai.ArcherRangedBowAttackGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.function.Predicate;


public class Archer extends AbstractHumanCompanionEntity implements RangedAttackMob {

    public Archer(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        this.goalSelector.addGoal(2, new ArcherRangedBowAttackGoal<>(this, 1.0D, 20, 20.0F));

    }

    @Override
    public boolean isValidWeapon(ItemStack stack) {
        return stack.getItem() instanceof BowItem;
    }

    public void tick() {
        super.tick();
    }

    @Override
    public void performRangedAttack(LivingEntity toAttack, float velocity) {
        ItemStack bow = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this, Items.BOW));
        ItemStack arrow = this.getProjectile(bow);
        AbstractArrow abstractarrow = this.getArrow(arrow, velocity, bow);
        double d0 = toAttack.getX() - this.getX();
        double d1 = toAttack.getY(0.3333333333333333D) - abstractarrow.getY();
        double d2 = toAttack.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        abstractarrow.shoot(d0, d1 + d3 * (double)0.20F, d2, 1.6F, (float)(this.level().getDifficulty().getId() * 3));
        this.playSound(SoundEvents.ARROW_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.level().addFreshEntity(abstractarrow);
        if (!this.level().isClientSide) {
            this.getMainHandItem().hurtAndBreak(1, this, EquipmentSlot.MAINHAND);
            if (this.getMainHandItem().isEmpty()) {
                MutableComponent broken = Component.literal("My bow broke!");
                if (this.isTame()) {
                    this.getOwner().sendSystemMessage(Component.translatable("chat.type.text", this.getDisplayName(),
                            broken));
                }
            }
        }
    }

    protected AbstractArrow getArrow(ItemStack arrow, float velocity, @Nullable ItemStack weapon) {
        return ProjectileUtil.getMobArrow(this, arrow, velocity, weapon);
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
    }

    @Override
    public ItemStack getSpawnWeapon() {
        return Items.BOW.getDefaultInstance();
    }
}