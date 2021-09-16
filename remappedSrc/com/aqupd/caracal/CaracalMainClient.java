package com.aqupd.caracal;

import com.aqupd.caracal.client.renderer.CaracalEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
@SuppressWarnings("deprecation")
public class CaracalMainClient implements ClientModInitializer {
    @Override
    public void onInitializeClient()
    {
        EntityRendererRegistry.INSTANCE.register(CaracalMain.CARACAL, CaracalEntityRenderer::new);
    }
}
