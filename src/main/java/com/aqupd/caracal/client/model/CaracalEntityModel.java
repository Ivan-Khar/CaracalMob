package com.aqupd.caracal.client.model;

import com.aqupd.caracal.entities.CaracalEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class CaracalEntityModel extends EntityModel<CaracalEntity> {
    private final ModelPart base;
    public CaracalEntityModel(){
        this.textureHeight = 64;
        this.textureWidth = 64;

        base = new ModelPart(this, 0, 0);
        base.addCuboid(-8, -10, -8, 16, 16, 16);
    }

    @Override
    public void setAngles(CaracalEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {

        matrices.translate(0,1.125,0);
        base.render(matrices, vertices, light, overlay);
    }
}
