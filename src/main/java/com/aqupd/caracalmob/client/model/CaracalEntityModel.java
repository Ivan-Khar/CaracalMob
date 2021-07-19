package com.aqupd.caracalmob.client.model;

import com.aqupd.caracalmob.entity.CaracalEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.math.MathHelper;

public class CaracalEntityModel<C extends TameableEntity> extends EntityModel<CaracalEntity> {
    private final ModelPart main;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart tail1;
    private final ModelPart head;
    private final ModelPart ear1;
    private final ModelPart earfluff1;
    private final ModelPart ear2;
    private final ModelPart earfluff2;
    private final ModelPart mouth;
    private final ModelPart front_right_leg;
    private final ModelPart front_left_leg;
    private final ModelPart back_right_leg;
    private final ModelPart back_left_leg;
    public CaracalEntityModel(ModelPart root) {
        this.main = root.getChild("main");
        this.back_left_leg = this.main.getChild("back_left_leg");
        this.back_right_leg = this.main.getChild("back_right_leg");
        this.front_left_leg = this.main.getChild("front_left_leg");
        this.front_right_leg = this.main.getChild("front_right_leg");
        this.head = this.main.getChild("head");
        this.mouth = this.head.getChild("mouth");
        this.ear2 = this.head.getChild("ear2");
        this.earfluff2 = this.ear2.getChild("earfluff2");
        this.ear1 = this.head.getChild("ear1");
        this.earfluff1 = this.ear1.getChild("earfluff1");
        this.body = this.main.getChild("body");
        this.tail = this.body.getChild("tail");
        this.tail1 = this.tail.getChild("tail1");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData modelPartData1 = modelPartData.addChild("main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 15.5F, 0.0F));
        ModelPartData modelPartData2 = modelPartData1.addChild("body", ModelPartBuilder.create().uv(20, 9).cuboid(-2.5F, -3.0F, -8.5F, 5.0F, 6.0F, 17.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        ModelPartData modelPartData3 = modelPartData2.addChild("tail", ModelPartBuilder.create().uv(29, 21).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F), ModelTransform.pivot(0.0F, -2.5F, 8.0F));
        modelPartData3.addChild("tail1", ModelPartBuilder.create().uv(33, 21).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F), ModelTransform.pivot(0.0F, 4.0F, -0.5F));
        ModelPartData modelPartData4 = modelPartData1.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -3.1F, -3.6F, 6.0F, 4.0F, 4.0F), ModelTransform.pivot(0.0F, -1.9F, -8.1F));
        ModelPartData modelPartData5 = modelPartData4.addChild("ear1", ModelPartBuilder.create().uv(0, 8).cuboid(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F), ModelTransform.pivot(1.5F, -3.1F, -0.2F));
        modelPartData5.addChild("earfluff1", ModelPartBuilder.create().uv(4, 8).cuboid(0.0F, -2.0F, -0.5F, 0.0F, 2.0F, 1.0F), ModelTransform.pivot(0.5F, -1.5F, 0.0F));
        ModelPartData modelPartData6 = modelPartData4.addChild("ear2", ModelPartBuilder.create().uv(0, 8).cuboid(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F), ModelTransform.pivot(-1.5F, -3.1F, -0.2F));
        modelPartData6.addChild("earfluff2", ModelPartBuilder.create().uv(4, 8).cuboid(0.0F, -2.0F, -0.5F, 0.0F, 2.0F, 1.0F), ModelTransform.pivot(-0.5F, -1.5F, 0.0F));
        modelPartData4.addChild("mouth", ModelPartBuilder.create().uv(16, 1).cuboid(-1.5F, -1.1F, -4.6F, 3.0F, 2.0F, 1.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
        modelPartData1.addChild("front_right_leg", ModelPartBuilder.create().uv(46, 0).cuboid(-0.75F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F), ModelTransform.pivot(-1.5F, 1.5F, -6.5F));
        modelPartData1.addChild("front_left_leg", ModelPartBuilder.create().uv(46, 0).cuboid(-1.25F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F), ModelTransform.pivot(1.5F, 1.5F, -6.5F));
        modelPartData1.addChild("back_right_leg", ModelPartBuilder.create().uv(54, 0).cuboid(-1.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F), ModelTransform.pivot(-1.25F, 1.5F, 6.0F));
        modelPartData1.addChild("back_left_leg", ModelPartBuilder.create().uv(54, 0).cuboid(-1.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F), ModelTransform.pivot(1.25F, 1.5F, 6.0F));
        return TexturedModelData.of(modelData, 64, 64);
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
        } else if (entity.isInSittingPose() && !entity.isInSleepingPose()) {
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
        } else if (entity.isInSleepingPose()){
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