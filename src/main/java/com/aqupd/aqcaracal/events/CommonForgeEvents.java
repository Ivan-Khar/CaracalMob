package com.aqupd.aqcaracal.events;

import com.aqupd.aqcaracal.Caracal;
import com.aqupd.aqcaracal.setup.Registration;
import com.aqupd.aqcaracal.utils.AqConfig;
import com.aqupd.aqcaracal.utils.AqDebug;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Caracal.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonForgeEvents {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void biomeLoading(BiomeLoadingEvent event) {
        AqDebug.INSTANCE.startDebug(AqConfig.INSTANCE.getBooleanProperty("debug"));

        int weight = AqConfig.INSTANCE.getNumberProperty("spawn.weight");
        int mingroup = AqConfig.INSTANCE.getNumberProperty("spawn.min");
        int maxgroup = AqConfig.INSTANCE.getNumberProperty("spawn.max");
        String[] biomelist = AqConfig.INSTANCE.getStringProperty("spawn.biomes").split(",");

        for (String s : biomelist) {
            if (event.getCategory().toString().equals(s))
                event.getSpawns().addSpawn(MobCategory.CREATURE,
                        new SpawnerData(Registration.CARACAL.get(), weight, mingroup, maxgroup));
        }
    }
}

