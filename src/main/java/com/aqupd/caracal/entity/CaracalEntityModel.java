package com.aqupd.caracal.entity;

import com.aqupd.caracal.CaracalMain;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

import java.time.LocalDate;
import java.util.Locale;

public class CaracalEntityModel extends DefaultedEntityGeoModel<CaracalEntity> {

  public CaracalEntityModel() {
    super(new ResourceLocation(CaracalMain.MODID, "caracal"), true);
  }

  @Override
  public ResourceLocation getModelResource(CaracalEntity entity) {
    return new ResourceLocation(CaracalMain.MODID, "geo/caracal.geo.json");
  }

  @Override
  public ResourceLocation getTextureResource(CaracalEntity entity) {
    int day_of_month = LocalDate.now().getDayOfMonth();
    int month = LocalDate.now().getMonthValue();
    if (entity.getCustomName() != null && !entity.getCustomName().getString().isEmpty()) {
      String n = entity.getCustomName().getString().toLowerCase(Locale.ENGLISH);

      if (n.contains("шляп") || n.contains("hat")) {
        return new ResourceLocation(CaracalMain.MODID, "textures/entity/caracalshlopa.png");
      } else if (n.contains("мирный") || n.contains("peaceful")) {
        return new ResourceLocation(CaracalMain.MODID, "textures/entity/caracalcommander.png");
      } else if (n.contains("командир") || n.contains("commander")) {
        return new ResourceLocation(CaracalMain.MODID, "textures/entity/caracalcommander.png");
      } else if (n.contains("аноним") || n.contains("anon")) {
        return new ResourceLocation(CaracalMain.MODID, "textures/entity/caracalanonymous.png");
      } else if (n.contains("новогодний") || n.contains("year")) {
        return new ResourceLocation(CaracalMain.MODID, "textures/entity/caracalnewyear.png");
      } else if (n.contains("взрывной") || n.contains("explosive")) {
        return new ResourceLocation(CaracalMain.MODID, "textures/entity/caracalexplosive.png");
      } else if (n.contains("водный") || n.contains("water")) {
        return new ResourceLocation(CaracalMain.MODID, "textures/entity/caracalwater.png");
      }
    } else if ((day_of_month >= 25 && month == 12) || (day_of_month <= 5 && month == 1)) {
      return new ResourceLocation(CaracalMain.MODID, "textures/entity/caracalchristmasdays.png");
    } else if ((day_of_month == 1 && month == 4)) {
      return new ResourceLocation(CaracalMain.MODID, "textures/entity/caracalapril.png");
    } else if ((day_of_month == 6 && month == 5)) {
      return switch (entity.getMaskColor()) {
        case 1 -> new ResourceLocation(CaracalMain.MODID, "textures/entity/caracalbirthday1.png");
        case 2 -> new ResourceLocation(CaracalMain.MODID, "textures/entity/caracalbirthday2.png");
        case 3 -> new ResourceLocation(CaracalMain.MODID, "textures/entity/caracalbirthday3.png");
        default -> new ResourceLocation(CaracalMain.MODID, "textures/entity/caracal.png");
      };
    }
    return new ResourceLocation(CaracalMain.MODID, "textures/entity/caracal.png");
  }

  @Override
  public ResourceLocation getAnimationResource(CaracalEntity animatable) {
    return new ResourceLocation(CaracalMain.MODID, "animations/caracal.animation.json");
  }
}
