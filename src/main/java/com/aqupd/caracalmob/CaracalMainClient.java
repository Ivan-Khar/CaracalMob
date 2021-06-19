package com.aqupd.caracalmob;

import com.aqupd.caracalmob.client.model.CaracalEntityModel;
import com.aqupd.caracalmob.client.renderer.CaracalEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;

@Environment(EnvType.CLIENT)
@SuppressWarnings("deprecation")
public class CaracalMainClient implements ClientModInitializer {
    public static final EntityModelLayer CARACAL_MODEL_LAYER = new EntityModelLayer(CaracalMain.CARACAL_ID, "main");

    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(CARACAL_MODEL_LAYER, () -> CaracalEntityModel.createModelData());
        //Register the renderer
        EntityRendererRegistry.INSTANCE.register(CaracalMain.CARACAL, CaracalEntityRenderer::new);

    }
}
