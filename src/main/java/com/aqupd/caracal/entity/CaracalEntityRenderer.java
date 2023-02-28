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
  public void preRender(MatrixStack poseStack, CaracalEntity animatable, BakedGeoModel model, VertexConsumerProvider bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);

    if (animatable.isBaby()) { poseStack.scale(0.6F, 0.6F, 0.6F); }
    else { poseStack.scale(1.0F, 1.0F, 1.0F); }
  }
}
