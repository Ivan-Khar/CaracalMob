package com.aqupd.aqcaracal.client.renderer;

import com.aqupd.aqcaracal.Caracal;
import com.aqupd.aqcaracal.client.renderer.model.CaracalEntityModel;
import com.aqupd.aqcaracal.entities.CaracalEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.time.LocalDate;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;

public class CaracalEntityRenderer<Type extends CaracalEntity> extends MobRenderer<Type, CaracalEntityModel<Type>> {

    public CaracalEntityRenderer(Context context) {
        super(context, new CaracalEntityModel<>(context.bakeLayer(CaracalEntityModel.LAYER_LOCATION)), 0.5f);
    }

    protected void scale(CaracalEntity entity, PoseStack matrixStack, float pPartialTickTime){
        if(entity.getAge() < 0) {
            matrixStack.scale(0.6F, 0.6F, 0.6F);
        }
        else {
            matrixStack.scale(1.0F, 1.0F, 1.0F);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity) {
        int day_of_month = LocalDate.now().getDayOfMonth();
        int month = LocalDate.now().getMonthValue();

        if (entity.getCustomName() != null && !entity.getCustomName().getString().isEmpty()) {
            String n = entity.getCustomName().getString().toLowerCase(Locale.ENGLISH);
            if (n.contains("шляп") || n.contains("hat")) {
                return new ResourceLocation(Caracal.MODID, "textures/entity/caracal/caracalshlopa.png");
            } else if (n.contains("мирный") || n.contains("peaceful")) {
                return new ResourceLocation(Caracal.MODID, "textures/entity/caracal/caracalcommander.png");
            } else if (n.contains("командир") || n.contains("commander")) {
                return new ResourceLocation(Caracal.MODID, "textures/entity/caracal/caracalcommander.png");
            } else if (n.contains("аноним") || n.contains("anon")) {
                return new ResourceLocation(Caracal.MODID, "textures/entity/caracal/caracalanonymous.png");
            } else if (n.contains("новогодний") || n.contains("year")) {
                return new ResourceLocation(Caracal.MODID, "textures/entity/caracal/caracalnewyear.png");
            } else if (n.contains("взрывной") || n.contains("explosive")) {
                return new ResourceLocation(Caracal.MODID, "textures/entity/caracal/caracalexplosive.png");
            } else if (n.contains("водный") || n.contains("water")) {
                return new ResourceLocation(Caracal.MODID, "textures/entity/caracal/caracalwater.png");
            }
        } else if ((day_of_month >= 25 && month == 12) || (day_of_month <= 5 && month == 1)) {
            return new ResourceLocation(Caracal.MODID, "textures/entity/caracal/caracalchristmasdays.png");
        }
        return new ResourceLocation(Caracal.MODID, "textures/entity/caracal/caracal.png");
    }
}
