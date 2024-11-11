package mc.mian.humanoidcompanions.common.entity.custom;

import mc.mian.humanoidcompanions.common.entity.HCEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.*;

public class AbstractHCEntity extends TamableAnimal {
    private static final EntityDataAccessor<Integer> DATA_TYPE_ID = SynchedEntityData.defineId(AbstractHCEntity.class,
            EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SEX = SynchedEntityData.defineId(AbstractHCEntity.class,
            EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BASE_HEALTH = SynchedEntityData.defineId(AbstractHCEntity.class,
            EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> EXP_LVL = SynchedEntityData.defineId(AbstractHCEntity.class,
            EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> EATING = SynchedEntityData.defineId(AbstractHCEntity.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> ALERT = SynchedEntityData.defineId(AbstractHCEntity.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HUNTING = SynchedEntityData.defineId(AbstractHCEntity.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> PATROLLING = SynchedEntityData.defineId(AbstractHCEntity.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> FOLLOWING = SynchedEntityData.defineId(AbstractHCEntity.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> GUARDING = SynchedEntityData.defineId(AbstractHCEntity.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> STATIONERY =
            SynchedEntityData.defineId(AbstractHCEntity.class,
                    EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Optional<BlockPos>> PATROL_POS = SynchedEntityData.defineId(AbstractHCEntity.class,
            EntityDataSerializers.OPTIONAL_BLOCK_POS);
    private static final EntityDataAccessor<Integer> PATROL_RADIUS = SynchedEntityData.defineId(AbstractHCEntity.class,
            EntityDataSerializers.INT);
    private static final EntityDataAccessor<String> FOOD1 = SynchedEntityData.defineId(AbstractHCEntity.class,
            EntityDataSerializers.STRING);
    private static final EntityDataAccessor<String> FOOD2 = SynchedEntityData.defineId(AbstractHCEntity.class,
            EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Integer> FOOD1_AMT = SynchedEntityData.defineId(AbstractHCEntity.class,
            EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> FOOD2_AMT = SynchedEntityData.defineId(AbstractHCEntity.class,
            EntityDataSerializers.INT);

    public SimpleContainer inventory = new SimpleContainer(27);
    public EquipmentSlot[] armorTypes = new EquipmentSlot[]{EquipmentSlot.FEET, EquipmentSlot.LEGS,
            EquipmentSlot.CHEST, EquipmentSlot.HEAD};
    public List<NearestAttackableTargetGoal> alertMobGoals = new ArrayList<>();
    public List<NearestAttackableTargetGoal> huntMobGoals = new ArrayList<>();
    public PatrolGoal patrolGoal;
    public MoveBackToPatrolGoal moveBackGoal;
    public int experienceLevel;
    public int totalExperience;
    public float experienceProgress;
    private int lastLevelUpTime;
    private final Map<String, Integer> foodRequirements = new HashMap<>();
    private String food1;
    private String food2;

    public AbstractHCEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        this.setTame(false);
//        this.setCanPickUpLoot(true);
        ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
        this.getNavigation().setCanFloat(true);
        for (int i = 0; i < CompanionData.alertMobs.length; i++) {
            alertMobGoals.add(new NearestAttackableTargetGoal(this, CompanionData.alertMobs[i], false));
        }
        for (int i = 0; i < CompanionData.huntMobs.length; i++) {
            huntMobGoals.add(new NearestAttackableTargetGoal(this, CompanionData.huntMobs[i], false));
        }
    }

    @Override
    public ItemStack eat(Level world, ItemStack stack) {
        if (stack.isEdible()) {
            this.heal(stack.getItem().getFoodProperties().getNutrition());
        }
        super.eat(world, stack);
        return stack;
    }


    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parent) {
        return HCEntities.Knight.get().create(level);
    }
}
