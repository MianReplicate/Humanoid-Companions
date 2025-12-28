package mc.mian.humanoidcompanions.common.entity.custom;

import mc.mian.humanoidcompanions.common.entity.custom.ai.ArbalistRangedCrossbowAttackGoal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.CrossbowAttackMob;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.function.Predicate;

public class Arbalist extends AbstractHumanCompanionEntity implements CrossbowAttackMob {

    private static final EntityDataAccessor<Boolean> IS_CHARGING_CROSSBOW = SynchedEntityData.defineId(Arbalist.class,
            EntityDataSerializers.BOOLEAN);

    public Arbalist(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        this.goalSelector.addGoal(3, new ArbalistRangedCrossbowAttackGoal<>(this, 1.0D, 8.0F));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IS_CHARGING_CROSSBOW, false);
    }

    @Override
    public boolean isValidWeapon(ItemStack stack) {
        return stack.getItem() instanceof CrossbowItem;
    }

    public void tick() {
        super.tick();
    }

    public void performRangedAttack(LivingEntity p_33272_, float p_33273_) {
        this.performCrossbowAttack(this, 1.6F);
    }


//    public void shootCrossbowProjectile(LivingEntity entity, ItemStack stack, Projectile projectile, float p_33278_) {
//        this.shootCrossbowProjectile(this, entity, projectile, p_33278_, 1.6F);
//    }

    public void setChargingCrossbow(boolean p_33302_) {
        this.entityData.set(IS_CHARGING_CROSSBOW, p_33302_);
    }

    public void onCrossbowAttackPerformed() {
        this.noActionTime = 0;
    }

    public boolean canFireProjectileWeapon(ProjectileWeaponItem p_33280_) {
        return p_33280_ == Items.CROSSBOW;
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
    }

    @Override
    public ItemStack getSpawnWeapon() {
        return Items.CROSSBOW.getDefaultInstance();
    }
}