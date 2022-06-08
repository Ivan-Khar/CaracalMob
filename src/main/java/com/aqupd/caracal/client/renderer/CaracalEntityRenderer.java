package com.aqupd.caracal.client.renderer;

import com.aqupd.caracal.CaracalMainClient;
import com.aqupd.caracal.client.model.CaracalEntityModel;
import com.aqupd.caracal.entity.CaracalEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.time.LocalDate;
import java.util.Locale;

import static com.aqupd.caracal.utils.AqLogger.*;

@Environment(EnvType.CLIENT)
public class CaracalEntityRenderer extends MobEntityRenderer<CaracalEntity, CaracalEntityModel<CaracalEntity>> {

    public CaracalEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new CaracalEntityModel<>(context.getPart(CaracalMainClient.CARACAL_MODEL_LAYER)), 0.6f);
    }

    @Override
    public Identifier getTexture(CaracalEntity entity) {
        int day_of_month = LocalDate.now().getDayOfMonth();
        int month = LocalDate.now().getMonthValue();
        if (entity.getCustomName() != null && !entity.getCustomName().getString().isEmpty()) {
            String n = entity.getCustomName().getString().toLowerCase(Locale.ENGLISH);

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
        } else if ((day_of_month == 1 && month == 4)) {
            return new Identifier("aqupd", "textures/entity/caracalapril.png");
        } else if ((day_of_month == 6 && month == 5)) {
            return switch (entity.getMaskColor()) {
                case 1 -> new Identifier("aqupd", "textures/entity/caracalbirthday1.png");
                case 2 -> new Identifier("aqupd", "textures/entity/caracalbirthday2.png");
                case 3 -> new Identifier("aqupd", "textures/entity/caracalbirthday3.png");
                default -> new Identifier("aqupd", "textures/entity/caracal.png");
            };
        }
        return new Identifier("aqupd", "textures/entity/caracal.png");
    }

    protected void scale(CaracalEntity entity, MatrixStack matrixStack, float f) {
        if(entity.getBreedingAge() < 0) {
            matrixStack.scale(0.6F, 0.6F, 0.6F);
        }
        else {
            matrixStack.scale(1.0F, 1.0F, 1.0F);
        }
    }
}
