package com.aqupd.caracal.setup;

import com.aqupd.caracal.CaracalMain;
import com.aqupd.caracal.entity.CaracalEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CaracalEntities {
  private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CaracalMain.MODID);

  public static final RegistryObject<EntityType<CaracalEntity>> CARACAL = ENTITIES.register("caracal", () -> EntityType.Builder.of(CaracalEntity::new, MobCategory.CREATURE).sized(0.6f, 0.7f)
    .build(new ResourceLocation(CaracalMain.MODID, "caracal").toString()));

  public static void init(IEventBus bus) {
    ENTITIES.register(bus);
  }
}
