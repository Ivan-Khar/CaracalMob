package com.aqupd.caracal.client.model;

import com.aqupd.caracal.entities.CaracalEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class CaracalEntityModel extends EntityModel<CaracalEntity> {
    private final ModelPart main;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart tail1;
    private final ModelPart explosive;
    private final ModelPart tnt;
    private final ModelPart wire;
    private final ModelPart wire12_r1;
    private final ModelPart wire12_r2;
    private final ModelPart wire10_r1;
    private final ModelPart head;
    private final ModelPart ear1;
    private final ModelPart earfluff1;
    private final ModelPart ear2;
    private final ModelPart earfluff2;
    private final ModelPart mouth;
    private final ModelPart anonymous;
    private final ModelPart anonymous3_r1;
    private final ModelPart anonymous2_r1;
    private final ModelPart commander;
    private final ModelPart commander2_r1;
    private final ModelPart front_right_leg;
    private final ModelPart front_left_leg;
    private final ModelPart back_right_leg;
    private final ModelPart back_left_leg;

    public CaracalEntityModel() {
        textureWidth = 64;
        textureHeight = 64;
        main = new ModelPart(this);
        main.setPivot(0.0F, 15.5F, 0.0F);


        body = new ModelPart(this);
        body.setPivot(0.0F, 0.0F, 0.0F);
        main.addChild(body);
        body.setTextureOffset(20, 9).addCuboid(-2.5F, -3.0F, -8.5F, 5.0F, 6.0F, 17.0F, 0.0F, false);

        tail = new ModelPart(this);
        tail.setPivot(0.0F, -2.5F, 8.0F);
        body.addChild(tail);
        setRotationAngle(tail, 0.6109F, 0.0F, 0.0F);
        tail.setTextureOffset(29, 21).addCuboid(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        tail1 = new ModelPart(this);
        tail1.setPivot(0.0F, 4.0F, -0.5F);
        tail.addChild(tail1);
        setRotationAngle(tail1, 0.6109F, 0.0F, 0.0F);
        tail1.setTextureOffset(33, 21).addCuboid(-0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);

        explosive = new ModelPart(this);
        explosive.setPivot(0.0F, 0.0F, 1.7F);
        body.addChild(explosive);


        tnt = new ModelPart(this);
        tnt.setPivot(0.0F, -1.5F, -9.9F);
        explosive.addChild(tnt);
        tnt.setTextureOffset(48, 10).addCuboid(-6.5F, -1.5F, 5.9F, 4.0F, 4.0F, 4.0F, 0.0F, false);
        tnt.setTextureOffset(48, 10).addCuboid(2.5F, -1.5F, 9.9F, 4.0F, 4.0F, 4.0F, 0.0F, false);
        tnt.setTextureOffset(48, 10).addCuboid(2.5F, -1.5F, 5.9F, 4.0F, 4.0F, 4.0F, 0.0F, false);
        tnt.setTextureOffset(48, 10).addCuboid(-6.5F, -1.5F, 9.9F, 4.0F, 4.0F, 4.0F, 0.0F, false);

        wire = new ModelPart(this);
        wire.setPivot(7.0F, 2.9F, -2.0F);
        explosive.addChild(wire);
        wire.setTextureOffset(48, 18).addCuboid(-14.0F, -6.0F, -0.5F, 7.0F, 0.0F, 1.0F, 0.0F, false);
        wire.setTextureOffset(48, 18).addCuboid(-10.0F, 0.2F, -0.5F, 6.0F, 0.0F, 1.0F, 0.0F, false);
        wire.setTextureOffset(48, 18).addCuboid(-7.0F, -6.0F, -0.5F, 7.0F, 0.0F, 1.0F, 0.0F, false);
        wire.setTextureOffset(48, 18).addCuboid(-7.0F, -6.0F, 3.5F, 7.0F, 0.0F, 1.0F, 0.0F, false);
        wire.setTextureOffset(48, 18).addCuboid(-14.0F, -6.0F, 3.5F, 7.0F, 0.0F, 1.0F, 0.0F, false);
        wire.setTextureOffset(48, 18).addCuboid(-10.0F, 0.2F, 3.5F, 6.0F, 0.0F, 1.0F, 0.0F, false);

        wire12_r1 = new ModelPart(this);
        wire12_r1.setPivot(0.0F, 0.0F, 0.0F);
        wire.addChild(wire12_r1);
        setRotationAngle(wire12_r1, 0.0F, 0.0F, -0.5236F);
        wire12_r1.setTextureOffset(46, 18).addCuboid(-3.9F, -1.8F, 3.5F, 5.0F, 0.0F, 1.0F, 0.0F, false);
        wire12_r1.setTextureOffset(46, 18).addCuboid(-3.9F, -1.8F, -0.5F, 5.0F, 0.0F, 1.0F, 0.0F, false);

        wire12_r2 = new ModelPart(this);
        wire12_r2.setPivot(0.0F, 0.0F, 0.0F);
        wire.addChild(wire12_r2);
        setRotationAngle(wire12_r2, 0.0F, 0.0F, 0.5236F);
        wire12_r2.setTextureOffset(46, 18).addCuboid(-13.2F, 5.2F, -0.5F, 5.0F, 0.0F, 1.0F, 0.0F, false);
        wire12_r2.setTextureOffset(46, 18).addCuboid(-13.2F, 5.2F, 3.5F, 5.0F, 0.0F, 1.0F, 0.0F, false);

        wire10_r1 = new ModelPart(this);
        wire10_r1.setPivot(-14.0F, -6.0F, 4.8F);
        wire.addChild(wire10_r1);
        setRotationAngle(wire10_r1, 0.0F, 0.0F, -1.5708F);
        wire10_r1.setTextureOffset(46, 18).addCuboid(-4.0F, 14.0F, -5.3F, 4.0F, 0.0F, 1.0F, 0.0F, false);
        wire10_r1.setTextureOffset(46, 18).addCuboid(-4.0F, 0.0F, -5.3F, 4.0F, 0.0F, 1.0F, 0.0F, false);
        wire10_r1.setTextureOffset(46, 18).addCuboid(-4.0F, 14.0F, -1.3F, 4.0F, 0.0F, 1.0F, 0.0F, false);
        wire10_r1.setTextureOffset(46, 18).addCuboid(-4.0F, 0.0F, -1.3F, 4.0F, 0.0F, 1.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPivot(0.0F, -1.9F, -8.1F);
        main.addChild(head);
        head.setTextureOffset(0, 0).addCuboid(-3.0F, -3.1F, -3.6F, 6.0F, 4.0F, 4.0F, 0.0F, false);

        ear1 = new ModelPart(this);
        ear1.setPivot(1.5F, -3.1F, -0.2F);
        head.addChild(ear1);
        ear1.setTextureOffset(0, 8).addCuboid(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        earfluff1 = new ModelPart(this);
        earfluff1.setPivot(0.5F, -1.5F, 0.0F);
        ear1.addChild(earfluff1);
        setRotationAngle(earfluff1, 0.0F, 0.0F, 0.6109F);
        earfluff1.setTextureOffset(4, 8).addCuboid(0.0F, -2.0F, -0.5F, 0.0F, 2.0F, 1.0F, 0.0F, false);

        ear2 = new ModelPart(this);
        ear2.setPivot(-1.5F, -3.1F, -0.2F);
        head.addChild(ear2);
        ear2.setTextureOffset(0, 8).addCuboid(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

        earfluff2 = new ModelPart(this);
        earfluff2.setPivot(-0.5F, -1.5F, 0.0F);
        ear2.addChild(earfluff2);
        setRotationAngle(earfluff2, 0.0F, 0.0F, -0.6109F);
        earfluff2.setTextureOffset(4, 8).addCuboid(0.0F, -2.0F, -0.5F, 0.0F, 2.0F, 1.0F, 0.0F, false);

        mouth = new ModelPart(this);
        mouth.setPivot(0.0F, 0.0F, 0.0F);
        head.addChild(mouth);
        mouth.setTextureOffset(16, 1).addCuboid(-1.5F, -1.1F, -4.6F, 3.0F, 2.0F, 1.0F, 0.0F, false);

        anonymous = new ModelPart(this);
        anonymous.setPivot(0.0F, -0.8F, -1.7F);
        head.addChild(anonymous);
        anonymous.setTextureOffset(46, 32).addCuboid(-4.0F, -3.3F, -3.1F, 8.0F, 7.0F, 1.0F, 0.0F, false);
        anonymous.setTextureOffset(46, 40).addCuboid(-2.0F, 3.7F, -3.1F, 4.0F, 1.0F, 1.0F, 0.0F, false);
        anonymous.setTextureOffset(46, 45).addCuboid(-3.0F, -1.8F, 2.2F, 6.0F, 1.0F, 0.0F, 0.0F, false);

        anonymous3_r1 = new ModelPart(this);
        anonymous3_r1.setPivot(0.0F, 1.6F, 1.6F);
        anonymous.addChild(anonymous3_r1);
        setRotationAngle(anonymous3_r1, 0.0F, 0.1745F, 0.0F);
        anonymous3_r1.setTextureOffset(46, 38).addCuboid(-3.1F, -3.4F, -4.9F, 0.0F, 1.0F, 5.0F, 0.0F, false);

        anonymous2_r1 = new ModelPart(this);
        anonymous2_r1.setPivot(0.0F, 1.6F, 1.6F);
        anonymous.addChild(anonymous2_r1);
        setRotationAngle(anonymous2_r1, 0.0F, -0.1745F, 0.0F);
        anonymous2_r1.setTextureOffset(46, 37).addCuboid(3.1F, -3.4F, -4.9F, 0.0F, 1.0F, 5.0F, 0.0F, false);

        commander = new ModelPart(this);
        commander.setPivot(0.0F, -1.1F, -1.7F);
        head.addChild(commander);
        commander.setTextureOffset(20, 0).addCuboid(-3.5F, -2.9F, -2.5F, 7.0F, 1.0F, 5.0F, 0.0F, false);

        commander2_r1 = new ModelPart(this);
        commander2_r1.setPivot(0.0F, 0.9F, 1.6F);
        commander.addChild(commander2_r1);
        setRotationAngle(commander2_r1, 1.2566F, 0.0F, 0.0F);
        commander2_r1.setTextureOffset(21, 6).addCuboid(-0.8F, -3.5F, -2.4F, 4.0F, 1.0F, 4.0F, 0.0F, false);
        commander2_r1.setTextureOffset(21, 6).addCuboid(-3.2F, -3.5F, -2.4F, 4.0F, 1.0F, 4.0F, 0.0F, false);

        front_right_leg = new ModelPart(this);
        front_right_leg.setPivot(-1.5F, 1.5F, -6.5F);
        main.addChild(front_right_leg);
        front_right_leg.setTextureOffset(46, 0).addCuboid(-0.75F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

        front_left_leg = new ModelPart(this);
        front_left_leg.setPivot(1.5F, 1.5F, -6.5F);
        main.addChild(front_left_leg);
        front_left_leg.setTextureOffset(46, 0).addCuboid(-1.25F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

        back_right_leg = new ModelPart(this);
        back_right_leg.setPivot(-1.25F, 1.5F, 6.0F);
        main.addChild(back_right_leg);
        back_right_leg.setTextureOffset(54, 0).addCuboid(-1.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F, 0.0F, false);

        back_left_leg = new ModelPart(this);
        back_left_leg.setPivot(1.25F, 1.5F, 6.0F);
        main.addChild(back_left_leg);
        back_left_leg.setTextureOffset(54, 0).addCuboid(-1.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F, 0.0F, false);
    }

    @Override
    public void setAngles(CaracalEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        main.setPivot(0.0F, 15.5F, 0.0F);
        body.setPivot(0.0F, 0.0F, 0.0F);
        tail.setPivot(0.0F, -2.5F, 8.0F);
        head.setPivot(0.0F, -0.9F, -7.7F);
        earfluff1.setPivot(0.5F, -1.5F, 0.0F);
        earfluff2.setPivot(-0.5F, -1.5F, 0.0F);
        front_right_leg.setPivot(-1.5F, 1.5F, -6.5F);
        front_left_leg.setPivot(1.5F, 1.5F, -6.5F);
        back_right_leg.setPivot(-1.25F, 1.5F, 6.0F);
        back_left_leg.setPivot(1.25F, 1.5F, 6.0F);

        setRotationAngle(tail, 0.6109F, 0.0F, 0.0F);
        setRotationAngle(tail1, 0.6109F, 0.0F, 0.0F);
        setRotationAngle(earfluff1, 0.0F, 0.0F, 0.6109F);
        setRotationAngle(earfluff2, 0.0F, 0.0F, -0.6109F);
        setRotationAngle(main, 0.0F, 0.0F, 0.0F);
        setRotationAngle(body, 0.0F, 0.0F, 0.0F);
        setRotationAngle(head, (headPitch * 0.017453292F), (headYaw * 0.017453292F), 0.0F);
        setRotationAngle(back_left_leg, (MathHelper.cos(limbAngle * 0.6662F) * limbDistance), 0.0F, 0.0F);
        setRotationAngle(back_right_leg, (MathHelper.cos(limbAngle * 0.6662F + 3.1415927F + 0.3F) * limbDistance), 0.0F, 0.0F);
        setRotationAngle(front_left_leg, (MathHelper.cos(limbAngle * 0.6662F + 0.3F) * limbDistance), 0.0F, 0.0F);
        setRotationAngle(front_right_leg, (MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * limbDistance), 0.0F, 0.0F);

        if (entity.isInSneakingPose()) {
            body.setPivot(0.0F, 1.0F, 0.0F);
            head.setPivot(0.0F, 0.9F, -7.7F);
            this.body.pitch = 0.0873F;
            this.tail.pitch = 0.4363F;
            this.earfluff1.roll = 1.2217F;
            this.earfluff2.roll = -1.2217F;
        } else if (entity.isInSittingPose() && !entity.isSleepingWithOwner()) {
            main.setPivot(0.0F, 19.5F, 0.0F);
            head.setPivot(0.0F, -6.9F, -5.0F);
            front_left_leg.setPivot(1.5F, -2.5F, -5.6F);
            front_right_leg.setPivot(-1.5F, -2.5F, -5.6F);
            back_left_leg.setPivot(1.25F, 3.2F, 2.2F);
            back_right_leg.setPivot(-1.25F, 3.2F, 2.2F);
            tail.setPivot(0.0F, -2.5F, 5.0F);

            this.body.pitch = -0.8854F;
            this.earfluff1.roll = 1.2217F;
            this.earfluff2.roll = -1.2217F;
            this.front_left_leg.pitch = -0.2618F;
            this.front_right_leg.pitch = -0.2618F;
            this.back_left_leg.pitch = -1.5708F;
            this.back_right_leg.pitch = -1.5708F;
            this.tail.pitch = 2.5744F;
        } else if (entity.isSleepingWithOwner()){
            main.setPivot(0.0F, 21.5F, 0.0F);
            this.main.roll = 1.285F;
            this.head.roll = -1.185F;
            this.earfluff1.roll = 1.3217F;
            this.earfluff2.roll = -1.3217F;
            this.front_left_leg.pitch = -0.9F;
            this.front_right_leg.yaw = -0.1F;
            this.front_right_leg.pitch = -1.4F;
            this.back_left_leg.pitch = 0.6F;
            this.back_right_leg.yaw = -0.1F;
            this.back_right_leg.pitch = 1.0F;
            this.tail.pivotY = -1.0F;
            this.tail.roll = -1.185F;
            this.tail.yaw = -0.285F;
        }
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer	buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
        main.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelPart bone, float pitch, float yaw, float roll) {
        bone.pitch = pitch;
        bone.yaw = yaw;
        bone.roll = roll;
    }
}
