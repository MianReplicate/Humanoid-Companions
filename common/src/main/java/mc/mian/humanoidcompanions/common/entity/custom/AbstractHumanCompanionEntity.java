package mc.mian.humanoidcompanions.common.entity.custom;

import commonnetwork.api.Network;
import mc.mian.humanoidcompanions.common.config.HCConfiguration;
import mc.mian.humanoidcompanions.common.container.CompanionContainer;
import mc.mian.humanoidcompanions.CompanionData;
import mc.mian.humanoidcompanions.common.entity.HCEntities;
import mc.mian.humanoidcompanions.common.entity.custom.ai.*;
import mc.mian.humanoidcompanions.common.network.custom.OpenInventoryPacket;
import mc.mian.humanoidcompanions.common.util.HCUtil;
import mc.mian.humanoidcompanions.platform.Services;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.apache.commons.lang3.ArrayUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractHumanCompanionEntity extends TamableAnimal {

    private static final ResourceLocation BASE_HEALTH_LOC = HCUtil.modLoc("base_health");
    private static final ResourceLocation LEVEL_HEALTH_LOC = HCUtil.modLoc("level_health");

    private static final EntityDataAccessor<Integer> DATA_TYPE_ID = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> SEX = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> BASE_HEALTH = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> EXP_LVL = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> EATING = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> ALERT = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HUNTING = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> PATROLLING = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> FOLLOWING = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> GUARDING = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> STATIONARY =
            SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Optional<BlockPos>> PATROL_POS = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.OPTIONAL_BLOCK_POS);
    private static final EntityDataAccessor<Integer> PATROL_RADIUS = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.INT);
    private static final EntityDataAccessor<String> FOOD1 = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.STRING);
    private static final EntityDataAccessor<String> FOOD2 = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Integer> FOOD1_AMT = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
            EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> FOOD2_AMT = SynchedEntityData.defineId(AbstractHumanCompanionEntity.class,
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
    private final Map<String, Integer> foodRequirements = new HashMap<>(Map.of("", 0));
    private String food1 = "";
    private String food2 = "";

    public AbstractHumanCompanionEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        this.setTame(false, false);
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
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new EatGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new AvoidCreeperGoal(this, Creeper.class, 10.0F, 1.5D, 1.5D));
        this.goalSelector.addGoal(3, new MoveBackToGuardGoal(this));
        this.goalSelector.addGoal(3, new CustomFollowOwnerGoal(this, 1.3D, 8.0F, 2.5F));
        this.goalSelector.addGoal(5, new CustomWaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new OpenDoorGoal(this, true));
        this.goalSelector.addGoal(9, new LowHealthGoal(this));
        this.targetSelector.addGoal(1, new CustomOwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new CustomOwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new CustomHurtByTargetGoal(this)));
    }
    
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.FOLLOW_RANGE, 20.0D)
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.ATTACK_DAMAGE, 1.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.32D);
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_TYPE_ID, 1);
        builder.define(EATING, false);
        builder.define(ALERT, false);
        builder.define(HUNTING, false);
        builder.define(PATROLLING, false);
        builder.define(FOLLOWING, false);
        builder.define(GUARDING, false);
        builder.define(STATIONARY, false);
        builder.define(PATROL_POS, Optional.empty());
        builder.define(PATROL_RADIUS, 10);
        builder.define(SEX, 0);
        builder.define(BASE_HEALTH, HCConfiguration.BASE_HEALTH.get());
        builder.define(EXP_LVL, 0);
        builder.define(FOOD1, "");
        builder.define(FOOD2, "");
        builder.define(FOOD1_AMT, 0);
        builder.define(FOOD2_AMT, 0);
    }

    public abstract boolean swings();

    @Override
    public void aiStep() {
        if(this.swings())
            this.updateSwingTime();
        super.aiStep();
    }

    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn,
                                        MobSpawnType reason, SpawnGroupData spawnDataIn) {
        int baseHealth = HCConfiguration.BASE_HEALTH.get() + CompanionData.getHealthModifier();
        modifyMaxHealth(baseHealth - 20, BASE_HEALTH_LOC, true);
        this.setHealth(this.getMaxHealth());
        setBaseHealth(baseHealth);
        setSex(this.random.nextInt(2));
        setCompanionSkin(this.random.nextInt(CompanionData.skins[getSex()].length));
        setCustomName(Component.literal(CompanionData.getRandomName(getSex())));
        setPatrolPos(this.blockPosition());
        setPatrolling(true);
        setPatrolRadius(15);
        patrolGoal = new PatrolGoal(this, 60, getPatrolRadius());
        moveBackGoal = new MoveBackToPatrolGoal(this, getPatrolRadius());
        this.goalSelector.addGoal(3, moveBackGoal);
        this.goalSelector.addGoal(3, patrolGoal);
        setFoodRequirements();

        if (HCConfiguration.SPAWN_ARMOR.get()) {
            for (int i = 0; i < 4; i++) {
                EquipmentSlot armorType = armorTypes[i];
                ItemStack itemstack = CompanionData.getSpawnArmor(armorType);
                if (!itemstack.isEmpty()) {
                    this.inventory.setItem(i, itemstack);
                }
            }
            checkArmor();
        }

        if (HCConfiguration.SPAWN_WEAPON.get()) {
            ItemStack itemstack = getSpawnWeapon();
            if (!itemstack.isEmpty()) {
                this.inventory.setItem(4, itemstack);
                checkWeapon(null, null);
            }
        }

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn);
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.put("inventory", this.inventory.createTag(this.registryAccess()));
        tag.putInt("skin", this.getCompanionSkin());
        tag.putBoolean("Eating", this.isEating());
        tag.putBoolean("Alert", this.isAlert());
        tag.putBoolean("Hunting", this.isHunting());
        tag.putBoolean("Patrolling", this.isPatrolling());
        tag.putBoolean("Following", this.isFollowing());
        tag.putBoolean("Guarding", this.isGuarding());
        tag.putBoolean("Stationery", this.isStationary());
        tag.putInt("radius", this.getPatrolRadius());
        tag.putInt("sex", this.getSex());
        tag.putInt("baseHealth", this.getBaseHealth());
        tag.putFloat("XpP", this.experienceProgress);
        tag.putInt("XpLevel", this.experienceLevel);
        tag.putInt("XpTotal", this.totalExperience);
        tag.putString("food1", food1);
        tag.putString("food2", food2);
        tag.putInt("food1_amt", foodRequirements.get(food1));
        tag.putInt("food2_amt", foodRequirements.get(food2));
        if (this.getPatrolPos() != null) {
            int[] patrolPos = {this.getPatrolPos().getX(), this.getPatrolPos().getY(), this.getPatrolPos().getZ()};
            tag.putIntArray("patrol_pos", patrolPos);
        }
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setCompanionSkin(tag.getInt("skin"));
        this.setEating(tag.getBoolean("Eating"));
        this.setAlert(tag.getBoolean("Alert"));
        this.setHunting(tag.getBoolean("Hunting"));
        this.setPatrolling(tag.getBoolean("Patrolling"));
        this.setFollowing(tag.getBoolean("Following"));
        this.setGuarding(tag.getBoolean("Guarding"));
        this.setStationary(tag.getBoolean("Stationery"));
        this.setPatrolRadius(tag.getInt("radius"));
        this.setSex(tag.getInt("sex"));
        this.experienceProgress = tag.getFloat("XpP");
        this.experienceLevel = tag.getInt("XpLevel");
        this.setExpLvl(tag.getInt("XpLevel"));
        this.totalExperience = tag.getInt("XpTotal");
        food1 = tag.getString("food1");
        food2 = tag.getString("food2");
        foodRequirements.put(food1, tag.getInt("food1_amt"));
        foodRequirements.put(food2, tag.getInt("food2_amt"));
        if (tag.getInt("baseHealth") == 0) {
            this.setBaseHealth(HCConfiguration.BASE_HEALTH.get());
        } else {
            this.setBaseHealth(tag.getInt("baseHealth"));
        }
        if (tag.getBoolean("Alert")) {
            addAlertGoals();
        }
        if (tag.getBoolean("Hunting")) {
            addHuntingGoals();
        }
        if (tag.contains("inventory", 9)) {
            this.inventory.fromTag(tag.getList("inventory", 10), this.registryAccess());
        }
        if (tag.contains("patrol_pos")) {
            int[] positions = tag.getIntArray("patrol_pos");
            setPatrolPos(new BlockPos(positions[0], positions[1], positions[2]));
        }
        if (tag.contains("radius")) {
            patrolGoal = new PatrolGoal(this, 60, tag.getInt("radius"));
            moveBackGoal = new MoveBackToPatrolGoal(this, tag.getInt("radius"));
            this.goalSelector.addGoal(3, moveBackGoal);
            this.goalSelector.addGoal(3, patrolGoal);
        }
        this.setItemSlot(EquipmentSlot.FEET, ItemStack.EMPTY);
        this.setItemSlot(EquipmentSlot.LEGS, ItemStack.EMPTY);
        this.setItemSlot(EquipmentSlot.CHEST, ItemStack.EMPTY);
        this.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        checkArmor();

        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
        checkWeapon(null, null);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return ArrayUtils.contains(foodRequirements.keySet().toArray(new String[0]), stack.getItem().getDescription().getString());
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parent) {
        return HCEntities.KNIGHT.get().create(level);
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (hand == InteractionHand.MAIN_HAND) {
            if (!this.isTame() && !this.level().isClientSide()) {
                if (itemstack.getComponents().has(DataComponents.FOOD)) {
                    String itemFood = itemstack.getItem().getDescription().getString();
                    if (isFood(itemstack)) {
                        if (foodRequirements.get(itemFood) > 0) {
                            itemstack.shrink(1);
                            foodRequirements.put(itemFood, foodRequirements.get(itemFood) - 1);
                            if (foodRequirements.get(food1) <= 0 && foodRequirements.get(food2) <= 0) {
                                this.tame(player);
                                player.sendSystemMessage(Component.translatable("chat.type.text", this.getDisplayName(),
                                        Component.literal("Thanks!")));
                                player.sendSystemMessage(Component.literal("Companion added"));
                                setPatrolPos(null);
                                setPatrolling(false);
                                setFollowing(true);
                                setPatrolRadius(4);
                                patrolGoal.radius = 4;
                                moveBackGoal.radius = 4;
                            } else {
                                if (foodRequirements.get(itemFood) == 0) {
                                    player.sendSystemMessage(Component.translatable("chat.type.text", this.getDisplayName(),
                                            CompanionData.ENOUGH_FOOD[this.random.nextInt(CompanionData.ENOUGH_FOOD.length)]));
                                } else {
                                    player.sendSystemMessage(Component.translatable("chat.type.text", this.getDisplayName(),
                                            CompanionData.tameFail[this.random.nextInt(CompanionData.tameFail.length)]));
                                }
                            }
                        } else {
                            player.sendSystemMessage(Component.translatable("chat.type.text", this.getDisplayName(),
                                    CompanionData.ENOUGH_FOOD[this.random.nextInt(CompanionData.ENOUGH_FOOD.length)]));
                        }
                    } else {
                        player.sendSystemMessage(Component.translatable("chat.type.text", this.getDisplayName(),
                                CompanionData.WRONG_FOOD[this.random.nextInt(CompanionData.WRONG_FOOD.length)]));
                    }
                } else {
                    String task =
                            this.getDisplayName().getString().split(" ")[0] + " wants: " + foodRequirements.get(food1) + " " + food1 + ", " + foodRequirements.get(food2) + " "  + food2;
                    player.sendSystemMessage(Component.translatable("chat.type.text", this.getDisplayName(),
                            CompanionData.notTamed[this.random.nextInt(CompanionData.notTamed.length)]));
                    player.sendSystemMessage(Component.literal(task));
                }
            } else {
                if (this.isAlliedTo(player)) {
                    if(player.isShiftKeyDown()) {
                        if(!this.level().isClientSide()) {
                            if (!this.isOrderedToSit()) {
                                this.setOrderedToSit(true);
                                Component text = Component.literal("I'll stand here.");
                                player.sendSystemMessage(Component.translatable("chat.type.text", this.getDisplayName(),
                                        text));
                            } else {
                                this.setOrderedToSit(false);
                                Component text = Component.literal("I'll move around.");
                                player.sendSystemMessage(Component.translatable("chat.type.text", this.getDisplayName(),
                                        text));
                            }
                        }
                    } else {
                        if(!this.level().isClientSide()) {
                            this.openGui((ServerPlayer) player);
                        }
                    }
                }
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        return super.mobInteract(player, hand);
    }

    public void openGui(ServerPlayer player) {
        if (player.containerMenu != player.inventoryMenu) {
            player.closeContainer();
        }
        player.nextContainerCounter();
        Network.getNetworkHandler().sendToClient(new OpenInventoryPacket(player.containerCounter, this.inventory.getContainerSize(), this.getId()), player);

        player.containerMenu = new CompanionContainer(player.containerCounter, player.getInventory(), this.inventory);
        player.initMenu(player.containerMenu);

        Services.EVENT.postOpenContainer(player, player.containerMenu);
    }

    public void checkArmor() {
        ItemStack head = this.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chest = this.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legs = this.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack feet = this.getItemBySlot(EquipmentSlot.FEET);
        for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack itemstack = this.inventory.getItem(i);
            if (itemstack.getItem() instanceof ArmorItem) {
                switch (((ArmorItem) itemstack.getItem()).getEquipmentSlot()) {
                    case HEAD:
                        if (head.isEmpty()) {
                            this.setItemSlot(EquipmentSlot.HEAD, itemstack);
                        } else {
                            if (((ArmorItem) itemstack.getItem()).getDefense() > ((ArmorItem) head.getItem()).getDefense()) {
                                this.setItemSlot(EquipmentSlot.HEAD, itemstack);
                            } else if (((ArmorItem) itemstack.getItem()).getMaterial() == ArmorMaterials.NETHERITE && ((ArmorItem) head.getItem()).getMaterial() != ArmorMaterials.NETHERITE) {
                                this.setItemSlot(EquipmentSlot.HEAD, itemstack);
                            }
                        }
                        break;
                    case CHEST:
                        if (chest.isEmpty()) {
                            this.setItemSlot(EquipmentSlot.CHEST, itemstack);
                        } else {
                            if (((ArmorItem) itemstack.getItem()).getDefense() > ((ArmorItem) chest.getItem()).getDefense()) {
                                this.setItemSlot(EquipmentSlot.CHEST, itemstack);
                            } else if (((ArmorItem) itemstack.getItem()).getMaterial() == ArmorMaterials.NETHERITE && ((ArmorItem) chest.getItem()).getMaterial() != ArmorMaterials.NETHERITE) {
                                this.setItemSlot(EquipmentSlot.CHEST, itemstack);
                            }
                        }
                        break;
                    case LEGS:
                        if (legs.isEmpty()) {
                            this.setItemSlot(EquipmentSlot.LEGS, itemstack);
                        } else {
                            if (((ArmorItem) itemstack.getItem()).getDefense() > ((ArmorItem) legs.getItem()).getDefense()) {
                                this.setItemSlot(EquipmentSlot.LEGS, itemstack);
                            } else if (((ArmorItem) itemstack.getItem()).getMaterial() == ArmorMaterials.NETHERITE && ((ArmorItem) legs.getItem()).getMaterial() != ArmorMaterials.NETHERITE) {
                                this.setItemSlot(EquipmentSlot.LEGS, itemstack);
                            }
                        }
                        break;
                    case FEET:
                        if (feet.isEmpty()) {
                            this.setItemSlot(EquipmentSlot.FEET, itemstack);
                        } else {
                            if (((ArmorItem) itemstack.getItem()).getDefense() > ((ArmorItem) feet.getItem()).getDefense()) {
                                this.setItemSlot(EquipmentSlot.FEET, itemstack);
                            } else if (((ArmorItem) itemstack.getItem()).getMaterial() == ArmorMaterials.NETHERITE && ((ArmorItem) feet.getItem()).getMaterial() != ArmorMaterials.NETHERITE) {
                                this.setItemSlot(EquipmentSlot.FEET, itemstack);
                            }
                        }
                        break;
                }
            }
        }
    }

    public abstract boolean isValidWeapon(ItemStack stack);

    public DamageSource generateDamageSource(LivingEntity target){
        return target.damageSources().mobAttack(this);
    }

    public void checkWeapon(LivingEntity target, DamageSource source){
        ItemStack hand = this.getItemBySlot(EquipmentSlot.MAINHAND);
        for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack itemstack = this.inventory.getItem(i);
            if (isValidWeapon(itemstack)) {
                if (hand.isEmpty()) {
                    this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
                } else if (isValidWeapon(hand)) {
                    if (getTotalAttackDamage(itemstack, target, source) > getTotalAttackDamage(hand, target, source)) {
                        this.setItemSlot(EquipmentSlot.MAINHAND, itemstack);
                    }
                }
            }
        }
    }

    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() == this.getOwner() && !HCConfiguration.FRIENDLY_FIRE_PLAYER.get()) {
            return false;
        }

        if (source.is(DamageTypeTags.IS_FALL) && !HCConfiguration.FALL_DAMAGE.get()) {
            return false;
        }

        hurtArmor(source, amount);
        return super.hurt(source, amount);
    }

    public void hurtArmor(DamageSource source, float amount) {
        if (!(amount <= 0.0F)) {
            amount /= 4.0F;
            if (amount < 1.0F) {
                amount = 1.0F;
            }

            for(ItemStack itemstack : this.getArmorSlots()) {
                if ((!source.is(DamageTypeTags.IS_FIRE) || !itemstack.getItem().components().has(DataComponents.FIRE_RESISTANT)) && itemstack.getItem() instanceof ArmorItem) {
                    itemstack.hurtAndBreak((int)amount, this, ((ArmorItem) itemstack.getItem()).getEquipmentSlot());
                }
            }

        }
    }

    protected void dropEquipment() {
        for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack itemstack = this.inventory.getItem(i);
            if (!itemstack.isEmpty() && !EnchantmentHelper.has(itemstack, EnchantmentEffectComponents.PREVENT_EQUIPMENT_DROP)) {
                this.spawnAtLocation(itemstack);
            }
        }
    }

    public boolean doHurtTarget(Entity entity) {
        ItemStack itemstack = this.getMainHandItem();
        if (!this.level().isClientSide && !itemstack.isEmpty() && entity instanceof LivingEntity) {
            itemstack.hurtAndBreak(1, this, EquipmentSlot.MAINHAND);
            if (this.getMainHandItem().isEmpty()) {
                Component broken = Component.literal("My weapon broke!");
                if (this.isTame()) {
                    this.getOwner().sendSystemMessage(Component.translatable("chat.type.text", this.getDisplayName(),
                            broken));
                }
            }
        }
        return super.doHurtTarget(entity);
    }

    @Override
    public ItemStack eat(Level level, ItemStack stack, FoodProperties foodProperties) {
        if (stack.has(DataComponents.FOOD)) {
            this.heal(foodProperties.nutrition());
        }
        super.eat(level, stack, foodProperties);
        return stack;
    }

    public ItemStack checkFood() {
        for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
            ItemStack itemstack = this.inventory.getItem(i);
            if (itemstack.has(DataComponents.FOOD)) {
                if ((float)itemstack.get(DataComponents.FOOD).nutrition() + this.getHealth() <= this.getMaxHealth()) {
                    return itemstack;
                }
            }
        }
        return ItemStack.EMPTY;
    }

    public void clearTarget() {
        this.setTarget(null);
    }

    @Override
    public void setTarget(LivingEntity target){
        super.setTarget(target);
        if(target != null){
            checkWeapon(target, generateDamageSource(target));
        }
    }

    public void giveExperiencePoints(int pXpPoints) {
        this.experienceProgress += (float)pXpPoints / (float)this.getXpNeededForNextLevel();
        this.totalExperience = Mth.clamp(this.totalExperience + pXpPoints, 0, Integer.MAX_VALUE);

        while(this.experienceProgress < 0.0F) {
            float f = this.experienceProgress * (float)this.getXpNeededForNextLevel();
            if (this.experienceLevel > 0) {
                this.giveExperienceLevels(-1);
                this.experienceProgress = 1.0F + f / (float)this.getXpNeededForNextLevel();
            } else {
                this.giveExperienceLevels(-1);
                this.experienceProgress = 0.0F;
            }
        }

        while(this.experienceProgress >= 1.0F) {
            this.experienceProgress = (this.experienceProgress - 1.0F) * (float)this.getXpNeededForNextLevel();
            this.giveExperienceLevels(1);
            this.experienceProgress /= (float)this.getXpNeededForNextLevel();
        }

    }

    public void giveExperienceLevels(int pLevels) {
        this.experienceLevel += pLevels;
        if (this.experienceLevel < 0) {
            this.experienceLevel = 0;
            this.experienceProgress = 0.0F;
            this.totalExperience = 0;
        }
        setExpLvl(this.experienceLevel);

        if (pLevels > 0 && this.experienceLevel % 5 == 0 && (float)this.lastLevelUpTime < (float)this.tickCount - 100.0F) {
            this.lastLevelUpTime = this.tickCount;
        }

    }

    public int getXpNeededForNextLevel() {
        if (this.experienceLevel >= 30) {
            return 112 + (this.experienceLevel - 30) * 9;
        } else {
            return this.experienceLevel >= 15 ? 37 + (this.experienceLevel - 15) * 5 : 7 + this.experienceLevel * 2;
        }
    }

    public int getExperienceReward() {
        if (!this.level().getGameRules().getBoolean(GameRules.RULE_KEEPINVENTORY) && !this.isSpectator()) {
            int i = this.experienceLevel * 7;
            return Math.min(i, 100);
        } else {
            return 0;
        }
    }

    public void modifyMaxHealth(int change, ResourceLocation location, boolean permanent) {
        AttributeInstance attributeinstance = this.getAttribute(Attributes.MAX_HEALTH);
        Set<AttributeModifier> modifiers = attributeinstance.getModifiers();
        if (!modifiers.isEmpty()) {
            Iterator<AttributeModifier> iterator = modifiers.iterator();
            while (iterator.hasNext()) {
                AttributeModifier attributeModifier = iterator.next();
                if (attributeModifier != null && attributeModifier.id().equals(location)) {
                    this.getAttribute(Attributes.MAX_HEALTH).removeModifier(attributeModifier);
                }
            }
        }
        AttributeModifier HEALTH_MODIFIER = new AttributeModifier(location,
                change, AttributeModifier.Operation.ADD_VALUE);
        if (permanent) {
            attributeinstance.addPermanentModifier(HEALTH_MODIFIER);
        } else {
            attributeinstance.addTransientModifier(HEALTH_MODIFIER);
        }
    }

    public void checkStats() {
        if ((int) this.getMaxHealth() != getBaseHealth() + (getExpLvl() / 3)) {
            if (getExpLvl() / 3 != 0) {
                modifyMaxHealth(getExpLvl() / 3, LEVEL_HEALTH_LOC, false);
            }
        }
    }

    public void tick() {
        if (!this.level().isClientSide()) {
            checkArmor();
            if(this.getTarget() == null){
                checkWeapon(null, null);
            }
            if (this.tickCount % 10 == 0) {
                checkStats();
                LivingEntity target = this.getTarget();
                if (target != null && !target.isAlive()) {
                    this.clearTarget();
                }
            }
        }
        super.tick();
    }

    public void setFoodRequirements() {
        foodRequirements.clear();
        List<Item> allFoods = CompanionData.getFoods();
        int length = allFoods.size();
        food1 = allFoods.get(random.nextInt(length)).getDescription().getString();
        food2 = allFoods.get(random.nextInt(length)).getDescription().getString();
        while (food1.equals(food2)) {
            food2 = allFoods.get(random.nextInt(length)).getDescription().getString();
        }
        foodRequirements.put(food1, random.nextInt(5) + 1);
        foodRequirements.put(food2, random.nextInt(5) + 1);
    }

    public void release() {
        this.setTame(false, false);
        this.setOwnerUUID(null);
        setFollowing(false);
        setAlert(false);
        removeAlertGoals();
        setHunting(false);
        removeHuntingGoals();
        setPatrolPos(this.blockPosition());
        setPatrolling(true);
        setStationary(false);
        setPatrolRadius(15);
        setFoodRequirements();
        if (this.isOrderedToSit()) {
            this.setOrderedToSit(false);
        }
    }

    public double getTotalAttackDamage(ItemStack stack, LivingEntity target, DamageSource source) {
        AtomicReference<Double> damage = new AtomicReference<>(0.0);
        AtomicReference<Double> multiplier = new AtomicReference<>((double) 1);
        stack.forEachModifier(EquipmentSlot.MAINHAND, (holder, modifier) -> {
            if(holder.is(Attributes.ATTACK_DAMAGE)){
                switch (modifier.operation()) {
                    case ADD_VALUE -> damage.updateAndGet(v -> v + modifier.amount());
                    case ADD_MULTIPLIED_BASE -> damage.updateAndGet(v -> v + damage.get() * modifier.amount());
                    case ADD_MULTIPLIED_TOTAL -> multiplier.updateAndGet(v -> v * modifier.amount());
                }
            }
        });

        float actualDamage = target == null || source == null ? damage.get().floatValue() : EnchantmentHelper.modifyDamage((ServerLevel) this.level(), stack, target, source, damage.get().floatValue());

        return actualDamage * multiplier.get();
    }

    public void setExpLvl(int lvl) {
        this.entityData.set(EXP_LVL, lvl);
    }

    public int getExpLvl() {
        return this.entityData.get(EXP_LVL);
    }

    public void setPatrolPos(BlockPos position) {
        this.entityData.set(PATROL_POS, Optional.ofNullable(position));
    }

    
    public BlockPos getPatrolPos() {
        return this.entityData.get(PATROL_POS).orElse(null);
    }

    public void setPatrolRadius(int radius) {
        this.entityData.set(PATROL_RADIUS, radius);
    }

    public int getPatrolRadius() {
        return this.entityData.get(PATROL_RADIUS);
    }

    public ResourceLocation getResourceLocation() {
        return CompanionData.skins[getSex()][getCompanionSkin()];
    }

    public int getCompanionSkin() {
        return this.entityData.get(DATA_TYPE_ID);
    }

    public void setCompanionSkin(int skinIndex) {
        this.entityData.set(DATA_TYPE_ID, skinIndex);
    }

    public void setSex(int sex) {
        this.entityData.set(SEX, sex);
    }

    public int getSex() {
        return this.entityData.get(SEX);
    }

    public void setBaseHealth(int health) {
        this.entityData.set(BASE_HEALTH, health);
    }

    public int getBaseHealth() {
        return this.entityData.get(BASE_HEALTH);
    }

    public boolean isEating() {
        return this.entityData.get(EATING);
    }

    public boolean isAlert() {
        return this.entityData.get(ALERT);
    }

    public boolean isHunting() {
        return this.entityData.get(HUNTING);
    }

    public boolean isPatrolling() {
        return this.entityData.get(PATROLLING);
    }

    public boolean isGuarding() {
        return this.entityData.get(GUARDING);
    }

    public boolean isStationary() {
        return this.entityData.get(STATIONARY);
    }

    public boolean isFollowing() {
        return this.entityData.get(FOLLOWING);
    }

    public void setEating(boolean eating) {
        this.entityData.set(EATING, eating);
    }

    public void setAlert(boolean alert) {
        this.entityData.set(ALERT, alert);
    }

    public void setHunting(boolean hunting) {
        this.entityData.set(HUNTING, hunting);
    }

    public void setPatrolling(boolean patrolling) {
        this.entityData.set(PATROLLING, patrolling);
    }

    public void setGuarding(boolean guarding) {
        this.entityData.set(GUARDING, guarding);
    }

    public void setStationary(boolean stationery) {
        this.entityData.set(STATIONARY, stationery);
    }

    public void setFollowing(boolean following) {
        this.entityData.set(FOLLOWING, following);
    }

    public void addAlertGoals() {
        for (NearestAttackableTargetGoal alertMobGoal : alertMobGoals) {
            this.targetSelector.addGoal(4, alertMobGoal);
        }
    }

    public void removeAlertGoals() {
        for (NearestAttackableTargetGoal alertMobGoal : alertMobGoals) {
            this.targetSelector.removeGoal(alertMobGoal);
        }
    }

    public void addHuntingGoals() {
        for (NearestAttackableTargetGoal huntMobGoal : huntMobGoals) {
            this.targetSelector.addGoal(4, huntMobGoal);
        }
    }

    public void removeHuntingGoals() {
        for (NearestAttackableTargetGoal huntMobGoal : huntMobGoals) {
            this.targetSelector.removeGoal(huntMobGoal);
        }
    }

    public abstract ItemStack getSpawnWeapon();
}
