package com.aqupd.caracal.ai;

import com.aqupd.caracal.entity.CaracalEntity;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.enums.BedPart;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class CaracalSitOnBlockGoal extends MoveToTargetPosGoal {

  private final CaracalEntity caracalEntity;

  public CaracalSitOnBlockGoal(CaracalEntity caracalEntity, double speed) {
    super(caracalEntity, speed, 8);
    this.caracalEntity = caracalEntity;
  }

  public boolean canStart() {
    return (
      this.caracalEntity.isTamed() &&
        !this.caracalEntity.isSitting() &&
        super.canStart()
    );
  }

  public void start() {
    super.start();
    this.caracalEntity.setInSittingPose(false);
  }

  public void stop() {
    super.stop();
    this.caracalEntity.setInSittingPose(false);
  }

  public void tick() {
    super.tick();
    this.caracalEntity.setInSittingPose(this.hasReached());
  }

  protected boolean isTargetPos(WorldView world, BlockPos pos) {
    if (!world.isAir(pos.up())) {
      return false;
    } else {
      BlockState blockState = world.getBlockState(pos);
      return blockState.isIn(
        BlockTags.BEDS, state -> state.getOrEmpty(BedBlock.PART)
                  .map(part -> part != BedPart.HEAD)
                  .orElse(true)
      );
    }
  }
}
