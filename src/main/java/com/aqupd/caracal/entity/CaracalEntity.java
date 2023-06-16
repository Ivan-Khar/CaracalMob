package com.aqupd.caracal.entity;

import com.aqupd.caracal.ai.CaracalSitOnBlockGoal;
import com.aqupd.caracal.setup.CaracalEntities;
import com.aqupd.caracal.utils.AqConfig;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.example.entity.BatEntity;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static com.aqupd.caracal.setup.CaracalSounds.*;
import static net.minecraft.world.InteractionResult.*;
import static net.minecraft.world.entity.ai.attributes.Attributes.*;

@SuppressWarnings({"ConstantConditions", "FieldMayBeFinal", "rawtypes", "resource"})
public class CaracalEntity extends TamableAnimal implements GeoEntity {

  private static final Ingredient TAMING_INGREDIENT;
  private static final EntityDataAccessor<Boolean> IN_SLEEPING_POSE;
  private static final EntityDataAccessor<Integer> CARACAL_BIRTHDAY_COLOR;
  private static final EntityDataAccessor<Integer> CURRENT_ANIMATION;

  private boolean songPlaying;
  @Nullable
  private BlockPos songSource;

  private CaracalTemptGoal temptGoal;
  private static double health = AqConfig.INSTANCE.getDoubleProperty("entity.health");
  private static double speed = AqConfig.INSTANCE.getDoubleProperty("entity.speed");
  private static double follow = AqConfig.INSTANCE.getDoubleProperty("entity.follow");
  private static double damage = AqConfig.INSTANCE.getDoubleProperty("entity.damage");
  private static double knockback = AqConfig.INSTANCE.getDoubleProperty("entity.knockback");

  private static final HashSet<UUID> peopleToAttack = new HashSet<>(){{
    add(UUID.fromString("06e02a3f-dc56-43b5-95b9-191387a59e01"));
  }};

  public CaracalEntity(EntityType<? extends CaracalEntity> entityType, Level level) {
    super(entityType, level);
  }

  private boolean commander;

