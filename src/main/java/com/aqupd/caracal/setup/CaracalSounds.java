package com.aqupd.caracal.setup;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

@SuppressWarnings("SpellCheckingInspection")
public class CaracalSounds {
  public static final SoundEvent ENTITY_CARACAL_SCREAM = register("entity.caracal.scream");
  public static final SoundEvent ENTITY_CARACAL_SMALL_SCREAM = register("entity.caracal.small.scream");
  public static final SoundEvent ENTITY_CARACAL_HISS = register("entity.caracal.hiss");
  public static final SoundEvent ENTITY_CARACAL_PURR = register("entity.caracal.purr");
  public static final SoundEvent ENTITY_CARACAL_PURREOW = register("entity.caracal.purreow");
  public static final SoundEvent ENTITY_CARACAL_DEATH = register("entity.caracal.death");
  public static final SoundEvent ENTITY_CARACAL_EAT = register("entity.caracal.eat");
  public static final SoundEvent ENTITY_CARACAL_BEG_FOR_FOOD = register("entity.caracal.beg_for_food");

  public static void init() {}

  private static SoundEvent register(String id) {
    SoundEvent soundEvent = SoundEvent.createVariableRangeEvent(new ResourceLocation("aqupd", id));
    return Registry.register(BuiltInRegistries.SOUND_EVENT, id, soundEvent);
  }
}
