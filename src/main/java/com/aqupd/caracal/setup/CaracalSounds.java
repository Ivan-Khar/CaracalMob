package com.aqupd.caracal.setup;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

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
    SoundEvent se = new SoundEvent(new Identifier("aqupd", id));
    return Registry.register(Registry.SOUND_EVENT, id, se);
  }
}
