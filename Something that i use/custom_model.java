// Made with Blockbench 3.8.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports

	package com.example.mod;

	public class custom_model extends EntityModel<Entity> {
private final ModelPart body;
	private final ModelPart head;
	private final ModelPart ear1;
	private final ModelPart ear2;
	private final ModelPart tail;
	private final ModelPart tail2_r1;
	private final ModelPart tail1_r1;
	private final ModelPart front_left_leg;
	private final ModelPart front_right_leg;
	private final ModelPart back_left_leg;
	private final ModelPart back_right_leg;
public custom_model() {
		textureWidth = 64;
		textureHeight = 32;
		body = new ModelPart(this);
		body.setPivot(0.0F, 24.0F, 0.0F);
		body.setTextureOffset(20, 9).addCuboid(-2.5F, -11.5F, -8.5F, 5.0F, 6.0F, 17.0F, 0.0F, false);

		head = new ModelPart(this);
		head.setPivot(0.0F, 24.0F, 0.0F);
		head.setTextureOffset(0, 0).addCuboid(-3.0F, -14.0F, -12.5F, 6.0F, 4.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(16, 1).addCuboid(-1.5F, -12.0F, -13.5F, 3.0F, 2.0F, 1.0F, 0.0F, false);

		ear1 = new ModelPart(this);
		ear1.setPivot(0.0F, 0.0F, 0.0F);
		head.addChild(ear1);
		ear1.setTextureOffset(4, 8).addCuboid(2.0F, -17.5F, -9.5F, 0.0F, 2.0F, 1.0F, 0.0F, false);
		ear1.setTextureOffset(0, 8).addCuboid(1.0F, -15.5F, -9.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		ear2 = new ModelPart(this);
		ear2.setPivot(0.0F, 0.0F, 0.0F);
		head.addChild(ear2);
		ear2.setTextureOffset(4, 8).addCuboid(-2.0F, -17.5F, -9.5F, 0.0F, 2.0F, 1.0F, 0.0F, false);
		ear2.setTextureOffset(0, 8).addCuboid(-2.0F, -15.5F, -9.5F, 1.0F, 2.0F, 1.0F, 0.0F, false);

		tail = new ModelPart(this);
		tail.setPivot(0.0F, 24.0F, 0.0F);
		

		tail2_r1 = new ModelPart(this);
		tail2_r1.setPivot(0.0F, -5.5F, 8.0F);
		tail.addChild(tail2_r1);
		setRotationAngle(tail2_r1, 1.309F, 0.0F, 0.0F);
		tail2_r1.setTextureOffset(33, 21).addCuboid(-0.5F, 1.0F, 2.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		tail1_r1 = new ModelPart(this);
		tail1_r1.setPivot(0.0F, -9.0F, 8.0F);
		tail.addChild(tail1_r1);
		setRotationAngle(tail1_r1, 0.5672F, 0.0F, 0.0F);
		tail1_r1.setTextureOffset(29, 21).addCuboid(-0.5F, -2.0F, 0.5F, 1.0F, 4.0F, 1.0F, 0.0F, false);

		front_left_leg = new ModelPart(this);
		front_left_leg.setPivot(1.25F, 20.5F, -6.5F);
		front_left_leg.setTextureOffset(46, 0).addCuboid(-1.0F, -3.5F, -1.0F, 2.0F, 7.0F, 2.0F, 0.0F, false);

		front_right_leg = new ModelPart(this);
		front_right_leg.setPivot(0.0F, 24.0F, 0.0F);
		front_right_leg.setTextureOffset(46, 0).addCuboid(-2.25F, -7.0F, -7.5F, 2.0F, 7.0F, 2.0F, 0.0F, false);

		back_left_leg = new ModelPart(this);
		back_left_leg.setPivot(0.0F, 24.0F, 0.0F);
		back_left_leg.setTextureOffset(54, 0).addCuboid(0.25F, -7.0F, 4.5F, 2.0F, 7.0F, 3.0F, 0.0F, false);

		back_right_leg = new ModelPart(this);
		back_right_leg.setPivot(0.0F, 24.0F, 0.0F);
		back_right_leg.setTextureOffset(54, 0).addCuboid(-2.25F, -7.0F, 4.5F, 2.0F, 7.0F, 3.0F, 0.0F, false);
}
@Override
public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
}
@Override
public void render(MatrixStack matrixStack, VertexConsumer	buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		tail.render(matrixStack, buffer, packedLight, packedOverlay);
		front_left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		front_right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		back_left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		back_right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
}
public void setRotationAngle(ModelPart bone, float x, float y, float z) {
		bone.pitch = x;
		bone.yaw = y;
		bone.roll = z;
}

	}