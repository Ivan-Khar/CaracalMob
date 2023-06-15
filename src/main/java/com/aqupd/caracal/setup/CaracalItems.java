package com.aqupd.caracal.setup;

import com.aqupd.caracal.CaracalMain;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CaracalItems {
  private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CaracalMain.MODID);

  private static class CaracalSpawnEggItem extends ForgeSpawnEggItem {
    public CaracalSpawnEggItem(Properties properties) {
      super(CaracalEntities.CARACAL, 5453358, 15592688, properties);
    }
  }

  public static final RegistryObject<Item> CARACAL_SPAWN_EGG = ITEMS.register("caracal_spawn_egg",
    () -> new CaracalSpawnEggItem(new Item.Properties()));

  public static void init(IEventBus bus) {
    ITEMS.register(bus);
  }
}
