// Made with Model Converter by Globox_Z
// Generate all required imports
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
    private final ModelPart front_right_leg;
    private final ModelPart front_left_leg;
    private final ModelPart back_right_leg;
    private final ModelPart back_left_leg;
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
    private final ModelPart explosive;
    private final ModelPart wire13_r1;
    private final ModelPart wire12_r1;
    private final ModelPart wire10_r1;
    public CaracalEntityModel(ModelPart root) {
        this.main = root.getChild("main");
        this.head = this.main.getChild("head");
        this.head2 = this.head.getChild("head2");
        this.anonymous = this.head2.getChild("anonymous");
        this.anonymous2_r1 = this.anonymous.getChild("anonymous2_r1");
        this.anonymous3_r1 = this.anonymous.getChild("anonymous3_r1");
        this.commander = this.head2.getChild("commander");
        this.commander3_r1 = this.commander.getChild("commander3_r1");
        this.hat = this.head2.getChild("hat");
        this.hat3_r1 = this.hat.getChild("hat3_r1");
        this.hat5_r2 = this.hat.getChild("hat5_r2");
        this.hat5_r1 = this.hat.getChild("hat5_r1");
        this.ear2 = this.head2.getChild("ear2");
        this.earfluff2 = this.ear2.getChild("earfluff2");
        this.ear1 = this.head2.getChild("ear1");
        this.earfluff1 = this.ear1.getChild("earfluff1");
        this.back_left_leg = this.main.getChild("back_left_leg");
        this.back_right_leg = this.main.getChild("back_right_leg");
        this.front_left_leg = this.main.getChild("front_left_leg");
        this.front_right_leg = this.main.getChild("front_right_leg");
        this.body = this.main.getChild("body");
        this.explosive = this.body.getChild("explosive");
        this.wire10_r1 = this.explosive.getChild("wire10_r1");
        this.wire12_r1 = this.explosive.getChild("wire12_r1");
        this.wire13_r1 = this.explosive.getChild("wire13_r1");
        this.tail = this.body.getChild("tail");
        this.tail2 = this.tail.getChild("tail2");
    }
    public static TexturedModelData createModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData modelPartData1 = modelPartData.addChild("main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F,15.5F,0.0F));
        ModelPartData modelPartData4 = modelPartData1.addChild("body", ModelPartBuilder.create().uv(20,9).cuboid(-2.5F, -3.0F, -8.5F, 5.0F, 6.0F, 17.0F), ModelTransform.pivot(0.0F,0.0F,0.0F));
        modelPartData3.addChild("front_right_leg", ModelPartBuilder.create().uv(46,0).cuboid(-0.75F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F), ModelTransform.pivot(-1.5F,1.5F,-6.5F));
        modelPartData3.addChild("front_left_leg", ModelPartBuilder.create().uv(46,0).cuboid(-1.25F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F), ModelTransform.pivot(1.5F,1.5F,-6.5F));
        modelPartData3.addChild("back_right_leg", ModelPartBuilder.create().uv(54,0).cuboid(-1.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F), ModelTransform.pivot(-1.25F,1.5F,6.0F));
        modelPartData3.addChild("back_left_leg", ModelPartBuilder.create().uv(54,0).cuboid(-1.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F), ModelTransform.pivot(1.25F,1.5F,6.0F));
        ModelPartData modelPartData7 = modelPartData0.addChild("tail", ModelPartBuilder.create().uv(29,21).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F), ModelTransform.pivot(0.0F,-2.5F,8.0F));
        modelPartData0.addChild("tail2", ModelPartBuilder.create().uv(33,21).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F), ModelTransform.pivot(0.0F,4.0F,-0.5F));
        ModelPartData modelPartData10 = modelPartData3.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F,-0.9F,-7.7F));
        ModelPartData modelPartData14 = modelPartData3.addChild("head2", ModelPartBuilder.create().uv(0,0).cuboid(-3.0F, -2.0F, -2.0F, 6.0F, 4.0F, 4.0F).uv(16,1).cuboid(-1.5F, 0.0F, -3.0F, 3.0F, 2.0F, 1.0F), ModelTransform.pivot(0.0F,-2.1F,-2.0F));
        ModelPartData modelPartData15 = modelPartData14.addChild("ear1", ModelPartBuilder.create().uv(0,8).cuboid(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F), ModelTransform.pivot(1.5F,-2.0F,1.4F));
        modelPartData15.addChild("earfluff1", ModelPartBuilder.create().uv(4,8).cuboid(0.0F, -2.0F, -0.5F, 0.0F, 2.0F, 1.0F), ModelTransform.pivot(0.5F,-1.5F,0.0F));
        ModelPartData modelPartData16 = modelPartData14.addChild("ear2", ModelPartBuilder.create().uv(0,8).cuboid(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F), ModelTransform.pivot(-1.5F,-2.0F,1.4F));
        modelPartData16.addChild("earfluff2", ModelPartBuilder.create().uv(4,8).cuboid(0.0F, -2.0F, -0.5F, 0.0F, 2.0F, 1.0F), ModelTransform.pivot(-0.5F,-1.5F,0.0F));
        ModelPartData modelPartData17 = modelPartData14.addChild("hat", ModelPartBuilder.create().uv(0,16).cuboid(-3.5F, -0.5F, -3.7F, 7.0F, 1.0F, 7.0F).uv(0,24).cuboid(-2.5F, -3.5F, -2.7F, 5.0F, 3.0F, 5.0F), ModelTransform.pivot(0.0F,-2.0F,-0.1F));
        modelPartData17.addChild("hat5_r1", ModelPartBuilder.create().uv(0,32).cuboid(-1.0F, -1.8F, -1.0F, 2.0F, 2.0F, 2.0F), ModelTransform.pivot(-0.8F,-8.0F,-0.2F));
        modelPartData17.addChild("hat5_r2", ModelPartBuilder.create().uv(8,32).cuboid(-0.2F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F).uv(8,32).cuboid(-0.2F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F), ModelTransform.pivot(-0.7572F,-6.7089F,-0.2F));
        modelPartData17.addChild("hat3_r1", ModelPartBuilder.create().uv(12,32).cuboid(-1.7F, -1.2F, -1.5F, 3.0F, 3.0F, 3.0F), ModelTransform.pivot(0.0F,-5.0F,-0.2F));
        ModelPartData modelPartData18 = modelPartData14.addChild("commander", ModelPartBuilder.create().uv(20,0).cuboid(-3.5F, -2.9F, -2.5F, 7.0F, 1.0F, 5.0F), ModelTransform.pivot(0.0F,0.0F,-0.1F));
        modelPartData18.addChild("commander3_r1", ModelPartBuilder.create().uv(21,6).cuboid(-3.2F, -3.5F, -2.4F, 4.0F, 1.0F, 4.0F).uv(21,6).cuboid(-0.8F, -3.5F, -2.4F, 4.0F, 1.0F, 4.0F), ModelTransform.pivot(0.0F,0.9F,1.6F));
        ModelPartData modelPartData19 = modelPartData14.addChild("anonymous", ModelPartBuilder.create().uv(46,32).cuboid(-4.0F, -3.3F, -3.1F, 8.0F, 7.0F, 1.0F).uv(46,40).cuboid(-2.0F, 3.7F, -3.1F, 4.0F, 1.0F, 1.0F).uv(46,45).cuboid(-3.0F, -1.8F, 2.2F, 6.0F, 1.0F, 0.0F), ModelTransform.pivot(0.0F,0.3F,-0.1F));
        modelPartData19.addChild("anonymous3_r1", ModelPartBuilder.create().uv(46,38).cuboid(-3.1F, -3.4F, -4.9F, 0.0F, 1.0F, 5.0F), ModelTransform.pivot(0.0F,1.6F,1.6F));
        modelPartData19.addChild("anonymous2_r1", ModelPartBuilder.create().uv(46,37).cuboid(3.1F, -3.4F, -4.9F, 0.0F, 1.0F, 5.0F), ModelTransform.pivot(0.0F,1.6F,1.6F));
        ModelPartData modelPartData20 = modelPartData6.addChild("explosive", ModelPartBuilder.create().uv(48,10).cuboid(2.5F, -3.0F, 0.0F, 4.0F, 4.0F, 4.0F).uv(48,10).cuboid(2.5F, -3.0F, -4.0F, 4.0F, 4.0F, 4.0F).uv(48,10).cuboid(-6.5F, -3.0F, 0.0F, 4.0F, 4.0F, 4.0F).uv(48,10).cuboid(-6.5F, -3.0F, -4.0F, 4.0F, 4.0F, 4.0F).uv(48,18).cuboid(-7.0F, -3.1F, -2.5F, 7.0F, 0.0F, 1.0F).uv(48,18).cuboid(-3.0F, 3.1F, -2.5F, 6.0F, 0.0F, 1.0F).uv(48,18).cuboid(0.0F, -3.1F, -2.5F, 7.0F, 0.0F, 1.0F).uv(48,18).cuboid(0.0F, -3.1F, 1.5F, 7.0F, 0.0F, 1.0F).uv(48,18).cuboid(-7.0F, -3.1F, 1.5F, 7.0F, 0.0F, 1.0F).uv(48,18).cuboid(-3.0F, 3.1F, 1.5F, 6.0F, 0.0F, 1.0F), ModelTransform.pivot(0.0F,0.0F,1.7F));
        modelPartData20.addChild("wire13_r1", ModelPartBuilder.create().uv(46,18).cuboid(-13.2F, 5.2F, 3.5F, 5.0F, 0.0F, 1.0F).uv(46,18).cuboid(-13.2F, 5.2F, -0.5F, 5.0F, 0.0F, 1.0F), ModelTransform.pivot(7.0F,2.9F,-2.0F));
        modelPartData20.addChild("wire12_r1", ModelPartBuilder.create().uv(46,18).cuboid(-3.9F, -1.8F, 3.5F, 5.0F, 0.0F, 1.0F).uv(46,18).cuboid(-3.9F, -1.8F, -0.5F, 5.0F, 0.0F, 1.0F), ModelTransform.pivot(7.0F,2.9F,-2.0F));
        modelPartData20.addChild("wire10_r1", ModelPartBuilder.create().uv(46,18).cuboid(-4.0F, 14.0F, -5.3F, 4.0F, 0.0F, 1.0F).uv(46,18).cuboid(-4.0F, 0.0F, -5.3F, 4.0F, 0.0F, 1.0F).uv(46,18).cuboid(-4.0F, 14.0F, -1.3F, 4.0F, 0.0F, 1.0F).uv(46,18).cuboid(-4.0F, 0.0F, -1.3F, 4.0F, 0.0F, 1.0F), ModelTransform.pivot(-7.0F,-3.1F,2.8F));
        ModelPartData modelPartData2 = modelPartData.addChild("main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F,15.5F,0.0F));
        ModelPartData modelPartData5 = modelPartData0.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F,0.0F,0.0F));
        ModelPartData modelPartData8 = modelPartData0.addChild("tail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F,-2.5F,8.0F));
        ModelPartData modelPartData11 = modelPartData3.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F,-0.9F,-7.7F));
        modelPartData15.addChild("earfluff1", ModelPartBuilder.create(), ModelTransform.pivot(0.5F,-1.5F,0.0F));
        modelPartData16.addChild("earfluff2", ModelPartBuilder.create(), ModelTransform.pivot(-0.5F,-1.5F,0.0F));
        modelPartData3.addChild("front_right_leg", ModelPartBuilder.create(), ModelTransform.pivot(-1.5F,1.5F,-6.5F));
        modelPartData3.addChild("front_left_leg", ModelPartBuilder.create(), ModelTransform.pivot(1.5F,1.5F,-6.5F));
        modelPartData3.addChild("back_right_leg", ModelPartBuilder.create(), ModelTransform.pivot(-1.25F,1.5F,6.0F));
        modelPartData3.addChild("back_left_leg", ModelPartBuilder.create(), ModelTransform.pivot(1.25F,1.5F,6.0F));
        ModelPartData modelPartData6 = modelPartData0.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F,1.0F,0.0F));
        ModelPartData modelPartData12 = modelPartData3.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F,0.9F,-7.7F));
        ModelPartData modelPartData3 = modelPartData.addChild("main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F,19.5F,0.0F));
        ModelPartData modelPartData13 = modelPartData3.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F,-5.5F,-5.5F));
        modelPartData3.addChild("front_left_leg", ModelPartBuilder.create(), ModelTransform.pivot(1.5F,-2.5F,-6.1F));
        modelPartData3.addChild("front_right_leg", ModelPartBuilder.create(), ModelTransform.pivot(-1.5F,-2.5F,-6.1F));
        modelPartData3.addChild("back_left_leg", ModelPartBuilder.create(), ModelTransform.pivot(1.25F,3.2F,2.2F));
        modelPartData3.addChild("back_right_leg", ModelPartBuilder.create(), ModelTransform.pivot(-1.25F,3.2F,2.2F));
        ModelPartData modelPartData9 = modelPartData0.addChild("tail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F,-2.5F,5.0F));
        return TexturedModelData.of(modelData,64,64);
    }
    @Override
    public void setAngles(CaracalEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        wire13_r1.roll = 0.5236F;
        wire12_r1.roll = -0.5236F;
        wire10_r1.roll = -1.5708F;
        hat5_r1.roll = -0.4189F;
        hat5_r2.roll = -0.2618F;
        hat3_r1.roll = -0.1222F;
        commander3_r1.pitch = 1.2566F;
        anonymous3_r1.yaw = 0.1745F;
        anonymous2_r1.yaw = -0.1745F;
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
            this.body.pitch = 0.0873F;
            this.tail.pitch = 0.4363F;
            this.earfluff1.roll = 1.2217F;
            this.earfluff2.roll = -1.2217F;
        } else if (entity.isInSittingPose() && !entity.isInSleepingPose()) {
            this.body.pitch = -0.7854F;
            this.earfluff1.roll = 1.2217F;
            this.earfluff2.roll = -1.2217F;
            this.front_left_leg.pitch = -0.2618F;
            this.front_right_leg.pitch = -0.2618F;
            this.back_left_leg.pitch = -1.5708F;
            this.back_right_leg.pitch = -1.5708F;
            this.tail.pitch = 2.5744F;
        } else if (entity.isInSleepingPose()){
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
