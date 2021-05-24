package com.aqupd.caracal.client.model;

import com.aqupd.caracal.entities.CaracalEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class CaracalEntityModel extends EntityModel<CaracalEntity> {

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart ear1;
    private final ModelPart earfluff1;
    private final ModelPart ear2;
    private final ModelPart earfluff2;
    private final ModelPart tail;
    private final ModelPart tail2;
    private final ModelPart front_left_leg;
    private final ModelPart back_left_leg;
    private final ModelPart front_right_leg;
    private final ModelPart back_right_leg;

    public CaracalEntityModel(){
        textureWidth = 64;
        textureHeight = 32;
        body = new ModelPart(this);
        body.setPivot(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(20, 9).addCuboid(-2.5F, -11.5F, -8.5F, 5.0F, 6.0F, 17.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPivot(0.0F, 12.4F, -8.5F);
        head.setTextureOffset(0, 0).addCuboid(-3.0F, -2.4F, -4.0F, 6.0F, 4.0F, 4.0F, 0.0F, false);
        head.setTextureOffset(16, 1).addCuboid(-1.5F, -0.4F, -5.0F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        ear1 = new ModelPart(this);
        ear1.setPivot(1.5F, -2.4F, -0.5F);
        head.addChild(ear1);
        ear1.setTextureOffset(0, 8).addCuboid(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        earfluff1 = new ModelPart(this);
        earfluff1.setPivot(0.5F, -1.5F, 0.0F);
        ear1.addChild(earfluff1);
        setRotationAngle(earfluff1, 0.0F, 0.0F, 0.6109F);
        earfluff1.setTextureOffset(4, 8).addCuboid(0.0F, -2.0F, -0.5F, 0.0F, 2.0F, 1.0F, 0.0F, false);

        ear2 = new ModelPart(this);
        ear2.setPivot(-1.5F, -2.4F, -0.5F);
        head.addChild(ear2);
        ear2.setTextureOffset(0, 8).addCuboid(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        earfluff2 = new ModelPart(this);
        earfluff2.setPivot(-0.5F, -1.5F, 0.0F);
        ear2.addChild(earfluff2);
        setRotationAngle(earfluff2, 0.0F, 0.0F, -0.6109F);
        earfluff2.setTextureOffset(4, 8).addCuboid(0.0F, -2.0F, -0.5F, 0.0F, 2.0F, 1.0F, 0.0F, false);

        tail = new ModelPart(this);
        tail.setPivot(0.0F, 13.0F, 8.0F);
        setRotationAngle(tail, 0.6109F, 0.0F, 0.0F);
        tail.setTextureOffset(29, 21).addCuboid(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        tail2 = new ModelPart(this);
        tail2.setPivot(0.0F, 4.0F, -0.5F);
        tail.addChild(tail2);
        setRotationAngle(tail2, 0.6109F, 0.0F, 0.0F);
        tail2.setTextureOffset(33, 21).addCuboid(-0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        front_left_leg = new ModelPart(this);
        front_left_leg.setPivot(1.5F, 17.0F, -6.5F);
        front_left_leg.setTextureOffset(46, 0).addCuboid(-1.25F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

        back_left_leg = new ModelPart(this);
        back_left_leg.setPivot(1.25F, 17.0F, 6.0F);
        back_left_leg.setTextureOffset(54, 0).addCuboid(-1.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F, 0.0F, false);

        front_right_leg = new ModelPart(this);
        front_right_leg.setPivot(-1.5F, 17.0F, -6.5F);
        front_right_leg.setTextureOffset(46, 0).addCuboid(-0.75F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

        back_right_leg = new ModelPart(this);
        back_right_leg.setPivot(-1.25F, 17.0F, 6.0F);
        back_right_leg.setTextureOffset(54, 0).addCuboid(-1.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F, 0.0F, false);
    }

    @Override
    public void setAngles(CaracalEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertices, light, overlay);
        head.render(matrices, vertices, light, overlay);
        tail.render(matrices, vertices, light, overlay);
        front_left_leg.render(matrices, vertices, light, overlay);
        back_left_leg.render(matrices, vertices, light, overlay);
        front_right_leg.render(matrices, vertices, light, overlay);
        back_right_leg.render(matrices, vertices, light, overlay);
    }

    public void setRotationAngle(ModelPart bone, float x, float y, float z) {
        bone.pitch = x;
        bone.yaw = y;
        bone.roll = z;
    }
}
