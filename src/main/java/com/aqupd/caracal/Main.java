package com.aqupd.caracal;

import net.fabricmc.api.ModInitializer;
import com.aqupd.caracal.entities.CaracalEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class Main implements ModInitializer {


	public static final EntityType<CaracalEntity> CARACAL = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier("aqupd", "caracal"),
			FabricEntityTypeBuilder.create(SpawnGroup.CREATURE,
					CaracalEntity::new).dimensions(EntityDimensions.fixed(0.6f, 0.75f)).build()
	);

	public static final SpawnEggItem CARACAL_SPAWN_EGG = new SpawnEggItem(CARACAL, 5453358, 10587797, new FabricItemSettings().group(ItemGroup.MISC).fireproof().maxCount(64));

	@Override
	public void onInitialize() {

		Registry.register(Registry.ITEM, new Identifier("aqupd", "caracal_spawn_egg"), CARACAL_SPAWN_EGG);
		FabricDefaultAttributeRegistry.register(CARACAL, CaracalEntity.createcaracalAttributes());

		BiomeModifications.addSpawn(
				selection -> selection.getBiome().getCategory() == Biome.Category.SAVANNA,
				SpawnGroup.CREATURE,
				CARACAL,
				5, 1, 3 // weight/min group size/max group size
		);
	}
}
