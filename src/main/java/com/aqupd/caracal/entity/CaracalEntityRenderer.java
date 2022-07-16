package com.aqupd.caracal.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

@Environment(EnvType.CLIENT)
public class CaracalEntityRenderer extends GeoEntityRenderer<CaracalEntity> {

  public CaracalEntityRenderer(EntityRendererFactory.Context ctx) {
    super(ctx, new CaracalEntityModel());
  }

  @Override
  public RenderLayer getRenderType(CaracalEntity animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
    if (animatable.isBaby()) {
      stack.scale(0.6F, 0.6F, 0.6F);
    } else {
      stack.scale(1.0F, 1.0F, 1.0F);
    }
    return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
  }
}
