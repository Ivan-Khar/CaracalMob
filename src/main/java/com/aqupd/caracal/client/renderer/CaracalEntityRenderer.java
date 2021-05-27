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
    public Identifier getTexture(CaracalEntity entity) {
        if (entity.getCustomName() != null) {
            String n = entity.getCustomName().asString();

            if (n.equalsIgnoreCase("шлёпа") || n.equalsIgnoreCase("большой шлёпа") || n.equalsIgnoreCase("шлепа") || n.equalsIgnoreCase("большой шлепа") || n.equalsIgnoreCase("floppa") || n.equalsIgnoreCase("big floppa")) {
                return new Identifier("aqupd", "textures/entity/caracalshlopa.png");
            }

            if (n.equalsIgnoreCase("мирный командир") || n.equalsIgnoreCase("мирный командир шлепа") || n.equalsIgnoreCase("мирный командир шлёпа") || n.equalsIgnoreCase("peaceful floppa commander") || n.equalsIgnoreCase("peaceful commander")) {
                return new Identifier("aqupd", "textures/entity/caracalcommander.png");
            }

            if (n.equalsIgnoreCase("командир") || n.equalsIgnoreCase("командир шлепа") || n.equalsIgnoreCase("командир шлёпа") || n.equalsIgnoreCase("floppa commander") || n.equalsIgnoreCase("commander")) {
                return new Identifier("aqupd", "textures/entity/caracalcommander.png");
            }

            if (n.equalsIgnoreCase("анонимус") || n.equalsIgnoreCase("анонимный шлёпа") || n.equalsIgnoreCase("анонимный шлепа") || n.equalsIgnoreCase("anonymous floppa") || n.equalsIgnoreCase("anonymous")) {
                return new Identifier("aqupd", "textures/entity/caracalanonymous.png");
            }

            if (n.equalsIgnoreCase("новогодний") || n.equalsIgnoreCase("new year") || n.equalsIgnoreCase("новогодний шлепа") || n.equalsIgnoreCase("новогодний шлёпа") || n.equalsIgnoreCase("new year's floppa")) {
                return new Identifier("aqupd", "textures/entity/caracalnewyear.png");
            }

            if (n.equalsIgnoreCase("взрывной") || n.equalsIgnoreCase("взрывной шлёпа") || n.equalsIgnoreCase("взрывной шлепа") || n.equalsIgnoreCase("explosive") || n.equalsIgnoreCase("explosive floppa")) {
                return new Identifier("aqupd", "textures/entity/caracalexplosive.png");
            }
        }

        return new Identifier("aqupd", "textures/entity/caracal.png");
    }
}