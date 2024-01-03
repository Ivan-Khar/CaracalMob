package com.aqupd.caracal.events;

import com.aqupd.caracal.CaracalMain;
import com.aqupd.caracal.entity.CaracalEntity;
import com.aqupd.caracal.setup.CaracalEntities;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@Mod.EventBusSubscriber(modid = CaracalMain.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {
  @SubscribeEvent
  public static void commonSetup(FMLCommonSetupEvent event) {
    event.enqueueWork(() -> SpawnPlacements.register(CaracalEntities.CARACAL.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, CaracalEntity::canSpawn));
  }

  @SubscribeEvent
  public static void registerAttributes(EntityAttributeCreationEvent event) {
    event.put(CaracalEntities.CARACAL.get(), CaracalEntity.createCaracalAttributes().build());
  }
}
