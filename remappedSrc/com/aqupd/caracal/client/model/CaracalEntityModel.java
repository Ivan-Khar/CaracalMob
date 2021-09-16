package com.aqupd.caracal.client.model;
import com.aqupd.caracal.entity.CaracalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

import java.util.Locale;

public class CaracalEntityModel extends AnimatedGeoModel<CaracalEntity> {
    @Override
    public Identifier getModelLocation(CaracalEntity object) {
        return new Identifier("aqupd", "geo/GeckoLibCaracal.geo.json");
    }
    @Override
    public Identifier getTextureLocation(CaracalEntity object) {
        if (object.getCustomName() != null && !object.getCustomName().asString().isEmpty()) {
            String n = object.getCustomName().asString().toLowerCase(Locale.ENGLISH);

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
        }
        return new Identifier("aqupd", "textures/entity/caracal.png");
    }
    @Override
    public Identifier getAnimationFileLocation(CaracalEntity animatable) {
        return new Identifier("aqupd", "animations/GeckoLibCaracal.animation.json");
    }
}
//entity.isInSneakingPose()
//entity.isInSittingPose() && !entity.isInSleepingPose()
//entity.isInSleepingPose()