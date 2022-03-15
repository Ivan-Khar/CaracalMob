package com.aqupd.aqcaracal.items;

import com.aqupd.aqcaracal.setup.Registration;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;


public class CaracalSpawnEggItem extends ForgeSpawnEggItem {
    public CaracalSpawnEggItem(Properties properties) {
        super(Registration.CARACAL, 5453358, 15592688, properties);
    }
}
