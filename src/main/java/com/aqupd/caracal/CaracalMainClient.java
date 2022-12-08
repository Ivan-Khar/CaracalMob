package com.aqupd.caracal;

import com.aqupd.caracal.entity.CaracalEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

import static com.aqupd.caracal.CaracalMain.CARACAL;

@Environment(EnvType.CLIENT)
public class CaracalMainClient implements ClientModInitializer {
  @Override
  public void onInitializeClient() {
    EntityRendererRegistry.register(CARACAL, CaracalEntityRenderer::new);
  }
}
