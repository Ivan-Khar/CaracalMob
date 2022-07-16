package com.aqupd.caracal;

import static com.aqupd.caracal.CaracalMain.CARACAL;

import com.aqupd.caracal.entity.CaracalEntity;
import com.aqupd.caracal.entity.CaracalEntityModel;
import com.aqupd.caracal.entity.CaracalEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;

@Environment(EnvType.CLIENT)
public class CaracalMainClient implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    EntityRendererRegistry.register(CARACAL, CaracalEntityRenderer::new);
  }
}
