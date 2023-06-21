package com.aqupd.caracal;

import com.aqupd.caracal.entity.CaracalEntity;
import com.aqupd.caracal.setup.CaracalSounds;
import com.aqupd.caracal.utils.AqConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Heightmap;

import static com.aqupd.caracal.utils.AqLogger.logInfo;

public class CaracalMain implements ModInitializer {

  int weight = AqConfig.INSTANCE.getNumberProperty("spawn.weight");
  int mingroup = AqConfig.INSTANCE.getNumberProperty("spawn.min");
  int maxgroup = AqConfig.INSTANCE.getNumberProperty("spawn.max");

  public static final EntityType<CaracalEntity> CARACAL = Registry.register(
      BuiltInRegistries.ENTITY_TYPE, new ResourceLocation("aqupd", "caracal"),
      FabricEntityTypeBuilder.create(MobCategory.CREATURE, CaracalEntity::new)
          .dimensions(EntityDimensions.scalable(0.6f, 0.75f)).build());

  public static final Item CARACAL_SPAWN_EGG = new SpawnEggItem(
    CaracalMain.CARACAL,
    5453358,
    15592688,
    new FabricItemSettings().fireproof().maxCount(64));

  @SuppressWarnings("DataFlowIssue")
  @Override
  public void onInitialize() {
    //ServerWorldEvents.LOAD.register((server, world) -> AqDebug.INSTANCE.startDebug(AqConfig.INSTANCE.getBooleanProperty("debug")));
    CaracalSounds.init();

    Registry.register(BuiltInRegistries.ITEM, new ResourceLocation("aqupd", "caracal_spawn_egg"), CARACAL_SPAWN_EGG);
    FabricDefaultAttributeRegistry.register(CARACAL, CaracalEntity.createCaracalAttributes());

    BiomeModifications.addSpawn(
      //TODO: Make it choose biomes by tag
      BiomeSelectors.includeByKey(Biomes.SAVANNA),
      MobCategory.CREATURE,
      CaracalMain.CARACAL,
      weight, mingroup, maxgroup
    );

    SpawnPlacements.register(
      CARACAL,
      SpawnPlacements.Type.ON_GROUND,
      Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
      Mob::checkMobSpawnRules
    );

    ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(entries -> entries.accept(CARACAL_SPAWN_EGG));
    logInfo("Caracal mod is loaded!");
  }
}
