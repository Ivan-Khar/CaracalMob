package com.aqupd.caracal;

import com.aqupd.caracal.entity.CaracalEntity;
import com.aqupd.caracal.utils.AqConfig;
import com.aqupd.caracal.utils.AqDebug;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
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
import java.util.Arrays;
import static com.aqupd.caracal.utils.AqLogger.*;

public class CaracalMain implements ModInitializer {

    int weight = AqConfig.INSTANCE.getNumberProperty("spawn.weight");
    int mingroup = AqConfig.INSTANCE.getNumberProperty("spawn.min");
    int maxgroup = AqConfig.INSTANCE.getNumberProperty("spawn.max");

    String[] biomelist = AqConfig.INSTANCE.getStringProperty("spawn.biomes").split(",");

    public static final Identifier CARACAL_SCREAM = new Identifier("aqupd:caracal_scream");
    public static SoundEvent CARACAL_AMBIENT = new SoundEvent(CARACAL_SCREAM);
    public static final Identifier CARACAL_HISSING = new Identifier("aqupd:caracal_hiss");
    public static SoundEvent CARACAL_HISS = new SoundEvent(CARACAL_HISSING);

    public static Identifier CARACAL_ID = new Identifier("aqupd", "caracal");
    public static final EntityType<CaracalEntity> CARACAL = Registry.register(
            Registry.ENTITY_TYPE,
            CARACAL_ID,
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE,
                    CaracalEntity::new).dimensions(EntityDimensions.changing(0.6f, 0.75f)).build()
    );

    public static final SpawnEggItem CARACAL_SPAWN_EGG = new SpawnEggItem(CARACAL, 5453358, 15592688, new FabricItemSettings().group(ItemGroup.MISC).fireproof().maxCount(64));

    @Override
    public void onInitialize() {
        ServerWorldEvents.LOAD.register((server, world) -> AqDebug.INSTANCE.startDebug(AqConfig.INSTANCE.getBooleanProperty("debug")));

        Registry.register(Registry.SOUND_EVENT, com.aqupd.caracal.CaracalMain.CARACAL_SCREAM, CARACAL_AMBIENT);
        Registry.register(Registry.SOUND_EVENT, com.aqupd.caracal.CaracalMain.CARACAL_HISSING, CARACAL_HISS);
        Registry.register(Registry.ITEM, new Identifier("aqupd", "caracal_spawn_egg"), CARACAL_SPAWN_EGG);
        FabricDefaultAttributeRegistry.register(CARACAL, CaracalEntity.createcaracalAttributes());

        BiomeModifications.addSpawn(
                selection -> Arrays.stream(biomelist).anyMatch(x -> x.equals(selection.getBiome().getCategory().getName().toUpperCase())),
                SpawnGroup.CREATURE,
                CARACAL,
                weight, mingroup, maxgroup // weight/min group size/max group size
        );
        SpawnRestrictionAccessor.callRegister(CARACAL, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MobEntity::canMobSpawn); //MobEntity::canMobSpawn
        logInfo("Caracal mod is loaded!");
    }
}
