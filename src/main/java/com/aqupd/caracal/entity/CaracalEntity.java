package com.aqupd.caracal.entity;

import static com.aqupd.caracal.utils.AqLogger.*;

import com.aqupd.caracal.CaracalMain;
import com.aqupd.caracal.ai.CaracalSitOnBlockGoal;
import com.aqupd.caracal.utils.AqConfig;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CaracalEntity extends TameableEntity {

  private static final Ingredient TAMING_INGREDIENT;
  private static final TrackedData<Boolean> IN_SLEEPING_POSE;
  private static final TrackedData<Integer> CARACAL_BIRTHDAY_COLOR;

  private TemptGoal temptGoal;
  private static double health = AqConfig.INSTANCE.getDoubleProperty(
    "entity.health"
  );
  private static double speed = AqConfig.INSTANCE.getDoubleProperty(
    "entity.speed"
  );
  private static double follow = AqConfig.INSTANCE.getDoubleProperty(
    "entity.follow"
  );
  private static double damage = AqConfig.INSTANCE.getDoubleProperty(
    "entity.damage"
  );
  private static double knockback = AqConfig.INSTANCE.getDoubleProperty(
    "entity.knockback"
  );

  public CaracalEntity(
    EntityType<? extends CaracalEntity> entityType,
    World world
  ) {
    super(entityType, world);
  }

  private boolean commander;

  protected void initGoals() {
    this.temptGoal = new TemptGoal(this, 1.0D, TAMING_INGREDIENT, true);
    this.goalSelector.add(1, new SwimGoal(this));
    this.goalSelector.add(1, new SitGoal(this));
    this.goalSelector.add(
        2,
        new FollowOwnerGoal(this, 1.0D, 10.0F, 5.0F, false)
      );
    this.goalSelector.add(2, new SleepWithOwnerGoal(this));
    this.goalSelector.add(3, new EscapeDangerGoal(this, 1.4D));
    this.goalSelector.add(3, new AnimalMateGoal(this, 1.0D));
    this.goalSelector.add(3, this.temptGoal);
    this.goalSelector.add(5, new PounceAtTargetGoal(this, 0.3F));
    this.goalSelector.add(6, new AttackGoal(this));
    this.goalSelector.add(7, new CaracalSitOnBlockGoal(this, 0.8D));
    this.goalSelector.add(
        7,
        new LookAtEntityGoal(this, PlayerEntity.class, 10.0F)
      );
    this.goalSelector.add(8, new WanderAroundGoal(this, 0.5F));

    //caracals don't like this "texbobcat" person
    this.targetSelector.add(
        1,
        new UntamedActiveTargetGoal<>(
          this,
          PlayerEntity.class,
          false,
          livingEntity ->
            (livingEntity).getUuidAsString()
              .equals("06e02a3f-dc56-43b5-95b9-191387a59e01")
        )
      );
    this.targetSelector.add(1, new TrackOwnerAttackerGoal(this));
    this.targetSelector.add(
        3,
        new UntamedActiveTargetGoal<>(this, ChickenEntity.class, true, null)
      );
    this.targetSelector.add(
        3,
        new UntamedActiveTargetGoal<>(this, RabbitEntity.class, true, null)
      );
    this.targetSelector.add(
        3,
        new UntamedActiveTargetGoal<>(this, BatEntity.class, true, null)
      );
  }

  public void mobTick() {
    if (this.getMoveControl().isMoving()) {
      double d = this.getMoveControl().getSpeed();
      if (d == 0.6D) {
        this.setPose(EntityPose.CROUCHING);
        this.setSprinting(false);
      } else if (d == 1.33D) {
        this.setPose(EntityPose.STANDING);
        this.setSprinting(true);
      } else {
        this.setPose(EntityPose.STANDING);
        this.setSprinting(false);
      }
    } else {
      this.setPose(EntityPose.STANDING);
      this.setSprinting(false);
    }

    if (
      this.temptGoal != null &&
      this.temptGoal.isActive() &&
      !this.isTamed() &&
      this.age % 100 == 0
    ) {
      this.playSound(CaracalMain.CARACAL_BEG_FOR_FOOD, 1.0F, 1.0F);
    }

    this.updateAnimations();
  }

  private void updateAnimations() {
    if ((this.isInSleepingPose()) && this.age % 5 == 0) {
      this.playSound(
          CaracalMain.CARACAL_PURR,
          0.6F + 0.4F * (this.random.nextFloat() - this.random.nextFloat()),
          1.0F
        );
    }
  }

  static class TemptGoal extends net.minecraft.entity.ai.goal.TemptGoal {

    @Nullable
    private PlayerEntity player;

    private final CaracalEntity caracalEntity;

    public TemptGoal(
      CaracalEntity caracalEntity,
      double speed,
      Ingredient food,
      boolean canBeScared
    ) {
      super(caracalEntity, speed, food, canBeScared);
      this.caracalEntity = caracalEntity;
    }

    public void tick() {
      super.tick();
      if (this.player == null && this.mob.getRandom().nextInt(600) == 0) {
        this.player = this.closestPlayer;
      } else if (this.mob.getRandom().nextInt(500) == 0) {
        this.player = null;
      }
    }

    protected boolean canBeScared() {
      return (
        (this.player == null || !this.player.equals(this.closestPlayer)) &&
        super.canBeScared()
      );
    }

    public boolean canStart() {
      return super.canStart() && !this.caracalEntity.isTamed();
    }
  }

  public static DefaultAttributeContainer.Builder createcaracalAttributes() {
    return PassiveEntity
      .createLivingAttributes()
      .add(EntityAttributes.GENERIC_MAX_HEALTH, health)
      .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, speed)
      .add(EntityAttributes.GENERIC_FOLLOW_RANGE, follow)
      .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, damage)
      .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, knockback);
  }

  public int getMaskColor() {
    return (Integer) this.dataTracker.get(CARACAL_BIRTHDAY_COLOR);
  }

  public void setMaskColor(int type) {
    if (type < 1 || type > 3) {
      type = this.random.nextInt(3 - 1 + 1) + 1;
    }

    this.dataTracker.set(CARACAL_BIRTHDAY_COLOR, type);
  }

  public void writeCustomDataToNbt(NbtCompound tag) {
    super.writeCustomDataToNbt(tag);
    tag.putInt("CaracalBirthdayColor", this.getMaskColor());
    if (this.commander) {
      tag.putBoolean("Commander", true);
    }
  }

  public void readCustomDataFromNbt(NbtCompound tag) {
    super.readCustomDataFromNbt(tag);
    this.setMaskColor(tag.getInt("CaracalBirthdayColor"));
    if (tag.contains("Commander", 99)) {
      this.commander = tag.getBoolean("Commander");
    }
  }

  public void setCustomName(@Nullable Text name) {
    super.setCustomName(name);
    if (this.getCustomName() != null) {
      String n = this.getCustomName().getString().toLowerCase(Locale.ENGLISH);
      this.commander =
        (n.contains("командир") || n.contains("commander")) &&
        !(n.contains("мирный") || n.contains("peaceful"));
    }
  }

  public boolean handleFallDamage(
    float fallDistance,
    float damageMultiplier,
    DamageSource damageSource
  ) {
    return false;
  }

  @Nullable
  protected SoundEvent getAmbientSound() {
    if (this.isTamed()) {
      if (this.isInLove()) {
        return CaracalMain.CARACAL_PURR;
      } else {
        return this.random.nextInt(4) == 0
          ? CaracalMain.CARACAL_PURREOW
          : CaracalMain.CARACAL_SCREAM;
      }
    } else {
      return CaracalMain.CARACAL_SCREAM;
    }
  }

  public int getMinAmbientSoundDelay() {
    return ThreadLocalRandom.current().nextInt(300, 1200 + 1);
  }

  protected SoundEvent getHurtSound(DamageSource source) {
    return CaracalMain.CARACAL_HISS;
  }

  protected SoundEvent getDeathSound() {
    return CaracalMain.CARACAL_DEATH;
  }

  private float getAttackDamage() {
    return (float) this.getAttributeValue(
        EntityAttributes.GENERIC_ATTACK_DAMAGE
      );
  }

  public boolean tryAttack(Entity target) {
    return target.damage(DamageSource.mob(this), this.getAttackDamage());
  }

  protected void eat(PlayerEntity player, Hand hand, ItemStack stack) {
    if (this.isBreedingItem(stack)) {
      this.playSound(CaracalMain.CARACAL_EAT, 1.0F, 1.0F);
    }

    super.eat(player, hand, stack);
  }

  @Nullable
  public PassiveEntity createChild(
    ServerWorld serverWorld,
    PassiveEntity passiveEntity
  ) {
    CaracalEntity caracalEntity = CaracalMain.CARACAL.create(serverWorld);
    if (passiveEntity instanceof CaracalEntity) {
      if (this.isTamed()) {
        caracalEntity.setOwnerUuid(this.getOwnerUuid());
        caracalEntity.setTamed(true);
      }
    }
    caracalEntity.setMaskColor(10);
    return caracalEntity;
  }

  public boolean canBreedWith(AnimalEntity other) {
    if (!this.isTamed()) {
      return false;
    } else if (!(other instanceof CaracalEntity caracalEntity)) {
      return false;
    } else {
      return caracalEntity.isTamed() && super.canBreedWith(other);
    }
  }

  protected void initDataTracker() {
    super.initDataTracker();
    this.dataTracker.startTracking(CARACAL_BIRTHDAY_COLOR, 10);
    this.dataTracker.startTracking(IN_SLEEPING_POSE, false);
  }

  public void setInSleepingPose(boolean sleeping) {
    this.dataTracker.set(IN_SLEEPING_POSE, sleeping);
  }

  public boolean isInSleepingPose() {
    return this.dataTracker.get(IN_SLEEPING_POSE);
  }

  static class SleepWithOwnerGoal extends Goal {

    private final CaracalEntity caracalEntity;
    private PlayerEntity owner;
    private BlockPos bedPos;
    private int ticksOnBed;

    public SleepWithOwnerGoal(CaracalEntity caracalEntity) {
      this.caracalEntity = caracalEntity;
    }

    public boolean canStart() {
      if (!this.caracalEntity.isTamed()) {
        return false;
      } else if (this.caracalEntity.isSitting()) {
        return false;
      } else {
        LivingEntity livingEntity = this.caracalEntity.getOwner();
        if (livingEntity instanceof PlayerEntity) {
          this.owner = (PlayerEntity) livingEntity;
          if (!livingEntity.isSleeping()) {
            return false;
          }

          if (this.caracalEntity.squaredDistanceTo(this.owner) > 100.0D) {
            return false;
          }

          BlockPos blockPos = this.owner.getBlockPos();
          BlockState blockState =
            this.caracalEntity.world.getBlockState(blockPos);
          if (blockState.isIn(BlockTags.BEDS)) {
            this.bedPos =
              (BlockPos) blockState
                .getOrEmpty(BedBlock.FACING)
                .map(direction -> blockPos.offset(direction.getOpposite()))
                .orElseGet(() -> new BlockPos(blockPos));
            return !this.cannotSleep();
          }
        }

        return false;
      }
    }

    private boolean cannotSleep() {
      List<CaracalEntity> list =
        this.caracalEntity.world.getNonSpectatingEntities(
            CaracalEntity.class,
            (new Box(this.bedPos)).expand(2.0D)
          );
      Iterator<CaracalEntity> var2 = list.iterator();

      CaracalEntity caracalEntity;
      do {
        do {
          if (!var2.hasNext()) {
            return false;
          }

          caracalEntity = var2.next();
        } while (caracalEntity == this.caracalEntity);
      } while (!caracalEntity.isInSleepingPose());

      return true;
    }

    public boolean shouldContinue() {
      return (
        this.caracalEntity.isTamed() &&
        !this.caracalEntity.isSitting() &&
        this.owner != null &&
        this.owner.isSleeping() &&
        this.bedPos != null &&
        !this.cannotSleep()
      );
    }

    public void start() {
      if (this.bedPos != null) {
        this.caracalEntity.setInSittingPose(false);
        this.caracalEntity.getNavigation()
          .startMovingTo(
            (double) this.bedPos.getX(),
            (double) this.bedPos.getY(),
            (double) this.bedPos.getZ(),
            1.100000023841858D
          );
      }
    }

    public void stop() {
      this.caracalEntity.setInSleepingPose(false);
      float f = this.caracalEntity.world.getSkyAngle(1.0F);
      if (
        this.owner.getSleepTimer() >= 100 &&
        (double) f > 0.77D &&
        (double) f < 0.8D &&
        (double) this.caracalEntity.world.getRandom().nextFloat() < 0.7D
      ) {
        this.dropMorningGifts();
      }

      this.ticksOnBed = 0;
      this.caracalEntity.getNavigation().stop();
    }

    private void dropMorningGifts() {
      Random random = (Random) this.caracalEntity.getRandom();
      BlockPos.Mutable mutable = new BlockPos.Mutable();
      mutable.set(this.caracalEntity.getBlockPos());
      this.caracalEntity.teleport(
          (double) (mutable.getX() + random.nextInt(11) - 5),
          (double) (mutable.getY() + random.nextInt(5) - 2),
          (double) (mutable.getZ() + random.nextInt(11) - 5),
          false
        );
      mutable.set(this.caracalEntity.getBlockPos());
      LootTable lootTable =
        this.caracalEntity.world.getServer()
          .getLootManager()
          .getTable(LootTables.CAT_MORNING_GIFT_GAMEPLAY);
      net.minecraft.loot.context.LootContext.Builder builder =
        (
          new net.minecraft.loot.context.LootContext.Builder(
            (ServerWorld) this.caracalEntity.world
          )
        ).parameter(LootContextParameters.ORIGIN, this.caracalEntity.getPos())
          .parameter(LootContextParameters.THIS_ENTITY, this.caracalEntity)
          .random((net.minecraft.util.math.random.Random) random);
      List<ItemStack> list = lootTable.generateLoot(
        builder.build(LootContextTypes.GIFT)
      );
      Iterator<ItemStack> var6 = list.iterator();

      while (var6.hasNext()) {
        ItemStack itemStack = var6.next();
        this.caracalEntity.world.spawnEntity(
            new ItemEntity(
              this.caracalEntity.world,
              (double) mutable.getX() -
              (double) MathHelper.sin(
                this.caracalEntity.bodyYaw * 0.017453292F
              ),
              (double) mutable.getY(),
              (double) mutable.getZ() +
              (double) MathHelper.cos(
                this.caracalEntity.bodyYaw * 0.017453292F
              ),
              itemStack
            )
          );
      }
    }

    public void tick() {
      if (this.owner != null && this.bedPos != null) {
        this.caracalEntity.setInSittingPose(false);
        this.caracalEntity.getNavigation()
          .startMovingTo(
            (double) this.bedPos.getX(),
            (double) this.bedPos.getY(),
            (double) this.bedPos.getZ(),
            1.100000023841858D
          );
        if (this.caracalEntity.squaredDistanceTo(this.owner) < 2.5D) {
          ++this.ticksOnBed;
          if (this.ticksOnBed > 16) {
            this.caracalEntity.setInSleepingPose(true);
          } else {
            this.caracalEntity.lookAtEntity(this.owner, 45.0F, 45.0F);
          }
        } else {
          this.caracalEntity.setInSleepingPose(false);
        }
      }
    }
  }

  public ActionResult interactMob(PlayerEntity player, Hand hand) {
    ItemStack itemStack = player.getStackInHand(hand);
    Item item = itemStack.getItem();
    if (this.world.isClient) {
      if (this.isTamed() && this.isOwner(player)) {
        return ActionResult.SUCCESS;
      } else {
        return (
            !this.isBreedingItem(itemStack) ||
            !(this.getHealth() < this.getMaxHealth()) &&
            this.isTamed()
          )
          ? ActionResult.PASS
          : ActionResult.SUCCESS;
      }
    } else {
      ActionResult actionResult;
      if (this.isTamed()) {
        if (this.isOwner(player)) {
          if (
            item.isFood() &&
            this.isBreedingItem(itemStack) &&
            this.getHealth() < this.getMaxHealth()
          ) {
            this.eat(player, hand, itemStack);
            this.heal((float) item.getFoodComponent().getHunger());
            return ActionResult.CONSUME;
          }

          actionResult = super.interactMob(player, hand);
          if (!actionResult.isAccepted() || this.isBaby()) {
            this.setSitting(!this.isSitting());
          }
          return actionResult;
        }
      } else if (this.isBreedingItem(itemStack)) {
        this.eat(player, hand, itemStack);
        if (this.random.nextInt(3) == 0) {
          this.setOwner(player);
          this.setSitting(true);
          this.world.sendEntityStatus(this, (byte) 7);
        } else {
          this.world.sendEntityStatus(this, (byte) 6);
        }

        this.setPersistent();
        return ActionResult.CONSUME;
      }

      actionResult = super.interactMob(player, hand);
      if (actionResult.isAccepted()) {
        this.setPersistent();
      }

      return actionResult;
    }
  }

  @Nullable
  public EntityData initialize(
    ServerWorldAccess world,
    LocalDifficulty difficulty,
    SpawnReason spawnReason,
    @Nullable EntityData entityData,
    @Nullable NbtCompound entityNbt
  ) {
    entityData =
      super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    this.setMaskColor(10);
    return entityData;
  }

  public boolean isBreedingItem(ItemStack stack) {
    return TAMING_INGREDIENT.test(stack);
  }

  protected float getActiveEyeHeight(
    EntityPose pose,
    EntityDimensions dimensions
  ) {
    return dimensions.height * 0.98F;
  }

  public boolean canImmediatelyDespawn(double distanceSquared) {
    return !this.isTamed() && this.age > 2400;
  }

  static {
    TAMING_INGREDIENT =
      Ingredient.ofItems(Items.COD, Items.SALMON, Items.CHICKEN, Items.RABBIT);
    IN_SLEEPING_POSE =
      DataTracker.registerData(
        CaracalEntity.class,
        TrackedDataHandlerRegistry.BOOLEAN
      );
    CARACAL_BIRTHDAY_COLOR =
      DataTracker.registerData(
        CaracalEntity.class,
        TrackedDataHandlerRegistry.INTEGER
      );
  }

  @Environment(EnvType.CLIENT)
  public Vec3d method_29919() {
    return new Vec3d(
      0.0D,
      0.5F * this.getStandingEyeHeight(),
      this.getWidth() * 0.4F
    );
  }
}
