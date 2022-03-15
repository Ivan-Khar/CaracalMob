package com.aqupd.aqcaracal.ai;

import com.aqupd.aqcaracal.entities.CaracalEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;

import java.util.Random;

public class CaracalRelaxOnOwnerGoal extends Goal {
    private final CaracalEntity caracal;
    private Player ownerPlayer;
    private BlockPos goalPos;
    private int onBedTicks;

    public CaracalRelaxOnOwnerGoal(CaracalEntity caracal) {
        this.caracal = caracal;
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        if (!this.caracal.isTame()) {
            return false;
        } else if (this.caracal.isOrderedToSit()) {
            return false;
        } else {
            LivingEntity livingentity = this.caracal.getOwner();
            if (livingentity instanceof Player) {
                this.ownerPlayer = (Player) livingentity;
                if (!livingentity.isSleeping()) {
                    return false;
                }

                if (this.caracal.distanceToSqr(this.ownerPlayer) > 100.0D) {
                    return false;
                }

                BlockPos blockpos = this.ownerPlayer.blockPosition();
                BlockState blockstate = this.caracal.level.getBlockState(blockpos);
                if (blockstate.is(BlockTags.BEDS)) {
                    this.goalPos = blockstate.getOptionalValue(BedBlock.FACING).map((direction) -> {
                        return blockpos.relative(direction.getOpposite());
                    }).orElseGet(() -> {
                        return new BlockPos(blockpos);
                    });
                    return !this.spaceIsOccupied();
                }
            }
            return false;
        }
    }

    private boolean spaceIsOccupied() {
        for (CaracalEntity caracal : this.caracal.level.getEntitiesOfClass(CaracalEntity.class, (new AABB(this.goalPos)).inflate(2.0D))) {
            if (caracal != this.caracal && (caracal.isLying() || caracal.isRelaxStateOne())) {
                return true;
            }
        }

        return false;
    }

    public boolean canContinueToUse() {
        return this.caracal.isTame() && !this.caracal.isOrderedToSit() && this.ownerPlayer != null && this.ownerPlayer.isSleeping() && this.goalPos != null && !this.spaceIsOccupied();
    }

    public void start() {
        if (this.goalPos != null) {
            this.caracal.setInSittingPose(false);
            this.caracal.getNavigation().moveTo((double)this.goalPos.getX(), (double)this.goalPos.getY(), (double)this.goalPos.getZ(), (double)1.1F);
        }

    }

    public void stop() {
        this.caracal.setLying(false);
        float f = this.caracal.level.getTimeOfDay(1.0F);
        if (this.ownerPlayer.getSleepTimer() >= 100 && (double)f > 0.77D && (double)f < 0.8D && (double)this.caracal.level.getRandom().nextFloat() < 0.7D) {
            this.giveMorningGift();
        }

        this.onBedTicks = 0;
        this.caracal.setRelaxStateOne(false);
        this.caracal.getNavigation().stop();
    }

    private void giveMorningGift() {
        Random random = this.caracal.getRandom();
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        blockpos$mutableblockpos.set(this.caracal.blockPosition());
        this.caracal.randomTeleport((double)(blockpos$mutableblockpos.getX() + random.nextInt(11) - 5), (double)(blockpos$mutableblockpos.getY() + random.nextInt(5) - 2), (double)(blockpos$mutableblockpos.getZ() + random.nextInt(11) - 5), false);
        blockpos$mutableblockpos.set(this.caracal.blockPosition());
        LootTable loottable = this.caracal.level.getServer().getLootTables().get(BuiltInLootTables.CAT_MORNING_GIFT);
        LootContext.Builder lootcontext$builder = (new LootContext.Builder((ServerLevel)this.caracal.level)).withParameter(LootContextParams.ORIGIN, this.caracal.position()).withParameter(LootContextParams.THIS_ENTITY, this.caracal).withRandom(random);

        for(ItemStack itemstack : loottable.getRandomItems(lootcontext$builder.create(LootContextParamSets.GIFT))) {
            this.caracal.level.addFreshEntity(new ItemEntity(this.caracal.level, (double)blockpos$mutableblockpos.getX() - (double) Mth.sin(this.caracal.yBodyRot * ((float)Math.PI / 180F)), (double)blockpos$mutableblockpos.getY(), (double)blockpos$mutableblockpos.getZ() + (double)Mth.cos(this.caracal.yBodyRot * ((float)Math.PI / 180F)), itemstack));
        }

    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        if (this.ownerPlayer != null && this.goalPos != null) {
            this.caracal.setInSittingPose(false);
            this.caracal.getNavigation().moveTo((double) this.goalPos.getX(), (double) this.goalPos.getY(), (double) this.goalPos.getZ(), (double) 1.1F);
            if (this.caracal.distanceToSqr(this.ownerPlayer) < 2.5D) {
                ++this.onBedTicks;
                if (this.onBedTicks > 16) {
                    this.caracal.setLying(true);
                    this.caracal.setRelaxStateOne(false);
                } else {
                    this.caracal.lookAt(this.ownerPlayer, 45.0F, 45.0F);
                    this.caracal.setRelaxStateOne(true);
                }
            } else {
                this.caracal.setLying(false);
            }
        }
    }
}
