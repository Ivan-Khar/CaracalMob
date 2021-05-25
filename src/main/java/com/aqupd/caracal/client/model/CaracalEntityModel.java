package com.aqupd.caracal.client.model;

import com.aqupd.caracal.entities.CaracalEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class CaracalEntityModel extends EntityModel<CaracalEntity> {

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart tail;
    private final ModelPart tail2;
    private final ModelPart front_left_leg;
    private final ModelPart back_left_leg;
    private final ModelPart front_right_leg;
    private final ModelPart back_right_leg;
    private final ModelPart ear1;
    private final ModelPart ear2;
    private final ModelPart earfluff1;
    private final ModelPart earfluff2;
    protected int animationState = 1;
    
    public CaracalEntityModel(){
        textureWidth = 64;
        textureHeight = 32;
        body = new ModelPart(this);
        body.setPivot(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(20, 9).addCuboid(-2.5F, -11.5F, -8.5F, 5.0F, 6.0F, 17.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPivot(0.0F, 13.4F, -8.2F);
        head.setTextureOffset(0, 0).addCuboid(-3.0F, -2.9F, -3.6F, 6.0F, 4.0F, 4.0F, 0.0F, false);
        head.setTextureOffset(16, 1).addCuboid(-1.5F, -0.9F, -4.6F, 3.0F, 2.0F, 1.0F, 0.0F, false);

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
        this.head.pitch = headPitch * 0.017453292F;
        this.head.yaw = headYaw * 0.017453292F;
        if (this.animationState != 3) {
            this.body.pitch = 0.0F;
            if (this.animationState == 2) {
                this.back_left_leg.pitch = MathHelper.cos(limbAngle * 0.6662F) * limbDistance;
                this.back_right_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + 0.3F) * limbDistance;
                this.front_left_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F + 0.3F) * limbDistance;
                this.front_right_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * limbDistance;
                this.tail2.pitch = 0.5278761F + 0.31415927F * MathHelper.cos(limbAngle) * limbDistance;
            } else {
                this.back_left_leg.pitch = MathHelper.cos(limbAngle * 0.6662F) * limbDistance;
                this.back_right_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * limbDistance;
                this.front_left_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * limbDistance;
                this.front_right_leg.pitch = MathHelper.cos(limbAngle * 0.6662F) * limbDistance;
                if (this.animationState == 1) {
                    if (entity.isInSneakingPose()) {
                        setRotationAngle(body, 0.0524F, 0.0F, 0.0F);
                        setRotationAngle(earfluff1, 0.0F, 0.0F, 2.1126F);
                        setRotationAngle(earfluff2, 0.0F, 0.0F, -1.9199F);
                        head.setPivot(0.0F, 14.4F, -8.2F);
                        tail.setPivot(0.0F, 13.0F, 7.0F);
                    }
                    else if (entity.isInSittingPose()){
                        setRotationAngle(body, -0.7854F, 0.0F, 0.0F);
                        setRotationAngle(earfluff1, 0.0F, 0.0F, 1.1109F);
                        setRotationAngle(earfluff2, 0.0F, 0.0F, -1.1109F);
                        body.setPivot(0.0F, 25.2F, -5.0F);
                        head.setPivot(0.0F, 12.4F, -5.2F);
                        front_left_leg.setPivot(1.5F, 17.0F, -5.0F);
                        front_right_leg.setPivot(-1.5F, 17.0F, -5.0F);
                        setRotationAngle(front_left_leg, -0.3182F, 0.0F, 0.0F);
                        setRotationAngle(front_right_leg, -0.3182F, 0.0F, 0.0F);
                        back_left_leg.setPivot(1.25F, 23.0F, 3.0F);
                        back_right_leg.setPivot(-1.25F, 23.0F, 3.0F);
                        setRotationAngle(back_left_leg, -1.6F, 0.0F, 0.0F);
                        setRotationAngle(back_right_leg, -1.6F, 0.0F, 0.0F);
                        tail.setPivot(0.0F, 22.5F, 8.0F);
                        setRotationAngle(tail, 1.9109F, 0.0F, 0.0F);
                    }
                    else{
                        setRotationAngle(body, 0.0F, 0.0F, 0.0F);
                        setRotationAngle(earfluff1, 0.0F, 0.0F, 0.6109F);
                        setRotationAngle(earfluff2, 0.0F, 0.0F, -0.6109F);
                        body.setPivot(0.0F, 24.0F, 0.0F);
                        head.setPivot(0.0F, 13.4F, -8.2F);
                        ear1.setPivot(1.5F, -2.4F, -0.5F);
                        earfluff1.setPivot(0.5F, -1.5F, 0.0F);
                        ear2.setPivot(-1.5F, -2.4F, -0.5F);
                        earfluff2.setPivot(-0.5F, -1.5F, 0.0F);
                        tail.setPivot(0.0F, 13.0F, 8.0F);
                        tail2.setPivot(0.0F, 4.0F, -0.5F);
                        front_left_leg.setPivot(1.5F, 17.0F, -6.5F);
                        back_left_leg.setPivot(1.25F, 17.0F, 6.0F);
                        front_right_leg.setPivot(-1.5F, 17.0F, -6.5F);
                        back_right_leg.setPivot(-1.25F, 17.0F, 6.0F);
                        setRotationAngle(tail, 0.6109F, 0.0F, 0.0F);
                    }
                    this.tail2.pitch = 0.5278761F + 0.3853982F * MathHelper.cos(limbAngle) * limbDistance;
                } else {
                    this.tail2.pitch = 0.5278761F + 0.27123894F * MathHelper.cos(limbAngle) * limbDistance;
                }
            }
        }

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
