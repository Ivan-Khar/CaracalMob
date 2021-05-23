package com.aqupd.caracal.entities;

import com.aqupd.caracal.Main;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class CaracalEntity extends PathAwareEntity {
    public boolean CaracalEntityCanSpawn = true;

    public CaracalEntity(EntityType<? extends CaracalEntity> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 1000;
        this.setHealth(10.0F);
        this.setMovementSpeed(0.3F);
    }

    //public static DefaultAttributeContainer.Builder createcaracalAttributes(){
    //    return PassiveEntity.createLivingAttributes()
    //            .add(EntityAttributes.GENERIC_MAX_HEALTH, 10D)
    //            .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.30000001192092896D)
    //            .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 64.0D);
    //}

    @Override
    public boolean canSpawn(WorldView view){
        BlockPos blockunderentity = new BlockPos(this.getX(), this.getY() - 1, this.getZ());
        BlockPos posentity = new BlockPos(this.getX(), this.getY(), this.getZ());
        return view.intersectsEntities(this) && this.world.isDay() && !world.containsFluid(this.getBoundingBox())
        && this.world.getBlockState(posentity).getBlock().canMobSpawnInside()
        && this.world.getBlockState(blockunderentity).allowsSpawning(view, blockunderentity, Main.CARACAL)
        && CaracalEntityCanSpawn;
    }
}
