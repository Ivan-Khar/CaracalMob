package com.aqupd.caracal.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CaracalEntityRenderer extends GeoEntityRenderer<CaracalEntity> {

  public CaracalEntityRenderer(EntityRendererProvider.Context ctx) {
    super(ctx, new CaracalEntityModel());
  }

  @Override
  public void preRender(PoseStack poseStack, CaracalEntity animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);

    if (animatable.isBaby()) { poseStack.scale(0.6F, 0.6F, 0.6F); }
    else { poseStack.scale(1.0F, 1.0F, 1.0F); }
  }
}
