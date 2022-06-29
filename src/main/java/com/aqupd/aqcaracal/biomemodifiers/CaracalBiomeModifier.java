package com.aqupd.aqcaracal.biomemodifiers;

import com.aqupd.aqcaracal.setup.Registration;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public record CaracalBiomeModifier(HolderSet<Biome> biomes, Holder<PlacedFeature> feature)  implements BiomeModifier {
  @Override
  public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
  }

  @Override
  public Codec<? extends BiomeModifier> codec() {
    return Registration.CARACAL_CODEC.get();
  }
}
