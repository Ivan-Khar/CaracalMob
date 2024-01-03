package com.aqupd.caracal.setup;

import com.aqupd.caracal.CaracalMain;
import com.aqupd.caracal.entity.CaracalEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class CaracalEntities {
  private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, CaracalMain.MODID);

  public static final Supplier<EntityType<CaracalEntity>> CARACAL = ENTITIES.register("caracal", () -> EntityType.Builder.of(CaracalEntity::new, MobCategory.CREATURE).sized(0.6f, 0.7f)
    .build(new ResourceLocation(CaracalMain.MODID, "caracal").toString()));

  public static void init(IEventBus bus) {
    ENTITIES.register(bus);
  }
}
