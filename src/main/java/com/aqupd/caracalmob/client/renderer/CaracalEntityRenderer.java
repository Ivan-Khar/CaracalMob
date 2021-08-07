package com.aqupd.caracalmob.client.renderer;

import com.aqupd.caracalmob.CaracalMainClient;
import com.aqupd.caracalmob.client.model.CaracalEntityModel;
import com.aqupd.caracalmob.entity.CaracalEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CaracalEntityRenderer extends MobEntityRenderer<CaracalEntity, CaracalEntityModel<CaracalEntity>> {

    public CaracalEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CaracalEntityModel<CaracalEntity>(context.getPart(CaracalMainClient.CARACAL_MODEL_LAYER)), 0.6f);
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