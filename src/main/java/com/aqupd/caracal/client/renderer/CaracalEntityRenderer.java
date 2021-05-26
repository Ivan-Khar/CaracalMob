package com.aqupd.caracal.client.renderer;

import com.aqupd.caracal.client.model.CaracalEntityModel;
import com.aqupd.caracal.entities.CaracalEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CaracalEntityRenderer extends MobEntityRenderer<CaracalEntity, CaracalEntityModel> {

    public CaracalEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new CaracalEntityModel(), 0.6f);
    }

    protected void scale(CaracalEntity caracalEntity, MatrixStack matrixStack, float f){
        if (caracalEntity.isBaby()) {
            matrixStack.scale(0.65F, 0.65F, 0.65F);
        } else {
            matrixStack.scale(1.0F, 1.0F, 1.0F);
        }
    }
    @Override
    public Identifier getTexture(CaracalEntity entity){
        if ((entity.getCustomName() != null) && (entity.getCustomName().asString().equals("Шлёпа") || entity.getCustomName().asString().equals("Большой Шлёпа") || entity.getCustomName().asString().equals("Big Floppa") || entity.getCustomName().asString().equals("Floppa"))){
            return new Identifier("aqupd", "textures/entity/caracalshlopa.png");
        } else if ((entity.getCustomName() != null) && (entity.getCustomName().asString().equals("Командир") || entity.getCustomName().asString().equals("Commander"))){
            return new Identifier("aqupd", "textures/entity/caracalcommander.png");
        } else {
            return new Identifier("aqupd", "textures/entity/caracal.png");
        }
    }
}