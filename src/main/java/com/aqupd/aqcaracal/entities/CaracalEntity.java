package com.aqupd.aqcaracal.entities;

import com.aqupd.aqcaracal.ai.CaracalRelaxOnOwnerGoal;
import com.aqupd.aqcaracal.ai.CaracalTemptGoal;
import com.aqupd.aqcaracal.setup.Registration;
import com.aqupd.aqcaracal.utils.AqConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NonTameRandomTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;


import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

@SuppressWarnings("NullableProblems")
public class CaracalEntity extends TamableAnimal {

    private static final Ingredient TEMPT_INGREDIENT = Ingredient.of(Items.COD, Items.SALMON, Items.CHICKEN, Items.RABBIT);
    private static final EntityDataAccessor<Boolean> IS_LYING = SynchedEntityData.defineId(CaracalEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> RELAX_STATE_ONE = SynchedEntityData.defineId(CaracalEntity.class, EntityDataSerializers.BOOLEAN);

    private static double health = AqConfig.INSTANCE.getDoubleProperty("entity.health");
    private static double speed = AqConfig.INSTANCE.getDoubleProperty("entity.speed");
    private static double follow = AqConfig.INSTANCE.getDoubleProperty("entity.follow");
    private static double damage = AqConfig.INSTANCE.getDoubleProperty("entity.damage");
    private static double knockback = AqConfig.INSTANCE.getDoubleProperty("entity.knockback");

    private TemptGoal temptGoal;
    private float lieDownAmount;
    private float lieDownAmountO;
    private float lieDownAmountTail;
    private float lieDownAmountOTail;
    private float relaxStateOneAmount;
    private float relaxStateOneAmountO;

    public CaracalEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.temptGoal = new CaracalTemptGoal(this, 0.6D, TEMPT_INGREDIENT, true);
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new CaracalRelaxOnOwnerGoal(this));
        this.goalSelector.addGoal(3, this.temptGoal);
        this.goalSelector.addGoal(3, new PanicGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(7, new LeapAtTargetGoal(this, 0.3F));
        this.goalSelector.addGoal(8, new OcelotAttackGoal(this));
        this.goalSelector.addGoal(9, new BreedGoal(this, 0.8D));
        this.goalSelector.addGoal(10, new WaterAvoidingRandomStrollGoal(this, 0.8D, 1.0000001E-5F));
        this.goalSelector.addGoal(11, new LookAtPlayerGoal(this, Player.class, 10.0F));
        this.targetSelector.addGoal(1, new NonTameRandomTargetGoal<>(this, Player.class, false,
                livingEntity -> (livingEntity).getStringUUID().equals("06e02a3f-dc56-43b5-95b9-191387a59e01")));
        this.targetSelector.addGoal(1, new NonTameRandomTargetGoal<>(this, Chicken.class, false, null));
        this.targetSelector.addGoal(1, new NonTameRandomTargetGoal<>(this, Rabbit.class, false, null));
        this.targetSelector.addGoal(2, new NonTameRandomTargetGoal<>(this, Bat.class, false, null));
    }

