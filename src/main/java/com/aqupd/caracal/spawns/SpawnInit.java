package com.aqupd.caracal.spawns;

import java.util.function.Predicate;

import com.aqupd.caracal.Main;
import com.google.common.base.Preconditions;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.SpawnSettings;

@SuppressWarnings("deprecation")
public class SpawnInit {

    public static int SpawnRate = 100;

    public static void addSpawn(Predicate<BiomeSelectionContext> BiomeSelector, SpawnGroup spawnGroup, SpawnSettings.SpawnEntry se){
        Preconditions.checkArgument(se.type.getSpawnGroup() != SpawnGroup.MISC
                , "MISC spawns pigs");
        Identifier id = Registry.ENTITY_TYPE.getId(se.type);
        Preconditions.checkState(id != Registry.ENTITY_TYPE.getDefaultId(), "Unregistered entity type: %s", se.type);

        BiomeModifications.create(id).add(ModificationPhase.ADDITIONS, BiomeSelector, context -> {
            context.getSpawnSettings().addSpawn(spawnGroup, se);
        });
    }

    private static void normalspawn(){
        Predicate<BiomeSelectionContext> biomeSelector = (context) -> {
            Biome.Category category = context.getBiome().getCategory();
            return category == Biome.Category.JUNGLE;
        };

        addSpawn(biomeSelector, Main.CARACAL.getSpawnGroup(),
                new SpawnSettings.SpawnEntry(Main.CARACAL, SpawnRate, 2, 5));
    }

    public static void init() {
        normalspawn();
    }
}
