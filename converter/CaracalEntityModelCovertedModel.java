// Made with Model Converter by Globox_Z
// Generate all required imports
package com.aqupd.caracal.client.model; 
import com.aqupd.caracal.entity.CaracalEntity; 
import net.minecraft.client.model.*; 
import net.minecraft.client.render.VertexConsumer; 
import net.minecraft.client.render.entity.model.EntityModel; 
import net.minecraft.client.util.math.MatrixStack; 
import net.minecraft.entity.passive.TameableEntity; 
import net.minecraft.util.math.MathHelper;
public class CaracalEntityModel extends EntityModel<CaracalEntity> {
	private final ModelPart main;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart tail1;
	private final ModelPart tnt;
	private final ModelPart tnt_r1;
	private final ModelPart tnt_r2;
	private final ModelPart tnt_r3;
	private final ModelPart head;
	private final ModelPart mouth;
	private final ModelPart hats;
	private final ModelPart hats_r1;
	private final ModelPart hats_r2;
	private final ModelPart hats_r3;
	private final ModelPart hats_r4;
	private final ModelPart hats_r5;
	private final ModelPart ear1;
	private final ModelPart earfluff1;
	private final ModelPart ear2;
	private final ModelPart earfluff2;
	private final ModelPart anonymous;
	private final ModelPart anonymous_r1;
	private final ModelPart anonymous_r2;
	private final ModelPart commander;
	private final ModelPart commander_r1;
	private final ModelPart brtube;
	private final ModelPart brtube_r1;
	private final ModelPart brtube_r2;
	private final ModelPart brtube_r3;
	private final ModelPart beard;
	private final ModelPart beard_r1;
	private final ModelPart beard_r2;
	private final ModelPart beard_r3;
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
		this.beard = this.head.getChild("beard");
		this.beard_r3 = this.beard.getChild("beard_r3");
		this.beard_r2 = this.beard.getChild("beard_r2");
		this.beard_r1 = this.beard.getChild("beard_r1");
		this.brtube = this.head.getChild("brtube");
		this.brtube_r3 = this.brtube.getChild("brtube_r3");
		this.brtube_r2 = this.brtube.getChild("brtube_r2");
		this.brtube_r1 = this.brtube.getChild("brtube_r1");
		this.commander = this.head.getChild("commander");
		this.commander_r1 = this.commander.getChild("commander_r1");
		this.anonymous = this.head.getChild("anonymous");
		this.anonymous_r2 = this.anonymous.getChild("anonymous_r2");
		this.anonymous_r1 = this.anonymous.getChild("anonymous_r1");
		this.ear2 = this.head.getChild("ear2");
		this.earfluff2 = this.ear2.getChild("earfluff2");
		this.ear1 = this.head.getChild("ear1");
		this.earfluff1 = this.ear1.getChild("earfluff1");
		this.hats = this.head.getChild("hats");
		this.hats_r5 = this.hats.getChild("hats_r5");
		this.hats_r4 = this.hats.getChild("hats_r4");
		this.hats_r3 = this.hats.getChild("hats_r3");
		this.hats_r2 = this.hats.getChild("hats_r2");
		this.hats_r1 = this.hats.getChild("hats_r1");
		this.mouth = this.head.getChild("mouth");
		this.body = this.main.getChild("body");
		this.tnt = this.body.getChild("tnt");
		this.tnt_r3 = this.tnt.getChild("tnt_r3");
		this.tnt_r2 = this.tnt.getChild("tnt_r2");
		this.tnt_r1 = this.tnt.getChild("tnt_r1");
		this.tail = this.body.getChild("tail");
		this.tail1 = this.tail.getChild("tail1");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData modelPartData1 = modelPartData.addChild("main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F,15.5F,0.0F));
		ModelPartData modelPartData2 = modelPartData1.addChild("body", ModelPartBuilder.create().uv(20,9).cuboid(-2.5F, -3.0F, -8.5F, 5.0F, 6.0F, 17.0F), ModelTransform.pivot(0.0F,0.0F,0.0F));
		ModelPartData modelPartData3 = modelPartData2.addChild("tail", ModelPartBuilder.create().uv(29,21).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 4.0F, 1.0F), ModelTransform.pivot(0.0F,-2.5F,8.0F));
		modelPartData3.addChild("tail1", ModelPartBuilder.create().uv(33,21).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 4.0F, 1.0F), ModelTransform.pivot(0.0F,4.0F,-0.5F));
		ModelPartData modelPartData4 = modelPartData2.addChild("tnt", ModelPartBuilder.create(), ModelTransform.pivot(0.0F,-1.5F,-8.2F));
		modelPartData4.addChild("tnt_r1", ModelPartBuilder.create().uv(2,58).cuboid(-7.0F, 1.5F, -2.5F, 14.0F, 1.0F, 5.0F), ModelTransform.pivot(0.0F,0.5F,7.75F));
		modelPartData4.addChild("tnt_r2", ModelPartBuilder.create().uv(40,56).cuboid(-2.0F, -2.0F, -2.0F, 8.0F, 4.0F, 4.0F), ModelTransform.pivot(-4.5F,0.5F,11.9F));
		modelPartData4.addChild("tnt_r3", ModelPartBuilder.create().uv(40,56).cuboid(-2.0F, -2.0F, -2.0F, 8.0F, 4.0F, 4.0F), ModelTransform.pivot(4.5F,0.5F,11.9F));
		ModelPartData modelPartData5 = modelPartData1.addChild("head", ModelPartBuilder.create().uv(0,0).cuboid(-3.0F, -3.1F, -3.6F, 6.0F, 4.0F, 4.0F), ModelTransform.pivot(0.0F,-1.9F,-8.1F));
		modelPartData5.addChild("mouth", ModelPartBuilder.create().uv(16,1).cuboid(-1.5F, -1.1F, -4.6F, 3.0F, 2.0F, 1.0F), ModelTransform.pivot(0.0F,0.0F,0.0F));
		ModelPartData modelPartData6 = modelPartData5.addChild("hats", ModelPartBuilder.create().uv(0,8).cuboid(-2.5F, -14.0F, -13.5F, 5.0F, 1.0F, 7.0F).uv(0,16).cuboid(-2.5F, -16.999F, -11.5F, 5.0F, 3.0F, 3.0F), ModelTransform.pivot(0.0F,10.4F,8.1F));
		modelPartData6.addChild("hats_r1", ModelPartBuilder.create().uv(0,22).cuboid(-1.9F, -1.9F, -1.0F, 2.0F, 2.0F, 2.0F), ModelTransform.pivot(-1.1254F,-22.2308F,-10.0F));
		modelPartData6.addChild("hats_r2", ModelPartBuilder.create().uv(0,26).cuboid(-0.9F, -2.0F, -0.4999F, 1.0F, 3.0F, 1.0F), ModelTransform.pivot(-0.4674F,-20.2865F,-10.0F));
		modelPartData6.addChild("hats_r3", ModelPartBuilder.create().uv(8,22).cuboid(-1.5F, -1.4F, -1.5F, 3.0F, 3.0F, 3.0F), ModelTransform.pivot(-0.1619F,-18.1779F,-10.0F));
		modelPartData6.addChild("hats_r4", ModelPartBuilder.create().uv(0,16).cuboid(-2.5F, -1.501F, -1.5F, 5.0F, 3.0F, 3.0F), ModelTransform.pivot(0.0F,-15.499F,-10.0F));
		modelPartData6.addChild("hats_r5", ModelPartBuilder.create().uv(0,8).cuboid(-2.5F, -0.499F, -3.5F, 5.0F, 1.0F, 7.0F), ModelTransform.pivot(0.0F,-13.5F,-10.0F));
		ModelPartData modelPartData7 = modelPartData5.addChild("ear1", ModelPartBuilder.create().uv(0,8).cuboid(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F), ModelTransform.pivot(1.5F,-3.1F,-0.2F));
		modelPartData7.addChild("earfluff1", ModelPartBuilder.create().uv(4,8).cuboid(0.0F, -2.0F, -0.5F, 0.0F, 2.0F, 1.0F), ModelTransform.pivot(0.5F,-1.5F,0.0F));
		ModelPartData modelPartData8 = modelPartData5.addChild("ear2", ModelPartBuilder.create().uv(0,8).cuboid(-0.5F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F), ModelTransform.pivot(-1.5F,-3.1F,-0.2F));
		modelPartData8.addChild("earfluff2", ModelPartBuilder.create().uv(4,8).cuboid(0.0F, -2.0F, -0.5F, 0.0F, 2.0F, 1.0F), ModelTransform.pivot(-0.5F,-1.5F,0.0F));
		ModelPartData modelPartData9 = modelPartData5.addChild("anonymous", ModelPartBuilder.create().uv(46,32).cuboid(-4.0F, -3.3F, -3.1F, 8.0F, 7.0F, 1.0F).uv(46,40).cuboid(-2.0F, 3.7F, -3.1F, 4.0F, 1.0F, 1.0F).uv(46,45).cuboid(-3.0F, -1.8F, 2.2F, 6.0F, 1.0F, 0.0F), ModelTransform.pivot(0.0F,-0.8F,-1.7F));
		modelPartData9.addChild("anonymous_r1", ModelPartBuilder.create().uv(46,37).cuboid(-3.1F, -3.4F, -4.9F, 0.0F, 1.0F, 5.0F), ModelTransform.pivot(0.0F,1.6F,1.6F));
		modelPartData9.addChild("anonymous_r2", ModelPartBuilder.create().uv(46,37).cuboid(3.1F, -3.4F, -4.9F, 0.0F, 1.0F, 5.0F), ModelTransform.pivot(0.0F,1.6F,1.6F));
		ModelPartData modelPartData10 = modelPartData5.addChild("commander", ModelPartBuilder.create().uv(20,0).cuboid(-3.5F, -2.9F, -2.5F, 7.0F, 1.0F, 5.0F), ModelTransform.pivot(0.0F,-1.1F,-1.7F));
		modelPartData10.addChild("commander_r1", ModelPartBuilder.create().uv(21,6).cuboid(-0.8F, -3.5F, -2.4F, 4.0F, 1.0F, 4.0F).uv(21,6).cuboid(-3.2F, -3.5F, -2.4F, 4.0F, 1.0F, 4.0F), ModelTransform.pivot(0.0F,0.9F,1.6F));
		ModelPartData modelPartData11 = modelPartData5.addChild("brtube", ModelPartBuilder.create(), ModelTransform.pivot(0.0F,0.0F,0.0F));
		modelPartData11.addChild("brtube_r1", ModelPartBuilder.create().uv(47,9).cuboid(-0.3506F, -7.6304F, -0.5F, 1.0F, 9.0F, 1.0F), ModelTransform.pivot(4.3506F,0.2204F,-4.2F));
		modelPartData11.addChild("brtube_r2", ModelPartBuilder.create().uv(47,9).cuboid(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F), ModelTransform.pivot(3.7529F,1.5021F,-4.2F));
		modelPartData11.addChild("brtube_r3", ModelPartBuilder.create().uv(47,9).cuboid(-1.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F), ModelTransform.pivot(1.8735F,2.1862F,-4.2F));
		ModelPartData modelPartData12 = modelPartData5.addChild("beard", ModelPartBuilder.create().uv(17,21).cuboid(-2.0F, -0.2F, -1.3F, 4.0F, 2.0F, 2.0F).uv(17,21).cuboid(-1.3F, 1.8F, -1.1F, 2.0F, 2.0F, 2.0F).uv(17,21).cuboid(-0.9F, 3.5F, -0.9F, 2.0F, 2.0F, 1.0F).uv(17,21).cuboid(-0.2F, 1.8F, -0.8F, 2.0F, 2.0F, 1.0F), ModelTransform.pivot(0.0F,0.5F,-2.4F));
		modelPartData12.addChild("beard_r1", ModelPartBuilder.create().uv(19,24).cuboid(-1.29F, -6.91F, -0.5F, 4.0F, 0.0F, 1.0F).uv(19,24).cuboid(-1.29F, -0.61F, -0.5F, 4.0F, 0.0F, 1.0F), ModelTransform.pivot(3.8023F,-0.8542F,-0.5F));
		modelPartData12.addChild("beard_r2", ModelPartBuilder.create().uv(19,22).cuboid(-1.3F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F), ModelTransform.pivot(2.5469F,0.667F,-0.5F));
		modelPartData12.addChild("beard_r3", ModelPartBuilder.create().uv(19,22).cuboid(-0.7F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F), ModelTransform.pivot(-2.4531F,0.667F,-0.5F));
		modelPartData1.addChild("front_right_leg", ModelPartBuilder.create().uv(46,0).cuboid(-0.75F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F), ModelTransform.pivot(-1.5F,1.5F,-6.5F));
		modelPartData1.addChild("front_left_leg", ModelPartBuilder.create().uv(46,0).cuboid(-1.25F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F), ModelTransform.pivot(1.5F,1.5F,-6.5F));
		modelPartData1.addChild("back_right_leg", ModelPartBuilder.create().uv(54,0).cuboid(-1.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F), ModelTransform.pivot(-1.25F,1.5F,6.0F));
		modelPartData1.addChild("back_left_leg", ModelPartBuilder.create().uv(54,0).cuboid(-1.0F, 0.0F, -1.5F, 2.0F, 7.0F, 3.0F), ModelTransform.pivot(1.25F,1.5F,6.0F));
		return TexturedModelData.of(modelData,64,64);
	}
	@Override
	public void setAngles(CaracalEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
			//previously the render function, render code was moved to a method below
	}
	@Override
	public void render(MatrixStack matrixStack, VertexConsumer	buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
			
			main.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	public void setRotationAngle(ModelPart bone, float x, float y, float z) {
			bone.pitch = x;
			bone.yaw = y;
			bone.roll = z;
	}
}