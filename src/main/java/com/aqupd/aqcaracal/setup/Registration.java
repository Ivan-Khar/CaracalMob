package com.aqupd.aqcaracal.setup;

import com.aqupd.aqcaracal.biomemodifiers.CaracalBiomeModifier;
import com.aqupd.aqcaracal.entities.CaracalEntity;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.aqupd.aqcaracal.Caracal.MODID;

public class Registration {

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, MODID);
    private static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MODID);
    private static final DeferredRegister<Codec<? extends BiomeModifier>> BIOMEMODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, MODID);

    private static class CaracalSpawnEggItem extends ForgeSpawnEggItem {
        public CaracalSpawnEggItem(Properties properties) {
            super(Registration.CARACAL, 5453358, 15592688, properties);
        }
    }

    public static void init(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        SOUNDS.register(bus);
        ITEMS.register(bus);
        ENTITIES.register(bus);
        BIOMEMODIFIERS.register(bus);
    }

    public static final RegistryObject<SoundEvent> CARACAL_HISS = SOUNDS.register("entity.caracal.hiss",
            () -> new SoundEvent(new ResourceLocation(MODID, "entity.caracal.hiss")));
    public static final RegistryObject<SoundEvent> CARACAL_SCREAM = SOUNDS.register("entity.caracal.scream",
            () -> new SoundEvent(new ResourceLocation(MODID, "entity.caracal.scream")));
    public static final RegistryObject<SoundEvent> CARACAL_PURR = SOUNDS.register("entity.caracal.purr",
            () -> new SoundEvent(new ResourceLocation(MODID, "entity.caracal.purr")));
    public static final RegistryObject<SoundEvent> CARACAL_PURREOW = SOUNDS.register("entity.caracal.purreow",
            () -> new SoundEvent(new ResourceLocation(MODID, "entity.caracal.purreow")));
    public static final RegistryObject<SoundEvent> CARACAL_DEATH = SOUNDS.register("entity.caracal.death",
            () -> new SoundEvent(new ResourceLocation(MODID, "entity.caracal.death")));
    public static final RegistryObject<SoundEvent> CARACAL_EAT = SOUNDS.register("entity.caracal.eat",
            () -> new SoundEvent(new ResourceLocation(MODID, "entity.caracal.eat")));
    public static final RegistryObject<SoundEvent> CARACAL_BEG_FOR_FOOD = SOUNDS.register("entity.caracal.beg_for_food",
            () -> new SoundEvent(new ResourceLocation(MODID, "entity.caracal.beg_for_food")));

    public static final RegistryObject<EntityType<CaracalEntity>> CARACAL = ENTITIES.register("caracal",
            () -> EntityType.Builder.of(CaracalEntity::new, MobCategory.CREATURE).sized(0.6f, 0.7f)
                    .build(new ResourceLocation(MODID, "caracal").toString()));

    public static final RegistryObject<Item> CARACAL_SPAWN_EGG = ITEMS.register("caracal_spawn_egg",
            () -> new CaracalSpawnEggItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Codec<CaracalBiomeModifier>> CARACAL_CODEC = BIOMEMODIFIERS.register(MODID, () ->
        RecordCodecBuilder.create(builder -> builder.group(
          Biome.LIST_CODEC.fieldOf("biomes").forGetter(CaracalBiomeModifier::biomes),
          PlacedFeature.CODEC.fieldOf("feature").forGetter(CaracalBiomeModifier::feature)
        ).apply(builder, CaracalBiomeModifier::new)));
}
