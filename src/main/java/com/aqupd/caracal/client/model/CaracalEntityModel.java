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

    protected float sizemultiplier = 0.0F;

    public CaracalEntityModel(){
        textureWidth = 64;
        textureHeight = 64;
        body = new ModelPart(this);
        body.setPivot(0.0F, 24.0F, 0.0F);
        body.setTextureOffset(20, 9).addCuboid(-2.5F, -11.5F, -8.5F, 5.0F, 6.0F, 17.0F, sizemultiplier, false);

        ModelPart explosive = new ModelPart(this);
        explosive.setPivot(0.0F, -10.0F, -8.2F);
        body.addChild(explosive);
        explosive.setTextureOffset(48, 10).addCuboid(2.5F, -1.5F, 9.9F, 4.0F, 4.0F, 4.0F, 0.0F, false);
        explosive.setTextureOffset(48, 10).addCuboid(2.5F, -1.5F, 5.9F, 4.0F, 4.0F, 4.0F, 0.0F, false);
        explosive.setTextureOffset(48, 10).addCuboid(-6.5F, -1.5F, 9.9F, 4.0F, 4.0F, 4.0F, 0.0F, false);
        explosive.setTextureOffset(48, 10).addCuboid(-6.5F, -1.5F, 5.9F, 4.0F, 4.0F, 4.0F, 0.0F, false);
        explosive.setTextureOffset(48, 18).addCuboid(-7.0F, -1.6F, 7.4F, 7.0F, 0.0F, 1.0F, 0.0F, false);
        explosive.setTextureOffset(48, 18).addCuboid(-3.0F, 4.6F, 7.4F, 6.0F, 0.0F, 1.0F, 0.0F, false);
        explosive.setTextureOffset(48, 18).addCuboid(0.0F, -1.6F, 7.4F, 7.0F, 0.0F, 1.0F, 0.0F, false);
        explosive.setTextureOffset(48, 18).addCuboid(0.0F, -1.6F, 11.4F, 7.0F, 0.0F, 1.0F, 0.0F, false);
        explosive.setTextureOffset(48, 18).addCuboid(-7.0F, -1.6F, 11.4F, 7.0F, 0.0F, 1.0F, 0.0F, false);
        explosive.setTextureOffset(48, 18).addCuboid(-3.0F, 4.6F, 11.4F, 6.0F, 0.0F, 1.0F, 0.0F, false);

        ModelPart wire13_r1 = new ModelPart(this);
        wire13_r1.setPivot(7.0F, 4.4F, 7.9F);
        explosive.addChild(wire13_r1);
        setRotationAngle(wire13_r1, 0.0F, 0.0F, 0.5236F);
        wire13_r1.setTextureOffset(46, 18).addCuboid(-13.2F, 5.2F, 3.5F, 5.0F, 0.0F, 1.0F, 0.0F, false);
        wire13_r1.setTextureOffset(46, 18).addCuboid(-13.2F, 5.2F, -0.5F, 5.0F, 0.0F, 1.0F, 0.0F, false);

        ModelPart wire12_r1 = new ModelPart(this);
        wire12_r1.setPivot(7.0F, 4.4F, 7.9F);
        explosive.addChild(wire12_r1);
        setRotationAngle(wire12_r1, 0.0F, 0.0F, -0.5236F);
        wire12_r1.setTextureOffset(46, 18).addCuboid(-3.9F, -1.8F, 3.5F, 5.0F, 0.0F, 1.0F, 0.0F, false);
        wire12_r1.setTextureOffset(46, 18).addCuboid(-3.9F, -1.8F, -0.5F, 5.0F, 0.0F, 1.0F, 0.0F, false);

        ModelPart wire10_r1 = new ModelPart(this);
        wire10_r1.setPivot(-7.0F, -1.6F, 12.7F);
        explosive.addChild(wire10_r1);
        setRotationAngle(wire10_r1, 0.0F, 0.0F, -1.5708F);
        wire10_r1.setTextureOffset(46, 18).addCuboid(-4.0F, 14.0F, -5.3F, 4.0F, 0.0F, 1.0F, 0.0F, false);
        wire10_r1.setTextureOffset(46, 18).addCuboid(-4.0F, 0.0F, -5.3F, 4.0F, 0.0F, 1.0F, 0.0F, false);
        wire10_r1.setTextureOffset(46, 18).addCuboid(-4.0F, 14.0F, -1.3F, 4.0F, 0.0F, 1.0F, 0.0F, false);
        wire10_r1.setTextureOffset(46, 18).addCuboid(-4.0F, 0.0F, -1.3F, 4.0F, 0.0F, 1.0F, 0.0F, false);

        head = new ModelPart(this);
        head.setPivot(0.0F, 13.4F, -8.2F);
        head.setTextureOffset(0, 0).addCuboid(-3.0F, -2.9F, -3.6F, 6.0F, 4.0F, 4.0F, sizemultiplier, false);
        head.setTextureOffset(16, 1).addCuboid(-1.5F, -0.9F, -4.6F, 3.0F, 2.0F, 1.0F, sizemultiplier, false);

        ModelPart hat = new ModelPart(this);
        hat.setPivot(0.0F, 10.6F, 8.2F);
        head.addChild(hat);
        hat.setTextureOffset(0, 16).addCuboid(-3.5F, -14.0F, -13.5F, 7.0F, 1.0F, 7.0F, 0.0F, false);
        hat.setTextureOffset(0, 24).addCuboid(-2.5F, -17.0F, -12.5F, 5.0F, 3.0F, 5.0F, 0.0F, false);

        ModelPart hat5_r1 = new ModelPart(this);
        hat5_r1.setPivot(-0.8F, -21.5F, -10.0F);
        hat.addChild(hat5_r1);
        setRotationAngle(hat5_r1, 0.0F, 0.0F, -0.4189F);
        hat5_r1.setTextureOffset(0, 32).addCuboid(-1.0F, -1.8F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);

        ModelPart hat4_r1 = new ModelPart(this);
        hat4_r1.setPivot(-0.7572F, -20.2089F, -10.0F);
        hat.addChild(hat4_r1);
        setRotationAngle(hat4_r1, 0.0F, 0.0F, -0.2618F);
        hat4_r1.setTextureOffset(8, 32).addCuboid(-0.2F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, false);

        ModelPart hat3_r1 = new ModelPart(this);
        hat3_r1.setPivot(0.0F, -18.5F, -10.0F);
        hat.addChild(hat3_r1);
        setRotationAngle(hat3_r1, 0.0F, 0.0F, -0.1222F);
        hat3_r1.setTextureOffset(12, 32).addCuboid(-1.7F, -1.2F, -1.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);

        ModelPart commander = new ModelPart(this);
        commander.setPivot(0.0F, 0.0F, 0.0F);
        head.addChild(commander);
        commander.setTextureOffset(20, 0).addCuboid(-3.5F, -3.8F, -4.1F, 7.0F, 1.0F, 5.0F, 0.0F, false);

        ModelPart commander_r1 = new ModelPart(this);
        commander_r1.setPivot(0.0F, 0.0F, 0.0F);
        commander.addChild(commander_r1);
        setRotationAngle(commander_r1, 1.2566F, 0.0F, 0.0F);
        commander_r1.setTextureOffset(21, 6).addCuboid(-3.2F, -3.5F, -2.4F, 4.0F, 1.0F, 4.0F, 0.0F, false);
        commander_r1.setTextureOffset(21, 6).addCuboid(-0.8F, -3.5F, -2.4F, 4.0F, 1.0F, 4.0F, 0.0F, false);

        ModelPart anonymous = new ModelPart(this);
        anonymous.setPivot(0.0F, 0.7F, 0.0F);
        head.addChild(anonymous);
        anonymous.setTextureOffset(46, 32).addCuboid(-4.0F, -4.9F, -4.7F, 8.0F, 7.0F, 1.0F, 0.0F, false);
        anonymous.setTextureOffset(46, 40).addCuboid(-2.0F, 2.1F, -4.7F, 4.0F, 1.0F, 1.0F, 0.0F, false);
        anonymous.setTextureOffset(46, 45).addCuboid(-3.0F, -3.4F, 0.6F, 6.0F, 1.0F, 0.0F, 0.0F, false);

        ModelPart anonymous3_r1 = new ModelPart(this);
        anonymous3_r1.setPivot(0.0F, 0.0F, 0.0F);
        anonymous.addChild(anonymous3_r1);
        setRotationAngle(anonymous3_r1, 0.0F, 0.1745F, 0.0F);
        anonymous3_r1.setTextureOffset(46, 38).addCuboid(-3.1F, -3.4F, -4.9F, 0.0F, 1.0F, 5.0F, 0.0F, false);

        ModelPart anonymous2_r1 = new ModelPart(this);
        anonymous2_r1.setPivot(0.0F, 0.0F, 0.0F);
        anonymous.addChild(anonymous2_r1);
        setRotationAngle(anonymous2_r1, 0.0F, -0.1745F, 0.0F);
        anonymous2_r1.setTextureOffset(46, 37).addCuboid(3.1F, -3.4F, -4.9F, 0.0F, 1.0F, 5.0F, 0.0F, false);

        ear1 = new ModelPart(this);
        ear1.setPivot(1.5F, -2.4F, -0.5F);
        head.addChild(ear1);
        ear1.setTextureOffset(0, 8).addCuboid(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, sizemultiplier, false);

        earfluff1 = new ModelPart(this);
        earfluff1.setPivot(0.5F, -1.5F, 0.0F);
        ear1.addChild(earfluff1);
        setRotationAngle(earfluff1, 0.0F, 0.0F, 0.6109F);
        earfluff1.setTextureOffset(4, 8).addCuboid(0.0F, -2.0F, -0.5F, 0.0F, 2.0F, 1.0F, sizemultiplier, false);

        ear2 = new ModelPart(this);
        ear2.setPivot(-1.5F, -2.4F, -0.5F);
        head.addChild(ear2);
        ear2.setTextureOffset(0, 8).addCuboid(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, sizemultiplier, false);

        earfluff2 = new ModelPart(this);
        earfluff2.setPivot(-0.5F, -1.5F, 0.0F);
        ear2.addChild(earfluff2);
        setRotationAngle(earfluff2, 0.0F, 0.0F, -0.6109F);
        earfluff2.setTextureOffset(4, 8).addCuboid(0.0F, -2.0F, -0.5F, 0.0F, 2.0F, 1.0F, sizemultiplier, false);

        tail = new ModelPart(this);
        tail.setPivot(0.0F, 13.0F, 8.0F);
        setRotationAngle(tail, 0.6109F, 0.0F, 0.0F);
        tail.setTextureOffset(29, 21).addCuboid(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, sizemultiplier, false);

        tail2 = new ModelPart(this);
        tail2.setPivot(0.0F, 4.0F, -0.5F);
        tail.addChild(tail2);
        setRotationAngle(tail2, 0.6109F, 0.0F, 0.0F);
        tail2.setTextureOffset(33, 21).addCuboid(-0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F, sizemultiplier, false);

        front_left_leg = new ModelPart(this);
        front_left_leg.setPivot(1.5F, 17.0F, -6.5F);
        front_left_leg.setTextureOffset(46, 0).addCuboid(-1.25F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, sizemultiplier, false);

        back_left_leg = new ModelPart(this);
        back_left_leg.setPivot(1.25F, 17.0F, 6.0F);
        back_left_leg.setTextureOffset(54, 0).addCuboid(-1.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F, sizemultiplier, false);

        front_right_leg = new ModelPart(this);
        front_right_leg.setPivot(-1.5F, 17.0F, -6.5F);
        front_right_leg.setTextureOffset(46, 0).addCuboid(-0.75F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, sizemultiplier, false);

        back_right_leg = new ModelPart(this);
        back_right_leg.setPivot(-1.25F, 17.0F, 6.0F);
        back_right_leg.setTextureOffset(54, 0).addCuboid(-1.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F, sizemultiplier, false);
    }

    @Override
    public void setAngles(CaracalEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.head.pitch = headPitch * 0.017453292F;
        this.head.yaw = headYaw * 0.017453292F;
        this.body.pitch = 0.0F;
        this.back_left_leg.pitch = MathHelper.cos(limbAngle * 0.6662F) * limbDistance;
        this.back_right_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F + 0.3F) * limbDistance;
        this.front_left_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + 0.3F) * limbDistance;
        this.front_right_leg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * limbDistance;
        this.back_left_leg.yaw = 0.0F;
        this.back_right_leg.yaw = 0.0F;
        this.front_left_leg.yaw = 0.0F;
        this.front_right_leg.yaw = 0.0F;
        this.back_left_leg.roll = 0.0F;
        this.back_right_leg.roll = 0.0F;
        this.front_left_leg.roll = 0.0F;
        this.front_right_leg.roll = 0.0F;
        if (entity.isInSneakingPose()) {
            setRotationAngle(body, 0.0524F, 0.0F, 0.0F);
            setRotationAngle(earfluff1, 0.0F, 0.0F, 2.1126F);
            setRotationAngle(earfluff2, 0.0F, 0.0F, -1.9199F);
            head.setPivot(0.0F, 14.4F, -8.2F);
            tail.setPivot(0.0F, 13.0F, 7.0F);
        } else if (entity.isInSittingPose() && !entity.isSleepingWithOwner()) {
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
        } else if (entity.isSleepingWithOwner()){
            body.setPivot(-8.0F, 21.0F, 0.0F);
            setRotationAngle(body, -0.2F, 0.05F, 1.4F);
            head.setPivot(0.0F, 18.4F, -8.2F);
            this.head.yaw = -0.5F;
            front_left_leg.setPivot(1.5F, 21.0F, -6.5F);
            front_right_leg.setPivot(-1.5F, 19.0F, -6.5F);
            back_left_leg.setPivot(1.25F, 19.0F, 6.0F);
            back_right_leg.setPivot(-1.25F, 21.0F, 6.0F);
            tail.setPivot(0.0F, 20.0F, 10.0F);
            setRotationAngle(tail, 0.0F, 0.0F, 1.0F);
            setRotationAngle(front_left_leg, -1.1345F, 0.1745F, 1.5272F);
            setRotationAngle(front_right_leg, -0.6981F, 0.0F, 1.5272F);
            setRotationAngle(back_right_leg, 0.6545F, -0.0873F, 1.3526F);
            setRotationAngle(back_left_leg, 0.1309F, 0.0F, 1.4399F);
        } else {
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
