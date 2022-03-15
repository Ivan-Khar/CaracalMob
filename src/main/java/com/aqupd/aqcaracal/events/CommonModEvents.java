package com.aqupd.aqcaracal.events;

import com.aqupd.aqcaracal.Caracal;
import com.aqupd.aqcaracal.entities.CaracalEntity;
import com.aqupd.aqcaracal.setup.Registration;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Caracal.MODID, bus = Bus.MOD)
public class CommonModEvents {

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event){
        event.enqueueWork(() -> SpawnPlacements.register(Registration.CARACAL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CaracalEntity::canSpawn));
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(Registration.CARACAL.get(), CaracalEntity.createAttributes().build());
    }
}
