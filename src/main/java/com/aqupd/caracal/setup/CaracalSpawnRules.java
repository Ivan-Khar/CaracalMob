package com.aqupd.caracal.setup;

import com.aqupd.caracal.CaracalMain;
import com.aqupd.caracal.entity.CaracalBiomeModifier;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CaracalSpawnRules {
  private static final DeferredRegister<Codec<? extends BiomeModifier>> BIOMEMODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, CaracalMain.MODID);

  public static final RegistryObject<Codec<CaracalBiomeModifier>> CARACAL_CODEC = BIOMEMODIFIERS.register(CaracalMain.MODID, () ->
    RecordCodecBuilder.create(builder -> builder.group(
      Biome.LIST_CODEC.fieldOf("biomes").forGetter(CaracalBiomeModifier::biomes),
      PlacedFeature.CODEC.fieldOf("feature").forGetter(CaracalBiomeModifier::feature)
    ).apply(builder, CaracalBiomeModifier::new)));

  public static void init(IEventBus bus) {
    BIOMEMODIFIERS.register(bus);
  }

}