    public void setLying(boolean bool) { this.entityData.set(IS_LYING, bool); }
    public boolean isLying() {
        return this.entityData.get(IS_LYING);
    }
    public void setRelaxStateOne(boolean bool) {
        this.entityData.set(RELAX_STATE_ONE, bool);
    }
    public boolean isRelaxStateOne() {
        return this.entityData.get(RELAX_STATE_ONE);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_LYING, false);
        this.entityData.define(RELAX_STATE_ONE, false);
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
    }

    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
    }

    public void customServerAiStep() {
        if (this.getMoveControl().hasWanted()) {
            double d0 = this.getMoveControl().getSpeedModifier();
            if (d0 == 0.6D) {
                this.setPose(Pose.CROUCHING);
                this.setSprinting(false);
            } else if (d0 == 1.33D) {
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
    }

    @Override
    protected SoundEvent getAmbientSound() {
        if (this.isTame()) {
            if (this.isInLove()) {
                return Registration.CARACAL_PURR.get();
            } else {
                return this.random.nextInt(4) == 0 ? Registration.CARACAL_PURREOW.get() : Registration.CARACAL_SCREAM.get();
            }
        } else {
            return Registration.CARACAL_SCREAM.get();
        }
    }

    @Override
    public int getAmbientSoundInterval() {
        return 120;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return Registration.CARACAL_HISS.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return Registration.CARACAL_DEATH.get();
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, health)
                .add(Attributes.MOVEMENT_SPEED, speed)
                .add(Attributes.FOLLOW_RANGE, follow)
                .add(Attributes.ATTACK_KNOCKBACK, knockback)
                .add(Attributes.ATTACK_DAMAGE, damage);
    }

    @Override
    public boolean causeFallDamage(float p_148859_, float p_148860_, DamageSource p_148861_) {
        return false;
    }

    @Override
    protected void usePlayerItem(Player target, InteractionHand targetHand, ItemStack item) {
        if (this.isFood(item)) {
            this.playSound(Registration.CARACAL_EAT.get(), 1.0F, 1.0F);
        }

        super.usePlayerItem(target, targetHand, item);
    }

    private float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        return pEntity.hurt(DamageSource.mobAttack(this), this.getAttackDamage());
    }

    /**
     *
     * Called to update the entity's position/logic.
     */
    public void tick() {
        super.tick();
        if (this.temptGoal != null && this.temptGoal.isRunning() && !this.isTame() && this.tickCount % 100 == 0) {
            this.playSound(Registration.CARACAL_BEG_FOR_FOOD.get(), 1.0F, 1.0F);
        }

        this.handleLieDown();
    }

    private void handleLieDown() {
        if ((this.isLying() || this.isRelaxStateOne()) && this.tickCount % 5 == 0) {
            this.playSound(Registration.CARACAL_PURR.get(), 0.6F + 0.4F * (this.random.nextFloat() - this.random.nextFloat()), 1.0F);
        }

        this.updateLieDownAmount();
        this.updateRelaxStateOneAmount();
    }

    private void updateLieDownAmount() {
        this.lieDownAmountO = this.lieDownAmount;
        this.lieDownAmountOTail = this.lieDownAmountTail;
        if (this.isLying()) {
            this.lieDownAmount = Math.min(1.0F, this.lieDownAmount + 0.15F);
            this.lieDownAmountTail = Math.min(1.0F, this.lieDownAmountTail + 0.08F);
        } else {
            this.lieDownAmount = Math.max(0.0F, this.lieDownAmount - 0.22F);
            this.lieDownAmountTail = Math.max(0.0F, this.lieDownAmountTail - 0.13F);
        }

    }

    private void updateRelaxStateOneAmount() {
        this.relaxStateOneAmountO = this.relaxStateOneAmount;
        if (this.isRelaxStateOne()) {
            this.relaxStateOneAmount = Math.min(1.0F, this.relaxStateOneAmount + 0.1F);
        } else {
            this.relaxStateOneAmount = Math.max(0.0F, this.relaxStateOneAmount - 0.13F);
        }

    }

    public float getLieDownAmount(float p_28184_) {
        return Mth.lerp(p_28184_, this.lieDownAmountO, this.lieDownAmount);
    }

    public float getLieDownAmountTail(float p_28188_) {
        return Mth.lerp(p_28188_, this.lieDownAmountOTail, this.lieDownAmountTail);
    }

    public float getRelaxStateOneAmount(float p_28117_) {
        return Mth.lerp(p_28117_, this.relaxStateOneAmountO, this.relaxStateOneAmount);
    }

    public boolean canMate(Animal pOtherAnimal) {
        if (!this.isTame()) {
            return false;
        } else if (!(pOtherAnimal instanceof CaracalEntity caracal)) {
            return false;
        } else {
            return caracal.isTame() && super.canMate(pOtherAnimal);
        }
    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item item = itemstack.getItem();
        if (this.level.isClientSide) {
            if (this.isTame() && this.isOwnedBy(pPlayer)) {
                return InteractionResult.SUCCESS;
            } else {
                return !this.isFood(itemstack) || !(this.getHealth() < this.getMaxHealth()) && this.isTame() ? InteractionResult.PASS : InteractionResult.SUCCESS;
            }
        } else {
            if (this.isTame()) {
                if (this.isOwnedBy(pPlayer)) {
                    if (item.isEdible() && this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                        this.usePlayerItem(pPlayer, pHand, itemstack);
                        this.heal((float)item.getFoodProperties().getNutrition());
                        return InteractionResult.CONSUME;
                    }

                    InteractionResult interactionresult = super.mobInteract(pPlayer, pHand);
                    if (!interactionresult.consumesAction() || this.isBaby()) {
                        this.setOrderedToSit(!this.isOrderedToSit());
                    }

                    return interactionresult;
                }
            } else if (this.isFood(itemstack)) {
                this.usePlayerItem(pPlayer, pHand, itemstack);
                if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, pPlayer)) {
                    this.tame(pPlayer);
                    this.setOrderedToSit(true);
                    this.level.broadcastEntityEvent(this, (byte)7);
                } else {
                    this.level.broadcastEntityEvent(this, (byte)6);
                }

                this.setPersistenceRequired();
                return InteractionResult.CONSUME;
            }

            InteractionResult interactionresult1 = super.mobInteract(pPlayer, pHand);
            if (interactionresult1.consumesAction()) {
                this.setPersistenceRequired();
            }

            return interactionresult1;
        }
    }

    public boolean isFood(ItemStack pStack) {
        return TEMPT_INGREDIENT.test(pStack);
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return pSize.height * 0.98F;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parent) {
        CaracalEntity caracal = Registration.CARACAL.get().create(level);
        UUID uuid = this.getOwnerUUID();
        if (uuid != null && caracal != null) {
            caracal.setOwnerUUID(uuid);
            caracal.setTame(true);
        }

        return caracal;
    }

    public static boolean canSpawn(EntityType<CaracalEntity> entity, LevelAccessor level, MobSpawnType type, BlockPos pos, Random random){
        return Animal.checkAnimalSpawnRules(entity, level, type, pos, random);
    }
}
