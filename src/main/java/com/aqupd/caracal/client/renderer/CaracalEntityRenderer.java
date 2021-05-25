package com.aqupd.caracal.client.renderer;

import com.aqupd.caracal.client.model.CaracalEntityModel;
import com.aqupd.caracal.entities.CaracalEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.OcelotEntityModel;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CaracalEntityRenderer extends MobEntityRenderer<CaracalEntity, CaracalEntityModel> {

    public CaracalEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new CaracalEntityModel(), 0.4f);
    }

    @Override
    public Identifier getTexture(CaracalEntity entity){
        return new Identifier("aqupd", "caracal/textures/entity/caracal/caracal.png");
    }
}