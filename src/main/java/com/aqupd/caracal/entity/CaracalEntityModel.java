package com.aqupd.caracal.entity;

import java.time.LocalDate;
import java.util.Locale;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class CaracalEntityModel extends AnimatedGeoModel<CaracalEntity> {
  @Override
  public Identifier getModelResource(CaracalEntity entity) {
    return new Identifier("aqupd", "geo/caracal.geo.json");
  }

  @Override
  public Identifier getTextureResource(CaracalEntity entity) {
    int day_of_month = LocalDate.now().getDayOfMonth();
    int month = LocalDate.now().getMonthValue();
    if (
      entity.getCustomName() != null &&
        !entity.getCustomName().getString().isEmpty()
    ) {
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
    } else if (
      (day_of_month >= 25 && month == 12) || (day_of_month <= 5 && month == 1)
    ) {
      return new Identifier(
        "aqupd",
        "textures/entity/caracalchristmasdays.png"
      );
    } else if ((day_of_month == 1 && month == 4)) {
      return new Identifier("aqupd", "textures/entity/caracalapril.png");
    } else if ((day_of_month == 6 && month == 5)) {
      return switch (entity.getMaskColor()) {
        case 1 -> new Identifier(
          "aqupd",
          "textures/entity/caracalbirthday1.png"
        );
        case 2 -> new Identifier(
          "aqupd",
          "textures/entity/caracalbirthday2.png"
        );
        case 3 -> new Identifier(
          "aqupd",
          "textures/entity/caracalbirthday3.png"
        );
        default -> new Identifier("aqupd", "textures/entity/caracal.png");
      };
    }
    return new Identifier("aqupd", "textures/entity/caracal.png");
  }

  @Override
  public Identifier getAnimationResource(CaracalEntity animatable) {
    return new Identifier("aqupd", "animations/caracal.animation.json");
  }

  @Override
  public void setLivingAnimations(CaracalEntity entity, Integer uniqueID) {
    super.setLivingAnimations(entity, uniqueID);
  }
}
