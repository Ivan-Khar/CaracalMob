package com.aqupd.caracal.setup;

import com.aqupd.caracal.CaracalMain;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

@SuppressWarnings("SpellCheckingInspection")
public class CaracalSounds {
  private static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, CaracalMain.MODID);

  public static final Supplier<SoundEvent> ENTITY_CARACAL_SCREAM = SOUNDS.register("entity.caracal.scream", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(CaracalMain.MODID, "entity.caracal.scream")));
  public static final Supplier<SoundEvent> ENTITY_CARACAL_SMALL_SCREAM = SOUNDS.register("entity.caracal.small.scream", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(CaracalMain.MODID, "entity.caracal.small.scream")));
  public static final Supplier<SoundEvent> ENTITY_CARACAL_HISS = SOUNDS.register("entity.caracal.hiss", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(CaracalMain.MODID, "entity.caracal.hiss")));
  public static final Supplier<SoundEvent> ENTITY_CARACAL_PURR = SOUNDS.register("entity.caracal.purr", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(CaracalMain.MODID, "entity.caracal.purr")));
  public static final Supplier<SoundEvent> ENTITY_CARACAL_PURREOW = SOUNDS.register("entity.caracal.purreow", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(CaracalMain.MODID, "entity.caracal.purreow")));
  public static final Supplier<SoundEvent> ENTITY_CARACAL_DEATH = SOUNDS.register("entity.caracal.death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(CaracalMain.MODID, "entity.caracal.death")));
  public static final Supplier<SoundEvent> ENTITY_CARACAL_EAT = SOUNDS.register("entity.caracal.eat", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(CaracalMain.MODID, "entity.caracal.eat")));
  public static final Supplier<SoundEvent> ENTITY_CARACAL_BEG_FOR_FOOD = SOUNDS.register("entity.caracal.beg_for_food", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(CaracalMain.MODID, "entity.caracal.beg_for_food")));

  public static void init(IEventBus bus) {
    SOUNDS.register(bus);
  }

}