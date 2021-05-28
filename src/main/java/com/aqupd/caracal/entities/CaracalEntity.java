package com.aqupd.caracal.entities;

import com.aqupd.caracal.Main;
import com.aqupd.caracal.aqupdSoundEvents;
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
import net.minecraft.item.DyeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CaracalEntity extends TameableEntity {
    private static final TrackedData<Boolean> SLEEPING_WITH_OWNER;
    private static final Ingredient TAMING_INGREDIENT;

    public CaracalEntity(EntityType<? extends CaracalEntity> entityType, World world) {
        super(entityType, world);
    }

    private boolean commander;

    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(1, new SitGoal(this));
        this.goalSelector.add(2, new FollowOwnerGoal(this, 1.0D, 10.0F, 5.0F, false));
        this.goalSelector.add(2, new CaracalEntity.SleepWithOwnerGoal(this));
        this.goalSelector.add(3, new EscapeDangerGoal(this, 1.4D));
        this.goalSelector.add(3, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(3, new TemptGoal(this, 1.0D, false, TAMING_INGREDIENT));
        this.goalSelector.add(5, new PounceAtTargetGoal(this, 0.3F));
        this.goalSelector.add(6, new AttackGoal(this));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 10.0F));
        this.goalSelector.add(8, new WanderAroundGoal(this, 0.5F));

        this.targetSelector.add(1, new TrackOwnerAttackerGoal(this));
        this.targetSelector.add(3, new FollowTargetGoal<>(this, ChickenEntity.class, true));
        this.targetSelector.add(4, new FollowTargetGoal<>(this, RabbitEntity.class, true));
        this.targetSelector.add(5, new FollowTargetGoal<>(this, BatEntity.class, true));
        this.targetSelector.add(6, new CaracalEntity.FollowEntityGoal(this));
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
    }


    public static DefaultAttributeContainer.Builder createcaracalAttributes(){
        return PassiveEntity.createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20.0D)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.5D)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.5);
    }

    public void writeCustomDataToTag(CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        if (this.commander) {
            tag.putBoolean("Commander", true);
        }

    }

    public void readCustomDataFromTag(CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        if (tag.contains("Commander", 99)) {
            this.commander = tag.getBoolean("Commander");
        }

    }

    public void setCustomName(@Nullable Text name) {
        super.setCustomName(name);
        if (this.getCustomName() != null) {
            String n = this.getCustomName().asString();

            if (n.equalsIgnoreCase("Командир") || n.equalsIgnoreCase("Commander")) {
                this.commander = true;
            }

            if (!(n.equalsIgnoreCase("Командир") || n.equalsIgnoreCase("Commander"))) {
                this.commander = false;
            }
        }
    }

    static class FollowEntityGoal extends FollowTargetGoal<LivingEntity>{
        public FollowEntityGoal(CaracalEntity caracalEntity) {
            super(caracalEntity, LivingEntity.class, 0, true, true, LivingEntity::isMobOrPlayer);
        }

        public boolean canStart() {
            return ((CaracalEntity)this.mob).commander && super.canStart();
        }

        public void start() {
            super.start();
            this.mob.setDespawnCounter(0);
        }
    }

    public boolean handleFallDamage(float fallDistance, float damageMultiplier) {
        return false;
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return aqupdSoundEvents.CARACAL_AMBIENT;
    }

    public int getMinAmbientSoundDelay() {
        return ThreadLocalRandom.current().nextInt(300, 1200 + 1);
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return aqupdSoundEvents.CARACAL_HISS;
    }
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_OCELOT_DEATH;
    }

    private float getAttackDamage() {
        return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }

    public boolean tryAttack(Entity target) {
        return target.damage(DamageSource.mob(this), this.getAttackDamage());
    }


    protected void eat(PlayerEntity player, ItemStack stack) {
        if (this.isBreedingItem(stack)) {
            this.playSound(SoundEvents.ENTITY_CAT_EAT, 1.0F, 1.0F);
        }

        super.eat(player, stack);
    }

    @Nullable
    public PassiveEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        CaracalEntity caracalEntity = Main.CARACAL.create(serverWorld);
        if (passiveEntity instanceof CaracalEntity) {
            if (this.isTamed()) {
                assert caracalEntity != null;
                caracalEntity.setOwnerUuid(this.getOwnerUuid());
                caracalEntity.setTamed(true);
            }
        }
        return caracalEntity;
    }
    public boolean canBreedWith(AnimalEntity other) {
        if (!this.isTamed()) {
            return false;
        } else if (!(other instanceof CaracalEntity)) {
            return false;
        } else {
            CaracalEntity caracalEntity = (CaracalEntity)other;
            return caracalEntity.isTamed() && super.canBreedWith(other);
        }
    }
    public void setSleepingWithOwner(boolean sleeping) {
        this.dataTracker.set(SLEEPING_WITH_OWNER, sleeping);
    }

    public boolean isSleepingWithOwner() {
        return (Boolean)this.dataTracker.get(SLEEPING_WITH_OWNER);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SLEEPING_WITH_OWNER, false);
    }

    static class SleepWithOwnerGoal extends Goal {
        private final CaracalEntity caracal;
        private PlayerEntity owner;
        private BlockPos bedPos;
        private int ticksOnBed;

        public SleepWithOwnerGoal(CaracalEntity caracal) {
            this.caracal = caracal;
        }

        public boolean canStart() {
            if (!this.caracal.isTamed()) {
                return false;
            } else if (this.caracal.isSitting()) {
                return false;
            } else {
                LivingEntity livingEntity = this.caracal.getOwner();
                if (livingEntity instanceof PlayerEntity) {
                    this.owner = (PlayerEntity)livingEntity;
                    if (!livingEntity.isSleeping()) {
                        return false;
                    }

                    if (this.caracal.squaredDistanceTo(this.owner) > 100.0D) {
                        return false;
                    }

                    BlockPos blockPos = this.owner.getBlockPos();
                    BlockState blockState = this.caracal.world.getBlockState(blockPos);
                    if (blockState.getBlock().isIn(BlockTags.BEDS)) {
                        this.bedPos = (BlockPos)blockState.method_28500(BedBlock.FACING).map((direction) -> {
                            return blockPos.offset(direction.getOpposite());
                        }).orElseGet(() -> {
                            return new BlockPos(blockPos);
                        });
                        return !this.method_16098();
                    }
                }

                return false;
            }
        }

        private boolean method_16098() {
            List<CaracalEntity> list = this.caracal.world.getNonSpectatingEntities(CaracalEntity.class, (new Box(this.bedPos)).expand(2.0D));
            Iterator var2 = list.iterator();

            CaracalEntity caracalEntity;
            do {
                do {
                    if (!var2.hasNext()) {
                        return false;
                    }

                    caracalEntity = (CaracalEntity)var2.next();
                } while(caracalEntity == this.caracal);
            } while(!caracalEntity.isSleepingWithOwner());

            return true;
        }

        public boolean shouldContinue() {
            return this.caracal.isTamed() && !this.caracal.isSitting() && this.owner != null && this.owner.isSleeping() && this.bedPos != null && !this.method_16098();
        }

        public void start() {
            if (this.bedPos != null) {
                this.caracal.setInSittingPose(false);
                this.caracal.getNavigation().startMovingTo((double)this.bedPos.getX(), (double)this.bedPos.getY(), (double)this.bedPos.getZ(), 1.100000023841858D);
            }

        }

        public void stop() {
            this.caracal.setSleepingWithOwner(false);
            float f = this.caracal.world.getSkyAngle(1.0F);
            if (this.owner.getSleepTimer() >= 100 && (double)f > 0.77D && (double)f < 0.8D && (double)this.caracal.world.getRandom().nextFloat() < 0.7D) {
                this.dropMorningGifts();
            }

            this.ticksOnBed = 0;
            this.caracal.getNavigation().stop();
        }

        private void dropMorningGifts() {
            Random random = this.caracal.getRandom();
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            mutable.set(this.caracal.getBlockPos());
            this.caracal.teleport((double)(mutable.getX() + random.nextInt(11) - 5), (double)(mutable.getY() + random.nextInt(5) - 2), (double)(mutable.getZ() + random.nextInt(11) - 5), false);
            mutable.set(this.caracal.getBlockPos());
            LootTable lootTable = this.caracal.world.getServer().getLootManager().getTable(LootTables.CAT_MORNING_GIFT_GAMEPLAY);
            net.minecraft.loot.context.LootContext.Builder builder = (new net.minecraft.loot.context.LootContext.Builder((ServerWorld)this.caracal.world)).parameter(LootContextParameters.ORIGIN, this.caracal.getPos()).parameter(LootContextParameters.THIS_ENTITY, this.caracal).random(random);
            List<ItemStack> list = lootTable.generateLoot(builder.build(LootContextTypes.GIFT));
            Iterator var6 = list.iterator();

            while(var6.hasNext()) {
                ItemStack itemStack = (ItemStack)var6.next();
                this.caracal.world.spawnEntity(new ItemEntity(this.caracal.world, (double)mutable.getX() - (double) MathHelper.sin(this.caracal.bodyYaw * 0.017453292F), (double)mutable.getY(), (double)mutable.getZ() + (double)MathHelper.cos(this.caracal.bodyYaw * 0.017453292F), itemStack));
            }

        }

        public void tick() {
            if (this.owner != null && this.bedPos != null) {
                this.caracal.setInSittingPose(false);
                this.caracal.getNavigation().startMovingTo((double)this.bedPos.getX(), (double)this.bedPos.getY(), (double)this.bedPos.getZ(), 1.100000023841858D);
                if (this.caracal.squaredDistanceTo(this.owner) < 2.5D) {
                    ++this.ticksOnBed;
                    if (this.ticksOnBed > 16) {
                        this.caracal.setSleepingWithOwner(true);
                    } else {
                        this.caracal.lookAtEntity(this.owner, 45.0F, 45.0F);
                    }
                } else {
                    this.caracal.setSleepingWithOwner(false);
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
                return !this.isBreedingItem(itemStack) || !(this.getHealth() < this.getMaxHealth()) && this.isTamed() ? ActionResult.PASS : ActionResult.SUCCESS;
            }
        } else {
            ActionResult actionResult;
            if (this.isTamed()) {
                if (this.isOwner(player)) {
                    if (!(item instanceof DyeItem)) {
                        if (item.isFood() && this.isBreedingItem(itemStack) && this.getHealth() < this.getMaxHealth()) {
                            this.eat(player, itemStack);
                            this.heal((float) Objects.requireNonNull(item.getFoodComponent()).getHunger());
                            return ActionResult.CONSUME;
                        }

                        actionResult = super.interactMob(player, hand);
                        if (!actionResult.isAccepted() || this.isBaby()) {
                            this.setSitting(!this.isSitting());
                        }

                        return actionResult;
                    }
                }
            } else if (this.isBreedingItem(itemStack)) {
                this.eat(player, itemStack);
                if (this.random.nextInt(3) == 0) {
                    this.setOwner(player);
                    this.setSitting(true);
                    this.world.sendEntityStatus(this, (byte)7);
                } else {
                    this.world.sendEntityStatus(this, (byte)6);
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

    public boolean isBreedingItem(ItemStack stack) {
        return TAMING_INGREDIENT.test(stack);
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height * 0.9F;
    }

    public boolean canImmediatelyDespawn(double distanceSquared) {
        return !this.isTamed() && this.age > 2400;
    }


    static {
        TAMING_INGREDIENT = Ingredient.ofItems(Items.COD, Items.SALMON, Items.CHICKEN, Items.RABBIT);
        SLEEPING_WITH_OWNER = DataTracker.registerData(CatEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }

    @Environment(EnvType.CLIENT)
    public Vec3d method_29919() {
        return new Vec3d(0.0D, 0.5F * this.getStandingEyeHeight(), this.getWidth() * 0.4F);
    }
}
