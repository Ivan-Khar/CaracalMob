package com.aqupd.aqcaracal.client.renderer.model;

// Made with Blockbench 4.1.5
// Exported for Minecraft version 1.17 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.aqupd.aqcaracal.Caracal;
import com.aqupd.aqcaracal.entities.CaracalEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class CaracalEntityModel<Type extends CaracalEntity> extends EntityModel<Type> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(Caracal.MODID, "caracal"), "main");
    private final ModelPart main;
    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart tail1;
    private final ModelPart head;
    private final ModelPart ear1;
    private final ModelPart earfluff1;
    private final ModelPart ear2;
    private final ModelPart earfluff2;
    private final ModelPart front_right_leg;
    private final ModelPart front_left_leg;
    private final ModelPart back_right_leg;
    private final ModelPart back_left_leg;

    public CaracalEntityModel(ModelPart root) {
        this.main = root.getChild("main");
        this.body = this.main.getChild("body");
        this.head = this.main.getChild("head");
        this.ear2 = this.head.getChild("ear2");
        this.earfluff2 = this.ear2.getChild("earfluff2");
        this.ear1 = this.head.getChild("ear1");
        this.earfluff1 = this.ear1.getChild("earfluff1");
        this.tail = this.body.getChild("tail");
        this.tail1 = this.tail.getChild("tail1");
        this.back_left_leg = this.main.getChild("back_left_leg");
        this.back_right_leg = this.main.getChild("back_right_leg");
        this.front_left_leg = this.main.getChild("front_left_leg");
        this.front_right_leg = this.main.getChild("front_right_leg");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition main = partdefinition.addOrReplaceChild("main", CubeListBuilder.create(), PartPose.offset(0.0F, 15.5F, 0.0F));
        PartDefinition body = main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(20, 9).addBox(-2.5F, -3.0F, -8.5F, 5.0F, 6.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(29, 21).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.5F, 8.0F, 0.6109F, 0.0F, 0.0F));
        PartDefinition tail1 = tail.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(33, 21).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, -0.5F, 0.6109F, 0.0F, 0.0F));
        PartDefinition tnt = body.addOrReplaceChild("tnt", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, -8.2F));
        PartDefinition tnt_r1 = tnt.addOrReplaceChild("tnt_r1", CubeListBuilder.create().texOffs(2, 58).addBox(-7.0F, 1.5F, -2.5F, 14.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.5F, 7.75F, 1.5708F, 0.0F, 0.0F));
        PartDefinition tnt_r2 = tnt.addOrReplaceChild("tnt_r2", CubeListBuilder.create().texOffs(40, 56).addBox(-2.0F, -2.0F, -2.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, 0.5F, 11.9F, 0.0F, 1.5708F, 0.0F));
        PartDefinition tnt_r3 = tnt.addOrReplaceChild("tnt_r3", CubeListBuilder.create().texOffs(40, 56).addBox(-2.0F, -2.0F, -2.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.5F, 0.5F, 11.9F, 0.0F, 1.5708F, 0.0F));
        PartDefinition head = main.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.1F, -3.6F, 6.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.9F, -8.1F));
        PartDefinition hats = head.addOrReplaceChild("hats", CubeListBuilder.create().texOffs(0, 16).addBox(-3.5F, -14.0F, -13.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 24).addBox(-2.5F, -17.0F, -12.5F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.4F, 8.1F));
        PartDefinition hats_r1 = hats.addOrReplaceChild("hats_r1", CubeListBuilder.create().texOffs(0, 32).addBox(-1.0F, -1.8F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.8F, -21.5F, -10.0F, 0.0F, 0.0F, -0.4189F));
        PartDefinition hats_r2 = hats.addOrReplaceChild("hats_r2", CubeListBuilder.create().texOffs(8, 32).addBox(-0.2F, -1.5F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.7572F, -20.2089F, -10.0F, 0.0F, 0.0F, -0.2618F));
        PartDefinition hats_r3 = hats.addOrReplaceChild("hats_r3", CubeListBuilder.create().texOffs(12, 32).addBox(-1.7F, -1.2F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -18.5F, -10.0F, 0.0F, 0.0F, -0.1222F));
        PartDefinition ear1 = head.addOrReplaceChild("ear1", CubeListBuilder.create().texOffs(0, 8).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, -3.1F, -0.2F));
        PartDefinition earfluff1 = ear1.addOrReplaceChild("earfluff1", CubeListBuilder.create().texOffs(4, 8).addBox(0.0F, -2.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, -1.5F, 0.0F, 0.0F, 0.0F, 0.6109F));
        PartDefinition ear2 = head.addOrReplaceChild("ear2", CubeListBuilder.create().texOffs(0, 8).addBox(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, -3.1F, -0.2F));
        PartDefinition earfluff2 = ear2.addOrReplaceChild("earfluff2", CubeListBuilder.create().texOffs(4, 8).addBox(0.0F, -2.0F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, -1.5F, 0.0F, 0.0F, 0.0F, -0.6109F));
        PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(16, 1).addBox(-1.5F, -1.1F, -4.6F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition anonymous = head.addOrReplaceChild("anonymous", CubeListBuilder.create().texOffs(46, 32).addBox(-4.0F, -3.3F, -3.1F, 8.0F, 7.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(46, 40).addBox(-2.0F, 3.7F, -3.1F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(46, 45).addBox(-3.0F, -1.8F, 2.2F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.8F, -1.7F));
        PartDefinition anonymous_r1 = anonymous.addOrReplaceChild("anonymous_r1", CubeListBuilder.create().texOffs(46, 38).addBox(-3.1F, -3.4F, -4.9F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.6F, 1.6F, 0.0F, 0.1745F, 0.0F));
        PartDefinition anonymous_r2 = anonymous.addOrReplaceChild("anonymous_r2", CubeListBuilder.create().texOffs(46, 37).addBox(3.1F, -3.4F, -4.9F, 0.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.6F, 1.6F, 0.0F, -0.1745F, 0.0F));
        PartDefinition commander = head.addOrReplaceChild("commander", CubeListBuilder.create().texOffs(20, 0).addBox(-3.5F, -2.9F, -2.5F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.1F, -1.7F));
        PartDefinition commander_r1 = commander.addOrReplaceChild("commander_r1", CubeListBuilder.create().texOffs(21, 6).addBox(-0.8F, -3.5F, -2.4F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(21, 6).addBox(-3.2F, -3.5F, -2.4F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.9F, 1.6F, 1.2566F, 0.0F, 0.0F));
        PartDefinition brtube = head.addOrReplaceChild("brtube", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition brtube_r1 = brtube.addOrReplaceChild("brtube_r1", CubeListBuilder.create().texOffs(47, 9).addBox(-0.3506F, -7.6304F, -0.5F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.3506F, 0.2204F, -4.2F, 0.0F, 0.0F, 0.0873F));
        PartDefinition brtube_r2 = brtube.addOrReplaceChild("brtube_r2", CubeListBuilder.create().texOffs(47, 9).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.7529F, 1.5021F, -4.2F, 0.0F, 0.0F, 1.2217F));
        PartDefinition brtube_r3 = brtube.addOrReplaceChild("brtube_r3", CubeListBuilder.create().texOffs(47, 9).addBox(-1.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.8735F, 2.1862F, -4.2F, 0.0F, 0.0F, 2.138F));
        PartDefinition beard = head.addOrReplaceChild("beard", CubeListBuilder.create().texOffs(0, 38).addBox(-2.0F, -0.2F, -1.3F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-1.3F, 1.8F, -1.1F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-0.9F, 3.5F, -0.9F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-0.2F, 1.8F, -0.8F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, -2.4F));
        PartDefinition beard_r1 = beard.addOrReplaceChild("beard_r1", CubeListBuilder.create().texOffs(1, 40).addBox(-1.29F, -6.91F, -0.5F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(1, 40).addBox(-1.29F, -0.61F, -0.5F, 4.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.8023F, -0.8542F, -0.5F, 0.0F, 0.0F, -1.5708F));
        PartDefinition beard_r2 = beard.addOrReplaceChild("beard_r2", CubeListBuilder.create().texOffs(2, 39).addBox(-1.3F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5469F, 0.667F, -0.5F, 0.0F, 0.0F, -0.3491F));
        PartDefinition beard_r3 = beard.addOrReplaceChild("beard_r3", CubeListBuilder.create().texOffs(2, 39).addBox(-0.7F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.4531F, 0.667F, -0.5F, 0.0F, 0.0F, 0.3491F));
        PartDefinition front_right_leg = main.addOrReplaceChild("front_right_leg", CubeListBuilder.create().texOffs(46, 0).addBox(-0.75F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 1.5F, -6.5F));
        PartDefinition front_left_leg = main.addOrReplaceChild("front_left_leg", CubeListBuilder.create().texOffs(46, 0).addBox(-1.25F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 1.5F, -6.5F));
        PartDefinition back_right_leg = main.addOrReplaceChild("back_right_leg", CubeListBuilder.create().texOffs(54, 0).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.25F, 1.5F, 6.0F));
        PartDefinition back_left_leg = main.addOrReplaceChild("back_left_leg", CubeListBuilder.create().texOffs(54, 0).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(1.25F, 1.5F, 6.0F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Type entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
        main.setPos(0.0F, 15.5F, 0.0F);
        body.setPos(0.0F, 0.0F, 0.0F);
        tail.setPos(0.0F, -2.5F, 8.0F);
        head.setPos(0.0F, -0.9F, -7.7F);
        earfluff1.setPos(0.5F, -1.5F, 0.0F);
        earfluff2.setPos(-0.5F, -1.5F, 0.0F);
        front_right_leg.setPos(-1.5F, 1.5F, -6.5F);
        front_left_leg.setPos(1.5F, 1.5F, -6.5F);
        back_right_leg.setPos(-1.25F, 1.5F, 6.0F);
        back_left_leg.setPos(1.25F, 1.5F, 6.0F);

        setRotationAngle(tail, 0.6109F, 0.0F, 0.0F);
        setRotationAngle(tail1, 0.6109F, 0.0F, 0.0F);
        setRotationAngle(earfluff1, 0.0F, 0.0F, 0.6109F);
        setRotationAngle(earfluff2, 0.0F, 0.0F, -0.6109F);
        setRotationAngle(main, 0.0F, 0.0F, 0.0F);
        setRotationAngle(body, 0.0F, 0.0F, 0.0F);

        setRotationAngle(head, (headPitch * 0.017453292F), (headYaw * 0.017453292F), 0.0F);
        setRotationAngle(back_left_leg, (Mth.cos(limbSwing * 0.6662F) * limbSwingAmount), 0.0F, 0.0F);
        setRotationAngle(back_right_leg, (Mth.cos(limbSwing * 0.6662F + 3.1415927F + 0.3F) * limbSwingAmount), 0.0F, 0.0F);
        setRotationAngle(front_left_leg, (Mth.cos(limbSwing * 0.6662F + 0.3F) * limbSwingAmount), 0.0F, 0.0F);
        setRotationAngle(front_right_leg, (Mth.cos(limbSwing * 0.6662F + 3.1415927F) * limbSwingAmount), 0.0F, 0.0F);

        this.ear1.zRot = -0.05F * Mth.cos(ageInTicks * 0.1F) + 0.1F;
        this.ear2.zRot = 0.05F * Mth.cos(ageInTicks * 0.11F) - 0.1F;
        this.earfluff1.zRot = -0.15F * Mth.cos(ageInTicks * 0.11F) + 1.0F;
        this.earfluff2.zRot = 0.15F * Mth.cos(ageInTicks * 0.1F) - 1.0F;

        if (entity.isCrouching()) {
            body.setPos(0.0F, 1.0F, 0.0F);
            head.setPos(0.0F, 0.9F, -7.7F);
            this.body.xRot = 0.0873F;
            this.tail.xRot = 0.4363F;
            this.ear1.zRot = -0.05F * Mth.cos(ageInTicks * 0.1F) + 0.1F;
            this.ear2.zRot = 0.05F * Mth.cos(ageInTicks * 0.11F) - 0.1F;
            this.earfluff1.zRot = -0.15F * Mth.cos(ageInTicks * 0.25F) + 1.1F;
            this.earfluff2.zRot = 0.15F * Mth.cos(ageInTicks * 0.3F) - 1.1F;
        } else if (entity.isInSittingPose() && !entity.isLying()) {
            main.setPos(0.0F, 19.5F, 0.0F);
            head.setPos(0.0F, -6.9F, -5.0F);
            front_left_leg.setPos(1.5F, -2.5F, -5.6F);
            front_right_leg.setPos(-1.5F, -2.5F, -5.6F);
            back_left_leg.setPos(1.25F, 3.2F, 2.2F);
            back_right_leg.setPos(-1.25F, 3.2F, 2.2F);
            tail.setPos(0.0F, -2.5F, 5.0F);

            this.body.xRot = -0.8854F;
            this.ear1.zRot = -0.05F * Mth.cos(ageInTicks * 0.1F) + 0.1F;
            this.ear2.zRot = 0.05F * Mth.cos(ageInTicks * 0.11F) - 0.1F;
            this.earfluff1.zRot = -0.1F * Mth.cos(ageInTicks * 0.25F) + 1.1F;
            this.earfluff2.zRot = 0.1F * Mth.cos(ageInTicks * 0.3F) - 1.1F;
            this.front_left_leg.xRot = -0.2618F;
            this.front_right_leg.xRot = -0.2618F;
            this.back_left_leg.xRot = -1.5708F;
            this.back_right_leg.xRot = -1.5708F;
            this.tail.xRot = 2.5744F;
        } else if (entity.isLying()){
            main.setPos(0.0F, 21.5F, 0.0F);
            this.main.zRot = 1.285F;
            this.head.xRot = 0.0F;
            this.head.zRot = -1.1F;
            this.earfluff1.zRot = 1.3217F;
            this.earfluff2.zRot = -1.3217F;
            this.front_left_leg.xRot = -0.6F;
            this.front_left_leg.yRot = 0.1F;
            this.front_right_leg.yRot = -0.1F;
            this.front_right_leg.xRot = -1.1F;
            this.back_left_leg.xRot = 0.6F;
            this.back_right_leg.yRot = -0.1F;
            this.back_right_leg.xRot = 1.0F;
            this.tail.y = -1.0F;
            this.tail.zRot = -1.185F;
            this.tail.yRot = -0.285F;
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        main.render(poseStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelPart bone, float pitch, float yaw, float roll) {
        bone.xRot = pitch;
        bone.yRot = yaw;
        bone.zRot = roll;
    }
}
