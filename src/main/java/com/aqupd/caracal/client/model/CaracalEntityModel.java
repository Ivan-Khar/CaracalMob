package com.aqupd.caracal.client.model;

import com.aqupd.caracal.entities.CaracalEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class CaracalEntityModel extends EntityModel<CaracalEntity> {
    private final ModelPart main;
    private final ModelPart body;
    private final ModelPart explosive;
    private final ModelPart wire13_r1;
    private final ModelPart wire12_r1;
    private final ModelPart wire10_r1;
    private final ModelPart tail;
    private final ModelPart tail2;
    private final ModelPart head;
    private final ModelPart head2;
    private final ModelPart ear1;
    private final ModelPart earfluff1;
    private final ModelPart ear2;
    private final ModelPart earfluff2;
    private final ModelPart hat;
    private final ModelPart hat5_r1;
    private final ModelPart hat5_r2;
    private final ModelPart hat3_r1;
    private final ModelPart commander;
    private final ModelPart commander3_r1;
    private final ModelPart anonymous;
    private final ModelPart anonymous3_r1;
    private final ModelPart anonymous2_r1;
    private final ModelPart front_right_leg;
    private final ModelPart front_left_leg;
    private final ModelPart back_right_leg;
    private final ModelPart back_left_leg;

    public OcelotEntityModel(ModelPart root) {
        super(true, 10.0F, 4.0F);
        this.head = root.getChild("head");
        this.body = root.getChild("body");
        this.upperTail = root.getChild("tail1");
        this.lowerTail = root.getChild("tail2");
        this.leftHindLeg = root.getChild("left_hind_leg");
        this.rightHindLeg = root.getChild("right_hind_leg");
        this.leftFrontLeg = root.getChild("left_front_leg");
        this.rightFrontLeg = root.getChild("right_front_leg");
    }

    public static ModelData getModelData(Dilation dilation) {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("head", ModelPartBuilder.create().cuboid("main", -2.5F, -2.0F, -3.0F, 5.0F, 4.0F, 5.0F, dilation).cuboid("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2, dilation, 0, 24).cuboid("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2, dilation, 0, 10).cuboid("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2, dilation, 6, 10), ModelTransform.pivot(0.0F, 15.0F, -9.0F));
        modelPartData.addChild("body", ModelPartBuilder.create().uv(20, 0).cuboid(-2.0F, 3.0F, -8.0F, 4.0F, 16.0F, 6.0F, dilation), ModelTransform.of(0.0F, 12.0F, -10.0F, 1.5707964F, 0.0F, 0.0F));
        modelPartData.addChild("tail1", ModelPartBuilder.create().uv(0, 15).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, dilation), ModelTransform.of(0.0F, 15.0F, 8.0F, 0.9F, 0.0F, 0.0F));
        modelPartData.addChild("tail2", ModelPartBuilder.create().uv(4, 15).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, dilation), ModelTransform.pivot(0.0F, 20.0F, 14.0F));
        ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(8, 13).cuboid(-1.0F, 0.0F, 1.0F, 2.0F, 6.0F, 2.0F, dilation);
        modelPartData.addChild("left_hind_leg", modelPartBuilder, ModelTransform.pivot(1.1F, 18.0F, 5.0F));
        modelPartData.addChild("right_hind_leg", modelPartBuilder, ModelTransform.pivot(-1.1F, 18.0F, 5.0F));
        ModelPartBuilder modelPartBuilder2 = ModelPartBuilder.create().uv(40, 0).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 10.0F, 2.0F, dilation);
        modelPartData.addChild("left_front_leg", modelPartBuilder2, ModelTransform.pivot(1.2F, 14.1F, -5.0F));
        modelPartData.addChild("right_front_leg", modelPartBuilder2, ModelTransform.pivot(-1.2F, 14.1F, -5.0F));
        return modelData;
    }

    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of(this.head);
    }

    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.body, this.leftHindLeg, this.rightHindLeg, this.leftFrontLeg, this.rightFrontLeg, this.upperTail, this.lowerTail);
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

        setRotationAngle(head, (headPitch * 0.017453292F), (headYaw * 0.017453292F), 0.0F);
        setRotationAngle(body, 0.0F, 0.0F, 0.0F);
        setRotationAngle(earfluff1, 0.0F, 0.0F, 0.6109F);
        setRotationAngle(earfluff2, 0.0F, 0.0F, -0.6109F);
        setRotationAngle(tail, 0.6109F, 0.0F, 0.0F);
        setRotationAngle(tail2, 0.6109F, 0.0F, 0.0F);
        setRotationAngle(back_left_leg, (MathHelper.cos(limbAngle * 0.6662F) * limbDistance), 0.0F, 0.0F);
        setRotationAngle(back_right_leg, (MathHelper.cos(limbAngle * 0.6662F + 3.1415927F + 0.3F) * limbDistance), 0.0F, 0.0F);
        setRotationAngle(front_left_leg, (MathHelper.cos(limbAngle * 0.6662F + 0.3F) * limbDistance), 0.0F, 0.0F);
        setRotationAngle(front_right_leg, (MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * limbDistance), 0.0F, 0.0F);

        if (entity.isInSneakingPose()) {
            body.setPivot(0.0F, 1.0F, 0.0F);
            head.setPivot(0.0F, 0.9F, -7.7F);
            this.body.pitch = 0.0873F;
            this.tail.pitch = 0.4363F;
        } else if (entity.isInSittingPose() && !entity.isSleepingWithOwner()) {
            main.setPivot(0.0F, 19.5F, 0.0F);
            head.setPivot(0.0F, -5.5F, -5.5F);
            front_left_leg.setPivot(1.5F, -2.5F, -6.1F);
            front_right_leg.setPivot(-1.5F, -2.5F, -6.1F);
            back_left_leg.setPivot(1.25F, 3.2F, 2.2F);
            back_right_leg.setPivot(-1.25F, 3.2F, 2.2F);
            tail.setPivot(0.0F, -2.5F, 5.0F);

            this.body.pitch = -0.7854F;
            this.earfluff1.roll = 1.2217F;
            this.earfluff2.roll = -1.2217F;
            this.front_left_leg.pitch = -0.2618F;
            this.front_right_leg.pitch = -0.2618F;
            this.back_left_leg.pitch = -1.5708F;
            this.back_right_leg.pitch = -1.5708F;
            this.tail.pitch = 2.5744F;
        } else if (entity.isSleepingWithOwner()){

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
