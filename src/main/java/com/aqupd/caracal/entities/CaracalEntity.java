package com.aqupd.caracal.entities;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class CaracalEntity extends MobEntity {

    private static final TrackedData<Boolean> TRUSTING;
    
    public CaracalEntity(EntityType<? extends CaracalEntity> entityType, World world) {
        super(entityType, world);
    }
    
    private boolean isTrusting() {
        return this.dataTracker.get(TRUSTING);
    }

    private void setTrusting(boolean trusting) {
        this.dataTracker.set(TRUSTING, trusting);
    }

    public void writeCustomDataToTag(CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.putBoolean("Trusting", this.isTrusting());
    }

    public void readCustomDataFromTag(CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        this.setTrusting(tag.getBoolean("Trusting"));
    }
    
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(TRUSTING, false);
    }
    
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(7, new PounceAtTargetGoal(this, 0.3F));
        this.goalSelector.add(8, new AttackGoal(this));
        this.goalSelector.add(11, new LookAtEntityGoal(this, PlayerEntity.class, 10.0F));
        this.targetSelector.add(1, new FollowTargetGoal<>(this, ChickenEntity.class, false));
        this.targetSelector.add(1, new FollowTargetGoal<>(this, TurtleEntity.class, 10, false, false, TurtleEntity.BABY_TURTLE_ON_LAND_FILTER));
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
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0D);
    }

    public boolean canImmediatelyDespawn(double distanceSquared) {
        return !this.isTrusting() && this.age > 2400;
    }

    public boolean handleFallDamage(float fallDistance, float damageMultiplier) {
        return false;
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_OCELOT_AMBIENT;
    }

    public int getMinAmbientSoundDelay() {
        return 20;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_OCELOT_HURT;
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

    public boolean damage(DamageSource source, float amount) {
        return !this.isInvulnerableTo(source) && super.damage(source, amount);
    }

    @Environment(EnvType.CLIENT)
    public void handleStatus(byte status) {
        if (status == 41) {
            this.showEmoteParticle(true);
        } else if (status == 40) {
            this.showEmoteParticle(false);
        } else {
            super.handleStatus(status);
        }

    }

    private void showEmoteParticle(boolean positive) {
        ParticleEffect particleEffect = ParticleTypes.HEART;
        if (!positive) {
            particleEffect = ParticleTypes.SMOKE;
        }

        for(int i = 0; i < 7; ++i) {
            double d = this.random.nextGaussian() * 0.02D;
            double e = this.random.nextGaussian() * 0.02D;
            double f = this.random.nextGaussian() * 0.02D;
            this.world.addParticle(particleEffect, this.getParticleX(1.0D), this.getRandomBodyY() + 0.5D, this.getParticleZ(1.0D), d, e, f);
        }

    }
    public static boolean canSpawn(EntityType<CaracalEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return random.nextInt(3) != 0;
    }

    public boolean canSpawn(WorldView world) {
        if (world.intersectsEntities(this) && !world.containsFluid(this.getBoundingBox())) {
            BlockPos blockPos = this.getBlockPos();
            if (blockPos.getY() < world.getSeaLevel()) {
                return false;
            }

            BlockState blockState = world.getBlockState(blockPos.down());
            return blockState.isOf(Blocks.GRASS_BLOCK) || blockState.isIn(BlockTags.LEAVES);
        }

        return false;
    }

    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable CompoundTag entityTag) {
        if (entityData == null) {
            entityData = new PassiveEntity.PassiveData(1.0F);
        }

        return super.initialize(world, difficulty, spawnReason, (EntityData)entityData, entityTag);
    }

    @Environment(EnvType.CLIENT)
    public Vec3d method_29919() {
        return new Vec3d(0.0D, (double)(0.5F * this.getStandingEyeHeight()), (double)(this.getWidth() * 0.4F));
    }

    static {
        TRUSTING = DataTracker.registerData(CaracalEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }
}
