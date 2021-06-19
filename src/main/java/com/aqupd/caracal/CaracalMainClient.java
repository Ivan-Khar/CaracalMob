package com.aqupd.caracal;

import com.aqupd.caracal.client.renderer.CaracalEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRendererFactory;

@Environment(EnvType.CLIENT)
public class CaracalMainClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(CaracalMain.CARACAL, (EntityRendererFactory.Context context) -> {
            return new CaracalEntityRenderer(context);
        });
    }
}
