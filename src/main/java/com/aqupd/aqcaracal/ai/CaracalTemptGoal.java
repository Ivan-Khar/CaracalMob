package com.aqupd.aqcaracal.ai;

import com.aqupd.aqcaracal.entities.CaracalEntity;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nullable;

public class CaracalTemptGoal extends TemptGoal {
    @Nullable
    private Player selectedPlayer;
    private final CaracalEntity caracal;

    public CaracalTemptGoal(CaracalEntity caracal, double speed, Ingredient ingredient, boolean canScare) {
        super(caracal, speed, ingredient, canScare);
        this.caracal = caracal;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        super.tick();
        if (this.selectedPlayer == null && this.mob.getRandom().nextInt(600) == 0) {
            this.selectedPlayer = this.player;
        } else if (this.mob.getRandom().nextInt(500) == 0) {
            this.selectedPlayer = null;
        }

    }

    protected boolean canScare() {
        return false;
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        return super.canUse() && !this.caracal.isTame();
    }
}
