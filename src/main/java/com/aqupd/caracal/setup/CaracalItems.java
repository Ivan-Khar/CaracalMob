package com.aqupd.caracal.setup;

import com.aqupd.caracal.CaracalMain;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CaracalItems {
  private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, CaracalMain.MODID);

  public static final Supplier<Item> CARACAL_SPAWN_EGG = ITEMS.register("caracal_spawn_egg",
    () -> new DeferredSpawnEggItem(CaracalEntities.CARACAL, 5453358, 15592688, new Item.Properties()));

  public static void init(IEventBus bus) {
    ITEMS.register(bus);
  }
}
