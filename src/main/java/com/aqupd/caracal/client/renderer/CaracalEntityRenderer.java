package com.aqupd.caracal.client.renderer;

import com.aqupd.caracal.client.model.CaracalEntityModel;
import com.aqupd.caracal.entities.CaracalEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

import java.time.LocalDate;
import java.util.Locale;

@Environment(EnvType.CLIENT)
public class CaracalEntityRenderer extends MobEntityRenderer<CaracalEntity, CaracalEntityModel> {

    public CaracalEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new CaracalEntityModel(), 0.6f);
    }

    protected void scale(CaracalEntity caracalEntity, MatrixStack matrixStack, float f){
        if (caracalEntity.isBaby()) {
            matrixStack.scale(0.6F, 0.6F, 0.6F);
        } else {
            matrixStack.scale(1.0F, 1.0F, 1.0F);
        }
    }

    @Override
    public Identifier getTexture(CaracalEntity entity) {
        int day_of_month = LocalDate.now().getDayOfMonth();
        int month = LocalDate.now().getMonthValue();
        if (entity.getCustomName() != null && !entity.getCustomName().asString().isEmpty()) {
            String n = entity.getCustomName().asString().toLowerCase(Locale.ENGLISH);

            if (n.contains("шляп") || n.contains("hat")) {
                return new Identifier("aqupd", "textures/entity/caracalshlopa.png");
            } else if (n.contains("мирный") || n.contains("peaceful")) {
                return new Identifier("aqupd", "textures/entity/caracalcommander.png");
            } else if (n.contains("командир") || n.contains("commander")) {
                return new Identifier("aqupd", "textures/entity/caracalcommander.png");
            } else if (n.contains("аноним") || n.contains("anon")) {
                return new Identifier("aqupd", "textures/entity/caracalanonymous.png");
            } else if (n.contains("новогодний") || n.contains("year")) {
                return new Identifier("aqupd", "textures/entity/caracalnewyear.png");
            } else if (n.contains("взрывной") || n.contains("explosive")) {
                return new Identifier("aqupd", "textures/entity/caracalexplosive.png");
            } else if (n.contains("водный") || n.contains("water")) {
                return new Identifier("aqupd", "textures/entity/caracalwater.png");
            }
        } else if ((day_of_month >= 25 && month == 12) || (day_of_month <= 5 && month == 1)) {
            return new Identifier("aqupd", "textures/entity/caracalchristmasdays.png");
        }
        return new Identifier("aqupd", "textures/entity/caracal.png");
    }
}