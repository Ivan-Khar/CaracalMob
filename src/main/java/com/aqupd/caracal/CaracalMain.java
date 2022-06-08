package com.aqupd.caracal;

import static com.aqupd.caracal.utils.AqLogger.*;

import com.aqupd.caracal.entity.CaracalEntity;
import com.aqupd.caracal.utils.AqConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.impl.tag.convention.TagRegistration;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class CaracalMain implements ModInitializer {

  int weight = AqConfig.INSTANCE.getNumberProperty("spawn.weight");
  int mingroup = AqConfig.INSTANCE.getNumberProperty("spawn.min");
  int maxgroup = AqConfig.INSTANCE.getNumberProperty("spawn.max");

  public static SoundEvent CARACAL_SCREAM = new SoundEvent(
    new Identifier("aqupd:entity.caracal.scream")
  );
  public static SoundEvent CARACAL_HISS = new SoundEvent(
    new Identifier("aqupd:entity.caracal.hiss")
  );
  public static SoundEvent CARACAL_PURR = new SoundEvent(
    new Identifier("aqupd:entity.caracal.purr")
  );
  public static SoundEvent CARACAL_PURREOW = new SoundEvent(
    new Identifier("aqupd:entity.caracal.purreow")
  );
  public static SoundEvent CARACAL_DEATH = new SoundEvent(
    new Identifier("aqupd:entity.caracal.death")
  );
  public static SoundEvent CARACAL_EAT = new SoundEvent(
    new Identifier("aqupd:entity.caracal.eat")
  );
  public static SoundEvent CARACAL_BEG_FOR_FOOD = new SoundEvent(
    new Identifier("aqupd:entity.caracal.beg_for_food")
  );

  public static Identifier CARACAL_ID = new Identifier("aqupd", "caracal");
  public static final EntityType<CaracalEntity> CARACAL = Registry.register(
    Registry.ENTITY_TYPE,
    CARACAL_ID,
    FabricEntityTypeBuilder
      .create(SpawnGroup.CREATURE, CaracalEntity::new)
      .dimensions(EntityDimensions.changing(0.6f, 0.75f))
      .build()
  );

  public static final SpawnEggItem CARACAL_SPAWN_EGG = new SpawnEggItem(
    CaracalMain.CARACAL,
    5453358,
    15592688,
    new FabricItemSettings().group(ItemGroup.MISC).fireproof().maxCount(64)
  );

  @Override
  public void onInitialize() {
    //ServerWorldEvents.LOAD.register((server, world) -> AqDebug.INSTANCE.startDebug(AqConfig.INSTANCE.getBooleanProperty("debug")));

    Registry.register(
      Registry.SOUND_EVENT,
      "entity.caracal.scream",
      CARACAL_SCREAM
    );
    Registry.register(
      Registry.SOUND_EVENT,
      "entity.caracal.hiss",
      CARACAL_HISS
    );
    Registry.register(
      Registry.SOUND_EVENT,
      "entity.caracal.purr",
      CARACAL_PURR
    );
    Registry.register(
      Registry.SOUND_EVENT,
      "entity.caracal.purreow",
      CARACAL_PURREOW
    );
    Registry.register(
      Registry.SOUND_EVENT,
      "entity.caracal.death",
      CARACAL_DEATH
    );
    Registry.register(
      Registry.SOUND_EVENT,
      "entity.caracal.eat",
      CARACAL_EAT
    );
    Registry.register(
      Registry.SOUND_EVENT,
      "entity.caracal.beg_for_food",
      CARACAL_BEG_FOR_FOOD
    );

    Registry.register(
      Registry.ITEM,
      new Identifier("aqupd", "caracal_spawn_egg"),
      CARACAL_SPAWN_EGG
    );
    FabricDefaultAttributeRegistry.register(
      CARACAL,
      CaracalEntity.createcaracalAttributes()
    );

    BiomeModifications.addSpawn(
      BiomeSelectors.includeByKey(BiomeKeys.SAVANNA),
      SpawnGroup.CREATURE,
      CaracalMain.CARACAL,
      weight,
      mingroup,
      maxgroup
    );
    SpawnRestrictionAccessor.callRegister(
      CARACAL,
      SpawnRestriction.Location.ON_GROUND,
      Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
      MobEntity::canMobSpawn
    );
    logInfo("Caracal mod is loaded!");
  }
}
