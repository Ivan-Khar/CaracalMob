package com.aqupd.caracal.ai;

import com.aqupd.caracal.entity.CaracalEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BedPart;

public class CaracalSitOnBlockGoal extends MoveToBlockGoal {

  private final CaracalEntity caracalEntity;

  public CaracalSitOnBlockGoal(CaracalEntity caracalEntity, double speed) {
    super(caracalEntity, speed, 8);
    this.caracalEntity = caracalEntity;
  }

  public boolean canUse() {
    return (
      this.caracalEntity.isTame() &&
        !this.caracalEntity.isOrderedToSit() &&
        super.canUse()
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
    this.caracalEntity.setInSittingPose(this.isReachedTarget());
  }

  @Override
  protected boolean isValidTarget(LevelReader level, BlockPos pos) {
    if (!level.isEmptyBlock(pos.above())) {
      return false;
    } else {
      BlockState blockState = level.getBlockState(pos);
      return blockState.is(
        BlockTags.BEDS, state -> state.getOptionalValue(BedBlock.PART)
          .map(part -> part != BedPart.HEAD)
          .orElse(true)
      );
    }
  }
}
