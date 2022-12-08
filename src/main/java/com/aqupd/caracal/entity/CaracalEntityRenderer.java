package com.aqupd.caracal.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@Environment(EnvType.CLIENT)
public class CaracalEntityRenderer extends GeoEntityRenderer<CaracalEntity> {

  public CaracalEntityRenderer(EntityRendererFactory.Context ctx) {
    super(ctx, new CaracalEntityModel());
  }

  @Override
  public void postRender(MatrixStack stack, CaracalEntity animatable, BakedGeoModel model, VertexConsumerProvider bufferSource, VertexConsumer buffer, float partialTick, int packedLight,
                         int packedOverlay, float red, float green, float blue, float alpha) {
    if (animatable.isBaby()) {
      stack.scale(0.6F, 0.6F, 0.6F);
    } else {
      stack.scale(1.0F, 1.0F, 1.0F);
    }
    super.postRender(stack, animatable, model, bufferSource, buffer, partialTick, packedLight,
        packedOverlay, red, green, blue, alpha);
  }
}
