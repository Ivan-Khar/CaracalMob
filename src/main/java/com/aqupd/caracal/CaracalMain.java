package com.aqupd.caracal;

import com.aqupd.caracal.entity.CaracalEntity;
import com.aqupd.caracal.setup.CaracalSounds;
import com.aqupd.caracal.utils.AqConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

import static com.aqupd.caracal.utils.AqLogger.logInfo;

public class CaracalMain implements ModInitializer {

  int weight = AqConfig.INSTANCE.getNumberProperty("spawn.weight");
  int mingroup = AqConfig.INSTANCE.getNumberProperty("spawn.min");
  int maxgroup = AqConfig.INSTANCE.getNumberProperty("spawn.max");

  public static final EntityType<CaracalEntity> CARACAL = Registry.register(
    Registry.ENTITY_TYPE, new Identifier("aqupd", "caracal"),
    FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CaracalEntity::new)
      .dimensions(EntityDimensions.changing(0.6f, 0.75f)).build());

  public static final Item CARACAL_SPAWN_EGG = new SpawnEggItem(
    CaracalMain.CARACAL,
    5453358,
    15592688,
    new FabricItemSettings().group(ItemGroup.MISC).fireproof().maxCount(64));

  @Override
  public void onInitialize() {
    //ServerWorldEvents.LOAD.register((server, world) -> AqDebug.INSTANCE.startDebug(AqConfig.INSTANCE.getBooleanProperty("debug")));
    CaracalSounds.init();

    Registry.register(Registry.ITEM, new Identifier("aqupd", "caracal_spawn_egg"), CARACAL_SPAWN_EGG);
    FabricDefaultAttributeRegistry.register(CARACAL, CaracalEntity.createcaracalAttributes());

    BiomeModifications.addSpawn(
        BiomeSelectors.includeByKey(BiomeKeys.SAVANNA),
        SpawnGroup.CREATURE,
        CaracalMain.CARACAL,
        weight, mingroup, maxgroup
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
