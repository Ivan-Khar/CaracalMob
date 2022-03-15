package com.aqupd.aqcaracal.datagen;

import com.aqupd.aqcaracal.Caracal;
import com.aqupd.aqcaracal.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class Items extends ItemModelProvider {

    public Items(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Caracal.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        singleTexture(Registration.CARACAL_SPAWN_EGG.get().getRegistryName().getPath(),
                new ResourceLocation("item/template_spawn_egg"),
                new ResourceLocation(Caracal.MODID, "item/caracal_spawn_egg"));

    }
}