  protected void registerGoals() {
    this.temptGoal = new CaracalTemptGoal(this, 1.0D, TAMING_INGREDIENT, true);
    this.goalSelector.addGoal(1, new FloatGoal(this));
    this.goalSelector.addGoal(1, new SleepWithOwnerGoal(this));
    this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
    this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.0D, 10.0F, 5.0F, true));
    this.goalSelector.addGoal(3, new PanicGoal(this, 1.4D));
    this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
    this.goalSelector.addGoal(3, this.temptGoal);
    this.goalSelector.addGoal(5, new LeapAtTargetGoal(this, 0.3F));
    this.goalSelector.addGoal(6, new OcelotAttackGoal(this));
    this.goalSelector.addGoal(7, new CaracalSitOnBlockGoal(this, 0.8D));
    this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 10.0F));
    this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.5F));

    //caracals don't like this "texbobcat" person
    this.targetSelector.addGoal(1, new NonTameRandomTargetGoal<>(this, Player.class, false,
      le -> {
        UUID uuid = le.getUUID();
        if(uuid == null) return false;
        return peopleToAttack.contains(uuid);
      }));
    this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
    this.targetSelector.addGoal(3, new NonTameRandomTargetGoal<>(this, Chicken.class, true, null));
    this.targetSelector.addGoal(3, new NonTameRandomTargetGoal<>(this, Rabbit.class, true, null));
    this.targetSelector.addGoal(3, new NonTameRandomTargetGoal<>(this, BatEntity.class, true, null));
  }

  public void customServerAiStep() {
    if (this.getMoveControl().hasWanted()) {
      double d = this.getMoveControl().getSpeedModifier();
      if (d == 0.6D) {
        this.setPose(Pose.CROUCHING);
        this.setSprinting(false);
      } else if (d == 1.33D) {
        this.setPose(Pose.STANDING);
        this.setSprinting(true);
      } else {
        this.setPose(Pose.STANDING);
        this.setSprinting(false);
      }
    } else {
      this.setPose(Pose.STANDING);
      this.setSprinting(false);
    }

    if (this.temptGoal != null && this.temptGoal.isRunning() && !this.isTame() && this.age % 100 == 0) {
      this.playSound(ENTITY_CARACAL_BEG_FOR_FOOD.get(), 1.0F, 1.0F);
    }

    this.updateAnimations();
  }

  private void updateAnimations() {
    if ((this.isLying()) && this.age % 5 == 0) {
      if(this.random.nextFloat() > 0.7) this.playSound(ENTITY_CARACAL_PURR.get(), 0.6F + 0.4F * (this.random.nextFloat() - this.random.nextFloat()), 1.0F);
    }
    if (this.songSource == null || !this.songSource.closerToCenterThan(this.position(), 5.0) || !this.level().getBlockState(this.songSource).is(Blocks.JUKEBOX)) {
      this.songPlaying = false;
      this.songSource = null;
    }
  }

  private final AnimatableInstanceCache aFactory = GeckoLibUtil.createInstanceCache(this);

  private static final RawAnimation IDLE = RawAnimation.begin().thenLoop("animation.caracal.idle");
  private static final RawAnimation IDLE2SIT = RawAnimation.begin().thenPlay("animation.caracal.idle2sit");
  private static final RawAnimation IDLE2SLEEP = RawAnimation.begin().thenPlay("animation.caracal.idle2sleep");
  private static final RawAnimation WALK = RawAnimation.begin().thenLoop("animation.caracal.walk");
  private static final RawAnimation SNEAK = RawAnimation.begin().thenLoop("animation.caracal.sneak");
  private static final RawAnimation RUN = RawAnimation.begin().thenLoop("animation.caracal.run");
  private static final RawAnimation SIT = RawAnimation.begin().thenLoop("animation.caracal.sit");
  private static final RawAnimation SIT2IDLE = RawAnimation.begin().thenPlay("animation.caracal.sit2idle");
  private static final RawAnimation SIT2SLEEP = RawAnimation.begin().thenPlay("animation.caracal.sit2sleep");
  private static final RawAnimation SLEEP = RawAnimation.begin().thenLoop("animation.caracal.sleep");
  private static final RawAnimation SLEEP2IDLE = RawAnimation.begin().thenPlay("animation.caracal.sleep2idle");
  private static final RawAnimation SLEEP2SIT = RawAnimation.begin().thenPlay("animation.caracal.sleep2sit");
  private static final RawAnimation DANCE = RawAnimation.begin().thenPlayXTimes("animation.caracal.dance1", 9).thenLoop("animation.caracal.dance2");
  /*
  0 - IDLE animation.caracal.idle
  IDLE>SIT animation.caracal.idle2sit
  IDLE>SLEEP animation.caracal.idle2sleep
  1 - WALK animation.caracal.walk
  2 - SNEAK animation.caracal.sneak
  3 - RUN animation.caracal.run
  4 - SIT animation.caracal.sit
  SIT>IDLE animation.caracal.sit2idle
  SIT>SLEEP animation.caracal.sit2sleep
  5 - SLEEP animation.caracal.sleep
  SLEEP>IDLE animation.caracal.sleep2idle
  SLEEP>SIT animation.caracal.sleep2sit
  6 - DANCE animation.caracal.dance1 animation.caracal.dance2
  */
  @SuppressWarnings("SameReturnValue")
  private PlayState predicate(AnimationState<CaracalEntity> state) {
    AnimationController contr = state.getController();

    String animName = "";
    if (contr.getCurrentAnimation() != null) { animName = contr.getCurrentAnimation().animation().name(); }

    if (isSongPlaying()) {
      if (!animName.equals("animation.caracal.dance1") || !animName.equals("animation.caracal.dance2")) {
        setCurrentAnimation(6);
      }
    } else if(getCurrentAnimation() == 4 && !isInSittingPose()) {
      contr.setAnimation(SIT2IDLE);
      if (contr.getAnimationState() == AnimationController.State.PAUSED) setCurrentAnimation(0);
      return PlayState.CONTINUE;
    } else if(getCurrentAnimation() == 5 && !isLying() && !isInSittingPose()) {
      contr.setAnimation(SLEEP2IDLE);
      if (contr.getAnimationState() == AnimationController.State.PAUSED) setCurrentAnimation(0);
      return PlayState.CONTINUE;
    } else if(getCurrentAnimation() != 4 && isInSittingPose() && !isLying()) {
      if (getCurrentAnimation() <= 3) contr.setAnimation(IDLE2SIT);
      else if (getCurrentAnimation() == 5) contr.setAnimation(SLEEP2SIT);

      if (contr.getAnimationState() == AnimationController.State.PAUSED) setCurrentAnimation(4);
      return PlayState.CONTINUE;
    } else if(getCurrentAnimation() != 5 && isLying()) {
      if (getCurrentAnimation() <= 3) contr.setAnimation(IDLE2SLEEP);
      else if (getCurrentAnimation() == 4) contr.setAnimation(SIT2SLEEP);

      if (contr.getAnimationState() == AnimationController.State.PAUSED) setCurrentAnimation(5);
      return PlayState.CONTINUE;
    } else if(isLying()) {
      setCurrentAnimation(5);
    } else if(isInSittingPose()) {
      setCurrentAnimation(4);
    } else if(state.isMoving()) {
      setCurrentAnimation(1);
    } else {
      setCurrentAnimation(0);
    }

    switch (getCurrentAnimation()) {
      case 0 -> contr.setAnimation(IDLE);
      case 1 -> contr.setAnimation(WALK);
      case 2 -> contr.setAnimation(SNEAK);
      case 3 -> contr.setAnimation(RUN);
      case 4 -> contr.setAnimation(SIT);
      case 5 -> contr.setAnimation(SLEEP);
      case 6 -> contr.setAnimation(DANCE);
    }
    return PlayState.CONTINUE;
  }

  protected PlayState idle(AnimationState<CaracalEntity> state) {
    if(getCurrentAnimation() <= 3 && getCurrentAnimation() > 0) {
      state.setAnimation(IDLE);
      return PlayState.CONTINUE;
    }
    return PlayState.STOP;
  }

  @Override
  public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    controllers.add(
        new AnimationController<>(this, "animations", 0, this::predicate),
        new AnimationController<>(this, "idle", 0, this::idle)
    );
  }

  @Override
  public AnimatableInstanceCache getAnimatableInstanceCache() {
    return aFactory;
  }

  static class CaracalTemptGoal extends TemptGoal {
    @Nullable
    private Player selectedPlayer;
    private final CaracalEntity caracalEntity;

    public CaracalTemptGoal(CaracalEntity caracalEntity, double speed, Ingredient food, boolean canBeScared) {
      super(caracalEntity, speed, food, canBeScared);
      this.caracalEntity = caracalEntity;
    }

    public void tick() {
      super.tick();
      if (this.selectedPlayer == null && this.mob.getRandom().nextInt(this.adjustedTickDelay(600)) == 0) {
        this.selectedPlayer = this.player;
      } else if (this.mob.getRandom().nextInt(this.adjustedTickDelay(500)) == 0) {
        this.selectedPlayer = null;
      }
    }

    @Override
    protected boolean canScare() {
      if (this.selectedPlayer != null && this.selectedPlayer.equals(this.player)) {
        return false;
      }
      return super.canScare();
    }

    @Override
    public boolean canUse() {
      return super.canUse() && !this.caracalEntity.isTame();
    }
  }

  public static AttributeSupplier.Builder createCaracalAttributes() {
    return AgeableMob
      .createLivingAttributes()
      .add(MAX_HEALTH, health)
      .add(MOVEMENT_SPEED, speed)
      .add(FOLLOW_RANGE, follow)
      .add(ATTACK_DAMAGE, damage)
      .add(ATTACK_KNOCKBACK, knockback);
  }

  public int getCurrentAnimation() {
    return this.entityData.get(CURRENT_ANIMATION);
  }

  public void setCurrentAnimation(int animation) {
    this.entityData.set(CURRENT_ANIMATION, animation);
  }

  public int getMaskColor() {
    return this.entityData.get(CARACAL_BIRTHDAY_COLOR);
  }

  public void setMaskColor(int type) {
    if (type < 1 || type > 3) {
      type = this.random.nextInt(3 - 1 + 1) + 1;
    }

    this.entityData.set(CARACAL_BIRTHDAY_COLOR, type);
  }

  public void addAdditionalSaveData(CompoundTag tag) {
    super.addAdditionalSaveData(tag);
    tag.putInt("CaracalBirthdayColor", this.getMaskColor());
    if (this.commander) {
      tag.putBoolean("Commander", true);
    }
  }

  public void readAdditionalSaveData(CompoundTag tag) {
    super.readAdditionalSaveData(tag);
    this.setMaskColor(tag.getInt("CaracalBirthdayColor"));
    if (tag.contains("Commander", 99)) {
      this.commander = tag.getBoolean("Commander");
    }
  }

  public void setCustomName(@Nullable Component name) {
    super.setCustomName(name);
    if (this.getCustomName() != null) {
      String n = this.getCustomName().getString().toLowerCase(Locale.ENGLISH);
      this.commander =
        (n.contains("командир") || n.contains("commander")) &&
        !(n.contains("мирный") || n.contains("peaceful"));
    }
  }

  @Override
  public void setRecordPlayingNearby(BlockPos songPosition, boolean playing) {
    this.songSource = songPosition;
    this.songPlaying = playing;
  }

  public boolean isSongPlaying() {
    return this.songPlaying;
  }

  public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
    return false;
  }

  @Nullable
  protected SoundEvent getAmbientSound() {
    if (this.isTame()) {
      if (this.isInLove()) {
        return ENTITY_CARACAL_PURR.get();
      } else {
        return this.random.nextInt(4) == 0 ? ENTITY_CARACAL_PURREOW.get() : this.getAge() < 0 ? ENTITY_CARACAL_SMALL_SCREAM.get() : ENTITY_CARACAL_SCREAM.get();
      }
    } else {
      return this.getAge() < 0 ? ENTITY_CARACAL_SMALL_SCREAM.get() : ENTITY_CARACAL_SCREAM.get();
    }
  }

  protected SoundEvent getHurtSound(DamageSource source) {
    return ENTITY_CARACAL_HISS.get();
  }

  protected SoundEvent getDeathSound() {
    return ENTITY_CARACAL_DEATH.get();
  }

  private float getAttackDamage() {
    return (float) this.getAttributeValue(ATTACK_DAMAGE);
  }

  public boolean doHurtTarget(Entity target) {
    return target.hurt(this.damageSources().mobAttack(this), this.getAttackDamage());
  }

  protected void usePlayerItem(Player player, InteractionHand hand, ItemStack stack) {
    if (this.isBreedingItem(stack)) {
      this.playSound(ENTITY_CARACAL_EAT.get(), 1.0F, 1.0F);
    }

    super.usePlayerItem(player, hand, stack);
  }

  @Nullable
  public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob entity) {
    CaracalEntity caracalEntity = CaracalEntities.CARACAL.get().create(level);
    if (entity instanceof CaracalEntity) {
      if (this.isTame()) {
        caracalEntity.setOwnerUUID(this.getOwnerUUID());
        caracalEntity.setTame(true);
      }
    }
    caracalEntity.setMaskColor(10);
    return caracalEntity;
  }

  public boolean canMate(Animal other) {
    if (!this.isTame()) {
      return false;
    } else if (!(other instanceof CaracalEntity caracalEntity)) {
      return false;
    } else {
      return caracalEntity.isTame() && super.canMate(other);
    }
  }

  protected void defineSynchedData() {
    super.defineSynchedData();
    this.entityData.define(CARACAL_BIRTHDAY_COLOR, 10);
    this.entityData.define(IN_SLEEPING_POSE, false);
    this.entityData.define(CURRENT_ANIMATION, 0);
  }

  public void setLying(boolean sleeping) {
    this.entityData.set(IN_SLEEPING_POSE, sleeping);
  }

  public boolean isLying() {
    return this.entityData.get(IN_SLEEPING_POSE);
  }

  @SuppressWarnings("BooleanMethodIsAlwaysInverted")
  static class SleepWithOwnerGoal extends Goal {
    private final CaracalEntity caracal;
    @Nullable
    private Player ownerPlayer;
    @Nullable
    private BlockPos goalPos;
    private int onBedTicks;

    public SleepWithOwnerGoal(CaracalEntity caracalEntity) {
      this.caracal = caracalEntity;
    }

    @Override
    public boolean canUse() {
      if (!this.caracal.isTame()) {
        return false;
      }
      if (this.caracal.isOrderedToSit()) {
        return false;
      }
      LivingEntity livingEntity = this.caracal.getOwner();
      if (livingEntity instanceof Player) {
        this.ownerPlayer = (Player)livingEntity;
        if (!livingEntity.isSleeping()) {
          return false;
        }
        if (this.caracal.distanceToSqr(this.ownerPlayer) > 100.0) {
          return false;
        }
        BlockPos blockPos = this.ownerPlayer.blockPosition();
        BlockState blockState = this.caracal.level().getBlockState(blockPos);
        if (blockState.is(BlockTags.BEDS)) {
          this.goalPos = blockState.getOptionalValue(BedBlock.FACING).map(direction -> blockPos.relative(direction.getOpposite())).orElseGet(() -> new BlockPos(blockPos));
          return !this.spaceIsOccupied();
        }
      }
      return false;
    }

    private boolean spaceIsOccupied() {
      List<CaracalEntity> list = this.caracal.level().getEntitiesOfClass(CaracalEntity.class, new AABB(this.goalPos).inflate(2.0));
      for (CaracalEntity caracal : list) {
        if (caracal == this.caracal || !caracal.isLying()) continue;
        return true;
      }
      return false;
    }

    @Override
    public boolean canContinueToUse() {
      return this.caracal.isTame() && !this.caracal.isOrderedToSit() && this.ownerPlayer != null && this.ownerPlayer.isSleeping() && this.goalPos != null && !this.spaceIsOccupied();
    }

    @Override
    public void start() {
      if (this.goalPos != null) {
        this.caracal.setInSittingPose(false);
        this.caracal.getNavigation().moveTo(this.goalPos.getX(), this.goalPos.getY(), this.goalPos.getZ(), 1.1f);
      }
    }

    @Override
    public void stop() {
      this.caracal.setLying(false);
      float f = this.caracal.level().getTimeOfDay(1.0f);
      if (this.ownerPlayer.getSleepTimer() >= 100 && (double)f > 0.77 && (double)f < 0.8 && (double) this.caracal.level().getRandom().nextFloat() < 0.7) {
        this.giveMorningGift();
      }
      this.onBedTicks = 0;
      this.caracal.getNavigation().stop();
    }

    private void giveMorningGift() {
      RandomSource randomSource = this.caracal.getRandom();
      BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
      mutableBlockPos.set(this.caracal.isLeashed() ? this.caracal.getLeashHolder().blockPosition() : this.caracal.blockPosition());
      this.caracal.randomTeleport(mutableBlockPos.getX() + randomSource.nextInt(11) - 5, mutableBlockPos.getY() + randomSource.nextInt(5) - 2, mutableBlockPos.getZ() + randomSource.nextInt(11) - 5, false);
      mutableBlockPos.set(this.caracal.blockPosition());
      LootTable lootTable = this.caracal.level().getServer().getLootData().getLootTable(BuiltInLootTables.CAT_MORNING_GIFT);
      LootParams lootParams = new LootParams.Builder((ServerLevel)this.caracal.level()).withParameter(LootContextParams.ORIGIN, this.caracal.position()).withParameter(LootContextParams.THIS_ENTITY, this.caracal).create(LootContextParamSets.GIFT);
      ObjectArrayList<ItemStack> list = lootTable.getRandomItems(lootParams);
      for (ItemStack itemStack : list) {
        this.caracal.level().addFreshEntity(new ItemEntity(this.caracal.level(), (double)mutableBlockPos.getX() - (double) Mth.sin(this.caracal.yBodyRot * ((float)Math.PI / 180)), mutableBlockPos.getY(), (double)mutableBlockPos.getZ() + (double)Mth.cos(this.caracal.yBodyRot * ((float)Math.PI / 180)), itemStack));
      }
    }

    @Override
    public void tick() {
      if (this.ownerPlayer != null && this.goalPos != null) {
        this.caracal.setInSittingPose(false);
        this.caracal.getNavigation().moveTo(this.goalPos.getX(), this.goalPos.getY(), this.goalPos.getZ(), 1.1f);
        if (this.caracal.distanceToSqr(this.ownerPlayer) < 2.5) {
          ++this.onBedTicks;
          if (this.onBedTicks > this.adjustedTickDelay(16)) {
            this.caracal.setLying(true);
          } else {
            this.caracal.lookAt(this.ownerPlayer, 45.0f, 45.0f);
          }
        } else {
          this.caracal.setLying(false);
        }
      }
    }
  }

  public @NotNull InteractionResult mobInteract(Player player, InteractionHand hand) {
    ItemStack itemStack = player.getItemInHand(hand);
    Item item = itemStack.getItem();

    if (this.level().isClientSide()) {                                                //Client-Side world
      if (this.isTame() && this.isOwnedBy(player)) {                                  //If tamed & owned by player, interaction is successful
        return SUCCESS;
      } else {                                                                        //If not tamed
        if(this.isBreedingItem(itemStack)) return SUCCESS;                            //If caracal can eat item, interaction is successful
        if((this.getHealth() < this.getMaxHealth()) && this.isTame()) return CONSUME; //If tamed caracal is hurt, interaction is successful
      }
    } else {
      if(this.isTame() && this.isOwnedBy(player)) {
        if (this.isBreedingItem(itemStack)) {
          if (this.getHealth() < this.getMaxHealth()) {
            this.usePlayerItem(player, hand, itemStack);
            this.heal(item.getFoodProperties().getNutrition());
            return CONSUME;
          }

          if (this.getAge() == 0 && this.canFallInLove()) {
            this.usePlayerItem(player, hand, itemStack);
            this.setInLove(player);
            return SUCCESS;
          }

          if (this.isBaby()) {
            this.usePlayerItem(player, hand, itemStack);
            this.ageUp(getSpeedUpSecondsWhenFeeding(-this.getAge()), true);
            return sidedSuccess(this.level().isClientSide);
          }
        }
        setOrderedToSit(!isOrderedToSit());
      } else {
        if(this.isBreedingItem(itemStack)) {
          this.usePlayerItem(player, hand, itemStack);

          if (this.random.nextInt(3) == 0) {
            this.tame(player);
            this.setOrderedToSit(true);
            this.setPersistenceRequired();
            this.level().broadcastEntityEvent(this, (byte) 7);
          } else {
            this.level().broadcastEntityEvent(this, (byte) 6);
          }
          return CONSUME;
        }
      }
    }

    return PASS;
  }

  @Nullable
  public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag) {
    spawnData = super.finalizeSpawn(level, difficulty, reason, spawnData, dataTag);
    this.setMaskColor(10);
    return spawnData;
  }

  public boolean isBreedingItem(ItemStack stack) {
    return TAMING_INGREDIENT.test(stack);
  }

  protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
    return dimensions.height * 0.98F;
  }

  public boolean removeWhenFarAway(double distanceSquared) {
    return !this.isTame() && this.age > 2400;
  }

  public static boolean canSpawn(EntityType<CaracalEntity> entity, LevelAccessor level, MobSpawnType type, BlockPos pos, RandomSource random) {
    return Animal.checkAnimalSpawnRules(entity, level, type, pos, random);
  }

  static {
    TAMING_INGREDIENT = Ingredient.of(Items.COD, Items.SALMON, Items.CHICKEN, Items.RABBIT);
    IN_SLEEPING_POSE = SynchedEntityData.defineId(CaracalEntity.class, EntityDataSerializers.BOOLEAN);
    CARACAL_BIRTHDAY_COLOR = SynchedEntityData.defineId(CaracalEntity.class, EntityDataSerializers.INT);
    CURRENT_ANIMATION = SynchedEntityData.defineId(CaracalEntity.class, EntityDataSerializers.INT);
  }
}
