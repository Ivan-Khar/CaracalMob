package com.aqupd.caracal.events;

import com.aqupd.caracal.CaracalMain;
import com.aqupd.caracal.entity.CaracalEntity;
import com.aqupd.caracal.setup.CaracalEntities;
import com.aqupd.caracal.setup.CaracalItems;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

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
  
  @SubscribeEvent
  public static void addItemsToCreativeMenu(BuildCreativeModeTabContentsEvent event) {
    if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
      event.accept(CaracalItems.CARACAL_SPAWN_EGG);
    }
  }
}
